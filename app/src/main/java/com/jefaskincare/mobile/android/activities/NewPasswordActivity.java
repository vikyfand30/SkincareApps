package com.jefaskincare.mobile.android.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.jefaskincare.mobile.android.ChangePasswordSuccessActivity;
import com.jefaskincare.mobile.android.FormValidation;
import com.jefaskincare.mobile.android.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewPasswordActivity extends AppCompatActivity {

    @BindView(R.id.btnSaveNewPassword)
    Button btnSaveNewPassword;

    @BindView(R.id.ivBackNewPassword)
    ImageView ivBackNewPassword;

    @BindView(R.id.etNewPassword)
    EditText etNewPass;

    @BindView(R.id.etConfirmNewPassword)
    EditText etConfirmNewPass;



    private boolean isFormValidationSuccess = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_password);
        ButterKnife.bind(this);
        initContent();



        addTextWatcher(etNewPass);
        addTextWatcher(etConfirmNewPass);


    }


    private void initContent(){
        btnSaveNewPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isFormValidationSuccess){
                    Intent i = new Intent(NewPasswordActivity.this, ChangePasswordSuccessActivity.class);
                    startActivity(i);
                }

            }
        });

        ivBackNewPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBottomSheetDiscard(view);
//                view = getLayoutInflater().inflate(R.layout.fragment_bottom_sheet_forgot_password, null);
//                BottomSheetDialog dialog = new BottomSheetDialog(NewPasswordActivity.this);
//                dialog.setContentView(view);
//                dialog.show();
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
               validateNewPassword();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }

        });
    }

    public void validateNewPassword(){
        String newpass = etNewPass.getText().toString();
        String confirmnewpass = etConfirmNewPass.getText().toString();

        if (FormValidation.required(newpass) && FormValidation.validPassword(newpass) && (FormValidation.required(confirmnewpass) && (FormValidation.validPassword(confirmnewpass) && (newpass.equals(confirmnewpass)))))
        {
            isFormValidationSuccess = true;
            btnSaveNewPassword.setBackground(ContextCompat.getDrawable(NewPasswordActivity.this, R.drawable.button_primary_selector));
        }else{
            isFormValidationSuccess =false;
            btnSaveNewPassword.setBackground(ContextCompat.getDrawable(NewPasswordActivity.this, R.drawable.button_selector_selected));
        }
    }

    private void openBottomSheetDiscard(View v) {
        Context context = v.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.fragment_bottom_sheet_forgot_password, null);

        Button btnDiscard = (Button) view.findViewById(R.id.btnDiscardPass);


        final Dialog mBottomSheetDialog = new Dialog(context, R.style.Theme_MaterialComponents_BottomSheetDialog);
        mBottomSheetDialog.setContentView(view);
        mBottomSheetDialog.setCancelable(true);
        mBottomSheetDialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        mBottomSheetDialog.getWindow().setGravity(Gravity.BOTTOM);
        mBottomSheetDialog.show();

        btnDiscard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(NewPasswordActivity.this, OtpActivity.class);
                startActivity(i);
            }
        });


    }
}
