package com.jefaskincare.mobile.android.activities.Presenter;

import android.content.Context;
import android.util.Log;

import com.android.volley.VolleyError;
import com.jefaskincare.mobile.android.activities.Model.District;
import com.jefaskincare.mobile.android.activities.View.AddressView;
import com.jefaskincare.mobile.android.api.ApiServices;
import com.jefaskincare.mobile.android.api.IResult;
import com.jefaskincare.mobile.android.api.ParamKey;
import com.jefaskincare.mobile.android.api.Parsing;
import com.jefaskincare.mobile.android.api.UrlKey;
import com.jefaskincare.mobile.android.api.VolleyServices;

import org.json.JSONException;
import org.json.JSONObject;

public class AddressPresenter {

    private Context context;
    private AddressView view;
    private ApiServices params;
    private IResult callbackProvince;
    private IResult callbackDistrict;
    private IResult callbackDistrictID;
    private IResult callbackCity;
    private IResult callbackChangeAddress;
    private IResult callbackAddAddress;
    private VolleyServices volleyServices;
    private Parsing parse;

    public AddressPresenter(Context context, AddressView view) {
        this.context = context;
        this.view = view;
        params = new ApiServices();
        parse = new Parsing();
    }

    public void GetProvince() {
        initProvinceCallback();
        volleyServices = new VolleyServices(callbackProvince, context);
        volleyServices.postDataVolley(UrlKey.KEY_METHOD_ADDRESS_PROVINCE,
                params.ProvinceList(
                        "",
                        "",
                        "P"));
    }

    private void initProvinceCallback() {
        callbackProvince = new IResult() {
            @Override
            public void notifySuccess(JSONObject response) {
                try {
                    if (response.get(ParamKey.KEY_CODE).equals(ParamKey.KEY_SUCCESS)
                            || response.get(ParamKey.KEY_CODE).equals(ParamKey.KEY_SUCCESS_ONE)) {

                        view.GetProvinceSuccess(
                                parse.ParsingProvince(response));

                    } else {
                        view.GetDataFailed(response.get(ParamKey.KEY_MESSAGE).toString());
                    }
                } catch (JSONException e) {
                    view.GetDataFailed("Something Wrong, please try again");
                    Log.e("Error ", e.getMessage());
                }
            }

            @Override
            public void notifyError(VolleyError error) {
                view.GetDataFailed(error.getMessage());
            }
        };
    }

    public void GetCity(String provinceId) {
        initCityCallback();
        volleyServices = new VolleyServices(callbackCity, context);
        volleyServices.postDataVolley(UrlKey.KEY_METHOD_ADDRESS_PROVINCE,
                params.CityList(
                        provinceId,
                        "",
                        "C"));
    }

    private void initCityCallback() {
        callbackCity = new IResult() {
            @Override
            public void notifySuccess(JSONObject response) {
                try {
                    if (response.get(ParamKey.KEY_CODE).equals(ParamKey.KEY_SUCCESS)
                            || response.get(ParamKey.KEY_CODE).equals(ParamKey.KEY_SUCCESS_ONE)) {

                        view.GetCitySuccess(
                                parse.ParsingCity(response));

                    } else {
                        view.GetDataFailed(response.get(ParamKey.KEY_MESSAGE).toString());
                    }
                } catch (JSONException e) {
                    view.GetDataFailed("Something Wrong, please try again");
                    Log.e("Error ", e.getMessage());
                }
            }

            @Override
            public void notifyError(VolleyError error) {
                view.GetDataFailed(error.getMessage());
            }
        };
    }

    public void GetDistrict(String city) {
        InitDistrictCallback();
        volleyServices = new VolleyServices(callbackDistrict, context);
        volleyServices.postDataVolley(UrlKey.KEY_METHOD_ADDRESS_CITY,
                params.DistrictList(
                        city,
                        "",
                        "D"));
    }

    private void InitDistrictCallback() {
        callbackDistrict = new IResult() {
            @Override
            public void notifySuccess(JSONObject response) {
                try {
                    if (response.get(ParamKey.KEY_CODE).equals(ParamKey.KEY_SUCCESS)
                            || response.get(ParamKey.KEY_CODE).equals(ParamKey.KEY_SUCCESS_ONE)) {

                        view.GetDistrictSuccess(
                                parse.ParsingDistrict(response));

                    } else {
                        view.GetDataFailed(response.get(ParamKey.KEY_MESSAGE).toString());
                    }
                } catch (JSONException e) {
                    view.GetDataFailed("Something Wrong, please try again");
                    Log.e("Error ", e.getMessage());
                }
            }

            @Override
            public void notifyError(VolleyError error) {
                view.GetDataFailed(error.getMessage());
            }
        };
    }

    public void GetPostalCode(District district) {
        view.GetPostalCode(district.getPostalCode());
    }

    public void ChangeAddress(String userid,
                              String address,
                              String addressid,
                              String districtid,
                              String zip,
                              double latitude,
                              double longitude,
                              String addressname,
                              String addresphone) {
        InitChangeAddressCallback();
        volleyServices = new VolleyServices(callbackChangeAddress, context);
        volleyServices.postDataVolley(UrlKey.KEY_METHOD_ADDRESS_CHANGE,
                params.ChangeAddress(
                        userid,
                        address,
                        addressid,
                        districtid,
                        zip,
                        latitude,
                        longitude,
                        addressname,
                        addresphone));
    }

    private void InitChangeAddressCallback() {
        callbackChangeAddress = new IResult() {
            @Override
            public void notifySuccess(JSONObject response) {
                try {
                    if (response.get(ParamKey.KEY_CODE).equals(ParamKey.KEY_SUCCESS)
                            || response.get(ParamKey.KEY_CODE).equals(ParamKey.KEY_SUCCESS_ONE)) {

                        view.ChangeAddressSuccess();

                    } else {
                        view.GetDataFailed(response.get(ParamKey.KEY_MESSAGE).toString());
                    }
                } catch (JSONException e) {
                    view.GetDataFailed("Something Wrong, please try again");
                    Log.e("Error ", e.getMessage());
                }
            }

            @Override
            public void notifyError(VolleyError error) {
                view.GetDataFailed(error.getMessage());
            }
        };
    }

    public void AddAddress(String userid,
                           String address,
                           String districtid,
                           String zip,
                           double latitude,
                           double longitude,
                           String addressname,
                           String addressphone) {
        InitAddAddressCallback();
        volleyServices = new VolleyServices(callbackAddAddress, context);
        volleyServices.postDataVolley(UrlKey.KEY_METHOD_ADDRESS_ADD,
                params.AddAddress(
                        userid,
                        address,
                        districtid,
                        zip,
                        latitude,
                        longitude,
                        addressname,
                        addressphone));
    }

    private void InitAddAddressCallback() {
        callbackAddAddress = new IResult() {
            @Override
            public void notifySuccess(JSONObject response) {
                try {
                    if (response.get(ParamKey.KEY_CODE).equals(ParamKey.KEY_SUCCESS)
                            || response.get(ParamKey.KEY_CODE).equals(ParamKey.KEY_SUCCESS_ONE)) {

                        view.ChangeAddressSuccess();

                    } else {
                        view.GetDataFailed(response.get(ParamKey.KEY_MESSAGE).toString());
                    }
                } catch (JSONException e) {
                    view.GetDataFailed("Something Wrong, please try again");
                    Log.e("Error ", e.getMessage());
                }
            }

            @Override
            public void notifyError(VolleyError error) {
                view.GetDataFailed(error.getMessage());
            }
        };
    }

    public void GetDistrictId(String district) {
        InitDistrictCallbackId();
        volleyServices = new VolleyServices(callbackDistrict, context);
        volleyServices.postDataVolley(UrlKey.KEY_METHOD_ADDRESS_DISTRIC,
                params.DistrictList(
                        "0",
                        district,
                        "D"));
    }

    private void InitDistrictCallbackId() {
        callbackDistrict = new IResult() {
            @Override
            public void notifySuccess(JSONObject response) {
                try {
                    if (response.get(ParamKey.KEY_CODE).equals(ParamKey.KEY_SUCCESS)
                            || response.get(ParamKey.KEY_CODE).equals(ParamKey.KEY_SUCCESS_ONE)) {

                        view.GetDistrictSuccess(
                                parse.ParsingDistrict(response));

                    } else {
                        view.GetDataFailed(response.get(ParamKey.KEY_MESSAGE).toString());
                    }
                } catch (JSONException e) {
                    view.GetDataFailed("Something Wrong, please try again");
                    Log.e("Error ", e.getMessage());
                }
            }

            @Override
            public void notifyError(VolleyError error) {
                view.GetDataFailed(error.getMessage());
            }
        };
    }

}
