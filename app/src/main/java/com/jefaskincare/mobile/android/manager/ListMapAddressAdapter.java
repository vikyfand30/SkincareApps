package com.jefaskincare.mobile.android.manager;

import android.content.Context;
import android.location.Address;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jefaskincare.mobile.android.R;

import java.util.ArrayList;
import java.util.List;

public class ListMapAddressAdapter extends RecyclerView.Adapter<ListMapAddressAdapter.ViewHolder> {

    private Context context;
    private List<Address> listAddress;
    private LayoutInflater mInflate;

    ListMapAddressAdapter(Context context, List<Address>listAddress){
        this.context = context;
        this.listAddress = listAddress;
        mInflate = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ListMapAddressAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflate.inflate(R.layout.item_list_profile, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListMapAddressAdapter.ViewHolder holder, int position) {
        holder.tvAddressName.setText(listAddress.get(position).getAddressLine(0));
    }

    @Override
    public int getItemCount() {
        if(listAddress != null){
            return listAddress.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvAddressName;

        public ViewHolder(@NonNull View view) {
            super(view);
            tvAddressName = view.findViewById(R.id.tvProfileItem);
        }
    }

    public void updateList(List<Address>newListAddress){
        // clear old list
        if (listAddress!=null) {
            listAddress.clear();
            if(newListAddress!=null){
                listAddress.addAll(newListAddress);
            }
        }else{
            if(newListAddress!=null){
                listAddress = new ArrayList<>(newListAddress);
            }
        }

        // notify adapter
        this.notifyDataSetChanged();
    }
}
