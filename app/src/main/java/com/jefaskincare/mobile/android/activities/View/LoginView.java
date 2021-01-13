package com.jefaskincare.mobile.android.activities.View;

import com.jefaskincare.mobile.android.fragment.profile.Model.User;
import com.jefaskincare.mobile.android.fragment.shop.Model.Product;

import java.util.ArrayList;

public class LoginView {

    public interface View{
        void LoginSuccess(User user);
        void LoginFailed(String error);
        void getCartSuccess(ArrayList<Product> productList, int totalPayment);
        void noCartFound();

    }
}
