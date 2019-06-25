package am.armboldmind.mvpapplication.shared.di.modules;

import am.armboldmind.mvpapplication.shared.data.api.INewsService;
import am.armboldmind.mvpapplication.shared.data.services.NewsService;
import am.armboldmind.mvpapplication.shared.di.scopes.NewsScope;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class NewsModule {

    @Provides
    @NewsScope
    INewsService providesINewsService(Retrofit retrofit) {
        return retrofit.create(INewsService.class);
    }

    @Provides
    @NewsScope
    NewsService providesNewsService(INewsService service) {
        return new NewsService(service);
    }
}