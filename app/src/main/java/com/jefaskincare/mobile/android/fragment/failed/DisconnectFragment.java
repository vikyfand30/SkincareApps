package com.jefaskincare.mobile.android.fragment.failed;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jefaskincare.mobile.android.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DisconnectFragment extends Fragment {


    public DisconnectFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_disconnect, container, false);
    }

}
