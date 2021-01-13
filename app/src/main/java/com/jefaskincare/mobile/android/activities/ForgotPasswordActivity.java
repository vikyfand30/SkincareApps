package com.jefaskincare.mobile.android.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.jefaskincare.mobile.android.FormValidation;
import com.jefaskincare.mobile.android.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ForgotPasswordActivity extends AppCompatActivity {

    @BindView(R.id.btnSubmitForgotPass)
   Button btnSubmit;

    @BindView(R.id.ivBackForgotPassword)
    ImageView ivBack;

    @BindView(R.id.etResetPassEmail)
    EditText etEmail;

    private boolean isFormValidationSuccess = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        ButterKnife.bind(this);


        addTextWatcher(etEmail);

        initContent();
    }


    private void initContent(){
     ivBack.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             ForgotPasswordActivity.super.onBackPressed();
//             Intent i = new Intent(ForgotPasswordActivity.this, LoginActivity.class);
//             startActivity(i);
         }
     });

     btnSubmit.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             if (isFormValidationSuccess){
                 Intent i = new Intent(ForgotPasswordActivity.this, OtpActivity.class);
                 startActivity(i);
             }

         }
     });

    }

    public void addTextWatcher(final EditText input) {
        input.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                validateEmail();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }

        });
    }

    public void validateEmail(){
        String email = etEmail.getText().toString();


        if (FormValidation.required(email) && FormValidation.validEmail(email))
        {
            isFormValidationSuccess = true;
            btnSubmit.setBackground(ContextCompat.getDrawable(ForgotPasswordActivity.this, R.drawable.button_primary_selector));
        }else{
            isFormValidationSuccess =false;
            btnSubmit.setBackground(ContextCompat.getDrawable(ForgotPasswordActivity.this, R.drawable.button_selector_selected));
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
