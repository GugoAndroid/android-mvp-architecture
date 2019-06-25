package am.armboldmind.mvpapplication.shared.di.components;

import am.armboldmind.mvpapplication.shared.di.components.root.IAppComponent;
import am.armboldmind.mvpapplication.shared.di.modules.NewsModule;
import am.armboldmind.mvpapplication.shared.di.scopes.NewsScope;
import am.armboldmind.mvpapplication.view.activities.newsDetailsActivity.NewsDetailsActivity;
import am.armboldmind.mvpapplication.view.activities.newsListActivity.NewsListActivity;
import dagger.Component;

@NewsScope
@Component(dependencies = {IAppComponent.class}, modules = {NewsModule.class})
public interface INewsComponent {
    void inject(NewsDetailsActivity activity);

    void inject(NewsListActivity activity);
}