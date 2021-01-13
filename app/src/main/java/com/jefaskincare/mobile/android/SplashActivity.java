package com.jefaskincare.mobile.android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

import com.jefaskincare.mobile.android.activities.Presenter.CartPresenter;
import com.jefaskincare.mobile.android.activities.View.CartView;
import com.jefaskincare.mobile.android.fragment.shop.Model.Product;
import com.jefaskincare.mobile.android.manager.Session;

import java.util.ArrayList;

public class SplashActivity extends AppCompatActivity implements CartView {

    private Session session;
    private int qty = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);

        CartPresenter presenter = new CartPresenter(this, this);
        session = new Session(this);

        if(session.getLoginOk(Session.LOGIN_OK)){
            presenter.getCart();
        }else{
            session.putSessionStr(Session.KEY_CART_COUNTER, String.valueOf(0));
            GoToMainActivity();
        }
    }

//    private void SyncDB(Product product){
//        presenter.AddToCart(product.getProductid(), product.getProductprice(), Integer.parseInt(product.getOrderqty()));
//        qty = qty + Integer.parseInt(product.getOrderqty());
//    }

    private void GoToMainActivity(){
        final Handler handler = new Handler();
        handler.postDelayed(() -> {
            Intent intent = new Intent(getApplication(), MainActivity.class);
            startActivity(intent);
            finish();
        }, 2000L);
    }

    @Override
    public void getCartSuccess(ArrayList<Product> productList, int totalPayment) {

        for(int i = 0; i < productList.size() ; i++){
            qty = qty + Integer.parseInt(productList.get(i).getOrderqty());
        }
        session.putSessionStr(Session.KEY_CART_COUNTER, String.valueOf(qty));
        GoToMainActivity();
    }

    @Override
    public void noCartFound() {
        GoToMainActivity();
    }

    @Override
    public void deleteItemSuccess() {

    }

    @Override
    public void deleteItemFailed() {

    }

    @Override
    public void updateCartSuccess() {

    }

    @Override
    public void updateCartFailed() {

    }
}
