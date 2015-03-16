/*
 * Author: Shahrooz Sabet
 * Date: 20141101
 * */
package namaad.bms;

import android.app.ListActivity;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class Senario extends ListActivity {
    private static final String TAG = "namaad.bms.Senario";
    public String temp1, temp2, temp3, ldr_1, ldr_2, ldr_1_setpoint;
    public String ldr_2_setpoint, temp1_setpoint, temp2_setpoint;
    public String temp3_setpoint, garden_magnet, front_magnet, hall_aye;
    public String room_aye;
    public Senario senClass;
    public boolean inTimer;
    private Map<String, String> sen;
    private CountDownTimer timer;
    private boolean flgSen1;
    private boolean flgSen2;
    private boolean flgSen3;
    private boolean flgSen4;
    private boolean flgSen5;
    private boolean flgSen6;
    private boolean flgSen7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sen = new LinkedHashMap<String, String>();
        sen.put(getResources().getText(R.string.senNameLock).toString(),
                getResources().getText(R.string.senLock).toString());
        sen.put(getResources().getText(R.string.senNameSleep).toString(),
                getResources().getText(R.string.senSleep).toString());
        sen.put(getResources().getText(R.string.senNameMovie).toString(),
                getResources().getText(R.string.senMovie).toString());
        sen.put(getResources().getText(R.string.senNameParty).toString(),
                getResources().getText(R.string.senParty).toString());
        sen.put(getResources().getText(R.string.senNameInHome).toString(),
                getResources().getText(R.string.senInHome).toString());
        sen.put(getResources().getText(R.string.senNameAutoCurt).toString(),
                getResources().getText(R.string.senAutoCurt).toString());
        /* Setup ListAdapter */
        ListAdapter adapt = new MultiAdapter(this);

		/* Configure this ListActivity */
        setListAdapter(adapt);
        ListView lv = getListView();
        lv.setOnItemClickListener(new ItemClick());
        senClass = this;
        timer = new CountDownTimer(999999999, 2000) {
            public void onTick(long millisUntilFinished) {
                inTimer = true;
                RequestTask rt = new RequestTask(senClass);
                Uri.Builder builder = new Uri.Builder();
                builder.scheme("http").authority("192.168.1.16")
                        .appendQueryParameter("aip", "192.168.1.16")
                        .appendQueryParameter("lcd1", "0");
                builder.appendQueryParameter("lcd2", "2222222222222");
                rt.execute(builder.build().toString());
                rt = new RequestTask(senClass);
                builder = new Uri.Builder();
                builder.scheme("http").authority("192.168.1.16")
                        .appendQueryParameter("aip", "192.168.1.16")
                        .appendQueryParameter("lcd1", "0");
                if (flgSen2
                        && (garden_magnet != null && garden_magnet
                        .endsWith("0"))
                        || (front_magnet != null && front_magnet.endsWith("0"))) {
                    builder.appendQueryParameter("lcd2", "2222222222221");
                } else if (flgSen6 && ldr_1 != null) {
                    // ldr1<0050 pardeh basteh, Cheragh Salon Roshan
                    // ldr1>0050 Pardeh baz, hameh sefr
                    if (new Integer(ldr_1) < 0050) {
                        builder.appendQueryParameter("lcd2", "222212222201");
                    } else {
                        builder.appendQueryParameter("lcd2", "00000000001");
                    }
                }
                rt.execute(builder.build().toString());
            }

            public void onFinish() {

            }
        }.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.senario, menu);
        return true;
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        timer.cancel();
    }

    private class ItemClick implements OnItemClickListener {
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            RequestTask rt = new RequestTask(senClass);
            Uri.Builder builder = new Uri.Builder();
            builder.scheme("http").authority("192.168.1.16")
                    .appendQueryParameter("aip", "192.168.1.16")
                    .appendQueryParameter("lcd1", "0");
            // Sen1
            // Do ta magne 0 bashad ya haley 0 bashad, alarm
            switch (position) {
                case 0:
                    builder.appendQueryParameter("lcd2", "000000000000");
                    rt.execute(builder.build().toString());
                    flgSen1 = true;
                    break;
                case 1:
                    builder.appendQueryParameter("lcd2", "000000000000");
                    rt.execute(builder.build().toString());
                    flgSen2 = true;
                    break;
                case 2:
                    builder.appendQueryParameter("lcd2", "100000000000");
                    rt.execute(builder.build().toString());
                    flgSen3 = true;
                    break;
                case 3:
                    builder.appendQueryParameter("lcd2", "100110011110");
                    rt.execute(builder.build().toString());
                    flgSen4 = true;
                    break;
                case 4:
                    builder.appendQueryParameter("lcd2", "000010000000");
                    rt.execute(builder.build().toString());
                    Calendar calendar = Calendar.getInstance();
                    Date startTime = calendar.getTime();
                    calendar.add(Calendar.SECOND, 5);
                    Date time2 = calendar.getTime();
                    calendar.add(Calendar.SECOND, 15);
                    Date time3 = calendar.getTime();
                    calendar.add(Calendar.SECOND, 20);
                    Date time4 = calendar.getTime();
                    calendar = Calendar.getInstance();
                    while (calendar.getTime().compareTo(time2) <= 0) {
                    }
                    rt = new RequestTask(senClass);
                    builder.appendQueryParameter("lcd2", "221222221222");
                    rt.execute(builder.build().toString());
                    while (calendar.getTime().compareTo(time3) <= 0) {
                    }
                    rt = new RequestTask(senClass);
                    builder.appendQueryParameter("lcd2", "222222222122");
                    rt.execute(builder.build().toString());
                    while (calendar.getTime().compareTo(time4) <= 0) {
                    }
                    rt = new RequestTask(senClass);
                    builder.appendQueryParameter("lcd2", "001000000000");
                    rt.execute(builder.build().toString());
                    flgSen5 = true;
                    break;
                case 5:
                    builder.appendQueryParameter("lcd2", "020002220222");
                    rt.execute(builder.build().toString());
                    flgSen6 = true;
                    break;
            }
        }
    }

    class MultiAdapter extends ArrayAdapter<String> {

        public MultiAdapter(Context context) {
            super(context, R.layout.senario, sen.keySet()
                    .toArray(new String[0]));
        }

        @Override
        // Called when updating the ListView
        public View getView(int position, View convertView, ViewGroup parent) {
            View row;
            if (convertView == null) { // Create new row view object
                LayoutInflater inflater = getLayoutInflater();
                row = inflater.inflate(R.layout.senario, parent, false);
            } else
                // reuse old row view to save time/battery
                row = convertView;

			/* Add new data to row object */
            TextView tvSenarioName = (TextView) row
                    .findViewById(R.id.tvSenarioName);
            TextView tvSenario = (TextView) row.findViewById(R.id.tvSenario);
            Object[] keyArrObj = sen.keySet().toArray();
            switch (position) {
                case 0:
                    tvSenarioName.setText((String) keyArrObj[0]);
                    tvSenario.setText(sen.get(getResources().getText(
                            R.string.senNameLock).toString()));
                    break;
                case 1:
                    tvSenarioName.setText((String) keyArrObj[1]);
                    tvSenario.setText(sen.get(getResources().getText(
                            R.string.senNameSleep).toString()));
                    break;
                case 2:
                    tvSenarioName.setText((String) keyArrObj[2]);
                    tvSenario.setText(sen.get(getResources().getText(
                            R.string.senNameMovie).toString()));
                    break;
                case 3:
                    tvSenarioName.setText((String) keyArrObj[3]);
                    tvSenario.setText(sen.get(getResources().getText(
                            R.string.senNameParty).toString()));
                    break;
                case 4:
                    tvSenarioName.setText((String) keyArrObj[4]);
                    tvSenario.setText(sen.get(getResources().getText(
                            R.string.senNameInHome).toString()));
                    break;
                case 5:
                    tvSenarioName.setText((String) keyArrObj[5]);
                    tvSenario.setText(sen.get(getResources().getText(
                            R.string.senNameAutoCurt).toString()));
                    break;
            }

            return row;
        }
    }

    private class Senario4Task extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            RequestTask rt = new RequestTask(senClass);
            Uri.Builder builder = new Uri.Builder();
            builder.scheme("http").authority("192.168.1.16")
                    .appendQueryParameter("aip", "192.168.1.16")
                    .appendQueryParameter("lcd1", "0");
            builder.appendQueryParameter("lcd2", "000010000000");
            rt.execute(builder.build().toString());
            Calendar calendar = Calendar.getInstance();
            Date startTime = calendar.getTime();
            calendar.add(Calendar.SECOND, 5);
            Date time2 = calendar.getTime();
            calendar.add(Calendar.SECOND, 15);
            Date time3 = calendar.getTime();
            calendar.add(Calendar.SECOND, 20);
            Date time4 = calendar.getTime();
            calendar = Calendar.getInstance();
            while (calendar.getTime().compareTo(time2) <= 0) {
            }
            rt = new RequestTask(senClass);
            builder.appendQueryParameter("lcd2", "221222221222");
            rt.execute(builder.build().toString());
            while (calendar.getTime().compareTo(time3) <= 0) {
            }
            rt = new RequestTask(senClass);
            builder.appendQueryParameter("lcd2", "222222222122");
            rt.execute(builder.build().toString());
            while (calendar.getTime().compareTo(time4) <= 0) {
            }
            rt = new RequestTask(senClass);
            builder.appendQueryParameter("lcd2", "001000000000");
            rt.execute(builder.build().toString());
            return null;
        }

    }
}
