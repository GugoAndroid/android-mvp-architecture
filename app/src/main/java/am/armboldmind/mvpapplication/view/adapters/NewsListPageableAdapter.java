package am.armboldmind.mvpapplication.view.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import am.armboldmind.mvpapplication.R;
import am.armboldmind.mvpapplication.model.newsModels.NewsListModel;
import am.armboldmind.mvpapplication.shared.utils.DateTimeUtils;

public class NewsListPageableAdapter extends RecyclerView.Adapter<NewsListPageableAdapter.ViewHolder> {

    private final Context mContext;
    private final List<NewsListModel> mList;
    private final NewsListPageableAdapter.OnItemClickListener mListener;
    private final int HEADER = 0;
    private final int CHILD = 1;

    public NewsListPageableAdapter(Context context, List<NewsListModel> list, NewsListPageableAdapter.OnItemClickListener listener) {
        mContext = context;
        mList = list;
        mListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        if (viewType == HEADER) {
            @SuppressLint("InflateParams") View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_news_list_item_header, null);
            view.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
            return new HeaderViewHolder(view);
        } else {
            @SuppressLint("InflateParams") View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_news_list_item_child, null);
            view.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
            return new ChildViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        if (getItemViewType(position) == CHILD)
            onChildBindViewHolder((ChildViewHolder) viewHolder, position);
    }

    private void onChildBindViewHolder(ChildViewHolder holder, int position) {
        NewsListModel listItemModel = mList.get(position);

        if (!listItemModel.isLoading()) {
            holder.parentLayout.setVisibility(View.VISIBLE);
            holder.loading.setVisibility(View.GONE);

            holder.onClick(holder.itemView, position);

            if (listItemModel.getImageUrl() != null) {
                RequestOptions options = new RequestOptions()
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .error(R.color.no_image_color)
                        .priority(Priority.HIGH);
                Glide.with(mContext)
                        .load(listItemModel.getImageUrl())
                        .apply(options)
                        .into(holder.photo);
            } else {
                holder.photo.setImageDrawable(null);
                holder.photo.setBackgroundColor(mContext.getResources().getColor(R.color.no_image_color));
            }

            holder.title.setText(listItemModel.getTitle());
            holder.date.setText(DateTimeUtils.convertServerDate(listItemModel.getCreatedDate(), DateTimeUtils.SERVER_DATE_PATTERN, DateTimeUtils.DATE_PATTERN_DAY_MONTH_YEAR));
        } else {
            holder.parentLayout.setVisibility(View.GONE);
            holder.loading.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        if (mList != null)
            return mList.size();
        else
            return 0;
    }

    @Override
    public int getItemViewType(int position) {
        return (position == 0) ? HEADER : CHILD;
    }

    public interface OnItemClickListener {
        void onClick(final int position);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    class ChildViewHolder extends ViewHolder {
        private final FrameLayout parentLayout;
        private final ImageView photo;
        private final TextView title, date;
        private final ProgressBar loading;

        ChildViewHolder(View itemView) {
            super(itemView);
            parentLayout = itemView.findViewById(R.id.adapter_news_list_item_parent_layout);
            loading = itemView.findViewById(R.id.adapter_news_list_item_layout_loading);
            photo = itemView.findViewById(R.id.adapter_news_list_item_photo);
            photo.setBackground(null);
            title = itemView.findViewById(R.id.adapter_news_list_item_title);
            title.setBackground(null);
            date = itemView.findViewById(R.id.adapter_news_list_item_date);
            date.setBackground(null);
        }

        void onClick(final View itemView, final int position) {
            itemView.setOnClickListener(v -> mListener.onClick(position));
        }
    }

    class HeaderViewHolder extends ViewHolder {
        HeaderViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}