package am.armboldmind.mvpapplication.shared.utils;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public final class KeyboardUtils {
    public static void hideSoftInput(AppCompatActivity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE);
        View view = activity.getCurrentFocus();
        if (view == null) {
            view = new View(activity);
        }
        if (inputMethodManager != null) {
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static void showKeyboard(AppCompatActivity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null)
            inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    public static void toggleSoftInput(Context context) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null)
            inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }

    public static void setCursorVisible(View contentView, EditText editText) {
        contentView.getViewTreeObserver().addOnGlobalLayoutListener(() -> {
            Rect r = new Rect();
            contentView.getWindowVisibleDisplayFrame(r);
            int screenHeight = contentView.getRootView().getHeight();
            int keypadHeight = screenHeight - r.bottom;

            if (keypadHeight > screenHeight * 0.15) {
                editText.setCursorVisible(true);
            } else {
                editText.setCursorVisible(false);
            }
        });
    }
}