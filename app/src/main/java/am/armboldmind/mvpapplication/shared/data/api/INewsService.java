package am.armboldmind.mvpapplication.shared.data.api;

import java.util.List;

import am.armboldmind.mvpapplication.model.PaginationModel;
import am.armboldmind.mvpapplication.model.ResponseModel;
import am.armboldmind.mvpapplication.model.newsModels.NewsDetailsModel;
import am.armboldmind.mvpapplication.model.newsModels.NewsListModel;
import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface INewsService {

    /**
     * news
     */
    @GET("api/news/getList?")
    Flowable<ResponseModel<PaginationModel<List<NewsListModel>>>> getNewsList(@Query("page") int page, @Query("size") int size);

    @GET("api/news/getById?")
    Flowable<ResponseModel<NewsDetailsModel>> getNewsDetails(@Query("id") long id);
}