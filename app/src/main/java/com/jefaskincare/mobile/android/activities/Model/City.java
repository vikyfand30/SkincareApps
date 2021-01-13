package com.jefaskincare.mobile.android.activities.Model;

import androidx.annotation.NonNull;

public class City {

    private String CityId;
    private String CityName;
    private String PostalCode;
    private String ProvName;

    public String getCityId() {
        return CityId;
    }

    public void setCityId(String cityId) {
        CityId = cityId;
    }

    public String getCityName() {
        return CityName;
    }

    public void setCityName(String cityName) {
        CityName = cityName;
    }

    public String getPostalCode() {
        return PostalCode;
    }

    public void setPostalCode(String postalCode) {
        PostalCode = postalCode;
    }

    public String getProvId() {
        return ProvName;
    }

    public void setProvId(String provId) {
        ProvName = provId;
    }


    @NonNull
    @Override
    public String toString() {
        return CityName;
    }
}
