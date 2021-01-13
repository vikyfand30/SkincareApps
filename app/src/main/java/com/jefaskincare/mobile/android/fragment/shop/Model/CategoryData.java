package com.jefaskincare.mobile.android.fragment.shop.Model;

import com.jefaskincare.mobile.android.R;

import java.util.ArrayList;

public class CategoryData {

    public static Object[][] items = new Object[][]{
            {0, "View All"},
            {1, "Serum"},
            {2, "Toner"},
            {3, "Mouisturizer"},
            {4, "Cleanser"},
            {1, "Facial Mask"},
            {2, "Sunscreen"},
            {3, "Serum"},
            {4, "Toner"},
            {1, "Mouisturizer"},
    };

    public static Object[][] background = new Object[][]{
            {0, R.drawable.img_btn_l_all},
            {1, R.drawable.img_btn_l_serum},
            {2, R.drawable.img_btn_l_toner},
            {3, R.drawable.img_btn_l_mousturizer},
            {4, R.drawable.img_btn_l_cleanser},
            {5, R.drawable.img_btn_l_facialmask},
            {6, R.drawable.img_btn_l_sunscreen}
    };

    public static Object[][] backgroundView = new Object[][]{
            {0, R.drawable.img_btn_s_all},
            {1, R.drawable.img_btn_s_serum},
            {2, R.drawable.img_btn_s_toner},
            {3, R.drawable.img_btn_s_moisturizer},
            {4, R.drawable.img_btn_s_cleanser},
            {5, R.drawable.img_btn_s_facialmask},
            {6, R.drawable.img_btn_s_sunscreen}
    };

//    public static ArrayList<Category> getListItems() {
//        ArrayList<Category> list = new ArrayList<Category>();
//        for (Object[] Items : items) {
//            Category category = new Category();
//            category.setCategoryId(Integer.parseInt(Items[0].toString()));
//            category.setCategoryName(Items[1].toString());
//            list.add(category);
//        }
//        return list;
//    }

    public static ArrayList<Category> getListBackgroundColor() {
        ArrayList<Category> list = new ArrayList<Category>();
        for (Object[] Items : background) {
            Category category = new Category();
            category.setId(Items[0].toString());
            category.setCatColor(Integer.parseInt(Items[1].toString()));
            list.add(category);
        }
        return list;
    }

    public static ArrayList<Category> getListBackgroundViewColor() {
        ArrayList<Category> list = new ArrayList<Category>();
        for (Object[] Items : backgroundView) {
            Category category = new Category();
            category.setId(Items[0].toString());
            category.setCatColor(Integer.parseInt(Items[1].toString()));
            list.add(category);
        }
        return list;
    }
}
