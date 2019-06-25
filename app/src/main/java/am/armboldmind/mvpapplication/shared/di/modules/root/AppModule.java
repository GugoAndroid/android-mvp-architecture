package am.armboldmind.mvpapplication.shared.di.modules.root;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

import javax.inject.Singleton;

import am.armboldmind.mvpapplication.shared.helpers.SharedPreferencesHelper;
import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private final Application mApplication;

    public AppModule(Application application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    Application providesApplication() {
        return mApplication;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return mApplication;
    }

    @Provides
    @Singleton
    Resources provideResources(Context context) {
        return context.getResources();
    }

    @Provides
    @Singleton
    SharedPreferencesHelper providesSharedPreferences(Application application) {
        return new SharedPreferencesHelper(application);
    }
}