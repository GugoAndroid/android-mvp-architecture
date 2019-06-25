package am.armboldmind.mvpapplication.model.newsModels;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewsListModel {
    private long id;
    private String imageUrl;
    private String title;
    private String createdDate;
    private boolean loading;
}