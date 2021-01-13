package com.jefaskincare.mobile.android.activities.Presenter;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Log;

import com.android.volley.VolleyError;
import com.jefaskincare.mobile.android.activities.Model.City;
import com.jefaskincare.mobile.android.activities.Model.District;
import com.jefaskincare.mobile.android.activities.View.CheckoutView;
import com.jefaskincare.mobile.android.api.ApiServices;
import com.jefaskincare.mobile.android.api.IResult;
import com.jefaskincare.mobile.android.api.ParamKey;
import com.jefaskincare.mobile.android.api.Parsing;
import com.jefaskincare.mobile.android.api.UrlKey;
import com.jefaskincare.mobile.android.api.VolleyServices;
import com.jefaskincare.mobile.android.fragment.profile.Model.Address;
import com.jefaskincare.mobile.android.fragment.profile.Model.User;
import com.jefaskincare.mobile.android.manager.Session;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckoutPresenter {

    private Context context;
    private CheckoutView view;
    private VolleyServices volleyServices;
    private IResult addressCallback;
    private IResult cityCallback;
    private IResult districtCallback;
    private IResult courierCallback;
    private IResult shippingCostCallback;
    private IResult finishCallback;
    private IResult setAddressCallback;
    private ApiServices param;
    private String cityId;
    private String districId;
    private Parsing parse;
    private User user;
    private Session session;

    public CheckoutPresenter(Context context, CheckoutView view){
        this.context = context;
        this.view = view;
        param = new ApiServices();
        parse = new Parsing();
        session = new Session(context);
    }

    public void GetAddress(){
        Session session = new Session(context);
        user = parse.ParsingUser(session.getValue(Session.KEY_LOGIN_DATA));

        InitAddressCallback();
        volleyServices = new VolleyServices(addressCallback, context);
        volleyServices.postDataVolley(UrlKey.KEY_METHOD_ADDRESS_USER,
                param.AddressUser(user.getUserid()));
    }

    private void InitAddressCallback() {
        addressCallback = new IResult() {
            @Override
            public void notifySuccess(JSONObject response) {
                try{
                    if(response.get(ParamKey.KEY_CODE).equals(ParamKey.KEY_SUCCESS)
                            || response.get(ParamKey.KEY_CODE).equals(ParamKey.KEY_SUCCESS_ONE)){

                        view.GetAddressSuccess(user,
                                parse.ParsingAddresses(response));

                    }else{
                        view.NoAddress();
                    }
                }catch (JSONException e){
                    view.CheckoutFailed("Something Wrong, please try again");
                    Log.e("Error ", e.getMessage());
                }
            }

            @Override
            public void notifyError(VolleyError error) {
                view.CheckoutFailed(error.getMessage());
            }
        };
    }

    public void GetDestinationId(String cityName) {
        InitCityResultCallback();
        volleyServices = new VolleyServices(cityCallback, context);
        volleyServices.postDataVolley(UrlKey.KEY_METHOD_ADDRESS_CITY,
                param.CityList(
                        "",
                        cityName,
                        "C"
                ));
    }

    private void InitCityResultCallback() {
        cityCallback = new IResult() {
            @Override
            public void notifySuccess(JSONObject response) {
                try{
                    if(response.get(ParamKey.KEY_CODE).equals(ParamKey.KEY_SUCCESS)
                            || response.get(ParamKey.KEY_CODE).equals(ParamKey.KEY_SUCCESS_ONE)){

                        GetCityInfo(response);

                    }else{
                        view.CheckoutFailed(response.get(ParamKey.KEY_MESSAGE).toString());
                    }
                }catch (JSONException e){
                    view.CheckoutFailed("Something Wrong, please try again");
                    Log.e("Error ", e.getMessage());
                }
            }

            @Override
            public void notifyError(VolleyError error) {
                view.CheckoutFailed(error.getMessage());
            }
        };
    }

    private void GetCityInfo(JSONObject response){
        Parsing parse = new Parsing();
        ArrayList<City> cities = new ArrayList<>(parse.ParsingCity(response));
        cityId = cities.get(1).getCityId();
        GetCourirInfo();
    }

    public void GetCourirInfo(){
        InitCourirResultCallback();
        volleyServices = new VolleyServices(courierCallback, context);
        volleyServices.postDataVolley(UrlKey.KEY_METHOD_COURIER_LIST,
                param.CourierList(
                        "151"
                     ));
    }

    private void InitCourirResultCallback() {
        courierCallback = new IResult() {
            @Override
            public void notifySuccess(JSONObject response) {
                try{
                    if(response.get(ParamKey.KEY_CODE).equals(ParamKey.KEY_SUCCESS)
                            || response.get(ParamKey.KEY_CODE).equals(ParamKey.KEY_SUCCESS_ONE)){

                        GetCourierList(response);

                    }else{
                        view.CheckoutFailed(response.get(ParamKey.KEY_MESSAGE).toString());
                    }
                }catch (JSONException e){
                    view.CheckoutFailed("Something Wrong, please try again");
                    Log.e("Error ", e.getMessage());
                }
            }

            @Override
            public void notifyError(VolleyError error) {

            }
        };
    }

    private void GetCourierList(JSONObject response) {
        view.GetCourierSuccess(parse.ParsingCourierList(response));
    }

    public void GetShippingPrice(String courierName, String districtId) {
        InitShippingCostResultCallback();
        volleyServices = new VolleyServices(shippingCostCallback, context);
        volleyServices.postDataVolley(UrlKey.KEY_METHOD_SHIPPING_PRICE,
                param.ShippingPrice(
                        "152",
                        courierName,
                        districtId));
    }

    private void InitShippingCostResultCallback() {
        shippingCostCallback = new IResult() {
            @Override
            public void notifySuccess(JSONObject response) {
                try{
                    if(response.get(ParamKey.KEY_CODE).equals(ParamKey.KEY_SUCCESS)
                            || response.get(ParamKey.KEY_CODE).equals(ParamKey.KEY_SUCCESS_ONE)){

                        GetCourierCost(response);

                    }else{
                        view.GetShippingCostFailed(response.get(ParamKey.KEY_MESSAGE).toString());
                    }
                }catch (JSONException e){
                    view.GetShippingCostFailed("Something Wrong, please try again");
                    Log.e("Error ", e.getMessage());
                }
            }

            @Override
            public void notifyError(VolleyError error) {

            }
        };
    }

    private void GetCourierCost(JSONObject response) {
        view.GetShippingCostSuccess(parse.ParsingShippingCost(response));
    }

    public void GetDistrictId(String districtName){
        InitDistrictResultCallback();
        volleyServices = new VolleyServices(districtCallback, context);
        volleyServices.postDataVolley(UrlKey.KEY_METHOD_ADDRESS_DISTRIC,
                param.DistrictList(
                        "0",
                        districtName,
                        "D"
                ));
    }

    private void InitDistrictResultCallback() {
        districtCallback = new IResult() {
            @Override
            public void notifySuccess(JSONObject response) {
                try{
                    if(response.get(ParamKey.KEY_CODE).equals(ParamKey.KEY_SUCCESS)
                            || response.get(ParamKey.KEY_CODE).equals(ParamKey.KEY_SUCCESS_ONE)){

                       GetDistrictInfo(response);

                    }else{
                        view.CheckoutFailed(response.get(ParamKey.KEY_MESSAGE).toString());
                    }
                }catch (JSONException e){
                    view.CheckoutFailed("Something Wrong, please try again");
                    Log.e("Error ", e.getMessage());
                }
            }

            @Override
            public void notifyError(VolleyError error) {

            }
        };
    }

    private void GetDistrictInfo(JSONObject response) {
        ArrayList<District> districts = new ArrayList<>(parse.ParsingDistrict(response));
        view.GetDistrictIdSuccess(districts.get(1).getDistrictId());
    }

    public void FinishCheckout(String userId){
        initFinishResultCallback();
        volleyServices = new VolleyServices(finishCallback, context);
        volleyServices.postDataVolley(UrlKey.KEY_METHOD_FINISH_CART,
                param.CartFinish(userId));
    }

    private void initFinishResultCallback() {
        finishCallback = new IResult() {
            @Override
            public void notifySuccess(JSONObject response) {
                try{
                    if(response.get(ParamKey.KEY_CODE).toString().equals(ParamKey.KEY_SUCCESS)
                            || response.get(ParamKey.KEY_CODE).equals(ParamKey.KEY_SUCCESS_ONE)){

                        view.FinishCheckoutSuccess();

                    }else{
                        view.FinishCheckoutFailed();
                    }
                }catch (JSONException e){
                    view.CheckoutFailed("Something Wrong, please try again");
                    Log.e("Error ", e.getMessage());
                }
            }

            @Override
            public void notifyError(VolleyError error) {
                view.FinishCheckoutFailed();
            }
        };
    }

    public void addAddressCheckout(String addressId){
        initAddAddressResultCallback();
        String orderId = session.getValue(Session.KEY_ORDER_ID);
        String userId = session.getValue(Session.KEY_USER_ID);
        volleyServices = new VolleyServices(addressCallback, context);
        volleyServices.postDataVolley(UrlKey.KEY_METHOD_ORDER_ADDRESS,
                param.OrderAddress(userId, orderId, addressId));
    }

    private void initAddAddressResultCallback() {
        addressCallback  = new IResult() {
            @Override
            public void notifySuccess(JSONObject response) {
                try{
                    if(response.get(ParamKey.KEY_CODE).equals(ParamKey.KEY_SUCCESS)
                            || response.get(ParamKey.KEY_CODE).equals(ParamKey.KEY_SUCCESS_ONE)){

                        view.AddOrderAddressSuccess();

                    }else{
                        view.AddOrderAddressFailed();
                    }
                }catch (JSONException e){
                    view.CheckoutFailed("Something Wrong, please try again");
                    Log.e("Error ", e.getMessage());
                    view.AddOrderAddressFailed();
                }
            }

            @Override
            public void notifyError(VolleyError error) {
                view.AddOrderAddressFailed();
            }
        };
    }


}
