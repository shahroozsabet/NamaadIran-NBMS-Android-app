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

public class SecurityBMS extends Activity implements OnClickListener {
    private static final String TAG = "namaad.bms.SecurityBMS";
    private boolean _logging;

    private Button btnLock;
    private Button btnMagnetYard;
    private Button btnMagnetEntr;
    private Button btnEyeBed;
    private Button btnEyeDoor;
    private Button btnLockDoorEntr;

    private boolean flgLock;
    private boolean flgMagnetYard;
    private boolean flgMagnetEntr;
    private boolean flgEyeBed;
    private boolean flgEyeDoor;
    private boolean flgLockDoorEntr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.security_bms);
        _logging = SettingsActivity.getLogging(this);
        btnLock = (Button) this.findViewById(R.id.btnLock);
        btnMagnetYard = (Button) this.findViewById(R.id.btnMagnetYard);
        btnMagnetEntr = (Button) this.findViewById(R.id.btnMagnetEntr);
        btnEyeBed = (Button) this.findViewById(R.id.btnEyeBed);
        btnEyeDoor = (Button) this.findViewById(R.id.btnEyeDoor);
        btnLockDoorEntr = (Button) this.findViewById(R.id.btnLockDoorEntr);
    }// 20 baz 21 basteh

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.security_bm, menu);
        return true;
    }

    @Override
    public void onResume() {
        super.onResume();
        btnLock.setOnClickListener(this);
        btnMagnetYard.setOnClickListener(this);
        btnMagnetEntr.setOnClickListener(this);
        btnEyeBed.setOnClickListener(this);
        btnEyeDoor.setOnClickListener(this);
        btnLockDoorEntr.setOnClickListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        btnLock.setOnClickListener(null);
        btnMagnetYard.setOnClickListener(null);
        btnMagnetEntr.setOnClickListener(null);
        btnEyeBed.setOnClickListener(null);
        btnEyeDoor.setOnClickListener(null);
        btnLockDoorEntr.setOnClickListener(null);
    }

    @Override
    public void onClick(View v) {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("http").authority("192.168.1.16")
                .appendQueryParameter("aip", "192.168.1.16")
                .appendQueryParameter("lcd1", "0");
        RequestTask rt = new RequestTask();
        switch (v.getId()) {
            case R.id.btnLock:
                if (flgLock == false)
                    builder.appendQueryParameter("lcd2", "221");
                else
                    builder.appendQueryParameter("lcd2", "220");
                rt.execute(builder.build().toString());
                flgLock = !flgLock;
                break;
            case R.id.btnMagnetYard:
                if (flgMagnetYard == false)
                    builder.appendQueryParameter("lcd2", "1");
                else
                    builder.appendQueryParameter("lcd2", "0");
                rt.execute(builder.build().toString());
                flgMagnetYard = !flgMagnetYard;
                break;
            case R.id.btnMagnetEntr:
                if (flgMagnetEntr == false)
                    builder.appendQueryParameter("lcd2", "2221");
                else
                    builder.appendQueryParameter("lcd2", "2220");
                rt.execute(builder.build().toString());
                flgMagnetEntr = !flgMagnetEntr;
                break;
            case R.id.btnEyeBed:
                if (flgEyeBed == false)
                    builder.appendQueryParameter("lcd2", "22221");
                else
                    builder.appendQueryParameter("lcd2", "22220");
                rt.execute(builder.build().toString());
                flgEyeBed = !flgEyeBed;
                break;
            case R.id.btnEyeDoor:
                if (flgEyeDoor == false)
                    builder.appendQueryParameter("lcd2", "222222221");
                else
                    builder.appendQueryParameter("lcd2", "222222220");
                rt.execute(builder.build().toString()).getStatus();
                flgEyeDoor = !flgEyeDoor;
                break;
            case R.id.btnLockDoorEntr:
                if (flgLockDoorEntr == false)
                    builder.appendQueryParameter("lcd2", "20");
                else
                    builder.appendQueryParameter("lcd2", "21");
                rt.execute(builder.build().toString()).getStatus();
                flgLockDoorEntr = !flgLockDoorEntr;
                break;
        }

    }
}
