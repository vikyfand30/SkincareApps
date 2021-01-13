package com.jefaskincare.mobile.android.fragment.feeds.presenter;

import android.content.Context;

import com.jefaskincare.mobile.android.fragment.feeds.model.Feeds;
import com.jefaskincare.mobile.android.fragment.feeds.model.FeedsData;
import com.jefaskincare.mobile.android.fragment.feeds.view.FeedsFragmentView;

import java.util.ArrayList;

public class FeedsFragmentPresenter {

    private Context context;
    private FeedsFragmentView.View view;

    public FeedsFragmentPresenter(Context context, FeedsFragmentView.View view){
        this.context = context;
        this.view = view;
        GetFeedsImage();
    }

    public void GetFeedsImage() {
        ArrayList<Feeds> dataFeed = new ArrayList<Feeds>();
        dataFeed = FeedsData.getFeeds();
        view.SetRV(dataFeed);
    }
}
