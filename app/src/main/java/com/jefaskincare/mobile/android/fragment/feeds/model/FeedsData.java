package com.jefaskincare.mobile.android.fragment.feeds.model;

import com.jefaskincare.mobile.android.R;
import com.jefaskincare.mobile.android.fragment.profile.Model.User;
import com.jefaskincare.mobile.android.fragment.shop.Model.Category;

import java.util.ArrayList;

public class FeedsData {

    public static Object[][] pic = new Object[][]{
            {R.drawable.media_satu},
            {R.drawable.media_dua},
            {R.drawable.media_tiga},
            {R.drawable.media_empat},
            {R.drawable.media_lima},
            {R.drawable.media_enam},
    };

    public static ArrayList<Feeds> getFeeds() {
        ArrayList<Feeds> list = new ArrayList<Feeds>();
        for (Object[] Items : pic) {
            Feeds feed = new Feeds();
            feed.setFeedImage(Integer.parseInt(Items[0].toString()));
            list.add(feed);
        }
        return list;
    }
}
