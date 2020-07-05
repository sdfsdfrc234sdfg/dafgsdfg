package com.llav_ad.zv_t.ui.authorization;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.llav_ad.zv_t.R;
import com.llav_ad.zv_t.databinding.FragmentRegisterBinding;
import com.llav_ad.zv_t.ent.dbData.UserInfo;
import com.llav_ad.zv_t.other_files.DbRequest;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;


public class RegisterFragment extends Fragment {

    private FragmentRegisterBinding binding;
    private final Calendar myCalendar = Calendar.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRegisterBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.backArrow.setOnClickListener(view1 -> Objects.requireNonNull(getActivity()).onBackPressed());

        binding.gerButton.setOnClickListener(view1 -> {
            updateUserInfo();
        });

        binding.birthday.setOnClickListener(viewB -> {
            dataPiker();
        });

    }


    public void updateUserInfo() {

        String login = binding.regLogin.getText().toString();
        String pass = binding.regPass.getText().toString();
        String email = binding.regMail.getText().toString();
        String phone = binding.regPhone.getText().toString();
        String gender = binding.spinner.getSelectedItem().toString();
        String age = binding.birthday.getText().toString();

        if (validateField(login) && validateField(email) && validateField(pass)) {
            DbRequest.update(new UserInfo(0, login, pass, email, phone, gender, age,true));
            Navigation.findNavController(getView()).navigate(R.id.action_registerFragment_to_mainFragment);
        } else {
            message();
        }
    }

    private Boolean validateField(String text) {
        if (text.equals("")) return false;
        else return true;
    }

    private void message() {
        Toast.makeText(this.getContext(), R.string.form_error, Toast.LENGTH_LONG).show();
    }


    private void dataPiker() {


        DatePickerDialog.OnDateSetListener date = (view, year, monthOfYear, dayOfMonth) -> {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        };


        DatePickerDialog dialog = new DatePickerDialog(Objects.requireNonNull(getContext()), android.R.style.Theme_Holo_Light_Dialog, date, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH));

        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    private void updateLabel() {
        String myFormat = "dd/MM/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        binding.birthday.setText(sdf.format(myCalendar.getTime()));
    }
}
