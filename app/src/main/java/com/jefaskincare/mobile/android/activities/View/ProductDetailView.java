package com.jefaskincare.mobile.android.activities.View;

import com.jefaskincare.mobile.android.fragment.shop.Model.Product;

public interface ProductDetailView {

    void GetProductSuccess(Product product);
    void AddToCartSuccess();
    void AddToCartFailed();
    void GetProductFailed(String error);
}
