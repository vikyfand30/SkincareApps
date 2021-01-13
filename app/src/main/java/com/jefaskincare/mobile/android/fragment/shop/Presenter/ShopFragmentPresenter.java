package com.jefaskincare.mobile.android.fragment.shop.Presenter;

import android.content.Context;
import android.view.View;

import com.android.volley.VolleyError;
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
import com.jefaskincare.mobile.android.fragment.shop.View.ShopFragmentView;
import com.jefaskincare.mobile.android.manager.Session;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ShopFragmentPresenter implements ShopFragmentView.Presenter {

    private ShopFragmentView.View view;
    private Context context;
    private IResult categoryCallback;
    private IResult itemsCallback;
    private IResult bestProductCallback;
    private IResult bannerCallback;
    private VolleyServices volleyServices;
    private Session session;

    public ShopFragmentPresenter(Context context, ShopFragmentView.View view){
        this.context = context;
        this.view = view;
        session = new Session(context);
    }

    private void getCategoryData(){
        initCategoryCallback();
        volleyServices = new VolleyServices(categoryCallback, context);
        volleyServices.getDataVolley(UrlKey.KEY_METHOD_CATEGORY_LIST);
    }

    private void initCategoryCallback() {
        categoryCallback = new IResult() {
            @Override
            public void notifySuccess(JSONObject response) {
                try{
                    if(response.get(ParamKey.KEY_CODE).equals(ParamKey.KEY_SUCCESS)
                            || response.get(ParamKey.KEY_CODE).equals(ParamKey.KEY_SUCCESS_ONE)){
                        ParsingCategory(response);
                    }else{
                        view.getCategoryFailed(response.get(ParamKey.KEY_MESSAGE).toString());
                    }
                }catch (JSONException e){
                    view.getCategoryFailed(e.getMessage());
                }

            }

            @Override
            public void notifyError(VolleyError error) {
            view.getCategoryFailed(error.getMessage());
            }
        };
    }

    private void ParsingCategory(JSONObject response) {
        Parsing parse = new Parsing();
        session.putSessionStr(Session.KEY_CATEGORY_DATA, response.toString());
        ArrayList<Category> backgroundList = new ArrayList<>(CategoryData.getListBackgroundColor());
        ArrayList<Category> categoryList = new ArrayList<>(parse.ParsingCategory(response));
        view.setRvCategory(categoryList, backgroundList);
    }

    private void getProductItem(){
        initProductCallback();
        volleyServices = new VolleyServices(itemsCallback, context);
        volleyServices.getDataVolley(UrlKey.KEY_METHOD_PRODUCT_LIST);
    }

    private void initProductCallback() {
        itemsCallback = new IResult() {
            @Override
            public void notifySuccess(JSONObject response) {
                try{
                    if(response.get(ParamKey.KEY_CODE).equals(ParamKey.KEY_SUCCESS)
                            || response.get(ParamKey.KEY_CODE).equals(ParamKey.KEY_SUCCESS_ONE)){
                        ParsingProduct(response);
                    }else{
                        view.getCategoryFailed(response.get(ParamKey.KEY_MESSAGE).toString());
                    }
                }catch (JSONException e){
                    view.getProductFailed(e.getMessage());
                }
            }

            @Override
            public void notifyError(VolleyError error) {
                view.getProductFailed(error.getMessage());
            }
        };
    }

    private void ParsingProduct(JSONObject response) {
        Parsing parse = new Parsing();
        session.putSessionStr(Session.KEY_PRODUCT_DATA, response.toString());
        ArrayList<Product> productList = new ArrayList<>(ProductData.getListItems());
        ArrayList<Product> productList2 = new ArrayList<>(parse.ParsingProduct(response));
        view.setRvItemsProduct(productList, productList2);
    }

    private void getBestSellerData(){
        initBestProductCallback();
        ApiServices params = new ApiServices();
        volleyServices = new VolleyServices(bestProductCallback, context);
        volleyServices.postDataVolley(UrlKey.KEY_METHOD_BEST_SELLER_LIST, params.BestSeller());
    }

    private void initBestProductCallback() {
        bestProductCallback = new IResult() {
            @Override
            public void notifySuccess(JSONObject response) {
                try{
                    if(response.get(ParamKey.KEY_CODE).equals(ParamKey.KEY_SUCCESS)
                            || response.get(ParamKey.KEY_CODE).equals(ParamKey.KEY_SUCCESS_ONE)){

                        ParsingBestProduct(response);
                    }else{
                        view.getCategoryFailed(response.get(ParamKey.KEY_MESSAGE).toString());
                    }
                }catch (JSONException e){
                    view.getProductFailed(e.getMessage());
                }
            }

            @Override
            public void notifyError(VolleyError error) {
                view.getProductFailed(error.getMessage());
            }
        };
    }

    private void ParsingBestProduct(JSONObject response) {
        Parsing parse = new Parsing();
        ArrayList<Product> listBestSeller = new ArrayList<>(ProductBestSellerData.getListItems());
        ArrayList<Product> listBestSeller2 = new ArrayList<>(parse.ParsingBestSeller(response));
        view.setRvBestSeller(listBestSeller, listBestSeller2);
    }

    private void getBanner(){
        initBannerResultCallback();
        ApiServices params = new ApiServices();
        volleyServices = new VolleyServices(bannerCallback, context);
        volleyServices.postDataVolley(UrlKey.KEY_METHOD_GET_BANNER,
                params.GetBanner());
    }

    private void initBannerResultCallback() {
        bannerCallback = new IResult() {
            @Override
            public void notifySuccess(JSONObject response) {
                try{
                    if(response.get(ParamKey.KEY_CODE).equals(ParamKey.KEY_SUCCESS)
                            || response.get(ParamKey.KEY_CODE).equals(ParamKey.KEY_SUCCESS_ONE)){

                        ParsingBanner(response);
                    }else{
                        view.getCategoryFailed(response.get(ParamKey.KEY_MESSAGE).toString());
                    }
                }catch (JSONException e){
                    view.getProductFailed(e.getMessage());
                }
            }

            @Override
            public void notifyError(VolleyError error) {

            }
        };
    }

    private void ParsingBanner(JSONObject response) {
        Parsing parse = new Parsing();
        view.setBanner(parse.ParsingBannerList(response));
    }

    @Override
    public void init() {
        getBanner();
        getCategoryData();
        getProductItem();
        getBestSellerData();
    }
}
