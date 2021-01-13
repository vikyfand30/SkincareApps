package com.jefaskincare.mobile.android.adapter.spinner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jefaskincare.mobile.android.R;
import com.jefaskincare.mobile.android.activities.Model.District;

import java.util.ArrayList;

public class DistrictAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<District> districtList;
    private LayoutInflater mInflater;

    public DistrictAdapter(Context context, ArrayList<District> districtList){
        this.context = context;
        this.districtList = districtList;
        this.mInflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return districtList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = mInflater.inflate(R.layout.item_view, null);
        TextView content = view.findViewById(R.id.tvContentItemView);
        content.setText(districtList.get(i).getDistrictName());

        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        convertView = mInflater.inflate(R.layout.item_dropdown, null);
        TextView content =convertView.findViewById(R.id.tvContentItemDropdown);
        content.setText(districtList.get(position).getDistrictName());
        return convertView;
    }
}
