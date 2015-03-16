/*
 * Author: Shahrooz Sabet
 * Date: 20141101
 * */
package namaad.bms;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class EnergyMan extends Activity implements OnClickListener {
    private static final String TAG = "namaad.bms.EnergyMan";
    public EditText etTemprCurSalon;
    public EditText etTemprIdealSalon;
    public EditText etTemprAlertSalon;
    public EditText etTemprIdealBed1;
    public EditText etTemprCurBed1;
    public EditText etTemprAlertBed1;
    public EditText etTemprCurBed2;
    public EditText etTemprIdealBed2;
    public EditText etTemprAlertBed2;
    private boolean _logging;
    private RadioButton rdbAuto;
    private RadioButton rdbCurtOpen;
    private RadioButton rdbCurtClose;
    private Button btnRisingYard;
    private Button btnFanBedroom;
    private Button btnFanSalon;
    private Button btnFanWC;
    private boolean flgRisingYard;
    private boolean flgFanBedroom;
    private boolean flgFanSalon;
    private boolean flgFanWC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.energy_man);
        _logging = SettingsActivity.getLogging(this);
        rdbAuto = (RadioButton) this.findViewById(R.id.rdbAuto);
        rdbCurtOpen = (RadioButton) this.findViewById(R.id.rdbCurtOpen);
        rdbCurtClose = (RadioButton) this.findViewById(R.id.rdbCurtClose);

        btnRisingYard = (Button) this.findViewById(R.id.btnRisingYard);
        btnFanBedroom = (Button) this.findViewById(R.id.btnFanBedroom);
        btnFanSalon = (Button) this.findViewById(R.id.btnFanSalon);
        btnFanWC = (Button) this.findViewById(R.id.btnFanWC);

        etTemprCurSalon = (EditText) this.findViewById(R.id.etTemprCurSalon);
        etTemprIdealSalon = (EditText) this
                .findViewById(R.id.etTemprIdealSalon);
        etTemprAlertSalon = (EditText) this
                .findViewById(R.id.etTemprAlertSalon);
        etTemprCurBed1 = (EditText) this.findViewById(R.id.etTemprCurBed1);
        etTemprIdealBed1 = (EditText) this.findViewById(R.id.etTemprIdealBed1);
        etTemprAlertBed1 = (EditText) this.findViewById(R.id.etTemprAlertBed1);
        etTemprCurBed2 = (EditText) this.findViewById(R.id.etTemprCurBed2);
        etTemprIdealBed2 = (EditText) this.findViewById(R.id.etTemprIdealBed2);
        etTemprAlertBed2 = (EditText) this.findViewById(R.id.etTemprAlertBed2);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.energy_man, menu);
        return true;
    }

    @Override
    public void onResume() {
        super.onResume();
        btnRisingYard.setOnClickListener(this);
        btnFanBedroom.setOnClickListener(this);
        btnFanSalon.setOnClickListener(this);
        btnFanWC.setOnClickListener(this);

        rdbAuto.setOnClickListener(this);
        rdbCurtClose.setOnClickListener(this);
        rdbCurtOpen.setOnClickListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        btnRisingYard.setOnClickListener(null);
        btnFanBedroom.setOnClickListener(null);
        btnFanSalon.setOnClickListener(null);
        btnFanWC.setOnClickListener(null);

        rdbAuto.setOnClickListener(null);
        rdbCurtClose.setOnClickListener(null);
        rdbCurtOpen.setOnClickListener(null);
    }

    @Override
    public void onClick(View v) {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("http").authority("192.168.1.16")
                .appendQueryParameter("aip", "192.168.1.16")
                .appendQueryParameter("lcd1", "0");
        Handler handler = new Handler();
        RequestTask rt = new RequestTask(this);
        switch (v.getId()) {
            case R.id.btnRisingYard:
                if (flgRisingYard == false)
                    builder.appendQueryParameter("lcd2", "2222222221");
                else
                    builder.appendQueryParameter("lcd2", "2222222220");
                rt.execute(builder.build().toString());
                flgRisingYard = !flgRisingYard;
                break;
            case R.id.btnFanBedroom:
                if (flgFanBedroom == false)
                    builder.appendQueryParameter("lcd2", "2222221");
                else
                    builder.appendQueryParameter("lcd2", "2222220");
                rt.execute(builder.build().toString());
                flgFanBedroom = !flgFanBedroom;
                break;
            case R.id.btnFanSalon:
                if (flgFanSalon == false)
                    builder.appendQueryParameter("lcd2", "22222221");
                else
                    builder.appendQueryParameter("lcd2", "22222220");
                rt.execute(builder.build().toString());
                flgFanSalon = !flgFanSalon;
                break;
            case R.id.btnFanWC:
                if (flgFanWC == false)
                    builder.appendQueryParameter("lcd2", "222221");
                else
                    builder.appendQueryParameter("lcd2", "222220");
                rt.execute(builder.build().toString());
                flgFanWC = !flgFanWC;
                break;
            case R.id.rdbAuto:
                builder.appendQueryParameter("lcd2", "222222222222");
                rt.execute(builder.build().toString());
                break;
            case R.id.rdbCurtClose:
                builder.appendQueryParameter("lcd2", "222222222201");
                rt.execute(builder.build().toString());
                // Execute some code after 10 seconds have passed
                handler.postDelayed(new Runnable() {
                    public void run() {
                        Uri.Builder builder = new Uri.Builder();
                        builder.scheme("http").authority("192.168.1.16")
                                .appendQueryParameter("aip", "192.168.1.16")
                                .appendQueryParameter("lcd1", "0");
                        builder.appendQueryParameter("lcd2", "222222222200");
                        RequestTask rt = new RequestTask();
                        rt.execute(builder.build().toString());
                    }
                }, 10000);
                break;
            case R.id.rdbCurtOpen:
                builder.appendQueryParameter("lcd2", "22222222221");
                rt.execute(builder.build().toString());
                // Execute some code after 10 seconds have passed
                handler.postDelayed(new Runnable() {
                    public void run() {
                        Uri.Builder builder = new Uri.Builder();
                        builder.scheme("http").authority("192.168.1.16")
                                .appendQueryParameter("aip", "192.168.1.16")
                                .appendQueryParameter("lcd1", "0");
                        builder.appendQueryParameter("lcd2", "22222222220");
                        RequestTask rt = new RequestTask();
                        rt.execute(builder.build().toString());
                    }
                }, 10000);
                break;
        }

    }
}
