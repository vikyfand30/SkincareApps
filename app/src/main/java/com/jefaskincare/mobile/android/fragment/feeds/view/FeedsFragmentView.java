package com.jefaskincare.mobile.android.fragment.feeds.view;


import com.jefaskincare.mobile.android.fragment.feeds.model.Feeds;

import java.util.ArrayList;

public class FeedsFragmentView {

    public interface View{
        void SetRV(ArrayList<Feeds> dataFeed);
    }
}
