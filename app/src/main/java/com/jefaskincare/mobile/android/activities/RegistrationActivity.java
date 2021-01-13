package com.jefaskincare.mobile.android.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.jefaskincare.mobile.android.FormValidation;
import com.jefaskincare.mobile.android.R;
import com.jefaskincare.mobile.android.activities.Model.RegisterData;
import com.jefaskincare.mobile.android.activities.Presenter.RegisterPresenter;
import com.jefaskincare.mobile.android.activities.View.RegisterView;
import com.jefaskincare.mobile.android.activities.View.ViewProductView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegistrationActivity extends AppCompatActivity implements RegisterView {

    @BindView(R.id.ivBackRegist)
    ImageView ivBackRegist;

    @BindView(R.id.btnSignUp)
    Button btnSignUp;

//    @BindView(R.id.etRegistUsername)
//    EditText etRegisUsername;

//    @BindView(R.id.etRegistFullname)
//    EditText etRegisterFullname;
//
//    @BindView(R.id.etRegistAddress)
//    EditText etRegisAddress;
    private  boolean isFormValidationSuccess = false;

    @BindView(R.id.etRegistEmail)
    EditText etRegisEmail;

    @BindView(R.id.etRegistRefferal)
    EditText etRegisRefferal;

    @BindView(R.id.etRegistPassword)
    EditText etRegisPassword;

    @BindView(R.id.etRegistConfirmPassword)
    EditText etRegisConfirmPassword;

    RegisterPresenter presenter;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        ButterKnife.bind(this);

        presenter = new RegisterPresenter(this, this);
        initContent();
    }

    private void initContent(){
        dialog = new ProgressDialog(this);
        
        addTextWatcher(etRegisPassword);
        addTextWatcher(etRegisEmail);
        addTextWatcher(etRegisConfirmPassword);
        ivBackRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegistrationActivity.super.onBackPressed();
//                Intent i = new Intent(RegistrationActivity.this, LoginActivity.class);
//                startActivity(i);
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isFormValidationSuccess) {

                    RetrieveData();
                }



            }

        });
    }

    private void addTextWatcher(EditText input) {
        input.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                validateRegistForm();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }

        });
    }


    private void RetrieveData(){
        RegisterData register = new RegisterData();
//        register.setUsername(etRegisUsername.getText().toString().trim());
//        register.setFullname(etRegisterFullname.getText().toString().trim());
//        register.setAddress(etRegisAddress.getText().toString().trim());
        register.setEmail(etRegisEmail.getText().toString().trim());
//        register.setPhone(etRegisPhone.getText().toString().trim());
        register.setPassword(etRegisPassword.getText().toString().trim());
        register.setcPassword(etRegisConfirmPassword.getText().toString().trim());
        String reff = etRegisRefferal.getText().toString().trim();
        if(reff.isEmpty()){
            register.setReferal("");
        }
        register.setSeller("");

        dialog.setMessage("Sedang proses...");
        dialog.setCancelable(false);
        dialog.show();

        presenter.RegisterInit(register);
    }

    @Override
    public void RegisterProcessSuccess() {
        dialog.dismiss();
        Intent i = new Intent(RegistrationActivity.this, VerifyEmailActivity.class);
        startActivity(i);
    }

    @Override
    public void PasswordNotMatch() {
        dialog.dismiss();
        Toast.makeText(this, "Password tidak sama", Toast.LENGTH_LONG).show();
    }

    @Override
    public void RegisterProcessFailed(String message) {
        dialog.dismiss();
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

        public void validateRegistForm(){
            String email = etRegisEmail.getText().toString();
            String pass = etRegisPassword.getText().toString();
            String pass2 = etRegisConfirmPassword.getText().toString();

            if (FormValidation.required(email) && FormValidation.validEmail(email) && (FormValidation.required(pass) && (FormValidation.validPassword(pass)) && (FormValidation.required(pass2) && (FormValidation.validPassword(pass) && pass.equals(pass2)))))
            {
                isFormValidationSuccess = true;
                btnSignUp.setBackground(ContextCompat.getDrawable(RegistrationActivity.this, R.drawable.button_primary_selector));
            }else{
                isFormValidationSuccess =false;
                btnSignUp.setBackground(ContextCompat.getDrawable(RegistrationActivity.this, R.drawable.button_selector_selected));
            }
    }
}
