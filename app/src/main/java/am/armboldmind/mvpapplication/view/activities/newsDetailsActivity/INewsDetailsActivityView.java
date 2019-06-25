package am.armboldmind.mvpapplication.view.activities.newsDetailsActivity;

import am.armboldmind.mvpapplication.model.newsModels.NewsDetailsModel;
import am.armboldmind.mvpapplication.view.root.IBaseView;

public interface INewsDetailsActivityView extends IBaseView {
    void showLoading();

    void hideLoading();

    void showNewsDetails(NewsDetailsModel newsDetailsModel);
}