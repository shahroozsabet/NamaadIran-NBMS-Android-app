/*
 * Author: Shahrooz Sabet
 * Date: 20141101
 * */
package namaad.bms;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainBMS extends Activity implements OnClickListener {
    private static final String TAG = "namaad.bms.MainBMS";
    private Button btnEnergyMan;
    private Button btnLighting;
    private Button btnSafety;
    private Button btnSecurity;
    private Button btnSenario;
    private Button btnSetup;
    private Button btnReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_bms);
        // _logging = SettingsActivity.getLogging(this);
        btnEnergyMan = (Button) this.findViewById(R.id.btnEnergyMan);
        btnLighting = (Button) this.findViewById(R.id.btnLighting);
        btnSafety = (Button) this.findViewById(R.id.btnSafety);
        btnSecurity = (Button) this.findViewById(R.id.btnSecurity);
        btnSenario = (Button) this.findViewById(R.id.btnSenario);
        btnSetup = (Button) this.findViewById(R.id.btnSetup);
        btnReset = (Button) this.findViewById(R.id.btnReset);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onResume() {
        super.onResume();
        btnEnergyMan.setOnClickListener(this);
        btnLighting.setOnClickListener(this);
        btnSafety.setOnClickListener(this);
        btnSecurity.setOnClickListener(this);
        btnSenario.setOnClickListener(this);
        btnSetup.setOnClickListener(this);
        btnReset.setOnClickListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        btnEnergyMan.setOnClickListener(null);
        btnLighting.setOnClickListener(null);
        btnSafety.setOnClickListener(null);
        btnSecurity.setOnClickListener(null);
        btnSenario.setOnClickListener(null);
        btnSetup.setOnClickListener(null);
        btnReset.setOnClickListener(null);
    }

    @Override
    public void onClick(View v) {
        Intent i = null;
        switch (v.getId()) {
            case R.id.btnEnergyMan:
                i = new Intent(this, EnergyMan.class);
                break;
            case R.id.btnLighting:
                i = new Intent(this, Lighting.class);
                break;
            case R.id.btnSafety:
                i = new Intent(this, Safety.class);
                break;
            case R.id.btnSecurity:
                i = new Intent(this, SecurityBMS.class);
                break;
            case R.id.btnSenario:
                i = new Intent(this, Senario.class);
                break;
            case R.id.btnSetup:
                i = new Intent(this, SettingsActivity.class);
                break;
        }
        if (v.getId() == R.id.btnReset) {
            RequestTask rt = new RequestTask();
            Uri.Builder builder = new Uri.Builder();
            builder.scheme("http").authority("192.168.1.16")
                    .appendQueryParameter("aip", "192.168.1.16")
                    .appendQueryParameter("lcd1", "0");
            builder.appendQueryParameter("lcd2", "0000000000000");
            rt.execute(builder.build().toString());
        } else
            startActivity(i);
    }
}
