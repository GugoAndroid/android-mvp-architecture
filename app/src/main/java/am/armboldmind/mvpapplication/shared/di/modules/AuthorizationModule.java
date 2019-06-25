package am.armboldmind.mvpapplication.shared.di.modules;

import am.armboldmind.mvpapplication.shared.data.api.IAuthorizationService;
import am.armboldmind.mvpapplication.shared.data.services.AuthorizationService;
import am.armboldmind.mvpapplication.shared.di.scopes.AuthorizationScope;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class AuthorizationModule {

    @Provides
    @AuthorizationScope
    IAuthorizationService providesIAuthorizationService(Retrofit retrofit) {
        return retrofit.create(IAuthorizationService.class);
    }

    @Provides
    @AuthorizationScope
    AuthorizationService providesAuthorizationService(IAuthorizationService service) {
        return new AuthorizationService(service);
    }
}