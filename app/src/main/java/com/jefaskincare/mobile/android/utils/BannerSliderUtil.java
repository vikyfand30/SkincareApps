package com.jefaskincare.mobile.android.utils;

import com.jefaskincare.mobile.android.fragment.shop.Model.Banner;

import java.util.ArrayList;

import ss.com.bannerslider.adapters.SliderAdapter;
import ss.com.bannerslider.viewholder.ImageSlideViewHolder;

public class BannerSliderUtil extends SliderAdapter {

    private ArrayList<Banner> bannerList;
    private int size;

    public BannerSliderUtil(ArrayList<Banner> bannerList) {
        this.bannerList = bannerList;
    }

    @Override
    public int getItemCount() {
        return bannerList.size();
    }

    @Override
    public void onBindImageSlide(int position, ImageSlideViewHolder imageSlideViewHolder) {

        imageSlideViewHolder.bindImageSlide("http://34.80.174.252" + bannerList.get(position).getBannerfile());

    }
}

