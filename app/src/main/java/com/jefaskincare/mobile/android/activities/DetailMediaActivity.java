package com.jefaskincare.mobile.android.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.jefaskincare.mobile.android.MainActivity;
import com.jefaskincare.mobile.android.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailMediaActivity extends AppCompatActivity {

    @BindView(R.id.ivDetailMedia)
    ImageView ivDetailMedia;

    @BindView(R.id.ivBackDetailMedia)
    ImageView ivBackDetailMedia;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_media);
        ButterKnife.bind(this);


        ivBackDetailMedia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DetailMediaActivity.super.onBackPressed();
//                Intent i = new Intent(DetailMediaActivity.this, MainActivity.class);
//                startActivity(i);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
