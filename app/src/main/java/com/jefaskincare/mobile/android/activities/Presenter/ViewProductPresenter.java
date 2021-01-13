package com.jefaskincare.mobile.android.activities.Presenter;

import android.content.Context;
import android.util.Log;

import com.android.volley.VolleyError;
import com.jefaskincare.mobile.android.activities.Model.ViewProduct;
import com.jefaskincare.mobile.android.activities.Model.ViewProductData;
import com.jefaskincare.mobile.android.activities.View.ViewProductView;
import com.jefaskincare.mobile.android.api.ApiServices;
import com.jefaskincare.mobile.android.api.IResult;
import com.jefaskincare.mobile.android.api.ParamKey;
import com.jefaskincare.mobile.android.api.Parsing;
import com.jefaskincare.mobile.android.api.UrlKey;
import com.jefaskincare.mobile.android.api.VolleyServices;
import com.jefaskincare.mobile.android.fragment.shop.Model.Category;
import com.jefaskincare.mobile.android.fragment.shop.Model.CategoryData;
import com.jefaskincare.mobile.android.fragment.shop.Model.Product;
import com.jefaskincare.mobile.android.fragment.shop.Model.ProductBestSellerData;
import com.jefaskincare.mobile.android.fragment.shop.Model.ProductData;
import com.jefaskincare.mobile.android.manager.Session;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ViewProductPresenter {
    private Context context;
    private ViewProductView.View view;
    private IResult productCallback;
    private IResult productFilterCallback;
    private VolleyServices volleyServices;
    private ApiServices params;
    private Session session;
    private String pos;

    public ViewProductPresenter(Context context, ViewProductView.View view) {
        this.context = context;
        this.view = view;
        session = new Session(context);
        params = new ApiServices();
    }

    public void GetViewCategory() {
        Parsing parse = new Parsing();
        ArrayList<Category> categoryList = new ArrayList<>(parse.ParsingCategory(GetCategoryFromSession()));
        ArrayList<Category> backgroundList = new ArrayList<Category>(CategoryData.getListBackgroundViewColor());
        view.SetRVCategory(categoryList, backgroundList);
    }

    private JSONObject GetCategoryFromSession() {
        JSONObject jsonData = null;
        try{
            jsonData = new JSONObject(session.getKeyCategoryData(Session.KEY_CATEGORY_DATA));
            return jsonData;
        }catch (JSONException e){
            Log.e("Error ", e.getMessage());
        }
        return  jsonData;
    }

    public void GetAllProduct(){
        Parsing parse = new Parsing();
        ArrayList<Product> productList = new ArrayList<>(parse.ParsingProduct(GetAllProductFromSession()));
        ArrayList<Product> productListImage = new ArrayList<>(ProductData.getListItemView());
        view.SetRVProduct(productListImage, productList, "0");
    }

    private JSONObject GetAllProductFromSession() {
        JSONObject jsonData = null;
        try{
            jsonData = new JSONObject(session.getKeyCategoryData(Session.KEY_PRODUCT_DATA));
            return jsonData;
        }catch (JSONException e){
            Log.e("Error ", e.getMessage());
        }
        return  jsonData;
    }

    public void GetViewProduct(String catid) {
        if(catid.equals("0")){
            GetAllProduct();
        }else{
            InitProductCallback();
            pos = catid;
            volleyServices = new VolleyServices(productCallback, context);
            volleyServices.postDataVolley(UrlKey.KEY_METHOD_PRODUCT_LIST_FILTER,
                    params.ProductWithFilter(catid));
        }

    }

    private void InitProductCallback() {
        productCallback = new IResult() {
            @Override
            public void notifySuccess(JSONObject response) {
                try{
                    if(response.get(ParamKey.KEY_CODE).equals(ParamKey.KEY_SUCCESS)
                            || response.get(ParamKey.KEY_CODE).equals(ParamKey.KEY_SUCCESS_ONE)){
                        ParsingProduct(response);
                    }else{
                        view.GetProductFailed(response.get(ParamKey.KEY_MESSAGE).toString());
                    }
                }catch (JSONException e){
                    view.GetProductFailed(e.getMessage());
                }
            }

            @Override
            public void notifyError(VolleyError error) {

            }
        };
    }

    private void ParsingProduct(JSONObject response) {
        Parsing parse = new Parsing();
        ArrayList<Product> listProductImage = new ArrayList<>(ProductData.getListItemView());
        ArrayList<Product> productList = new ArrayList<>(parse.ParsingProduct(response));
        view.SetRVProduct(listProductImage, productList, pos);
    }
}
