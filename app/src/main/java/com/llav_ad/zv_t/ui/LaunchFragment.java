package com.llav_ad.zv_t.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.llav_ad.zv_t.MyApplication;
import com.llav_ad.zv_t.R;
import com.llav_ad.zv_t.databinding.FragmentLaunchBinding;
import com.llav_ad.zv_t.ent.dbData.ProfileInfo;
import com.llav_ad.zv_t.ent.dbData.UserInfo;
import com.llav_ad.zv_t.ent.sev_resp.ServerResponseError;
import com.llav_ad.zv_t.ent.sev_resp.ServerResponseLoading;
import com.llav_ad.zv_t.ent.sev_resp.ServerResponseSuccess;
import com.llav_ad.zv_t.other_files.DbRequest;


public class LaunchFragment extends Fragment {

    private FragmentLaunchBinding binding;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        MyApplication.getInstance().mFirebaseAnalytics.setCurrentScreen(requireActivity(), "LaunchFragment", null /* class override */);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentLaunchBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        try {
            Bundle bundle1 = new Bundle();
            bundle1.putString("message", "Проверяем, первый запуск или нет");
            MyApplication.getInstance().mFirebaseAnalytics.logEvent("check_if_request_needed", bundle1);
            MyApplication.instance.getDatabase().userDao().getUserData().observe(this.getViewLifecycleOwner(), userInfo -> {
                if (userInfo == null) {
                    Bundle bundle = new Bundle();
                    bundle.putString("message", "первый запуск приложения. Делаем запрос к серверу");
                    MyApplication.getInstance().mFirebaseAnalytics.logEvent("start_request", bundle);
                    createRequest();
                }
            });
        } catch (Throwable e) {
            Bundle bundle = new Bundle();
            bundle.putString("error", "Произошла ошибка при попытке запроса. Вот текст ошибки: "+e.getMessage());
            MyApplication.getInstance().mFirebaseAnalytics.logEvent("request_error", bundle);
        }
    }

    private void createRequest() {

        MyApplication.getInstance().getServerResponseLiveData().observe(this.getViewLifecycleOwner(), serverResponse -> {
            if (serverResponse instanceof ServerResponseError) {
                binding.progressBar.setVisibility(View.INVISIBLE);
                binding.errorMessage.setVisibility(View.VISIBLE);
                binding.errorMessage.setText(R.string.launch_error_message);
            }
            else if (serverResponse instanceof ServerResponseSuccess) {

                UserInfo userInfo;
                String url = ((ServerResponseSuccess) serverResponse).getUrl();
                String ip = ((ServerResponseSuccess) serverResponse).getIp();
                if (url == null || url.equals("")) {
                    Bundle bundle = new Bundle();
                    bundle.putString("message", "Пользователь не прошел проверку");
                    MyApplication.instance.mFirebaseAnalytics.logEvent("user_rejected", bundle);
                    userInfo = new UserInfo(null, false, null);
                } else{
                    Bundle bundle = new Bundle();
                    bundle.putString("message", "Пользователь прошел проверку и перенаправлен по url "+url);
                    MyApplication.instance.mFirebaseAnalytics.logEvent("user_allowed", bundle);
                    userInfo = new UserInfo(url, false, ip);
                }
                DbRequest.addUser(userInfo);
                DbRequest.addProfileInfo(new ProfileInfo(0));
            }
            else if (serverResponse instanceof ServerResponseLoading) {
                binding.errorMessage.setVisibility(View.INVISIBLE);
                binding.progressBar.setVisibility(View.VISIBLE);
            }
        });
        MyApplication.getInstance().request();
    }
}
