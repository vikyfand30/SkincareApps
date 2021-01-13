package com.jefaskincare.mobile.android.activities.View;

import com.jefaskincare.mobile.android.fragment.shop.Model.Product;

import java.util.ArrayList;

public interface CartView {

    void getCartSuccess(ArrayList<Product> productList, int totalPayment);
    void noCartFound();

    void deleteItemSuccess();
    void deleteItemFailed();

    void updateCartSuccess();
    void updateCartFailed();
}
