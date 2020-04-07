package com.lala.love.ui.main_scr;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lala.love.App;
import com.lala.love.R;
import com.lala.love.databinding.FragmentMainBinding;
import com.lala.love.ui.main_scr.adapters.ViewPagerAdapter;
import com.lala.love.util.DbRequest;


public class MainFragment extends Fragment {

    private FragmentMainBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentMainBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.getActivity().getWindow().setStatusBarColor(Color.WHITE);

        binding.viewPager.setAdapter(new ViewPagerAdapter(this));
        binding.viewPager.setUserInputEnabled(false);
        binding.viewPager.setOffscreenPageLimit(4);

        binding.backForm.setOnClickListener(form -> {
            Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_settingsFragment);
        });

        binding.bottomNavigationView.setOnNavigationItemSelectedListener(
                item -> {
                    switch (item.getItemId()) {
                        case R.id.action_search:
                            binding.viewPager.setCurrentItem(0);
                            break;
                        case R.id.action_profile:
                            binding.viewPager.setCurrentItem(1);
                            break;
                        case R.id.action_articles:
                            binding.viewPager.setCurrentItem(2);
                            break;
                        case R.id.action_settings:
                            binding.viewPager.setCurrentItem(3);
                            break;
                    }
                    return true;
                });

        binding.exitIcon.setOnClickListener(view4 -> {
            logOut();
        });
    }


    private void logOut() {
        App.getInstance().getDatabase().userDao().getUserData().observe(this.getViewLifecycleOwner(), userInfo -> {
            if (userInfo == null) return;
            userInfo.auth = false;
            DbRequest.update(userInfo);
            Navigation.findNavController(getView()).navigate(R.id.log_out);
        });
    }
}
