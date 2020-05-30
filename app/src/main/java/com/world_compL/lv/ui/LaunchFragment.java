package com.world_compL.lv.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.world_compL.lv.MyApplication;
import com.world_compL.lv.R;
import com.world_compL.lv.databinding.FragmentLaunchBinding;
import com.world_compL.lv.ent.dbData.ProfileInfo;
import com.world_compL.lv.ent.dbData.UserInfo;
import com.world_compL.lv.ent.sev_resp.ServerResponseError;
import com.world_compL.lv.ent.sev_resp.ServerResponseLoading;
import com.world_compL.lv.ent.sev_resp.ServerResponseSuccess;
import com.world_compL.lv.other_files.DbRequest;


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


        MyApplication.instance.getDatabase().userDao().getUserData().observe(this.getViewLifecycleOwner(), userInfo -> {
            if (userInfo == null) {
                createRequest();
            }
        });
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
                    userInfo = new UserInfo(null, false, null);
                } else{
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
