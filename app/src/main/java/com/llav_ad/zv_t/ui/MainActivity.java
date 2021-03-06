package com.llav_ad.zv_t.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;
import android.view.View;

import com.llav_ad.zv_t.MyApplication;
import com.llav_ad.zv_t.R;

public class MainActivity extends AppCompatActivity {

    View navFragment;
    NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        setContentView(R.layout.activity_main);

        navFragment = findViewById(R.id.fragment);
        navController = Navigation.findNavController(navFragment);

        MyApplication.instance.getDatabase().userDao().getUserData().observe(this, userInfo -> {

            if (userInfo == null) return;
            if (userInfo.webViewUrl != null && !userInfo.webViewUrl.equals("")) {
                if (navController.getCurrentDestination().getId() == R.id.webViewFragment) { return; }

                Bundle bundle = new Bundle();
                bundle.putString("url", userInfo.webViewUrl);
                bundle.putString("ip", userInfo.ip);
                navController.navigate(R.id.action_launchFragment_to_webViewFragment, bundle);

            } else {
                if (userInfo.login == null && userInfo.pass == null) {
                    navController.navigate(R.id.action_launchFragment_to_loginFragment);
                } else if (!userInfo.auth && navController.getCurrentDestination().getId() == R.id.launchFragment) {
                    navController.navigate(R.id.action_launchFragment_to_loginFragment);
                } else if (userInfo.auth && navController.getCurrentDestination().getId() == R.id.launchFragment) {
                    navController.navigate(R.id.action_launchFragment_to_mainFragment);
                }
            }
        });
    }

    public void onBackPressed() {

        boolean goBack = true;

        if (navController.getCurrentDestination().getId() == R.id.webViewFragment) {
            NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
            assert navHostFragment != null;
            CustomBackPress f = (CustomBackPress) navHostFragment.getChildFragmentManager().getFragments().get(0);
            goBack = f.pressBack();
        }

        if (goBack) {
            super.onBackPressed();
        }
    }
}
