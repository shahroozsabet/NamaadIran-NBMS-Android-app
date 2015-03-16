/*
 * Author: Shahrooz Sabet
 * Date: 20141101
 * */
package namaad.bms;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

public class Safety extends Activity {
    private static final String TAG = "namaad.bms.Safety";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.safety);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.safety, menu);
        return true;
    }

}
