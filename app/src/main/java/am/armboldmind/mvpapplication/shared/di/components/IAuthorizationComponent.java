package am.armboldmind.mvpapplication.shared.di.components;

import am.armboldmind.mvpapplication.shared.di.components.root.IAppComponent;
import am.armboldmind.mvpapplication.shared.di.modules.AuthorizationModule;
import am.armboldmind.mvpapplication.shared.di.scopes.AuthorizationScope;
import am.armboldmind.mvpapplication.view.activities.signInActivity.fragments.SignInFragment;
import dagger.Component;

@AuthorizationScope
@Component(dependencies = {IAppComponent.class}, modules = {AuthorizationModule.class})
public interface IAuthorizationComponent {
    void inject(SignInFragment fragment);
}