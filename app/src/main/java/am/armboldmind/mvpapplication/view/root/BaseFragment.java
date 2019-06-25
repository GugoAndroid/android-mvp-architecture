package am.armboldmind.mvpapplication.view.root;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment extends Fragment implements IBaseView {

    private BaseActivity mBaseActivity;

    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            mBaseActivity = (BaseActivity) context;
        }
    }

    @Override
    public void onToast(String errorMessage) {
        if (mBaseActivity != null) {
            mBaseActivity.onToast(errorMessage);
        }
    }

    @Override
    public void onSnackBar(String message) {
        if (mBaseActivity != null) {
            mBaseActivity.onSnackBar(message);
        }
    }

    @Override
    public void showServerError() {
        if (mBaseActivity != null) {
            mBaseActivity.showServerError();
        }
    }

    @Override
    public void showNetworkError() {
        if (mBaseActivity != null) {
            mBaseActivity.showNetworkError();
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    public void requestPermissionsSafely(String[] permissions, Integer requestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode);
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    public boolean hasPermission(String permission) {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M
                || mBaseActivity.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
    }
}