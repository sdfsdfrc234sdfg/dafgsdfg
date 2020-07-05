package com.llav_ad.zv_t.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.llav_ad.zv_t.MyApplication;
import com.llav_ad.zv_t.R;
import com.llav_ad.zv_t.databinding.FragmentProfileBinding;
import com.llav_ad.zv_t.ent.SearchItem;

import java.util.List;


public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    List<SearchItem> list = MyApplication.getInstance().getAppData().searchList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        int itemId = getArguments().getInt("id");
        SearchItem item = list.get(itemId);

        binding.backArrow14.setOnClickListener(view1 -> {
            getActivity().onBackPressed();
        });

        binding.iconImg.setClipToOutline(true);

        binding.iconImg.setImageResource(item.getImg());
        binding.name.setText(item.getName() +" "+ item.getAge());
        binding.place.setText(item.getCity());
        binding.searchText.setText(item.getSearch());
        binding.getToKnowText.setText(item.getGetToKnow());
        binding.purposeOfDatingText.setText(item.getPurposeOfDating());
        binding.myDetailsText.setText(
                view.getResources().getString(R.string.height)+ " "+item.getHeight()+"\n"+
                        view.getResources().getString(R.string.weight)+ " "+item.getWeight()
        );
        binding.bodyTypeText.setText(item.getBodyType());
        binding.appearanceText.setText(item.getAppearance());
        binding.educationText.setText(item.getEducation());
        binding.knowledgeOfLanguagesText.setText(item.getKnowledgeOfLanguages());
        binding.orientationText.setText(item.getOrientation());
        binding.attitudeTowardsSmokingText.setText(item.getAttitudeTowardsSmoking());
        binding.attitudeToAlcoholText.setText(item.getAttitudeToAlcohol());
        binding.liveStateText.setText(item.getLiveState());
        binding.shildrenText.setText(item.getChildren());
        binding.moneyStateText.setText(item.getMoneyState());
    }
}