package com.jefaskincare.mobile.android.fragment.shop.View;

import com.jefaskincare.mobile.android.fragment.shop.Model.Banner;
import com.jefaskincare.mobile.android.fragment.shop.Model.Category;
import com.jefaskincare.mobile.android.fragment.shop.Model.Product;

import java.util.ArrayList;

public interface ShopFragmentView {

    interface View{
            void setRvBestSeller(ArrayList<Product> productList, ArrayList<Product> productList2);
        void setRvCategory(ArrayList<Category> categoryList, ArrayList<Category> backgroundList);
        void setRvItemsProduct(ArrayList<Product> productList, ArrayList<Product> productList2);
        void setBanner(ArrayList<Banner> bannerList);

        void getCategoryFailed(String message);
        void getProductFailed(String message);
        void getBannerFailed(String message);
    }

    interface Presenter{
        void init();
    }
}
