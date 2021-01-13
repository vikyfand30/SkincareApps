package com.jefaskincare.mobile.android.fragment.shop.Model;

import android.graphics.Color;

import com.jefaskincare.mobile.android.R;

import java.util.ArrayList;

public class ProductBestSellerData {

    public static Object[][] items = new Object[][]{
            {"#ffb0ab", R.drawable.img_best_seller_01,"Eye Treatment Serum", "Rp. 180.000"},
            {"#fa91eb", R.drawable.img_best_seller_02,"Eye Treatment Serum", "Rp. 180.000"},
            {"#bea8fe",R.drawable.img_best_seller_01, "Eye Treatment Serum", "Rp. 180.000"},
            {"#ffb0ab", R.drawable.img_best_seller_02, "Eye Treatment Serum", "Rp. 180.000"},
            {"#fa91eb", R.drawable.img_best_seller_01,"Eye Treatment Serum", "Rp. 180.000"},
            {"#bea8fe", R.drawable.img_best_seller_02,"Eye Treatment Serum", "Rp. 180.000"}
    };

    public static ArrayList<Product> getListItems() {
        ArrayList<Product> list = new ArrayList<Product>();
        for (Object[] aItems : items) {
            Product product = new Product();
            product.setProductBackgroundColor(aItems[0].toString());
            product.setProductImage(Integer.parseInt(aItems[1].toString()));
            product.setProductName(aItems[2].toString());
            product.setProductPrice(aItems[3].toString());
            list.add(product);
        }
        return list;
    }
}
