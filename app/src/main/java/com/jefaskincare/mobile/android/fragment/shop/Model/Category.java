package com.jefaskincare.mobile.android.fragment.shop.Model;

import android.content.Intent;

public class Category {

    private String id;
    private String value;
    private Integer CatColor;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getCatColor() {
        return CatColor;
    }

    public void setCatColor(Integer catColor) {
        CatColor = catColor;
    }
}
