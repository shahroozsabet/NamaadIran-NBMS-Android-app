/*
 * Author: Shahrooz Sabet
 * Date: 20141101
 * */
package namaad.bms;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Lighting extends Activity implements OnClickListener {
    private static final String TAG = "namaad.bms.Lighting";
    private boolean _logging;

    private Button btnBedroom;
    private Button btnKitchen;
    private Button btnWc;
    private Button btnSalon;
    private Button btnYard;

    private boolean flgBedroom;
    private boolean flgKitchen;
    private boolean flgWc;
    private boolean flgSalon;
    private boolean flgYard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lighting);
        _logging = SettingsActivity.getLogging(this);
        btnBedroom = (Button) this.findViewById(R.id.btnBedroom);
        btnKitchen = (Button) this.findViewById(R.id.btnKitchen);
        btnWc = (Button) this.findViewById(R.id.btnWc);
        btnSalon = (Button) this.findViewById(R.id.btnSalon);
        btnYard = (Button) this.findViewById(R.id.btnYard);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.lighting, menu);
        return true;
    }

    @Override
    public void onResume() {
        super.onResume();
        btnBedroom.setOnClickListener(this);
        btnKitchen.setOnClickListener(this);
        btnWc.setOnClickListener(this);
        btnSalon.setOnClickListener(this);
        btnYard.setOnClickListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        btnBedroom.setOnClickListener(null);
        btnKitchen.setOnClickListener(null);
        btnWc.setOnClickListener(null);
        btnSalon.setOnClickListener(null);
        btnYard.setOnClickListener(null);
    }

    @Override
    public void onClick(View v) {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("http").authority("192.168.1.16")
                .appendQueryParameter("aip", "192.168.1.16")
                .appendQueryParameter("lcd1", "0");
        RequestTask rt = new RequestTask();
        switch (v.getId()) {
            case R.id.btnBedroom:
                if (flgBedroom == false)
                    builder.appendQueryParameter("lcd2", "221");
                else
                    builder.appendQueryParameter("lcd2", "220");
                rt.execute(builder.build().toString());
                flgBedroom = !flgBedroom;
                break;
            case R.id.btnKitchen:
                if (flgKitchen == false)
                    builder.appendQueryParameter("lcd2", "1");
                else
                    builder.appendQueryParameter("lcd2", "0");
                rt.execute(builder.build().toString());
                flgKitchen = !flgKitchen;
                break;
            case R.id.btnWc:
                if (flgWc == false)
                    builder.appendQueryParameter("lcd2", "2221");
                else
                    builder.appendQueryParameter("lcd2", "2220");
                rt.execute(builder.build().toString());
                flgWc = !flgWc;
                break;
            case R.id.btnSalon:
                if (flgSalon == false)
                    builder.appendQueryParameter("lcd2", "22221");
                else
                    builder.appendQueryParameter("lcd2", "22220");
                rt.execute(builder.build().toString());
                flgSalon = !flgSalon;
                break;
            case R.id.btnYard:
                if (flgYard == false)
                    builder.appendQueryParameter("lcd2", "222222221");
                else
                    builder.appendQueryParameter("lcd2", "222222220");
                rt.execute(builder.build().toString()).getStatus();
                flgYard = !flgYard;
                break;
        }
    }
}
