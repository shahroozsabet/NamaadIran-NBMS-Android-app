/*
 * Author: Shahrooz Sabet
 * Date: 20141101
 * */
package namaad.bms;

import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class RequestTask extends AsyncTask<String, String, String> {

    public String temp1, temp2, temp3, ldr_1, ldr_2, ldr_1_setpoint;
    public String ldr_2_setpoint, temp1_setpoint, temp2_setpoint;
    public String temp3_setpoint, garden_magnet, front_magnet, hall_aye;
    public String room_aye;

    private EnergyMan energyMan;
    private Senario senario;

    public RequestTask() {

    }

    public RequestTask(EnergyMan energyMan) {
        this.energyMan = energyMan;
    }

    public RequestTask(Senario senario) {
        this.senario = senario;
    }

    @Override
    protected String doInBackground(String... uri) {
        HttpClient httpclient = new DefaultHttpClient();
        HttpResponse response;
        String responseString = null;
        try {
            response = httpclient.execute(new HttpGet(uri[0]));
            StatusLine statusLine = response.getStatusLine();
            if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                response.getEntity().writeTo(out);
                out.close();
                responseString = out.toString();
            } else {
                // Closes the connection.
                response.getEntity().getContent().close();
                throw new IOException(statusLine.getReasonPhrase());
            }
        } catch (ClientProtocolException e) {
            System.out
                    .println("// TODO ClientProtocolException, Handle problems..");
        } catch (IOException e) {
            System.out.println("// TODO IOException, Handle problems..");
        }
        return responseString;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        temp1 = result.substring(225, 229);
        temp2 = result.substring(260, 264);
        temp3 = result.substring(295, 299);
        ldr_1 = result.substring(336, 340);
        ldr_2 = result.substring(370, 374);
        ldr_1_setpoint = result.substring(422, 426);
        ldr_2_setpoint = result.substring(466, 470);
        temp1_setpoint = result.substring(516, 520);
        temp2_setpoint = result.substring(558, 562);
        temp3_setpoint = result.substring(600, 604);
        garden_magnet = result.substring(654, 658);
        front_magnet = result.substring(694, 698);
        hall_aye = result.substring(743, 747);
        room_aye = result.substring(779, 783);
        if (energyMan != null && energyMan.hasWindowFocus()) {
            energyMan.etTemprCurSalon.setText(temp1);
            energyMan.etTemprCurBed1.setText(temp2);
            energyMan.etTemprCurBed2.setText(temp3);
            // energyMan.etTemprIdealBed1.setText(ldr_1_setpoint);
            // energyMan.etTemprIdealBed2.setText(ldr_2_setpoint);
            // energyMan.etTemprIdealSalon.setText(ldr_1);
            // energyMan.etTemprAlertBed1.setText(temp2);
            // energyMan.etTemprAlertBed2.setText(temp3);
            // energyMan.etTemprAlertSalon.setText(temp1_setpoint);
        } else if (senario != null
                && (senario.hasWindowFocus() || senario.inTimer)) {
            senario.temp1 = temp1;
            senario.temp2 = temp2;
            senario.temp3 = temp3;
            senario.ldr_1 = ldr_1;
            senario.ldr_2 = ldr_2;
            senario.ldr_1_setpoint = ldr_1_setpoint;
            senario.ldr_2_setpoint = ldr_2_setpoint;
            senario.temp1_setpoint = temp1_setpoint;
            senario.temp2_setpoint = temp2_setpoint;
            senario.temp3_setpoint = temp3_setpoint;
            senario.garden_magnet = garden_magnet;
            senario.front_magnet = front_magnet;
            senario.hall_aye = hall_aye;
            senario.room_aye = room_aye;
        }

    }
}
