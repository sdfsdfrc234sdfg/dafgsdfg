package com.lala.love.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.lala.love.App;
import com.lala.love.R;

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

        App.instance.getDatabase().userDao().getUserData().observe(this, userInfo -> {

            if (userInfo == null) return;
            if (userInfo.webViewUrl != null && !userInfo.webViewUrl.equals("")) {
                if (navController.getCurrentDestination().getId() == R.id.webViewFragment) { return; }
                Bundle bundle = new Bundle();
                Log.d("JLKDSHGLS", "url: " + userInfo.webViewUrl);
                bundle.putString("url", userInfo.webViewUrl);
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
}
