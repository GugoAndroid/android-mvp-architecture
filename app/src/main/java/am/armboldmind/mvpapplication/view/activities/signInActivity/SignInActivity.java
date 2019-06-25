package am.armboldmind.mvpapplication.view.activities.signInActivity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import am.armboldmind.mvpapplication.R;
import am.armboldmind.mvpapplication.shared.utils.AppConstants;
import am.armboldmind.mvpapplication.view.activities.signInActivity.fragments.SignInFragment;
import am.armboldmind.mvpapplication.view.root.BaseActivity;

public class SignInActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        loadSignInFragment();
    }

    private void loadSignInFragment() {
        Fragment mFragment = new SignInFragment();
        FragmentManager mFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.replace(R.id.activity_sign_in_fragment, mFragment, AppConstants.SIGN_IN_FRAGMENT);
        fragmentTransaction.commit();
    }
}