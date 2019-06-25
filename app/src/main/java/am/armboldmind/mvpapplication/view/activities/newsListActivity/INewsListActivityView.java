package am.armboldmind.mvpapplication.view.activities.newsListActivity;

import java.util.List;

import am.armboldmind.mvpapplication.model.PaginationModel;
import am.armboldmind.mvpapplication.model.newsModels.NewsListModel;
import am.armboldmind.mvpapplication.view.root.IBaseView;

public interface INewsListActivityView extends IBaseView {
    void showLoading();

    void hideLoading();

    void showNewsList(PaginationModel<List<NewsListModel>> newsList);

    void updateNewsList(PaginationModel<List<NewsListModel>> newsList);
}