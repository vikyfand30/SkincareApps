package com.jefaskincare.mobile.android.activities.Model;

import com.jefaskincare.mobile.android.R;
import com.jefaskincare.mobile.android.fragment.shop.Model.Product;

import java.util.ArrayList;

public class ViewProductData {

    public static Object[][] items = new Object[][]{
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

    public static ArrayList<ViewProduct> getListItems(int flag) {
        ArrayList<ViewProduct> list = new ArrayList<ViewProduct>();
        if (flag == 0){
            for (Object[] aItems : items) {
                ViewProduct product = new ViewProduct();
                product.setProductId(Integer.valueOf(aItems[0].toString()));
                product.setProductImage(Integer.valueOf(aItems[1].toString()));
                product.setProductName(aItems[2].toString());
                product.setProductPrice(aItems[3].toString());
                product.setProductDesc((aItems[4].toString()));
                list.add(product);
            }
        }else{
            for (Object[] aItems : items) {
                int tempId = Integer.valueOf(aItems[0].toString());
                if(flag == tempId){
                    ViewProduct product = new ViewProduct();
                    product.setProductId(Integer.valueOf(aItems[0].toString()));
                    product.setProductImage(Integer.valueOf(aItems[1].toString()));
                    product.setProductName(aItems[2].toString());
                    product.setProductPrice(aItems[3].toString());
                    product.setProductDesc((aItems[4].toString()));
                    list.add(product);
                }
            }
        }

        return list;
    }
}
