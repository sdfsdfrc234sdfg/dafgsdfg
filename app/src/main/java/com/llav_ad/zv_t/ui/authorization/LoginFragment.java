package com.llav_ad.zv_t.ui.authorization;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.llav_ad.zv_t.MyApplication;
import com.llav_ad.zv_t.R;
import com.llav_ad.zv_t.databinding.FragmentLoginBinding;
import com.llav_ad.zv_t.ent.dbData.UserInfo;
import com.llav_ad.zv_t.other_files.DbRequest;


public class LoginFragment extends Fragment {


    private FragmentLoginBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        MyApplication.getInstance().getDatabase().userDao().getUserData().observe(this.getViewLifecycleOwner(), userInfo -> {

            if (userInfo == null) return;
            binding.loginButton.setOnClickListener(view1 -> {
                String loginText = binding.loginEditText.getText().toString();
                String passText = binding.regPass.getText().toString();
                logIn(loginText, passText, userInfo);
            });
        });


        binding.regTextView.setOnClickListener(view12 ->
                Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_registerFragment));
        binding.recoveryText.setOnClickListener(view13 ->
                Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_passwordRecoveryFragment));

        this.getActivity().getWindow().setStatusBarColor(Color.parseColor("#ffd625"));
    }


    private void logIn(String login, String pass, UserInfo userInfo) {

        if (userInfo.pass == null || userInfo.pass.equals("") || userInfo.login == null || userInfo.login.equals("")) {
            message();
            return;
        }

        if (userInfo.pass.equals(pass) && userInfo.login.equals(login)) {
            userInfo.auth = true;
            DbRequest.update(userInfo);
            Navigation.findNavController(getView()).navigate(R.id.action_loginFragment_to_mainFragment);
        } else {
            message();
        }
    }

    private void message() {
        Toast.makeText(this.getContext(), "user does not exist", Toast.LENGTH_LONG).show();
    }
}