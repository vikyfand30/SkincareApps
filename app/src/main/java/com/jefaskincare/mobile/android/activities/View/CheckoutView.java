package com.jefaskincare.mobile.android.activities.View;

import com.jefaskincare.mobile.android.activities.Model.Courier;
import com.jefaskincare.mobile.android.activities.Model.Shipping;
import com.jefaskincare.mobile.android.fragment.profile.Model.Address;
import com.jefaskincare.mobile.android.fragment.profile.Model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface CheckoutView {

    void GetAddressSuccess(User user, ArrayList<Address> address);
    void NoAddress();

    void GetCart();

    void GetCourierSuccess(ArrayList<Courier> courierList);
    void CheckoutFailed(String error);

    void GetShippingCostSuccess(Shipping shipping);
    void GetShippingCostFailed(String error);

    void GetDistrictIdSuccess(String districtId);
    void GetDistrictIdFailed();

    void FinishCheckoutSuccess();
    void FinishCheckoutFailed();

    void AddOrderAddressSuccess();
    void AddOrderAddressFailed();
}
