package am.armboldmind.mvpapplication.model.authorizationModels;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignInRequestModel {
    private String username;
    private String password;
}