package com.lala.love.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lala.love.App;
import com.lala.love.R;
import com.lala.love.databinding.FragmentLaunchBinding;
import com.lala.love.entities.dbData.ProfileInfo;
import com.lala.love.entities.dbData.UserInfo;
import com.lala.love.entities.server_response.ServerResponseError;
import com.lala.love.entities.server_response.ServerResponseLoading;
import com.lala.love.entities.server_response.ServerResponseSuccess;
import com.lala.love.util.DbRequest;


public class LaunchFragment extends Fragment {

    private FragmentLaunchBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentLaunchBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        App.instance.getDatabase().userDao().getUserData().observe(this.getViewLifecycleOwner(), userInfo -> {
            if (userInfo == null) {
                createRequest();
            }
        });
    }

    private void createRequest() {

        App.getInstance().getServerResponseLiveData().observe(this.getViewLifecycleOwner(), serverResponse -> {
            if (serverResponse instanceof ServerResponseError) {
                binding.progressBar.setVisibility(View.INVISIBLE);
                binding.errorMessage.setVisibility(View.VISIBLE);
                binding.errorMessage.setText(R.string.launch_error_message);
            }
            else if (serverResponse instanceof ServerResponseSuccess) {

                UserInfo userInfo;
                String url = ((ServerResponseSuccess) serverResponse).getUrl();
                if (url == null || url.equals("")) {
                    userInfo = new UserInfo(null, false);
                } else{
                    userInfo = new UserInfo(url, false);
                }
                DbRequest.addUser(userInfo);
                DbRequest.addProfileInfo(new ProfileInfo(0));
            }
            else if (serverResponse instanceof ServerResponseLoading) {
                binding.errorMessage.setVisibility(View.INVISIBLE);
                binding.progressBar.setVisibility(View.VISIBLE);
            }
        });
        App.getInstance().request();
    }
}
