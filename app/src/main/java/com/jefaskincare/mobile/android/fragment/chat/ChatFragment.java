package com.jefaskincare.mobile.android.fragment.chat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jefaskincare.mobile.android.R;
import com.jefaskincare.mobile.android.fragment.chat.adapter.VoucherAdapter;
import com.jefaskincare.mobile.android.fragment.chat.model.Voucher;
import com.jefaskincare.mobile.android.fragment.reviews.ReviewsAdapter;
import com.jefaskincare.mobile.android.fragment.reviews.ReviewsData;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChatFragment extends Fragment {

//    private ChatViewModel chatViewModel;
        @BindView(R.id.rvChat)
        RecyclerView rv;
    VoucherAdapter adapter;
    private ArrayList<Voucher> vouchersDataArrayList;


    public ChatFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_chat, container, false);
        ButterKnife.bind(this, v);




        return v;
    }

    private void addData() {
        vouchersDataArrayList = new ArrayList<>();
        vouchersDataArrayList.add(new Voucher(1, "Rp. 20.000", "Untuk 5x Sesi Pertanyaan"));
        vouchersDataArrayList.add(new Voucher(2, "Rp. 30.000", "Untuk 10x Sesi Pertanyaan"));
        vouchersDataArrayList.add(new Voucher(3, "Rp. 40.000", "Untuk 15x Sesi Pertanyaan"));
        vouchersDataArrayList.add(new Voucher(4, "Rp. 50.000", "Untuk 20x Sesi Pertanyaan"));
        vouchersDataArrayList.add(new Voucher(5, "Rp. 100.000", "Untuk 50x Sesi Pertanyaan"));




    }


}