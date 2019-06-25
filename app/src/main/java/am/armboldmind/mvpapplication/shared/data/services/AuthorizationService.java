package am.armboldmind.mvpapplication.shared.data.services;

import am.armboldmind.mvpapplication.model.ResponseModel;
import am.armboldmind.mvpapplication.model.authorizationModels.GuestModel;
import am.armboldmind.mvpapplication.model.authorizationModels.SignInRequestModel;
import am.armboldmind.mvpapplication.model.authorizationModels.SignInResponseModel;
import am.armboldmind.mvpapplication.shared.data.api.IAuthorizationService;
import am.armboldmind.mvpapplication.shared.data.services.root.BaseService;
import io.reactivex.Flowable;

public class AuthorizationService extends BaseService {

    private final IAuthorizationService mService;

    public AuthorizationService(IAuthorizationService service) {
        mService = service;
    }


    /**
     * token
     */
    public Flowable<ResponseModel<SignInResponseModel>> signIn(final SignInRequestModel signInRequestModel) {
        return requestSignIn(mService.signIn(signInRequestModel));
    }

    public Flowable<ResponseModel<SignInResponseModel>> signUpAsGuest(final GuestModel guestModel) {
        return requestSignIn(mService.signUpAsGuest(guestModel));
    }
}