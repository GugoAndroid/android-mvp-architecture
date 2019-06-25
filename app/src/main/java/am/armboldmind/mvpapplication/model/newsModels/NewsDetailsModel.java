package am.armboldmind.mvpapplication.model.newsModels;

import java.util.ArrayList;

import am.armboldmind.mvpapplication.model.MediaFileModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewsDetailsModel {
    private long id;
    private ArrayList<MediaFileModel> imageUrlList;
    private String title;
    private String description;
    private String createdDate;
}