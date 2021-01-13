package com.jefaskincare.mobile.android.fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.jefaskincare.mobile.android.R;
import com.jefaskincare.mobile.android.activities.OtpActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class BottomSheetForgotPasswordFragment extends BottomSheetDialogFragment {


    public BottomSheetForgotPasswordFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View v = inflater.inflate(R.layout.fragment_bottom_sheet_forgot_password, container, false);



        return v;
    }

}
