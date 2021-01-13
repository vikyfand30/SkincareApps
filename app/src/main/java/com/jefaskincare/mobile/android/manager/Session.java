package com.jefaskincare.mobile.android.manager;

import android.content.Context;
import android.content.SharedPreferences;

public class Session {

    public static final String KEY_SESSION = "Jefa";
    public static final String KEY_LOGIN_DATA = "Login Data";
    public static final String KEY_CATEGORY_DATA = "Category";
    public static final String KEY_PRODUCT_DATA = "Product";
    public static final String KEY_CART_COUNTER = "counter";
    public static final String KEY_USER_ID = "userid";
    public static final String KEY_ORDER_ID = "orderid";
    public static final String KEY_ORDER_DETAIL_ID = "orderdetailid";
    public static final String LOGIN_OK = "LoginOk";

    SharedPreferences sp;
    SharedPreferences.Editor spEditor;

    public Session(Context context){
        sp = context.getSharedPreferences(KEY_SESSION, Context.MODE_PRIVATE);
        spEditor = sp.edit();
    }

    public void putSessionStr(String keySP, String value){
        spEditor.putString(keySP, value);
        spEditor.apply();
    }

    public void putLoginOk(String keySP, Boolean value){
        spEditor.putBoolean(keySP, value);
        spEditor.apply();
    }

    public String getValue(String key){
        return sp.getString(key, "");
    }

    public String getKeyCategoryData(String key){
        return sp.getString(key, "");
    }

    public Boolean getLoginOk(String key) {
        return sp.getBoolean(key, false);
    }
}
