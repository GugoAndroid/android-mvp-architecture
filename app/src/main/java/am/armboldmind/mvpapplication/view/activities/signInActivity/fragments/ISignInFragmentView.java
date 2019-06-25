package am.armboldmind.mvpapplication.view.activities.signInActivity.fragments;

import am.armboldmind.mvpapplication.view.root.IBaseView;

public interface ISignInFragmentView extends IBaseView {
    void hideButtonLoading();

    void hideGuestButtonLoading();

    void signIn();
}