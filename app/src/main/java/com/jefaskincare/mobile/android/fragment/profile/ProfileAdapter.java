package com.jefaskincare.mobile.android.fragment.profile;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jefaskincare.mobile.android.R;
import com.jefaskincare.mobile.android.adapter.CategoryAdapter;

import java.util.ArrayList;
import java.util.List;

public class ProfileAdapter extends BaseAdapter{

    private ArrayList<String> data;
    private Activity activity;
    private static LayoutInflater inflater = null;

    public ProfileAdapter(Activity activity, ArrayList<String> data){
        this.data = data;
        this.activity = activity;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public String getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;
        ViewHolder holder = null;
            if(view == null){
                v = inflater.inflate(R.layout.item_list_profile, null, true);
                holder = new ViewHolder();
                holder.tvItems = v.findViewById(R.id.tvProfileItem);
                holder.tvItems.setText(data.get(i));
            }
        return v;
    }

    public static class ViewHolder{
        public TextView tvItems;
    }
}
