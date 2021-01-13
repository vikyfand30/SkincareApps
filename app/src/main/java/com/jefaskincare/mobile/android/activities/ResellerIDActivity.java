package com.jefaskincare.mobile.android.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.jefaskincare.mobile.android.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ResellerIDActivity extends AppCompatActivity {

    @BindView(R.id.ivBackResellerID)
    ImageView ivBackReseller;

    @BindView(R.id.etIDReseller)
    EditText etIDReseller;

    @BindView(R.id.btnSaveResellerID)
    Button btnSaveReseller;

    private boolean isFormValidationSuccess = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reseller_id);
        ButterKnife.bind(this);

        initContent();


    }

    public void initContent(){





    }
}
