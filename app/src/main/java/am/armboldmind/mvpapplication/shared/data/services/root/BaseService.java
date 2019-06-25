package am.armboldmind.mvpapplication.shared.data.services.root;

import am.armboldmind.mvpapplication.model.ResponseModel;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public abstract class BaseService {

    protected <T> Flowable<ResponseModel<T>> request(Flowable<ResponseModel<T>> call) {
        return call.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    protected <T> Flowable<T> requestSignIn(Flowable<T> call) {
        return call.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}