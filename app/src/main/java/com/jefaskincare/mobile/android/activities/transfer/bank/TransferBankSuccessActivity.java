package com.jefaskincare.mobile.android.activities.transfer.bank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jefaskincare.mobile.android.MainActivity;
import com.jefaskincare.mobile.android.R;
import com.jefaskincare.mobile.android.activities.OrderHistoryActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TransferBankSuccessActivity extends AppCompatActivity {

    @BindView(R.id.btnViewOrderHistory)
    Button btnViewOrderHistory;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_bank_success);
        ButterKnife.bind(this);

        btnViewOrderHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(TransferBankSuccessActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
