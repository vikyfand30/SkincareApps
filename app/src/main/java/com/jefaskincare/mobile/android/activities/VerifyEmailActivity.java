package com.jefaskincare.mobile.android.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jefaskincare.mobile.android.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VerifyEmailActivity extends AppCompatActivity {

    @BindView(R.id.btnVerifLogin)
    Button btnVerifLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_email);
        ButterKnife.bind(this);

        initContent();
    }


    private void initContent(){
        btnVerifLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(VerifyEmailActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });
    }
}
