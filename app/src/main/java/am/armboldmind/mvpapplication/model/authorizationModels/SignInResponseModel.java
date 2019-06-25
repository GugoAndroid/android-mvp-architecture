package am.armboldmind.mvpapplication.model.authorizationModels;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignInResponseModel {
    private String token;
    private String role;
}