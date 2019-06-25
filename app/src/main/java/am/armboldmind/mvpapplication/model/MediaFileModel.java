package am.armboldmind.mvpapplication.model;

import android.os.Parcel;
import android.os.Parcelable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MediaFileModel implements Parcelable {
    private long id;
    private String videoPath;
    private String imagePath;
    private int mediaType;

    public MediaFileModel()
    {}

    private MediaFileModel(Parcel in) {
        id = in.readLong();
        videoPath = in.readString();
        imagePath = in.readString();
        mediaType = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(videoPath);
        dest.writeString(imagePath);
        dest.writeInt(mediaType);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MediaFileModel> CREATOR = new Creator<MediaFileModel>() {
        @Override
        public MediaFileModel createFromParcel(Parcel in) {
            return new MediaFileModel(in);
        }

        @Override
        public MediaFileModel[] newArray(int size) {
            return new MediaFileModel[size];
        }
    };
}