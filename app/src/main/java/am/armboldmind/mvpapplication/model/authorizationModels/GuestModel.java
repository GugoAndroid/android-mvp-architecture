package am.armboldmind.mvpapplication.model.authorizationModels;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GuestModel {
    private String deviceId;
    private String deviceToken;
    private String modelName;
    private String languageName;
    private int osTypeId;
}