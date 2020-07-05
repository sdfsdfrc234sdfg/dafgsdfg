package com.llav_ad.zv_t.ui.main_scr;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.llav_ad.zv_t.MyApplication;
import com.llav_ad.zv_t.dbNet.database.UserDao;
import com.llav_ad.zv_t.databinding.FragmentOwnProfileBinding;
import com.llav_ad.zv_t.ent.dbData.ProfileInfo;
import com.llav_ad.zv_t.other_files.DbRequest;

import java.util.Objects;


public class ProfileFragment extends Fragment {

    private FragmentOwnProfileBinding binding;
    private UserDao userDao = MyApplication.getInstance().getDatabase().userDao();
    private ProfileInfo profileInfo;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentOwnProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.iconImg.setClipToOutline(true);

        binding.addPhoto.setOnClickListener(addPhoto -> {
            if (!checkPermission()) {
                uploadImg();
            }
        });


            userDao.getUserData().observe(this.getViewLifecycleOwner(), userInfo -> {
                binding.name.setText(userInfo.login);
            });

            userDao.getProfileInfo().observe(this.getViewLifecycleOwner(), profileInfo -> {

                this.profileInfo = profileInfo;

                if (profileInfo.getImg() != null) {

                    binding.iconImg.setImageURI(Uri.parse(profileInfo.getImg()));
                }


                binding.place.setText(profileInfo.getCity());
                binding.appearanceText.setText(profileInfo.getAppearance());
                binding.attitudeToAlcoholText.setText(profileInfo.getAttitudeToAlcohol());
                binding.getToKnowText.setText(profileInfo.getGetToKnow());
                binding.purposeOfDatingText.setText(profileInfo.getPurposeOfDating());
                binding.bodyTypeText.setText(profileInfo.getBodyType());
                binding.educationText.setText(profileInfo.getEducation());
                binding.knowledgeOfLanguagesText.setText(profileInfo.getKnowledgeOfLanguages());
                binding.orientationText.setText(profileInfo.getOrientation());
                binding.attitudeTowardsSmokingText.setText(profileInfo.getAttitudeTowardsSmoking());

                binding.weightText.setText(profileInfo.getWeight());
                binding.heightText.setText(profileInfo.getHeight());


            });

            binding.button.setOnClickListener(button -> {

                profileInfo.setCity(binding.place.getText().toString());
                profileInfo.setAppearance(binding.appearanceText.getText().toString());
                profileInfo.setAttitudeToAlcohol(binding.attitudeToAlcoholText.getText().toString());
                profileInfo.setGetToKnow(binding.getToKnowText.getText().toString());
                profileInfo.setPurposeOfDating(binding.purposeOfDatingText.getText().toString());
                profileInfo.setBodyType(binding.bodyTypeText.getText().toString());
                profileInfo.setEducation(binding.educationText.getText().toString());
                profileInfo.setKnowledgeOfLanguages(binding.knowledgeOfLanguagesText.getText().toString());
                profileInfo.setOrientation(binding.orientationText.getText().toString());
                profileInfo.setAttitudeTowardsSmoking(binding.attitudeTowardsSmokingText.getText().toString());

                profileInfo.setWeight(binding.weightText.getText().toString());
                profileInfo.setHeight(binding.heightText.getText().toString());

                DbRequest.updateProfileInfo(profileInfo);
            });

    }


    void uploadImg() {


        if (!checkPermission()) {
            requestPermission();
        } else {
            Intent intent = new Intent(
                    Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            );
            startActivityForResult(intent, 1);
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == Activity.RESULT_OK && data != null) {
            try {
                binding.iconImg.setImageURI(data.getData());
                profileInfo.setImg(Objects.requireNonNull(data.getData()).toString());
                DbRequest.updateProfileInfo(profileInfo);
            } catch (Exception e) {

            }
        }
    }

    private boolean checkPermission() {
        if (ContextCompat.checkSelfPermission(Objects.requireNonNull(getActivity()),
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    private void requestPermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(Objects.requireNonNull(getActivity()), android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            Toast.makeText(getActivity(), "Write External Storage permission allows us to do store images. Please allow this permission in App Settings.", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 2:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.e("value", "Permission Granted, Now you can use local drive .");
                } else {
                    Log.e("value", "Permission Denied, You cannot use local drive .");
                }
                break;
        }
    }
}
