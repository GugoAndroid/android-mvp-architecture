package am.armboldmind.mvpapplication.view.activities.signInActivity.fragments;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import javax.inject.Inject;

import am.armboldmind.mvpapplication.MyApplication;
import am.armboldmind.mvpapplication.R;
import am.armboldmind.mvpapplication.model.authorizationModels.GuestModel;
import am.armboldmind.mvpapplication.model.authorizationModels.SignInRequestModel;
import am.armboldmind.mvpapplication.presenter.authorization.SignInFragmentPresenter;
import am.armboldmind.mvpapplication.shared.utils.CommonUtils;
import am.armboldmind.mvpapplication.view.activities.newsListActivity.NewsListActivity;
import am.armboldmind.mvpapplication.view.root.BaseFragment;

public class SignInFragment extends BaseFragment implements ISignInFragmentView, View.OnClickListener {

    private AppCompatActivity mActivity;
    @Inject
    SignInFragmentPresenter mPresenter;
    private SignInRequestModel mSignInRequestModel;
    private GuestModel mGuestModel;
    private EditText username, password;
    private TextView signInButton, guestButton;
    private ProgressBar signInButtonLoading, guestButtonLoading;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);

        if (getActivity() != null)
            mActivity = (AppCompatActivity) getActivity();

        mSignInRequestModel = new SignInRequestModel();
        mGuestModel = new GuestModel();

        MyApplication.getInstance().getAuthorizationComponent().inject(this);
        mPresenter.setView(this);

        setGuestModel();
        initView(view);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.stopSubscriptions();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sign_in_login_button:
                boolean isValid = true;

                if (password.getText().toString().isEmpty()) {
                    isValid = false;
                    showViewError(password);
                }

                if (username.getText().toString().isEmpty()) {
                    isValid = false;
                    showViewError(username);
                }

                if (isValid) {
                    showButtonLoading();
                    mSignInRequestModel.setUsername(username.getText().toString().trim());
                    mSignInRequestModel.setPassword(password.getText().toString().trim());
                    mPresenter.signIn(mSignInRequestModel);
                }
                break;
            case R.id.sign_in_guest_button:
                showGuestButtonLoading();
                mPresenter.signUpAsGuest(mGuestModel);
                break;
        }
    }


    private void setGuestModel() {
        mGuestModel.setOsTypeId(1);
        mGuestModel.setDeviceToken("cW05tWv5vlI:APA91bFFymMKfFkyyxAA25YZoSF7d5zxcAsyqFA1dG-3gfyyXkPiB3khMujF6yGJM1dKBleCoPYx4HwAMYTsKCOVNrnfqWAcPZfdpqgg16TOY9-AcqmCHmaLc9pkDnHhByO7h4WP-gLN");
        mGuestModel.setModelName(String.format("%s %s", Build.MANUFACTURER, Build.MODEL));
        mGuestModel.setLanguageName("en");
        mGuestModel.setDeviceId(CommonUtils.getDeviceId(mActivity));
    }

    private void initView(View view) {
        username = view.findViewById(R.id.sign_in_login);
        password = view.findViewById(R.id.sign_in_password);

        signInButton = view.findViewById(R.id.sign_in_login_button);
        signInButton.setOnClickListener(this);
        signInButtonLoading = view.findViewById(R.id.sign_in_login_button_loading);
        signInButtonLoading.getIndeterminateDrawable().setColorFilter(getResources().getColor(R.color.white_color), PorterDuff.Mode.SRC_ATOP);

        guestButton = view.findViewById(R.id.sign_in_guest_button);
        guestButton.setOnClickListener(this);
        guestButtonLoading = view.findViewById(R.id.sign_in_guest_button_loading);
        guestButtonLoading.getIndeterminateDrawable().setColorFilter(getResources().getColor(R.color.main_red_color), PorterDuff.Mode.SRC_ATOP);
    }

    private void showViewError(EditText editText) {
        editText.requestFocus();
        editText.setHintTextColor(getResources().getColor(R.color.main_red_color));
    }

    private void showButtonLoading() {
        signInButton.setVisibility(View.INVISIBLE);
        signInButtonLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideButtonLoading() {
        signInButtonLoading.setVisibility(View.INVISIBLE);
        signInButton.setVisibility(View.VISIBLE);
    }

    private void showGuestButtonLoading() {
        guestButton.setVisibility(View.INVISIBLE);
        guestButtonLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideGuestButtonLoading() {
        guestButtonLoading.setVisibility(View.INVISIBLE);
        guestButton.setVisibility(View.VISIBLE);
    }

    @Override
    public void onToast(String errorMessage) {
        super.onToast(errorMessage);
        guestButton.setEnabled(true);
    }

    @Override
    public void signIn() {
        loadHomePage();
    }

    private void loadHomePage() {
        startActivity(new Intent(mActivity, NewsListActivity.class));
        mActivity.finish();
    }
}