package am.armboldmind.mvpapplication.view.activities.newsListActivity;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import am.armboldmind.mvpapplication.MyApplication;
import am.armboldmind.mvpapplication.R;
import am.armboldmind.mvpapplication.model.PaginationModel;
import am.armboldmind.mvpapplication.model.newsModels.NewsListModel;
import am.armboldmind.mvpapplication.presenter.news.NewsListActivityPresenter;
import am.armboldmind.mvpapplication.shared.utils.AppConstants;
import am.armboldmind.mvpapplication.view.activities.newsDetailsActivity.NewsDetailsActivity;
import am.armboldmind.mvpapplication.view.adapters.NewsListPageableAdapter;
import am.armboldmind.mvpapplication.view.root.BaseActivity;

public class NewsListActivity extends BaseActivity implements INewsListActivityView {

    @Inject
    NewsListActivityPresenter mPresenter;
    private RecyclerView newsRecyclerView;
    private ProgressBar pageLoading;
    private NewsListPageableAdapter mNewsListAdapter;
    private List<NewsListModel> mList;
    private int mPage = 0, mPageCount;
    private boolean mIsLoadingNewList = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);

        MyApplication.getInstance().getNewsComponent().inject(this);

        initView();
        onScrollListener();
        showLoading();

        mPresenter.setView(this);
        mPresenter.getNewsList(0, 20);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.stopSubscriptions();
    }

    private void initView() {
        newsRecyclerView = findViewById(R.id.activity_news_list_recycler_view);
        pageLoading = findViewById(R.id.activity_news_list_recycler_view_loading);
    }

    @Override
    public void showLoading() {
        pageLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        pageLoading.setVisibility(View.GONE);
    }

    @Override
    public void showNewsList(PaginationModel<List<NewsListModel>> newsList) {
        mList = newsList.getContent();
        if (mList.size() > 0)
            mList.add(0, new NewsListModel());
        mPageCount = newsList.getTotalPages();

        if (mPageCount > 1) {
            NewsListModel model = new NewsListModel();
            model.setLoading(true);
            mList.add(model);
        }

        mNewsListAdapter = new NewsListPageableAdapter(this, mList,
                position -> loadNewsDetailsPage(mList.get(position).getId()));
        newsRecyclerView.setAdapter(mNewsListAdapter);
        newsRecyclerView.setNestedScrollingEnabled(false);
        newsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        newsRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void updateNewsList(PaginationModel<List<NewsListModel>> newsList) {
        mIsLoadingNewList = false;
        mPage++;
        mList.remove(mList.size() - 1);
        mList.addAll(newsList.getContent());

        if (mPageCount > mPage + 1) {
            NewsListModel model = new NewsListModel();
            model.setLoading(true);
            mList.add(model);
        }

        mNewsListAdapter.notifyDataSetChanged();
    }

    private void onScrollListener() {
        newsRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (mList.size() > 1) {
                    if (((LinearLayoutManager) Objects.requireNonNull(recyclerView.getLayoutManager())).findLastVisibleItemPosition() == mList.size() - 1) {
                        if (!mIsLoadingNewList)
                            if (mPageCount > mPage + 1) {
                                mIsLoadingNewList = true;
                                mPresenter.getNewsList(mPage + 1, 20);
                            }
                    }
                }
            }
        });
    }

    private void loadNewsDetailsPage(final long id) {
        Intent intent = new Intent(this, NewsDetailsActivity.class);
        intent.putExtra(AppConstants.ID, id);
        startActivity(intent);
    }
}