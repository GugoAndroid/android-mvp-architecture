package am.armboldmind.mvpapplication.shared.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public final class NetworkStatusUtils {
    public static boolean isNetworkAvailable(Context context) {
        boolean status = false;
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = null;
            if (connectivityManager != null) {
                netInfo = connectivityManager.getNetworkInfo(0);
            }

            if (netInfo != null && netInfo.getState() == NetworkInfo.State.CONNECTED) {
                status = true;
            } else {
                if (connectivityManager != null) {
                    netInfo = connectivityManager.getNetworkInfo(1);
                }
                if (netInfo != null && netInfo.getState() == NetworkInfo.State.CONNECTED)
                    status = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return status;
    }
}