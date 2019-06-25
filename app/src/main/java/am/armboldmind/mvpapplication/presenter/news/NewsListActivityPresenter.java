package am.armboldmind.mvpapplication.presenter.news;

import java.util.List;

import javax.inject.Inject;

import am.armboldmind.mvpapplication.R;
import am.armboldmind.mvpapplication.model.PaginationModel;
import am.armboldmind.mvpapplication.model.ResponseModel;
import am.armboldmind.mvpapplication.model.newsModels.NewsListModel;
import am.armboldmind.mvpapplication.presenter.root.BasePresenter;
import am.armboldmind.mvpapplication.shared.data.services.NewsService;
import am.armboldmind.mvpapplication.shared.di.scopes.NewsScope;
import am.armboldmind.mvpapplication.view.activities.newsListActivity.INewsListActivityView;
import io.reactivex.disposables.Disposable;

@NewsScope
public class NewsListActivityPresenter extends BasePresenter<INewsListActivityView> {

    private final NewsService mService;

    @Inject
    NewsListActivityPresenter(NewsService service) {
        mService = service;
    }


    /**
     * api/news/getList?
     */
    public void getNewsList(final int page, final int size) {
        Disposable disposable = mService.getNewsList(page, size).subscribe(responseModel
                -> responseDepartments(responseModel, page > 0), this::errorSnackBar);
        addDisposable(disposable);
    }

    private void responseDepartments(ResponseModel<PaginationModel<List<NewsListModel>>> responseModel, boolean isUpdate) {
        if (responseModel != null) {
            if (responseModel.getSuccess() && responseModel.getData() != null
                    && responseModel.getData().getContent() != null) {
                if (isUpdate)
                    mView.updateNewsList(responseModel.getData());
                else
                    mView.showNewsList(responseModel.getData());
            } else {
                mView.onSnackBar(responseModel.getMessage());
            }
        } else {
            mView.onSnackBar(mContext.getResources().getString(R.string.default_error_message));
        }
        mView.hideLoading();
    }
}