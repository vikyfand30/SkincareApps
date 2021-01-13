package com.jefaskincare.mobile.android.activities.Model;

import androidx.annotation.NonNull;

public class District {

    private String DistrictId;
    private String DistrictName;
    private String DistrictType;
    private String PostalCode;
    private String CityName;
    private String ProvName;

    public String getDistrictId() {
        return DistrictId;
    }

    public void setDistrictId(String districtId) {
        DistrictId = districtId;
    }

    public String getDistrictName() {
        return DistrictName;
    }

    public void setDistrictName(String districtName) {
        DistrictName = districtName;
    }

    public String getDistrictType() {
        return DistrictType;
    }

    public void setDistrictType(String districtType) {
        DistrictType = districtType;
    }

    public String getPostalCode() {
        return PostalCode;
    }

    public void setPostalCode(String postalCode) {
        PostalCode = postalCode;
    }

    public String getCityName() {
        return CityName;
    }

    public void setCityName(String cityName) {
        CityName = cityName;
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
        return DistrictName;
    }
}
