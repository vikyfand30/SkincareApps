package com.jefaskincare.mobile.android.activities.Model;

import java.io.Serializable;

public class ViewProduct implements Serializable {

    private Integer ProductId;
    private String ProductName;
    private String ProductDesc;
    private String ProductPrice;
    private Integer ProductImage;
    private String ProductType;
    private String ProductBackgroundColor;

    public Integer getProductId() {
        return ProductId;
    }

    public void setProductId(Integer productId) {
        ProductId = productId;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getProductDesc() {
        return ProductDesc;
    }

    public void setProductDesc(String productDesc) {
        ProductDesc = productDesc;
    }

    public String getProductPrice() {
        return ProductPrice;
    }

    public void setProductPrice(String productPrice) {
        ProductPrice = productPrice;
    }

    public Integer getProductImage() {
        return ProductImage;
    }

    public void setProductImage(Integer productImage) {
        ProductImage = productImage;
    }

    public String getProductType() {
        return ProductType;
    }

    public void setProductType(String productType) {
        ProductType = productType;
    }

    public String getProductBackgroundColor() {
        return ProductBackgroundColor;
    }

    public void setProductBackgroundColor(String productBackgroundColor) {
        ProductBackgroundColor = productBackgroundColor;
    }
}
