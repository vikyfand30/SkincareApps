package com.jefaskincare.mobile.android.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.jefaskincare.mobile.android.R;
import com.poovam.pinedittextfield.LinePinField;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.iwgang.countdownview.CountdownView;

public class OtpActivity extends AppCompatActivity {

    @BindView(R.id.lineField)
    LinePinField linePinField;

    @BindView(R.id.btnSubmitOtp)
    Button btnSubmit;

    @BindView(R.id.ivBackOtp)
    ImageView ivBackOtp;

    @BindView(R.id.countdownView)
    CountdownView timer;

    @BindView(R.id.tvTittleResend)
    TextView tvTittleResend;

    @BindView(R.id.tvResend)
    TextView tvResend;

    private boolean isCountdownFinish = false;
    Snackbar snackbarwithbutton;
    View.OnClickListener customSnackbarClick;
    private boolean isFormValidationSuccess = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        ButterKnife.bind(this);

        setupCountdownview();
        addTextWatcher(linePinField);
        btnSubmit.setEnabled(false);

        btnSubmit.setOnClickListener(view -> {
            Intent i = new Intent(OtpActivity.this, NewPasswordActivity.class);
            startActivity(i);
        });

        ivBackOtp.setOnClickListener(view -> super.onBackPressed());

        customSnackbarClick = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                snackbarwithbutton.dismiss();
            }
        };
    }

    private void addTextWatcher(LinePinField linePinField) {
        linePinField.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().length() == 4) {
                //Method Call Api
                  btnSubmit.setBackground(ContextCompat.getDrawable(OtpActivity.this, R.drawable.button_primary_selector));
                  btnSubmit.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }

        });
    }

    private void setupCountdownview() {
        int otpDuration = 150000;
        tvTittleResend.setVisibility(View.INVISIBLE);
        tvResend.setVisibility(View.INVISIBLE);
//        int otpDuration = 5000;
        timer.start(otpDuration);
        timer.setOnCountdownEndListener(new CountdownView.OnCountdownEndListener() {
            @Override
            public void onEnd(CountdownView cv) {
                isCountdownFinish = true;
                updateCountdownview();
            }
        });
    }

    private void updateCountdownview() {
        tvResend.setVisibility(View.VISIBLE);
        tvTittleResend.setVisibility(View.VISIBLE);
        timer.setVisibility(View.GONE);
        timer.setVisibility(View.VISIBLE);
        tvResend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setupCountdownview();
                toastResend();
            }
        });
    }
    public void toastResend(){
        snackbarwithbutton = Snackbar.make(findViewById(android.R.id.content),"Resend a new email was successfully", Snackbar.LENGTH_LONG)
                .setAction("X", customSnackbarClick)
                .setActionTextColor(Color.WHITE);
        View snackView = snackbarwithbutton.getView();
        FrameLayout.LayoutParams parentParams = (FrameLayout.LayoutParams) snackView.getLayoutParams();
        snackView.setBackgroundResource(R.drawable.rounded_popup_black);
        parentParams.setMargins(20,20,20,250);
        snackbarwithbutton.show();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
