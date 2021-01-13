package com.jefaskincare.mobile.android.activities.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.jefaskincare.mobile.android.R;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReferralActivity extends AppCompatActivity {

    @BindView(R.id.ivBackReferral)
    ImageView ivBack;

    @BindView(R.id.tvReferral)
    TextView tvReferral;

    @BindView(R.id.ivCopyReferral)
    ImageView ivCopy;

    @BindView(R.id.btnShareReferral)
    Button btnShare;

    private ClipboardManager myClipboard;
    private ClipData myClip;
    Snackbar snackbarwithbutton;
    View.OnClickListener customSnackbarClickRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_referral);
        ButterKnife.bind(this);

        initContent();


    }


    public void initContent(){

        myClipboard = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ReferralActivity.super.onBackPressed();
            }
        });

        ivCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            String text = tvReferral.getText().toString();
            myClip = ClipData.newPlainText("text",text);
            myClipboard.setPrimaryClip(myClip);
            toastCopied();
            }
        });

        customSnackbarClickRef = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                snackbarwithbutton.dismiss();
            }
        };

        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Hi, i'm inviting you to download JEFA Skincare app at https/link.download.com, use code R45BF535 to earn chat voucher discounts!");
                sendIntent.setType("text/plain");

                Intent shareIntent = Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);
            }
        });
    }

    public void toastCopied(){
        snackbarwithbutton = Snackbar.make(findViewById(android.R.id.content),"Referral code copied to clipboard", Snackbar.LENGTH_LONG)
                .setAction("X", customSnackbarClickRef)
                .setActionTextColor(Color.WHITE);
        View snackView = snackbarwithbutton.getView();
        FrameLayout.LayoutParams parentParams = (FrameLayout.LayoutParams) snackView.getLayoutParams();
        snackView.setBackgroundResource(R.drawable.rounded_popup_black);
        parentParams.setMargins(20,20,20,20);
        parentParams.gravity = Gravity.TOP;
        snackbarwithbutton.show();
    }
}
