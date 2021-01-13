package com.jefaskincare.mobile.android.activities.Presenter;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.android.volley.VolleyError;
import com.jefaskincare.mobile.android.activities.Model.CartData;
import com.jefaskincare.mobile.android.activities.View.CartView;
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

import java.util.ArrayList;
import java.util.HashMap;

public class CartPresenter {

    private Context context;
    private Session session;
    private User user;
    private CartView view;
    private boolean add;
    private int totalPayment;
    private IResult addCartCallback;
    private IResult removeCartCallback;
    private IResult getCartCallback;
    private IResult updateCartCallback;
    private VolleyServices volleyServices;
    private ApiServices params;
    private Parsing parse;

    public CartPresenter(Context context, CartView view) {
        this.context = context;
        this.view = view;
        params = new ApiServices();
        session = new Session(context);
        parse = new Parsing();
        user = parse.ParsingUser(session.getValue(Session.KEY_LOGIN_DATA));
    }

    public void getCart() {
        if (user != null) {
            InitGetCartCallback();
            volleyServices = new VolleyServices(getCartCallback, context);
            volleyServices.postDataVolley(UrlKey.KEY_METHOD_LIST_CART,
                    params.CartList(user.getUserid()));
        } else {
            view.noCartFound();
        }
    }

    private void InitGetCartCallback() {
        getCartCallback = new IResult() {
            @Override
            public void notifySuccess(JSONObject response) {
                try {
                    if (response.get(ParamKey.KEY_CODE).equals(ParamKey.KEY_SUCCESS)
                            || response.get(ParamKey.KEY_CODE).equals(ParamKey.KEY_SUCCESS_ONE)) {

                        cartProcess(response);

                    } else {

                        view.noCartFound();

                    }
                } catch (JSONException e) {
                    view.noCartFound();
                }
            }

            @Override
            public void notifyError(VolleyError error) {
                view.noCartFound();
            }
        };
    }

    private void cartProcess(JSONObject response) {
        ArrayList<Product> productList = new ArrayList<>(parse.ParsingProduct(response));
        int totalPayment = 0;
        for (int i = 0; i < productList.size(); i++) {
            totalPayment = totalPayment + Integer.parseInt(productList.get(i).getOrdersub());
        }
        view.getCartSuccess(productList, totalPayment);
    }

    public void addCart(String productID,
                        String price,
                        String qty) {
        initAddCartCallback();
        volleyServices = new VolleyServices(addCartCallback, context);
        volleyServices.postDataVolley(UrlKey.KEY_METHOD_ADD_CART,
                params.CartAdd(
                        user.getUserid(),
                        productID,
                        price,
                        qty));
    }

    private void initAddCartCallback() {
        addCartCallback = new IResult() {
            @Override
            public void notifySuccess(JSONObject response) {

            }

            @Override
            public void notifyError(VolleyError error) {

            }
        };
    }

    public void removeCart(String productId) {
        initRemoveCartCallback();
        volleyServices = new VolleyServices(removeCartCallback, context);
        volleyServices.postDataVolley(UrlKey.KEY_METHOD_REMOVE_CART,
                params.CartRemove(
                        session.getValue(Session.KEY_ORDER_DETAIL_ID),
                        user.getUserid(),
                        productId
                ));
    }

    private void initRemoveCartCallback() {
        removeCartCallback = new IResult() {
            @Override
            public void notifySuccess(JSONObject response) {
                try {
                    if (response.get(ParamKey.KEY_CODE).equals(ParamKey.KEY_SUCCESS)
                            || response.get(ParamKey.KEY_CODE).equals(ParamKey.KEY_SUCCESS_ONE)) {

                        view.deleteItemSuccess();

                    } else {

                        view.deleteItemFailed();

                    }
                } catch (JSONException e) {
                    view.deleteItemFailed();
                }
            }

            @Override
            public void notifyError(VolleyError error) {
                view.deleteItemFailed();
            }
        };
    }

    public void updateCart(String productId,
                           String price,
                           String qty,
                           boolean add) {
        this.add = add;
        initUpdateCartResultCallback();
        volleyServices = new VolleyServices(updateCartCallback, context);
        volleyServices.postDataVolley(UrlKey.KEY_METHOD_UPDATE_CART,
                params.CartUpdate(
                        user.getUserid(),
                        productId,
                        price,
                        qty
                ));
    }

    private void initUpdateCartResultCallback() {
        updateCartCallback = new IResult() {
            @Override
            public void notifySuccess(JSONObject response) {
                try {
                    if (response.get(ParamKey.KEY_CODE).equals(ParamKey.KEY_SUCCESS)
                            || response.get(ParamKey.KEY_CODE).equals(ParamKey.KEY_SUCCESS_ONE)) {

                        UpdateCounter();
                        CheckOrderDetailID(response.get(ParamKey.KEY_MESSAGE).toString());
                        view.updateCartSuccess();

                    } else {

                        view.updateCartFailed();

                    }
                } catch (JSONException e) {
                    view.updateCartFailed();
                }
            }

            @Override
            public void notifyError(VolleyError error) {
                view.updateCartFailed();
            }
        };
    }

    private void UpdateCounter() {
        int qty = Integer.parseInt(session.getValue(Session.KEY_CART_COUNTER));
        if (add) {
            qty = qty + 1;
        } else {
            qty = qty - 1;
        }
        session.putSessionStr(Session.KEY_CART_COUNTER, String.valueOf(qty));

    }

    private void CheckOrderDetailID(String message) {
        String orderDetailId = session.getValue(Session.KEY_ORDER_DETAIL_ID);
        if(orderDetailId.isEmpty()){
            String[] messages = message.split("\\[");
            orderDetailId = messages[1].replace("]", "");
            session.putSessionStr(Session.KEY_ORDER_DETAIL_ID, orderDetailId);
        }
    }

    public void UpdateCounterRemoves(int removeQty) {
        int qty = Integer.parseInt(session.getValue(Session.KEY_CART_COUNTER));
        qty = qty - removeQty;
        session.putSessionStr(Session.KEY_CART_COUNTER, String.valueOf(qty));

    }

    public void ClearCounter() {
        session.putSessionStr(Session.KEY_CART_COUNTER, String.valueOf(0));

    }

}
