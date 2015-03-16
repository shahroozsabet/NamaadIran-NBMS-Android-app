/*
 * Author: Shahrooz Sabet
 * Date: 20141101
 * */
package util;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

public class ExceptionHandler {

    private final String app = "NBMS";

    // / <summary>
    // / Display the message as a Long toast message
    // / </summary>
    // / <param name="context">The context.</param>
    // / <param name="msg">The message to display</param>
    public static void toastMsg(Context context, String msg) {
        Toast toast = Toast.makeText(context, msg, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.TOP | Gravity.CENTER, 0, 0);
        toast.show();
    }

    // / <summary>
    // / Write a debug message to the log for activityName
    // / </summary>
    // / <param name="msg">The MSG.</param>
    // / <param name="logging">if set to <c>true</c> [logging].</param>
    // / <param name="activityName">Name of the activity.</param>
    public static void logDActivity(String msg, boolean logging,
                                    String activityName) {
        if (logging)
            Log.d(activityName, msg);
    }

}
