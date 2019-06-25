package am.armboldmind.mvpapplication.presenter.authorization;

import javax.inject.Inject;

import am.armboldmind.mvpapplication.R;
import am.armboldmind.mvpapplication.model.ResponseModel;
import am.armboldmind.mvpapplication.model.authorizationModels.GuestModel;
import am.armboldmind.mvpapplication.model.authorizationModels.SignInRequestModel;
import am.armboldmind.mvpapplication.model.authorizationModels.SignInResponseModel;
import am.armboldmind.mvpapplication.presenter.root.BasePresenter;
import am.armboldmind.mvpapplication.shared.data.services.AuthorizationService;
import am.armboldmind.mvpapplication.shared.di.scopes.AuthorizationScope;
import am.armboldmind.mvpapplication.shared.utils.AppConstants;
import am.armboldmind.mvpapplication.view.activities.signInActivity.fragments.ISignInFragmentView;
import io.reactivex.disposables.Disposable;

@AuthorizationScope
public class SignInFragmentPresenter extends BasePresenter<ISignInFragmentView> {

    private final AuthorizationService mService;

    @Inject
    SignInFragmentPresenter(AuthorizationService service) {
        mService = service;
    }


    /**
     * api/token/signUpAsGuest
     */
    public void signUpAsGuest(final GuestModel guestModel) {
        Disposable disposable = mService.signUpAsGuest(guestModel).subscribe(this::responseGuest, throwable -> {
            errorToast(throwable);
            mView.hideGuestButtonLoading();
        });
        addDisposable(disposable);
    }

    private void responseGuest(ResponseModel<SignInResponseModel> responseModel) {
        if (responseModel != null) {
            if (responseModel.getData() != null && responseModel.getData().getToken() != null) {
                mShared.setStringSharedPreferences(AppConstants.TOKEN, responseModel.getData().getToken());
                mView.signIn();
            } else {
                mView.onToast(responseModel.getMessage());
            }
        } else
            mView.onToast(mContext.getResources().getString(R.string.default_error_message));
        mView.hideGuestButtonLoading();
    }


    /**
     * api/token/signIn
     */
    public void signIn(final SignInRequestModel signInRequestModel) {
        Disposable disposable = mService.signIn(signInRequestModel).subscribe(this::response, throwable -> {
            errorToast(throwable);
            mView.hideButtonLoading();
        });
        addDisposable(disposable);
    }

    private void response(ResponseModel<SignInResponseModel> responseModel) {
        if (responseModel != null) {
            if (responseModel.getData() != null && responseModel.getData().getToken() != null) {
                mShared.setStringSharedPreferences(AppConstants.TOKEN, responseModel.getData().getToken());
            } else {
                mView.onToast(responseModel.getMessage());
            }
        } else
            mView.onToast(mContext.getResources().getString(R.string.default_error_message));
        mView.hideButtonLoading();
    }
}