package com.jefaskincare.mobile.android.activities.View;

import com.jefaskincare.mobile.android.activities.Model.ViewProduct;
import com.jefaskincare.mobile.android.fragment.shop.Model.Category;
import com.jefaskincare.mobile.android.fragment.shop.Model.Product;

import java.util.ArrayList;

public interface ViewProductView {

    interface View{
        void SetRVCategory(ArrayList<Category> categoryList, ArrayList<Category> backgroundList);
        void SetRVProduct(ArrayList<Product> productListImage, ArrayList<Product> productList, String pos);
        void GetProductFailed(String error);
    }
}
