package am.armboldmind.mvpapplication.shared.data.services;

import java.util.List;

import am.armboldmind.mvpapplication.model.PaginationModel;
import am.armboldmind.mvpapplication.model.ResponseModel;
import am.armboldmind.mvpapplication.model.newsModels.NewsDetailsModel;
import am.armboldmind.mvpapplication.model.newsModels.NewsListModel;
import am.armboldmind.mvpapplication.shared.data.api.INewsService;
import am.armboldmind.mvpapplication.shared.data.services.root.BaseService;
import io.reactivex.Flowable;

public class NewsService extends BaseService {

    private final INewsService mService;

    public NewsService(INewsService service) {
        mService = service;
    }


    /**
     * news
     */
    public Flowable<ResponseModel<PaginationModel<List<NewsListModel>>>> getNewsList(final int page, final int size) {
        return request(mService.getNewsList(page, size));
    }

    public Flowable<ResponseModel<NewsDetailsModel>> getNewsDetails(final long id) {
        return request(mService.getNewsDetails(id));
    }
}