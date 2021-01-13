package com.jefaskincare.mobile.android.api;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.jefaskincare.mobile.android.activities.Model.CartData;
import com.jefaskincare.mobile.android.activities.Model.City;
import com.jefaskincare.mobile.android.activities.Model.Courier;
import com.jefaskincare.mobile.android.activities.Model.District;
import com.jefaskincare.mobile.android.activities.Model.Province;
import com.jefaskincare.mobile.android.activities.Model.Shipping;
import com.jefaskincare.mobile.android.fragment.profile.Model.Address;
import com.jefaskincare.mobile.android.fragment.profile.Model.User;
import com.jefaskincare.mobile.android.fragment.shop.Model.Banner;
import com.jefaskincare.mobile.android.fragment.shop.Model.Category;
import com.jefaskincare.mobile.android.fragment.shop.Model.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Parsing {
    private Gson gson = new Gson();

    public String DeNumber(String number){
        float deNumber = Float.parseFloat(number);
        DecimalFormat formatter = new DecimalFormat("#,###,###,###");
        return formatter.format(deNumber);
    }

    public ArrayList<String> ParseArray(JSONObject respContent) {
        ArrayList<String> arrayList = new ArrayList<>();
        String varData;
        try {
            varData = respContent.get("data").toString();
            try {
                JSONArray jsonArray = new JSONArray(varData);
                for (int i = 0; i < jsonArray.length(); i++) {
                    String object = jsonArray.get(i).toString();
                    arrayList.add(object);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    public User ParsingUser(String data) {
        return gson.fromJson(data, User.class);
    }

    public CartData ParsingCartData(String data) {
        String jdata = "";
        try {
            JSONArray jsonArray = new JSONArray(data);
            jdata = jsonArray.get(0).toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return gson.fromJson(jdata, CartData.class);
    }

    public User ParsingUserDetail(String data) {
        return gson.fromJson(data, User.class);
    }

    public Address ParsingAddressUser(String data) {
        return gson.fromJson(data, Address.class);
    }

    public ArrayList<Address> ParsingAddresses(JSONObject response) {
        ArrayList<String> dataList = new ArrayList<>(ParseArray(response));
        ArrayList<Address> addressList = new ArrayList<>();
        for (int i = 0; i < dataList.size(); i++) {
            Address address = gson.fromJson(dataList.get(i), Address.class);
            addressList.add(address);
        }
        return addressList;
    }

    public City ParsingCityId(String data) {
        return gson.fromJson(data, City.class);
    }

    public Product ParsingProductDetail(JSONObject data) {
        ArrayList<String> dataList = new ArrayList<>(ParseArray(data));
        return gson.fromJson(dataList.get(0), Product.class);
    }

    public ArrayList<Category> ParsingCategory(JSONObject response) {
        ArrayList<String> dataList = new ArrayList<>(ParseArray(response));
        ArrayList<Category> categoryList = new ArrayList<>();
        Category categoryAll = new Category();

        categoryAll.setId("0");
        categoryAll.setValue("All");
        categoryList.add(categoryAll);

        for (int i = 0; i < dataList.size(); i++) {
            Category category = gson.fromJson(dataList.get(i), Category.class);
            categoryList.add(category);
        }
        return categoryList;
    }

    public ArrayList<Product> ParsingProduct(JSONObject response) {
        ArrayList<String> dataList = new ArrayList<>(ParseArray(response));
        ArrayList<Product> productList = new ArrayList<>();
        for (int i = 0; i < dataList.size(); i++) {
            Product product = gson.fromJson(dataList.get(i), Product.class);
            productList.add(product);
        }
        return productList;
    }

    public ArrayList<Product> ParsingBestSeller(JSONObject response) {
        ArrayList<String> dataList = new ArrayList<>(ParseArray(response));
        ArrayList<Product> productList = new ArrayList<>();
        for (int i = 0; i < dataList.size(); i++) {
            Product product = gson.fromJson(dataList.get(i), Product.class);
            productList.add(product);
        }
        return productList;
    }

    public ArrayList<Province> ParsingProvince(JSONObject response) {
        ArrayList<String> dataList = new ArrayList<>(ParseArray(response));
        ArrayList<Province> provinceList = new ArrayList<>();

        Province province = new Province();
        province.setProvId("0");
        province.setProvName("Pilih Provinsi");
        provinceList.add(province);

        for (int i = 0; i < dataList.size(); i++) {
            Province province1 = gson.fromJson(dataList.get(i), Province.class);
            provinceList.add(province1);
        }
        return provinceList;
    }

    public ArrayList<City> ParsingCity(JSONObject response) {
        ArrayList<String> dataList = new ArrayList<>(ParseArray(response));
        ArrayList<City> cityList = new ArrayList<>();

        City city = new City();
        city.setCityId("0");
        city.setCityName("Pilih Kabupaten / Kota");
        cityList.add(city);

        for (int i = 0; i < dataList.size(); i++) {
            City city1 = gson.fromJson(dataList.get(i), City.class);
            cityList.add(city1);
        }
        return cityList;
    }

    public ArrayList<District> ParsingDistrict(JSONObject response) {
        ArrayList<String> dataList = new ArrayList<>(ParseArray(response));
        ArrayList<District> districtList = new ArrayList<>();

        District district = new District();
        district.setDistrictId("0");
        district.setDistrictName("Pilih Kecamatan");
        districtList.add(district);

        for (int i = 0; i < dataList.size(); i++) {
            District district1 = gson.fromJson(dataList.get(i), District.class);
            districtList.add(district1);
        }
        return districtList;
    }

    public ArrayList<Courier> ParsingCourierList(JSONObject response) {
        ArrayList<String> dataList = new ArrayList<>(ParseArray(response));
        ArrayList<Courier> courierList = new ArrayList<>();
        for (int i = 0; i < dataList.size(); i++) {
            Courier courier = gson.fromJson(dataList.get(i), Courier.class);
            courierList.add(courier);
        }
        return courierList;
    }

    public Shipping ParsingShippingCost(JSONObject response) {
        ArrayList<String> dataList = new ArrayList<>(ParseArray(response));
        return  gson.fromJson(dataList.get(0), Shipping.class);
    }

    public ArrayList<Banner> ParsingBannerList(JSONObject response) {
        ArrayList<String> dataList = new ArrayList<>(ParseArray(response));
        ArrayList<Banner> bannerList = new ArrayList<>();
        for (int i = 0; i < dataList.size(); i++) {
            Banner banner = gson.fromJson(dataList.get(i), Banner.class);
            bannerList.add(banner);
        }
        return bannerList;
    }

}
