package am.armboldmind.mvpapplication.view.root;

public interface IBaseView {
    void onToast(String message);

    void onSnackBar(String message);

    void showServerError();

    void showNetworkError();
}