package com.jefaskincare.mobile.android.api;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class ApiServices {

    private JSONObject params = null;

    public JSONObject Login(String email, String password){
        params = new JSONObject();
        try{
            params.put(ParamKey.KEY_LOGIN_EMAIL, email);
            params.put(ParamKey.KEY_LOGIN_PASSWORD, password);
            return params;
        }catch (JSONException e){
            Log.e("Error ", e.getMessage());
        }

        return params;
    }

    public JSONObject UserDetail(String userid){
        params = new JSONObject();
        try{
            params.put(ParamKey.KEY_USER_DETAIL_USERID, userid);
            return params;
        }catch (JSONException e){
            Log.e("Error ", e.getMessage());
        }

        return params;
    }

    public JSONObject Register(String password, String email,
                               String reff, String selller){
        params = new JSONObject();
        try{
            params.put(ParamKey.KEY_REGISTER_PASSWORD, password);
            params.put(ParamKey.KEY_REGISTER_EMAIL, email);
            params.put(ParamKey.KEY_REGISTER_REFF, reff);
            params.put(ParamKey.KEY_REGISTER_SELLER, selller);

            return params;
        }catch (JSONException e){
            Log.e("Error ", e.getMessage());
        }

        return params;
    }

    public JSONObject ChangePassword(String userid, String opassword, String npassword){
        params = new JSONObject();
        try{
            params.put(ParamKey.KEY_CHANGE_PASSWORD_USERID, userid);
            params.put(ParamKey.KEY_CHANGE_PASSWORD_OPASSWORD, opassword);
            params.put(ParamKey.KEY_CHANGE_PASSWORD_NPASSWORD, npassword);
            return params;
        }catch (JSONException e){
            Log.e("Error ", e.getMessage());
        }

        return params;
    }

    public JSONObject BestSeller(){
        params = new JSONObject();
        try{
            params.put(ParamKey.KEY_BEST_SELLER_LIMIT, 5);
            return params;
        }catch (JSONException e){
            Log.e("Error ", e.getMessage());
        }

        return params;
    }

    public JSONObject ProvinceList(String id, String name, String act){
        params = new JSONObject();
        try{
            params.put(ParamKey.KEY_ADDRESS_ID, id);
            params.put(ParamKey.KEY_ADDRESS_NAME, name);
            params.put(ParamKey.KEY_ADDRESS_ACT, act);
            return params;
        }catch (JSONException e){
            Log.e("Error ", e.getMessage());
        }

        return params;
    }

    public JSONObject CityList(String id, String name, String act){
        params = new JSONObject();
        try{
            params.put(ParamKey.KEY_ADDRESS_ID, id);
            params.put(ParamKey.KEY_ADDRESS_NAME, name);
            params.put(ParamKey.KEY_ADDRESS_ACT, act);
            return params;
        }catch (JSONException e){
            Log.e("Error ", e.getMessage());
        }

        return params;
    }

    public JSONObject DistrictList(String id, String name, String act){
        params = new JSONObject();
        try{
            params.put(ParamKey.KEY_ADDRESS_ID, id);
            params.put(ParamKey.KEY_ADDRESS_NAME, name);
            params.put(ParamKey.KEY_ADDRESS_ACT, act);
            return params;
        }catch (JSONException e){
            Log.e("Error ", e.getMessage());
        }

        return params;
    }

    public JSONObject ProductDetail(String prodid){
        params = new JSONObject();
        try{
            params.put(ParamKey.KEY_PRODUCT_ID, prodid);
            return params;
        }catch (JSONException e){
            Log.e("Error ", e.getMessage());
        }

        return params;
    }

    public JSONObject ProductWithFilter(String catid){
        params = new JSONObject();
        try{
            params.put(ParamKey.KEY_PRODUCT_CATID, catid);
            return params;
        }catch (JSONException e){
            Log.e("Error ", e.getMessage());
        }

        return params;
    }

    public JSONObject AddressUser(String userid){
        params = new JSONObject();
        try{
            params.put(ParamKey.KEY_ADDRESS_USERID, userid);
            return params;
        }catch (JSONException e){
            Log.e("Error ", e.getMessage());
        }

        return params;
    }

    public JSONObject AddressRemove(String userid,
                                    String addressId){
        params = new JSONObject();
        try{
            params.put(ParamKey.KEY_ADDRESS_USERID, userid);
            params.put(ParamKey.KEY_ADDRESS_ADDRESS_ID, addressId);
            return params;
        }catch (JSONException e){
            Log.e("Error ", e.getMessage());
        }

        return params;
    }

    public JSONObject ChangeAddress(String userid,
                                    String address,
                                    String addressid,
                                    String district,
                                    String zip,
                                    double latitude,
                                    double longitude,
                                    String addressname,
                                    String addressphone){
        params = new JSONObject();
        try{
            params.put(ParamKey.KEY_ADDRESS_USERID, userid);
            params.put(ParamKey.KEY_ADDRESS_ADDRESS, address);
            params.put(ParamKey.KEY_ADDRESS_ADDRESS_ID, addressid);
            params.put(ParamKey.KEY_ADDRESS_DISTRICT_ID, district);
            params.put(ParamKey.KEY_ADDRESS_ZIP, zip);
            params.put(ParamKey.KEY_ADDRESS_LATITUDE, latitude);
            params.put(ParamKey.KEY_ADDRESS_LONGITUDE, longitude);
            params.put(ParamKey.KEY_ADDRESS_ADDRESSNAME, addressname);
            params.put(ParamKey.KEY_ADDRESS_ADDRESSPHONE, addressphone);
            return params;
        }catch (JSONException e){
            Log.e("Error ", e.getMessage());
        }

        return params;
    }

    public JSONObject AddAddress(String userid,
                                 String address,
                                 String district,
                                 String zip,
                                 double latitude,
                                 double longitude,
                                 String addressname,
                                 String addressphone){
        params = new JSONObject();
        try{
            params.put(ParamKey.KEY_ADDRESS_USERID, userid);
            params.put(ParamKey.KEY_ADDRESS_ADDRESS, address);
            params.put(ParamKey.KEY_ADDRESS_DISTRICT_ID, district);
            params.put(ParamKey.KEY_ADDRESS_ZIP, zip);
            params.put(ParamKey.KEY_ADDRESS_LATITUDE, String.valueOf(latitude));
            params.put(ParamKey.KEY_ADDRESS_LONGITUDE, String.valueOf(longitude));
            params.put(ParamKey.KEY_ADDRESS_ADDRESSNAME, addressname);
            params.put(ParamKey.KEY_ADDRESS_ADDRESSPHONE, addressphone);
            return params;
        }catch (JSONException e){
            Log.e("Error ", e.getMessage());
        }

        return params;
    }

    public JSONObject CartList(String userid){
        params = new JSONObject();
        try{
            params.put(ParamKey.KEY_ADDRESS_USERID, userid);
            return params;
        }catch (JSONException e){
            Log.e("Error ", e.getMessage());
        }

        return params;
    }

    public JSONObject CartAdd(String userid,
                              String productid,
                              String price,
                              String qty){
        params = new JSONObject();
        try{
            params.put(ParamKey.KEY_ADDRESS_USERID, userid);
            params.put(ParamKey.KEY_CART_PRODUCT_ID, productid);
            params.put(ParamKey.KEY_CART_PRICE, price);
            params.put(ParamKey.KEY_CART_QTY, qty);
            return params;
        }catch (JSONException e){
            Log.e("Error ", e.getMessage());
        }

        return params;
    }

    public JSONObject CartUpdate(String userid,
                                 String productid,
                                 String price,
                                 String qty){
        params = new JSONObject();
        try{
            params.put(ParamKey.KEY_ADDRESS_USERID, userid);
            params.put(ParamKey.KEY_CART_PRODUCT_ID, productid);
            params.put(ParamKey.KEY_CART_PRICE, price);
            params.put(ParamKey.KEY_CART_QTY, qty);
            return params;
        }catch (JSONException e){
            Log.e("Error ", e.getMessage());
        }

        return params;
    }

    public JSONObject CartRemove(String orderdetailid,
                                 String userid,
                                 String productid){
        params = new JSONObject();
        try{
            params.put(ParamKey.KEY_CART_ORDER_DETAIL_ID, orderdetailid);
            params.put(ParamKey.KEY_ADDRESS_USERID, userid);
            params.put(ParamKey.KEY_CART_PRODUCT_ID, productid);

            return params;
        }catch (JSONException e){
            Log.e("Error ", e.getMessage());
        }

        return params;
    }

    public JSONObject CartClear(String userid){
        params = new JSONObject();
        try{
            params.put(ParamKey.KEY_ADDRESS_USERID, userid);
            return params;
        }catch (JSONException e){
            Log.e("Error ", e.getMessage());
        }

        return params;
    }

    public JSONObject CartFinish(String userId){
        params = new JSONObject();
        try{
            params.put(ParamKey.KEY_CART_USER_ID, userId);
            return params;
        }catch (JSONException e){
            Log.e("Error ", e.getMessage());
        }
        return params;
    }

    public JSONObject OrderAddress(String userId,
                                   String orderId,
                                   String addressId){
        params = new JSONObject();
        try{
            params.put(ParamKey.KEY_CART_USER_ID, userId);
            params.put(ParamKey.KEY_CART_ORDER_ID, orderId);
            params.put(ParamKey.KEY_CART_ADDRESS_ID, addressId);
            return params;
        }catch (JSONException e){
            Log.e("Error ", e.getMessage());
        }
        return params;
    }

    public JSONObject CourierList(String cityId){
        params = new JSONObject();
        try{
            params.put(ParamKey.KEY_SHIPPING_CITY_ID, cityId);
            return params;
        }catch (JSONException e){
            Log.e("Error ", e.getMessage());
        }
        return params;
    }

    public JSONObject ShippingPrice(String origincityid,
                                    String couriername,
                                    String targetdistrictid){
        params = new JSONObject();
        try{
            params.put(ParamKey.KEY_SHIPPING_ORIGIN, origincityid);
            params.put(ParamKey.KEY_SHIPPING_COURIER, couriername);
            params.put(ParamKey.KEY_SHIPPING_TARGET, targetdistrictid);
            return params;
        }catch (JSONException e){
            Log.e("Error ", e.getMessage());
        }
        return params;
    }

    public JSONObject GetBanner(){
        params = new JSONObject();
        return params;
    }

}
