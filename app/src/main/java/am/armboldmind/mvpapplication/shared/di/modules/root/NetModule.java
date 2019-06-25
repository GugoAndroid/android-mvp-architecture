package am.armboldmind.mvpapplication.shared.di.modules.root;

import android.annotation.SuppressLint;
import android.app.Application;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import am.armboldmind.mvpapplication.BuildConfig;
import am.armboldmind.mvpapplication.shared.helpers.SharedPreferencesHelper;
import am.armboldmind.mvpapplication.shared.utils.AppConstants;
import am.armboldmind.mvpapplication.shared.utils.CommonUtils;
import am.armboldmind.mvpapplication.shared.utils.NetworkStatusUtils;
import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

@Module
public class NetModule {

    private final int mMaxStale = 60 * 60 * 24 * 5; // 5-days
    private String deviceToken = "";

    @Provides
    @Singleton
    Cache provideOkHttpCache(Application application) {
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        return new Cache(application.getCacheDir(), cacheSize);
    }

    @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(Cache cache, Application application) {
        final SharedPreferencesHelper shared = new SharedPreferencesHelper(application);

        return new OkHttpClient.Builder().addInterceptor(chain -> {
            String token = "";
            if (shared.getStringSharedPreferences(AppConstants.TOKEN) != null)
                token = shared.getStringSharedPreferences(AppConstants.TOKEN);

            Request original = chain.request();
            // Customize the request
            @SuppressLint("HardwareIds") Request request = original.newBuilder()
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + token)
                    .header("LanguageCode", "hy")
                    .header("DeviceId", CommonUtils.getDeviceId(application))
                    .header("Model", "" + CommonUtils.getMobileModel())
                    .header("OsType", "1")
                    .header("Cache-Control", (NetworkStatusUtils.isNetworkAvailable(application)) ?
                            "public, max-age=" + mMaxStale : "public, max-stale=" + mMaxStale)
                    .build();
            okhttp3.Response response = chain.proceed(request);
            response.cacheResponse();
            // Customize or return the response
            return response;
        })
                .cache(cache)
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .build();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
}