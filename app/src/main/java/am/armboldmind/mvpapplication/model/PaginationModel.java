package am.armboldmind.mvpapplication.model;

import android.os.Parcel;
import android.os.Parcelable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaginationModel<T> implements Parcelable {
    private int totalPages;
    private T content;

    public PaginationModel(){}

    private PaginationModel(Parcel in) {
        totalPages = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(totalPages);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PaginationModel> CREATOR = new Creator<PaginationModel>() {
        @Override
        public PaginationModel createFromParcel(Parcel in) {
            return new PaginationModel(in);
        }

        @Override
        public PaginationModel[] newArray(int size) {
            return new PaginationModel[size];
        }
    };
}