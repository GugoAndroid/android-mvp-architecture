package am.armboldmind.mvpapplication.shared.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.util.Patterns;

public final class CommonUtils {
    @SuppressLint("all")
    public static String getDeviceId(final Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    public static String getMobileModel() {
        return String.format("%s %s", Build.MANUFACTURER, Build.MODEL);
    }

    public static boolean isEmailValid(final String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}