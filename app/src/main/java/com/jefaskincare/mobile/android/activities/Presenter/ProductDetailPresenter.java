package com.jefaskincare.mobile.android.activities.Presenter;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.android.volley.VolleyError;
import com.jefaskincare.mobile.android.activities.Model.CartData;
import com.jefaskincare.mobile.android.activities.View.ProductDetailView;
import com.jefaskincare.mobile.android.api.ApiServices;
import com.jefaskincare.mobile.android.api.IResult;
import com.jefaskincare.mobile.android.api.ParamKey;
import com.jefaskincare.mobile.android.api.Parsing;
import com.jefaskincare.mobile.android.api.UrlKey;
import com.jefaskincare.mobile.android.api.VolleyServices;
import com.jefaskincare.mobile.android.db.DBHelper;
import com.jefaskincare.mobile.android.fragment.profile.Model.User;
import com.jefaskincare.mobile.android.fragment.shop.Model.Product;
import com.jefaskincare.mobile.android.manager.Session;

import org.json.JSONException;
import org.json.JSONObject;

public class ProductDetailPresenter {

    private Context context;
    private ProductDetailView view;
    private IResult resultCallback = null;
    private IResult addCartCallback;
    private IResult updateCartCallback;
    private VolleyServices volleyServices;
    private ApiServices params;
    private Session session;
    private User user;
    private Parsing parse;
    private String productid;
    private String price;

    public ProductDetailPresenter(Context context, ProductDetailView view) {
        this.context = context;
        this.view = view;
        params = new ApiServices();
        session = new Session(context);
        parse = new Parsing();
        GetUserInfo();
    }

    private void GetUserInfo() {
        user = parse.ParsingUser(session.getValue(Session.KEY_LOGIN_DATA));
    }

    public void GetProductDetail(String prodid) {
        InitResultCallback();
        volleyServices = new VolleyServices(resultCallback, context);
        volleyServices.postDataVolley(UrlKey.KEY_METHOD_PRODUCT_DETAIL,
                params.ProductDetail(prodid));
    }

    private void InitResultCallback() {
        resultCallback = new IResult() {
            @Override
            public void notifySuccess(JSONObject response) {
                try {
                    if (response.get(ParamKey.KEY_CODE).equals(ParamKey.KEY_SUCCESS)
                            || response.get(ParamKey.KEY_CODE).equals(ParamKey.KEY_SUCCESS_ONE)) {

                        view.GetProductSuccess(parse.ParsingProductDetail(response));

                    } else {

                        view.GetProductFailed(response.get(ParamKey.KEY_MESSAGE).toString());

                    }
                } catch (JSONException e) {
                    view.GetProductFailed("No Data");
                }
            }

            @Override
            public void notifyError(VolleyError error) {

            }
        };

    }

    public void AddToCart(String prodid, String price) {
        DBHelper db = new DBHelper(context);
        db.insertCart(prodid, user.getUserid(), price, 1);
        db.close();
    }

    public void addCart(String productID,
                        String price,
                        String qty) {
        InitAddCartCallback();
        volleyServices = new VolleyServices(addCartCallback, context);
        volleyServices.postDataVolley(UrlKey.KEY_METHOD_ADD_CART,
                params.CartAdd(
                        user.getUserid(),
                        productID,
                        price,
                        qty));
    }

    private void InitAddCartCallback() {
        addCartCallback = new IResult() {
            @Override
            public void notifySuccess(JSONObject response) {
                try {
                    if (response.get(ParamKey.KEY_CODE).equals(ParamKey.KEY_SUCCESS)
                            || response.get(ParamKey.KEY_CODE).equals(ParamKey.KEY_SUCCESS_ONE)) {

                        UpdateCounter(response.get(ParamKey.KEY_DATA).toString());
                        view.AddToCartSuccess();

                    } else {

                        view.AddToCartFailed();

                    }
                } catch (JSONException e) {
                    view.AddToCartFailed();
                }
            }

            @Override
            public void notifyError(VolleyError error) {
                view.AddToCartFailed();
            }
        };
    }

    private void UpdateCounter(String data) {
        CartData cart = parse.ParsingCartData(data);
        session.putSessionStr(Session.KEY_ORDER_ID, cart.getOrderid());
        session.putSessionStr(Session.KEY_ORDER_DETAIL_ID, cart.getOrderdetailid());
        int qty = Integer.parseInt(session.getValue(Session.KEY_CART_COUNTER));
        qty = qty + 1;
        session.putSessionStr(Session.KEY_CART_COUNTER, String.valueOf(qty));

    }


}
