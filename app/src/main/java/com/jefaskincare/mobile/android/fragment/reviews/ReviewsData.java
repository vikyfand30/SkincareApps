package com.jefaskincare.mobile.android.fragment.reviews;

import android.widget.TextView;

public class ReviewsData {

    String desc;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    String date;
    String name;

    public  ReviewsData(String desc, String date, String name){
        this.desc = desc;
        this.date = date;
        this.name = name;
    }



}
