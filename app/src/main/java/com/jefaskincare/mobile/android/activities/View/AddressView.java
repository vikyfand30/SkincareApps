package com.jefaskincare.mobile.android.activities.View;

import com.jefaskincare.mobile.android.activities.Model.City;
import com.jefaskincare.mobile.android.activities.Model.District;
import com.jefaskincare.mobile.android.activities.Model.Province;

import java.util.ArrayList;

public interface AddressView {
    void GetProvinceSuccess(ArrayList<Province> provinceList);
    void GetCitySuccess(ArrayList<City> citieList);
    void GetDistrictSuccess(ArrayList<District> districtList);
    void GetPostalCode(String postalCode);
    void ChangeAddressSuccess();
    void GetDataFailed(String error);
}
