package am.armboldmind.mvpapplication.presenter.root;

import android.content.Context;

import com.bumptech.glide.load.HttpException;

import javax.inject.Inject;

import am.armboldmind.mvpapplication.shared.helpers.SharedPreferencesHelper;
import am.armboldmind.mvpapplication.shared.networking.NetworkError;
import am.armboldmind.mvpapplication.view.root.IBaseView;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class BasePresenter<T extends IBaseView> {

    @Inject
    protected Context mContext;
    @Inject
    protected SharedPreferencesHelper mShared;
    private final CompositeDisposable mDisposables;
    protected T mView;

    protected BasePresenter() {
        mDisposables = new CompositeDisposable();
    }

    public void setView(T view) {
        mView = view;
    }

    public void stopSubscriptions() {
        mDisposables.dispose();
    }

    protected void addDisposable(Disposable disposable) {
        mDisposables.add(disposable);
    }

    protected void deleteDisposable(Disposable disposable) {
        mDisposables.delete(disposable);
    }

    protected void errorToast(Throwable throwable) {
        NetworkError networkError = new NetworkError(mContext, throwable);
        mView.onToast(networkError.getAppErrorMessage());
    }

    protected void errorSnackBar(Throwable throwable) {
        NetworkError networkError = new NetworkError(mContext, throwable);
        mView.onSnackBar(networkError.getAppErrorMessage());
    }

    protected void errorView(Throwable throwable) {
        NetworkError networkError = new NetworkError(mContext, throwable);
        if (networkError.isServerError())
            mView.showServerError();
        else if (networkError.isNetworkError())
            mView.showNetworkError();
        else if (networkError.getError() instanceof HttpException)
            mView.showServerError();
        else
            mView.showServerError();
    }
}