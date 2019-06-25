package am.armboldmind.mvpapplication.presenter.news;

import javax.inject.Inject;

import am.armboldmind.mvpapplication.R;
import am.armboldmind.mvpapplication.model.ResponseModel;
import am.armboldmind.mvpapplication.model.newsModels.NewsDetailsModel;
import am.armboldmind.mvpapplication.presenter.root.BasePresenter;
import am.armboldmind.mvpapplication.shared.data.services.NewsService;
import am.armboldmind.mvpapplication.shared.di.scopes.NewsScope;
import am.armboldmind.mvpapplication.view.activities.newsDetailsActivity.INewsDetailsActivityView;
import io.reactivex.disposables.Disposable;

@NewsScope
public class NewsDetailsActivityPresenter extends BasePresenter<INewsDetailsActivityView> {

    private final NewsService mService;

    @Inject
    NewsDetailsActivityPresenter(NewsService service) {
        mService = service;
    }


    /**
     * api/news/getById?
     */
    public void getNewsDetails(final long id) {
        Disposable disposable = mService.getNewsDetails(id).subscribe(this::response, this::errorSnackBar);
        addDisposable(disposable);
    }

    private void response(ResponseModel<NewsDetailsModel> responseModel) {
        if (responseModel != null) {
            if (responseModel.getSuccess() && responseModel.getData() != null) {
                mView.hideLoading();
                mView.showNewsDetails(responseModel.getData());
            } else {
                mView.onSnackBar(responseModel.getMessage());
            }
        } else {
            mView.onSnackBar(mContext.getResources().getString(R.string.default_error_message));
        }
    }
}