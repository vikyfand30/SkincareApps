package com.jefaskincare.mobile.android.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.jefaskincare.mobile.android.MainActivity;
import com.jefaskincare.mobile.android.R;
import com.jefaskincare.mobile.android.activities.transfer.bank.TransferBankSuccessActivity;
import com.jefaskincare.mobile.android.fragment.profile.Model.User;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileDetailEditActivity extends AppCompatActivity {

    @BindView(R.id.ivBackEditDataDiri)
    ImageView ivBack;

    @BindView(R.id.etEmailDataDiri)
    EditText etEmail;

    @BindView(R.id.etPhoneDataDiri)
    EditText etPhone;

    @BindView(R.id.etNamaPenerimaDataDiri)
    EditText etNamaPenerima;

    @BindView(R.id.spinnerDataDiri)
    Spinner sp;

    @BindView(R.id.etDateDataDiri)
    EditText etDate;

    @BindView(R.id.btnSaveDataDiri)
    Button btnSaveDataDiri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_detail_edit);
        ButterKnife.bind(this);

        init();



    }

    private void init() {
        User user = (User) getIntent().getSerializableExtra("user");

        etEmail.setText(user.getEmail());

        etNamaPenerima.setText(user.getAddressname());

        etPhone.setText(user.getAddressphone());

        ivBack.setOnClickListener(view -> super.onBackPressed());

        btnSaveDataDiri.setOnClickListener(view -> {
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
            finish();
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
