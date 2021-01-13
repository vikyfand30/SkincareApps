package com.jefaskincare.mobile.android.fragment.chat.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.jefaskincare.mobile.android.R;
import com.jefaskincare.mobile.android.activities.ChatActivity;
import com.jefaskincare.mobile.android.fragment.BottomSheetChatFragment;
import com.jefaskincare.mobile.android.fragment.chat.model.Voucher;

import java.util.ArrayList;

public class VoucherAdapter extends RecyclerView.Adapter<VoucherAdapter.VoucherViewHolder> {

    private Context context;
    private ArrayList<Voucher> dataList;

    public VoucherAdapter(Context context, ArrayList<Voucher> dataList){
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public VoucherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_voucher_chat, parent, false);
        return new VoucherViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VoucherViewHolder holder, int position) {
        holder.tvVoucherPrice.setText(dataList.get(position).getVoucherPrice());
        holder.tvVoucherKet.setText(dataList.get(position).getVoucherKet());
        holder.cvVoucher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               openBottomSheet(view);
            }
        });
    }
    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class VoucherViewHolder extends RecyclerView.ViewHolder{
        private TextView tvVoucherPrice;
        private  TextView tvVoucherKet;
        CardView cvVoucher;

        public VoucherViewHolder(View itemView) {
            super(itemView);
            tvVoucherPrice = (TextView) itemView.findViewById(R.id.tvVoucherPrice);
            cvVoucher = (CardView) itemView.findViewById(R.id.cvVoucher);
            tvVoucherKet = (TextView) itemView.findViewById(R.id.tvVoucherKet);
        }
    }

    private void openBottomSheet(View v){
        Context context = v.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.fragment_bottom_sheet_chat, null);

        ImageView ivFingerprint = (ImageView)view.findViewById(R.id.ivFingerprint);


        final Dialog mBottomSheetDialog = new Dialog(context, R.style.Theme_MaterialComponents_BottomSheetDialog);
        mBottomSheetDialog.setContentView(view);
        mBottomSheetDialog.setCancelable(true);
        mBottomSheetDialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        mBottomSheetDialog.getWindow().setGravity(Gravity.BOTTOM);
        mBottomSheetDialog.show();


        ivFingerprint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBottomSheetPaid(view);
            }
        });
    }

    private void openBottomSheetPaid(View v) {
        Context context = v.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.fragment_bottom_sheet_chat_paid, null);

        Button btnDone = (Button) view.findViewById(R.id.btnDone);

        final Dialog mBottomSheetDialog = new Dialog(context, R.style.Theme_MaterialComponents_BottomSheetDialog);
        mBottomSheetDialog.setContentView(view);
        mBottomSheetDialog.setCancelable(true);
        mBottomSheetDialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        mBottomSheetDialog.getWindow().setGravity(Gravity.BOTTOM);
        mBottomSheetDialog.show();


        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =  new Intent(context, ChatActivity.class);
                context.startActivity(i);
            }
        });

    }


}
