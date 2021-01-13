package com.jefaskincare.mobile.android.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.jefaskincare.mobile.android.MainActivity;
import com.jefaskincare.mobile.android.R;
import com.jefaskincare.mobile.android.fragment.chat.adapter.VoucherAdapter;
import com.jefaskincare.mobile.android.fragment.chat.model.Voucher;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BaseChatActivity extends AppCompatActivity {

    @BindView(R.id.ivBackBaseChat)
    ImageView ivBackChat;

    @BindView(R.id.rvVoucher)
    RecyclerView rv;
    VoucherAdapter adapter;
    private ArrayList<Voucher> vouchersDataArrayList;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_chat);
        ButterKnife.bind(this);


        addData();

        adapter = new VoucherAdapter(mContext, vouchersDataArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(BaseChatActivity.this);
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(adapter);

        ivBackChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BaseChatActivity.super.onBackPressed();
//                Intent i =  new Intent(BaseChatActivity.this, MainActivity.class);
//                startActivity(i);
            }
        });
    }


    private void addData() {
        vouchersDataArrayList = new ArrayList<>();
        vouchersDataArrayList.add(new Voucher(1, "Rp20.000", "Untuk 5x Sesi Pertanyaan"));
        vouchersDataArrayList.add(new Voucher(2, "Rp30.000", "Untuk 10x Sesi Pertanyaan"));
        vouchersDataArrayList.add(new Voucher(3, "Rp40.000", "Untuk 15x Sesi Pertanyaan"));
        vouchersDataArrayList.add(new Voucher(4, "Rp50.000", "Untuk 20x Sesi Pertanyaan"));
        vouchersDataArrayList.add(new Voucher(5, "Rp100.000", "Untuk 50x Sesi Pertanyaan"));

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
