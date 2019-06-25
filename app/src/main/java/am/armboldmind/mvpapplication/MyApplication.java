package am.armboldmind.mvpapplication;

import androidx.multidex.MultiDexApplication;

import am.armboldmind.mvpapplication.shared.di.components.DaggerIAuthorizationComponent;
import am.armboldmind.mvpapplication.shared.di.components.DaggerINewsComponent;
import am.armboldmind.mvpapplication.shared.di.components.IAuthorizationComponent;
import am.armboldmind.mvpapplication.shared.di.components.INewsComponent;
import am.armboldmind.mvpapplication.shared.di.components.root.DaggerIAppComponent;
import am.armboldmind.mvpapplication.shared.di.components.root.IAppComponent;
import am.armboldmind.mvpapplication.shared.di.modules.AuthorizationModule;
import am.armboldmind.mvpapplication.shared.di.modules.NewsModule;
import am.armboldmind.mvpapplication.shared.di.modules.root.AppModule;
import am.armboldmind.mvpapplication.shared.di.modules.root.NetModule;

public class MyApplication extends MultiDexApplication {

    private static MyApplication mInstance;
    private IAppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

        mAppComponent = DaggerIAppComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule())
                .build();
    }

    public static synchronized MyApplication getInstance() {
        return mInstance;
    }

    public IAuthorizationComponent getAuthorizationComponent() {
        return DaggerIAuthorizationComponent.builder()
                .iAppComponent(mAppComponent)
                .authorizationModule(new AuthorizationModule())
                .build();
    }

    public INewsComponent getNewsComponent() {
        return DaggerINewsComponent.builder()
                .iAppComponent(mAppComponent)
                .newsModule(new NewsModule())
                .build();
    }
}