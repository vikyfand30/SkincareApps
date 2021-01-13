package com.jefaskincare.mobile.android.fragment.shop.Model;

import android.graphics.Color;
import android.graphics.drawable.Drawable;

import com.jefaskincare.mobile.android.R;

import java.util.ArrayList;

public class ProductData {

    public static Object[][] items = new Object[][]{
            {1, R.drawable.img_m_product_01, "Eye Treatment Serum", "Rp. 85.0000", "Aloe vera is a succulent plant species of the genus Aloe. An evergreen perennial, it originates from the Arabian Peninsula but grows wild in tropical climates around the world and is cultivated for agricultural and medicinal uses."},
            {2, R.drawable.img_m_product_02, "Eye Treatment Serum", "Rp. 85.0000", "Aloe vera is a succulent plant species of the genus Aloe. An evergreen perennial, it originates from the Arabian Peninsula but grows wild in tropical climates around the world and is cultivated for agricultural and medicinal uses."},
            {1, R.drawable.img_m_product_01, "Eye Treatment Serum", "Rp. 85.0000", "Aloe vera is a succulent plant species of the genus Aloe. An evergreen perennial, it originates from the Arabian Peninsula but grows wild in tropical climates around the world and is cultivated for agricultural and medicinal uses."},
            {2, R.drawable.img_m_product_02, "Eye Treatment Serum", "Rp. 85.0000", "Aloe vera is a succulent plant species of the genus Aloe. An evergreen perennial, it originates from the Arabian Peninsula but grows wild in tropical climates around the world and is cultivated for agricultural and medicinal uses."},
            {1, R.drawable.img_m_product_01, "Eye Treatment Serum", "Rp. 85.0000", "Aloe vera is a succulent plant species of the genus Aloe. An evergreen perennial, it originates from the Arabian Peninsula but grows wild in tropical climates around the world and is cultivated for agricultural and medicinal uses."},
            {2, R.drawable.img_m_product_02, "Eye Treatment Serum", "Rp. 85.0000", "Aloe vera is a succulent plant species of the genus Aloe. An evergreen perennial, it originates from the Arabian Peninsula but grows wild in tropical climates around the world and is cultivated for agricultural and medicinal uses."},
    };

    public static Object[][] items2 = new Object[][]{
            {1, R.drawable.img_product_01, "Eye Treatment Serum", "Rp. 85.0000", "Aloe vera is a succulent plant species of the genus Aloe. An evergreen perennial, it originates from the Arabian Peninsula but grows wild in tropical climates around the world and is cultivated for agricultural and medicinal uses."},
            {2, R.drawable.img_product_02, "Eye Treatment Serum", "Rp. 85.0000", "Aloe vera is a succulent plant species of the genus Aloe. An evergreen perennial, it originates from the Arabian Peninsula but grows wild in tropical climates around the world and is cultivated for agricultural and medicinal uses."},
            {3, R.drawable.img_product_03, "Eye Treatment Serum", "Rp. 85.0000", "Aloe vera is a succulent plant species of the genus Aloe. An evergreen perennial, it originates from the Arabian Peninsula but grows wild in tropical climates around the world and is cultivated for agricultural and medicinal uses."},
            {4, R.drawable.img_product_04, "Eye Treatment Serum", "Rp. 85.0000", "Aloe vera is a succulent plant species of the genus Aloe. An evergreen perennial, it originates from the Arabian Peninsula but grows wild in tropical climates around the world and is cultivated for agricultural and medicinal uses."},
            {1, R.drawable.img_product_01, "Eye Treatment Serum", "Rp. 85.0000", "Aloe vera is a succulent plant species of the genus Aloe. An evergreen perennial, it originates from the Arabian Peninsula but grows wild in tropical climates around the world and is cultivated for agricultural and medicinal uses."},
            {2, R.drawable.img_product_02, "Eye Treatment Serum", "Rp. 85.0000", "Aloe vera is a succulent plant species of the genus Aloe. An evergreen perennial, it originates from the Arabian Peninsula but grows wild in tropical climates around the world and is cultivated for agricultural and medicinal uses."},
            {3, R.drawable.img_product_03, "Eye Treatment Serum", "Rp. 85.0000", "Aloe vera is a succulent plant species of the genus Aloe. An evergreen perennial, it originates from the Arabian Peninsula but grows wild in tropical climates around the world and is cultivated for agricultural and medicinal uses."},
            {4, R.drawable.img_product_04, "Eye Treatment Serum", "Rp. 85.0000", "Aloe vera is a succulent plant species of the genus Aloe. An evergreen perennial, it originates from the Arabian Peninsula but grows wild in tropical climates around the world and is cultivated for agricultural and medicinal uses."},
            {1, R.drawable.img_product_01, "Eye Treatment Serum", "Rp. 85.0000", "Aloe vera is a succulent plant species of the genus Aloe. An evergreen perennial, it originates from the Arabian Peninsula but grows wild in tropical climates around the world and is cultivated for agricultural and medicinal uses."},
            {2, R.drawable.img_product_02, "Eye Treatment Serum", "Rp. 85.0000", "Aloe vera is a succulent plant species of the genus Aloe. An evergreen perennial, it originates from the Arabian Peninsula but grows wild in tropical climates around the world and is cultivated for agricultural and medicinal uses."},
    };

    public static ArrayList<Product> getListItems() {
        ArrayList<Product> list = new ArrayList<Product>();
        for (Object[] aItems : items) {
            Product product = new Product();
            product.setProductId(Integer.valueOf(aItems[0].toString()));
            product.setProductImage(Integer.parseInt(aItems[1].toString()));
            list.add(product);
        }
        return list;
    }

    public static ArrayList<Product> getListItemView() {
        ArrayList<Product> list = new ArrayList<Product>();
        for (Object[] aItems : items2) {
            Product product = new Product();
            product.setProductId(Integer.valueOf(aItems[0].toString()));
            product.setProductImage(Integer.parseInt(aItems[1].toString()));
//            product.setProductName(aItems[2].toString());
//            product.setProductPrice(aItems[3].toString());
//            product.setProductDesc((aItems[4].toString()));
            list.add(product);
        }
        return list;
    }
}
