package am.armboldmind.mvpapplication.shared.networking;

import android.content.Context;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;

import am.armboldmind.mvpapplication.R;
import am.armboldmind.mvpapplication.shared.helpers.SharedPreferencesHelper;
import am.armboldmind.mvpapplication.shared.utils.NetworkStatusUtils;
import retrofit2.HttpException;

import static java.net.HttpURLConnection.HTTP_FORBIDDEN;
import static java.net.HttpURLConnection.HTTP_UNAUTHORIZED;

public class NetworkError extends Throwable {

    private final Context mContext;
    private final Throwable mError;

    public NetworkError(Context context, Throwable throwable) {
        super(throwable);
        mContext = context;
        mError = throwable;
    }

    public boolean isServerError() {
        return isAuthFailure() || mError instanceof SocketTimeoutException;
    }

    public boolean isNetworkError() {
        return isAuthFailure() || mError instanceof IOException;
    }

    private Boolean isAuthFailure() {
        if (mError instanceof HttpException) {
            if (((HttpException) mError).code() == HTTP_UNAUTHORIZED || ((HttpException) mError).code() == HTTP_FORBIDDEN) {
                SharedPreferencesHelper shared = new SharedPreferencesHelper(mContext);
                shared.deleteSharedPreferences();

               /* Intent intent = new Intent(mContext, SignInActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION);
                mContext.startActivity(intent);*/
                return true;
            } else
                return false;
        } else
            return false;
    }

    public Throwable getError() {
        return mError;
    }

    public String getAppErrorMessage() {
        if (!isAuthFailure()) {
            if (mError instanceof SocketTimeoutException)
                return mContext.getResources().getString(R.string.default_error_message);
            if (mError instanceof ConnectException && NetworkStatusUtils.isNetworkAvailable(mContext))
                return mContext.getResources().getString(R.string.default_error_message);
            if (mError instanceof ConnectException && !NetworkStatusUtils.isNetworkAvailable(mContext))
                return mContext.getResources().getString(R.string.network_error_message);
            if (mError instanceof IOException)
                return mContext.getResources().getString(R.string.network_error_message);
            if (!(mError instanceof HttpException))
                return mContext.getResources().getString(R.string.default_error_message);
        }

        return mContext.getResources().getString(R.string.default_error_message);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NetworkError that = (NetworkError) o;

        return mError != null ? mError.equals(that.mError) : that.mError == null;
    }

    @Override
    public int hashCode() {
        return mError != null ? mError.hashCode() : 0;
    }
}