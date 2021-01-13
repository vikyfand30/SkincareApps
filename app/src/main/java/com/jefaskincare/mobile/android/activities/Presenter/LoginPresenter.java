package com.jefaskincare.mobile.android.activities.Presenter;

import android.content.Context;
import android.util.Log;

import com.android.volley.VolleyError;
import com.jefaskincare.mobile.android.activities.View.LoginView;
import com.jefaskincare.mobile.android.api.ApiServices;
import com.jefaskincare.mobile.android.api.IResult;
import com.jefaskincare.mobile.android.api.ParamKey;
import com.jefaskincare.mobile.android.api.Parsing;
import com.jefaskincare.mobile.android.api.UrlKey;
import com.jefaskincare.mobile.android.api.VolleyServices;
import com.jefaskincare.mobile.android.fragment.profile.Model.User;
import com.jefaskincare.mobile.android.fragment.shop.Model.Product;
import com.jefaskincare.mobile.android.manager.Session;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class LoginPresenter {

    private Context context;
    private LoginView.View view;
    private VolleyServices volleyServices;
    private IResult resultCallback = null;
    private IResult resultCallbackUserDetail = null;
    private IResult getCartCallback;
    private ApiServices param;
    private Parsing parse;
    private Session session;

    public LoginPresenter(Context context, LoginView.View view){
        this.context = context;
        this.view = view;
        parse = new Parsing();
        session = new Session(context);
        initMethod();
    }

    private void initMethod() {
        initResultCallback();
        volleyServices = new VolleyServices(resultCallback, context);
        param = new ApiServices();
    }

    public void LoginProcess(String email, String password){
        volleyServices.postDataVolley(UrlKey.KEY_METHOD_LOGIN,
                param.Login(email, password));
    }

    private void initResultCallback() {
        resultCallback = new IResult() {
            @Override
            public void notifySuccess(JSONObject response) {
                try{
                    if(response.get(ParamKey.KEY_CODE).equals(ParamKey.KEY_SUCCESS)
                    || response.get(ParamKey.KEY_CODE).equals(ParamKey.KEY_SUCCESS_ONE)){

                        DetailUserProcess(response.get(ParamKey.KEY_DATA).toString());

                    }else{
                        view.LoginFailed(response.get(ParamKey.KEY_MESSAGE).toString());
                    }
                }catch (JSONException e){
                    view.LoginFailed("Something Wrong, please try again");
                Log.e("Error ", e.getMessage());
                }
            }

            @Override
            public void notifyError(VolleyError error) {
                view.LoginFailed(error.getMessage());
                Log.e("Error ", error.getMessage());
            }
        };
    }

    private void DetailUserProcess(String data){
        initResultCallbackUserDetail();
        Parsing parse = new Parsing();
        User user = parse.ParsingUser(data);

        if (user.getResult().equals("1")) {
            session.putSessionStr(Session.KEY_USER_ID, user.getUserid());
            volleyServices = new VolleyServices(resultCallbackUserDetail, context);
            volleyServices.postDataVolley(UrlKey.KEY_METHOD_USER_DETAIL,
                    param.UserDetail(user.getUserid()));
        } else {
            view.LoginFailed(user.getMessage());
        }
    }

    private void initResultCallbackUserDetail() {
        resultCallbackUserDetail = new IResult() {
            @Override
            public void notifySuccess(JSONObject response) {
                try{
                    if(response.get(ParamKey.KEY_CODE).equals(ParamKey.KEY_SUCCESS)
                            || response.get(ParamKey.KEY_CODE).equals(ParamKey.KEY_SUCCESS_ONE)){

                        DetailHandler(response);

                    }else{
                        view.LoginFailed(response.get(ParamKey.KEY_MESSAGE).toString());
                    }
                }catch (JSONException e){
                    view.LoginFailed("Something Wrong, please try again");
                    Log.e("Error ", e.getMessage());
                }
            }

            @Override
            public void notifyError(VolleyError error) {
                view.LoginFailed(error.getMessage());
                Log.e("Error ", error.getMessage());
            }
        };
    }

    private void DetailHandler(JSONObject response){
        Parsing parse = new Parsing();
        try{
            String data = response.get(ParamKey.KEY_DATA).toString();
            session.putSessionStr(Session.KEY_LOGIN_DATA, data);
            view.LoginSuccess(parse.ParsingUserDetail(data));
        }catch (JSONException e){
            view.LoginFailed(e.getMessage());
            Log.e("Error ", e.getMessage());
        }
    }

    public void getCart(User user){
        InitGetCartCallback();
        volleyServices = new VolleyServices(getCartCallback, context);
        volleyServices.postDataVolley(UrlKey.KEY_METHOD_LIST_CART,
                param.CartList(user.getUserid()));
    }

    private void InitGetCartCallback() {
        getCartCallback = new IResult() {
            @Override
            public void notifySuccess(JSONObject response) {
                try {
                    if(response.get(ParamKey.KEY_CODE).equals(ParamKey.KEY_SUCCESS)
                            || response.get(ParamKey.KEY_CODE).equals(ParamKey.KEY_SUCCESS_ONE)){

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

            }
        };
    }

    private void cartProcess(JSONObject response) {
        ArrayList<Product> productList = new ArrayList<>(parse.ParsingProduct(response));
        int totalPayment = 0;
        for (int i = 0; i < productList.size() ; i++){
            totalPayment = totalPayment + Integer.parseInt(productList.get(i).getOrdersub());
        }
        view.getCartSuccess(productList, totalPayment);
    }

}
