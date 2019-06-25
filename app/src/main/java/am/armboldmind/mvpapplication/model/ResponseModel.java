package am.armboldmind.mvpapplication.model;

import android.os.Parcel;
import android.os.Parcelable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseModel<T> implements Parcelable {
    private Boolean success;
    private T data;
    private String message;

    private ResponseModel(Parcel in) {
        byte tmpSuccess = in.readByte();
        success = tmpSuccess == 0 ? null : tmpSuccess == 1;
        message = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (success == null ? 0 : success ? 1 : 2));
        dest.writeString(message);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ResponseModel> CREATOR = new Creator<ResponseModel>() {
        @Override
        public ResponseModel createFromParcel(Parcel in) {
            return new ResponseModel(in);
        }

        @Override
        public ResponseModel[] newArray(int size) {
            return new ResponseModel[size];
        }
    };
}