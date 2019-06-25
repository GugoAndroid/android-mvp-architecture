package am.armboldmind.mvpapplication.view.activities.newsDetailsActivity;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import javax.inject.Inject;

import am.armboldmind.mvpapplication.MyApplication;
import am.armboldmind.mvpapplication.R;
import am.armboldmind.mvpapplication.model.newsModels.NewsDetailsModel;
import am.armboldmind.mvpapplication.presenter.news.NewsDetailsActivityPresenter;
import am.armboldmind.mvpapplication.shared.utils.AppConstants;
import am.armboldmind.mvpapplication.shared.utils.DateTimeUtils;
import am.armboldmind.mvpapplication.view.root.BaseActivity;

public class NewsDetailsActivity extends BaseActivity implements INewsDetailsActivityView {

    @Inject
    NewsDetailsActivityPresenter mPresenter;
    private long mId = 0;
    private TextView title, data, description;
    private ProgressBar pageLoading;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);

        MyApplication.getInstance().getNewsComponent().inject(this);

        if (getIntent().getExtras() != null) {
            mId = getIntent().getExtras().getLong(AppConstants.ID);
        }

        initView();
        showLoading();

        mPresenter.setView(this);
        mPresenter.getNewsDetails(mId);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.stopSubscriptions();
    }

    private void initView() {
        title = findViewById(R.id.activity_news_details_text);
        data = findViewById(R.id.activity_news_details_date);
        description = findViewById(R.id.activity_news_details_description);
        pageLoading = findViewById(R.id.activity_news_details_loading);
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
    public void showNewsDetails(NewsDetailsModel newsDetailsModel) {
        title.setText(newsDetailsModel.getTitle());
        data.setText(DateTimeUtils.convertServerDate(newsDetailsModel.getCreatedDate(), DateTimeUtils.SERVER_DATE_PATTERN, DateTimeUtils.DATE_PATTERN_DAY_MONTH_YEAR));
        description.setText(Html.fromHtml(newsDetailsModel.getDescription()));
    }
}