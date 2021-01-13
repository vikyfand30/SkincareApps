package com.jefaskincare.mobile.android.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jefaskincare.mobile.android.R;
import com.jefaskincare.mobile.android.activities.ShippingAddressActivity;
import com.jefaskincare.mobile.android.fragment.profile.Model.Address;
import com.jefaskincare.mobile.android.fragment.profile.Model.User;
import com.jefaskincare.mobile.android.fragment.profile.ProfileDetailAddressActivity;

import java.util.ArrayList;

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Address> addresses;
    private User user;
    private LayoutInflater mInflater;

    public AddressAdapter(Context context, ArrayList<Address> addresses, User user){
        this.context = context;
        this.addresses = addresses;
        this.user = user;
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_address_profile, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvNameAddress.setText(addresses.get(position).getAddressname());
        holder.tvPhoneAddress.setText(addresses.get(position).getAddressphone());
        if(addresses.get(position).getPos().equals("1")){
            holder.tvLocationAdrress.setText("Rumah");
        }else{
            holder.tvLocationAdrress.setText("Lainnya");
        }
        holder.tvProfileAddress.setText(addresses.get(position).getAddress());
        holder.btnEditProfileAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ShippingAddressActivity.class);
                intent.putExtra("userid", user.getUserid());
                intent.putExtra("addressid", addresses.get(position).getAddressid());
                intent.putExtra("address", addresses.get(position));
                intent.putExtra("flag", true);
                context.startActivity(intent);
            }
        });
        holder.llAddressDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ProfileDetailAddressActivity.class);
                intent.putExtra("user", user);
                intent.putExtra("address", addresses.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return addresses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout llAddressDetail;
        TextView tvNameAddress;
        TextView tvPhoneAddress;
        TextView tvLocationAdrress;
        Button btnEditProfileAddress;
        TextView tvProfileAddress;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            llAddressDetail = itemView.findViewById(R.id.llAddressDetail);
            tvNameAddress = itemView.findViewById(R.id.tvNameAddress);
            tvPhoneAddress = itemView.findViewById(R.id.tvPhoneAddress);
            tvLocationAdrress = itemView.findViewById(R.id.tvLocationAddress);
            btnEditProfileAddress = itemView.findViewById(R.id.btnEditProfileAddress);
            tvProfileAddress = itemView.findViewById(R.id.tvProfileAddress);
        }
    }
}
