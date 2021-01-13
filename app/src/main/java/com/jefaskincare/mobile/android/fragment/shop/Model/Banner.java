package com.jefaskincare.mobile.android.fragment.shop.Model;

import java.io.Serializable;

public class Banner implements Serializable {

    private String bannername;
    private String bannerfile;

    public String getBannername() {
        return bannername;
    }

    public void setBannername(String bannername) {
        this.bannername = bannername;
    }

    public String getBannerfile() {
        return bannerfile;
    }

    public void setBannerfile(String bannerfile) {
        this.bannerfile = bannerfile;
    }
}
