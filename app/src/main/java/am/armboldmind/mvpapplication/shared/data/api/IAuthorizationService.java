package am.armboldmind.mvpapplication.shared.data.api;

import am.armboldmind.mvpapplication.model.ResponseModel;
import am.armboldmind.mvpapplication.model.authorizationModels.GuestModel;
import am.armboldmind.mvpapplication.model.authorizationModels.SignInRequestModel;
import am.armboldmind.mvpapplication.model.authorizationModels.SignInResponseModel;
import io.reactivex.Flowable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IAuthorizationService {

    /**
     * token
     */
    @POST("api/token/signIn")
    Flowable<ResponseModel<SignInResponseModel>> signIn(@Body SignInRequestModel signInRequestModel);

    @POST("api/token/signUpAsGuest")
    Flowable<ResponseModel<SignInResponseModel>> signUpAsGuest(@Body GuestModel guestModel);
}