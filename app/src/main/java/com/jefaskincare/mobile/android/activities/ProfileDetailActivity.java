package com.jefaskincare.mobile.android.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.jefaskincare.mobile.android.R;
import com.jefaskincare.mobile.android.api.Parsing;
import com.jefaskincare.mobile.android.fragment.profile.Model.User;
import com.jefaskincare.mobile.android.manager.Session;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileDetailActivity extends AppCompatActivity {

    @BindView(R.id.ivBackDataDiri)
    ImageView ivBack;

    @BindView(R.id.tvEmailDataDiri)
    TextView tvEmail;

    @BindView(R.id.tvPhoneDataDiri)
    TextView tvPhone;

    @BindView(R.id.tvNamaPenerimaDataDiri)
    TextView tvNamaPenerima;

    @BindView(R.id.tvJenisKelaminDataDiri)
    TextView tvJenisKelamin;

    @BindView(R.id.tvDateDataDiri)
    TextView tvDate;

    @BindView(R.id.btnUbahDataDiri)
    Button btnUbahDataDiri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_detail);
        ButterKnife.bind(this);

        init();

    }

    private void init() {
        Parsing parse = new Parsing();
        Session session = new Session(this);
        User user = parse.ParsingUser(session.getValue(Session.KEY_LOGIN_DATA));

        tvNamaPenerima.setText(user.getAddressname());
        tvPhone.setText(user.getAddressphone());
        tvEmail.setText(user.getEmail());

        btnUbahDataDiri.setOnClickListener(view -> {
            Intent i = new Intent(this, ProfileDetailEditActivity.class);
            i.putExtra("user", user);
            startActivity(i);
        });

        ivBack.setOnClickListener(view -> super.onBackPressed());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
