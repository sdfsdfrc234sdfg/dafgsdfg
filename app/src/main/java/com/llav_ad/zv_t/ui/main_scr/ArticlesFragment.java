package com.llav_ad.zv_t.ui.main_scr;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.llav_ad.zv_t.MyApplication;
import com.llav_ad.zv_t.R;
import com.llav_ad.zv_t.databinding.FragmentArticlesBinding;
import com.llav_ad.zv_t.ui.main_scr.adapters.ArticlesRvAdapter;
import com.llav_ad.zv_t.other_files.AppData;


public class ArticlesFragment extends Fragment implements ArticlesRvAdapter.Interactor {

    private FragmentArticlesBinding binding;
    private AppData appData = MyApplication.getInstance().getAppData();
    private ArticlesRvAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentArticlesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (adapter == null) {
            adapter = new ArticlesRvAdapter(appData.articleList, this);
        }
        binding.articleRv.setAdapter(adapter);
        binding.articleRv.setLayoutManager(new LinearLayoutManager(this.getContext()));

    }

    @Override
    public void interact(int id) {
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        Navigation.findNavController(getView()).navigate(R.id.action_mainFragment_to_articleFragment, bundle);
    }
}
