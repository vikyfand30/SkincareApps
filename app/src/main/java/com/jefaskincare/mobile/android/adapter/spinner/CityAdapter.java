package com.jefaskincare.mobile.android.adapter.spinner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jefaskincare.mobile.android.R;
import com.jefaskincare.mobile.android.activities.Model.City;
import com.jefaskincare.mobile.android.adapter.FeedsAdapter;

import java.util.ArrayList;

public class CityAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<City> cityList;
    private LayoutInflater mInflater;

    public CityAdapter(Context context, ArrayList<City> cityList){
        this.context = context;
        this.cityList = cityList;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return cityList.size();
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
        content.setText(cityList.get(i).getCityName());

        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        convertView = mInflater.inflate(R.layout.item_dropdown, null);
        TextView content =convertView.findViewById(R.id.tvContentItemDropdown);
        content.setText(cityList.get(position).getCityName());
        return convertView;
    }

}
