package com.jefaskincare.mobile.android.fragment.shop.Model;

import android.content.Intent;

import java.io.Serializable;

public class Product implements Serializable {

    private Integer ProductId;
    private String ProductName;
    private String ProductDesc;
    private String ProductPrice;
    private Integer ProductImage;
    private String ProductType;
    private String ProductBackgroundColor;

    private String productid;
    private String productname;
    private String productdesc;
    private String productprice;
    private String productweight;
    private String productstock;
    private String productsellcount;
    private String productfile;
    private String orderprice;
    private String orderqty;
    private String ordersub;

    private Integer productqty;

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

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getProductdesc() {
        return productdesc;
    }

    public void setProductdesc(String productdesc) {
        this.productdesc = productdesc;
    }

    public String getProductprice() {
        return productprice;
    }

    public void setProductprice(String productprice) {
        this.productprice = productprice;
    }

    public String getProductweight() {
        return productweight;
    }

    public void setProductweight(String productweight) {
        this.productweight = productweight;
    }

    public String getProductstock() {
        return productstock;
    }

    public void setProductstock(String productstock) {
        this.productstock = productstock;
    }

    public String getProductsellcount() {
        return productsellcount;
    }

    public void setProductsellcount(String productsellcount) {
        this.productsellcount = productsellcount;
    }

    public Integer getProductqty() {
        return productqty;
    }

    public void setProductqty(Integer productqty) {
        this.productqty = productqty;
    }

    public String getProductfile() {
        return productfile;
    }

    public void setProductfile(String productfile) {
        this.productfile = productfile;
    }

    public String getOrderprice() {
        return orderprice;
    }

    public void setOrderprice(String orderprice) {
        this.orderprice = orderprice;
    }

    public String getOrderqty() {
        return orderqty;
    }

    public void setOrderqty(String orderqty) {
        this.orderqty = orderqty;
    }

    public String getOrdersub() {
        return ordersub;
    }

    public void setOrdersub(String ordersub) {
        this.ordersub = ordersub;
    }
}
