package com.jefaskincare.mobile.android.adapter.spinner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.jefaskincare.mobile.android.R;
import com.jefaskincare.mobile.android.activities.Model.Province;
import com.jefaskincare.mobile.android.activities.View.LoginView;

import org.w3c.dom.Text;

import java.time.chrono.MinguoChronology;
import java.util.ArrayList;
import java.util.List;

public class ProvinceAdapter extends ArrayAdapter<ArrayList<Province>> {

    private Context context;
    private ArrayList<Province> provinceList;
    private LayoutInflater mInflater;


    public ProvinceAdapter(@NonNull Context context, int resource, @NonNull List<ArrayList<Province>> objects) {
        super(context, resource, objects);
    }
}
