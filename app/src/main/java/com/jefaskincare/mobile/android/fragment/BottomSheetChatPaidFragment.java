package com.jefaskincare.mobile.android.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.jefaskincare.mobile.android.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BottomSheetChatPaidFragment extends BottomSheetDialogFragment {


    public BottomSheetChatPaidFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bottom_sheet_chat_paid, container, false);
    }

}
