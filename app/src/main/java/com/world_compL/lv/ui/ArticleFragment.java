package com.world_compL.lv.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.world_compL.lv.MyApplication;
import com.world_compL.lv.databinding.FragmentArticleBinding;
import com.world_compL.lv.ent.ArtItem;

import java.util.List;


public class ArticleFragment extends Fragment {

    private FragmentArticleBinding binding;
    private List<ArtItem> items = MyApplication.getInstance().getAppData().articleList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentArticleBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        int itemId = getArguments().getInt("id");
        ArtItem item = items.get(itemId);

        binding.backArrow124.setOnClickListener(view1 -> {

            getActivity().onBackPressed();
        });

        binding.articleImg.setImageResource(item.getImage());
        binding.articleImg.setClipToOutline(true);
        binding.titleText.setText(item.getTitle());
        binding.text.setText(item.getText());
    }
}
