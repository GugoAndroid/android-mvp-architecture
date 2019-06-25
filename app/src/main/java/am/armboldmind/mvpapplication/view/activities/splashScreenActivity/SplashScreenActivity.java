package am.armboldmind.mvpapplication.view.activities.splashScreenActivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import am.armboldmind.mvpapplication.view.activities.signInActivity.SignInActivity;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loadSplashPage();
    }

    private void loadSplashPage() {
        Intent intent = new Intent(this, SignInActivity.class);
        startActivity(intent);
        finish();
    }
}