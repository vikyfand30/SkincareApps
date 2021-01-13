package com.jefaskincare.mobile.android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jefaskincare.mobile.android.activities.LoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChangePasswordSuccessActivity extends AppCompatActivity {

    @BindView(R.id.btnLoginChangePassword)
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password_success);
        ButterKnife.bind(this);

        initContent();


    }

    public void initContent(){

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ChangePasswordSuccessActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });

    }
}
