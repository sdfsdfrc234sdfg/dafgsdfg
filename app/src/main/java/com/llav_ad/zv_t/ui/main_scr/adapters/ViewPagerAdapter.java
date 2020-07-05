package com.llav_ad.zv_t.ui.main_scr.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.llav_ad.zv_t.ui.SettingsFragment;
import com.llav_ad.zv_t.ui.main_scr.ArticlesFragment;
import com.llav_ad.zv_t.ui.main_scr.ProfileFragment;
import com.llav_ad.zv_t.ui.main_scr.SearchFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {

    public ViewPagerAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position) {
            case 0:
                return new SearchFragment();
            case 1:
                return new ProfileFragment();
            case 2:
                return new ArticlesFragment();
            case 3:
                return new SettingsFragment();
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
