package com.jefaskincare.mobile.android.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jefaskincare.mobile.android.MainActivity;
import com.jefaskincare.mobile.android.R;
import com.jefaskincare.mobile.android.activities.Presenter.CartPresenter;
import com.jefaskincare.mobile.android.activities.View.CartView;
import com.jefaskincare.mobile.android.adapter.CartAdapter;
import com.jefaskincare.mobile.android.api.Parsing;
import com.jefaskincare.mobile.android.fragment.profile.ProfileListAdapter;
import com.jefaskincare.mobile.android.fragment.shop.Model.Product;
import com.jefaskincare.mobile.android.manager.Session;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CartActivity extends AppCompatActivity implements CartView {

    @BindView(R.id.ivBackCart)
    ImageView ivBackCart;

    @BindView(R.id.tvTotalProductCart)
    TextView tvTotalProductCart;

    @BindView(R.id.btnCartCheckout)
    Button btnCartCheckOut;

    @BindView(R.id.rvProductCart)
    RecyclerView rvProductCart;

    private CartPresenter presenter;
    private int totalPrice;
    private int action2;
    private int index2;
    private CartAdapter adapter;
    private Product order;
    private Parsing parse;
    private boolean noItem = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        ButterKnife.bind(this);

        initValue();

        parse = new Parsing();
        presenter = new CartPresenter(this, this);
        presenter.getCart();
    }

    private void initValue() {
        btnCartCheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(noItem){
                    Toast.makeText(getApplicationContext(), "Anda Belum Memiliki Cart", Toast.LENGTH_LONG).show();
                }else{
                    Session session = new Session(getApplicationContext());
                    if(session.getLoginOk(Session.LOGIN_OK)){
                        Intent intent = new Intent(getApplicationContext(), CheckoutActivity.class);
                        intent.putExtra("flag",false);
                        intent.putExtra("total", totalPrice);
                        startActivity(intent);
                    }else{
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                    }
                }
            }
        });

        ivBackCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CartActivity.super.onBackPressed();
//                if (getIntent().getBooleanExtra("from", true)){
//                    Intent i = new Intent(CartActivity.this, MainActivity.class);
//                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    i.putExtra("FRAGMENT_KEY", getIntent().getIntExtra("FRAGMENT_KEY", 0));
//                    startActivity(i);
//                }else{
//                    Intent intent = new Intent(getApplicationContext(), ProductDetailActivity.class);
//                    intent.putExtra("product", getIntent().getSerializableExtra("product"));
//                    intent.putExtra("flag", 0);
//                    startActivity(intent);
//                }

            }
        });

        String price = "Rp0";
        tvTotalProductCart.setText(price);
    }

    @Override
    public void getCartSuccess(ArrayList<Product> productList, int totalPayment) {
        noItem = false;
        totalPrice = totalPayment;
        String price = "Rp" + parse.DeNumber(String.valueOf(totalPayment));
        tvTotalProductCart.setText(price);
        adapter = new CartAdapter(getApplicationContext(), productList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rvProductCart.setLayoutManager(layoutManager);
        rvProductCart.setAdapter(adapter);
        adapter.setOnClickCallback(new CartAdapter.OnItemCallback() {
            @Override
            public void onItemClicked(Product product, int action, int index) {
                index2 = index;
                action2 = action;
                order = product;
                switch (action){
                    case 0:
                        presenter.removeCart(product.getProductid());
                        break;

                    case 1:
                        int qty = Integer.parseInt(product.getOrderqty()) - 1;
                        if(qty == 0){
                            presenter.removeCart(product.getProductid());
                        }else{
                            presenter.updateCart(
                                    product.getProductid(),
                                    product.getOrderprice(),
                                    String.valueOf(qty),
                                    false);
                        }

                        break;

                    case 2:
                        int qty2 = Integer.parseInt(product.getOrderqty()) + 1;
                        presenter.updateCart(
                                product.getProductid(),
                                product.getOrderprice(),
                                String.valueOf(qty2),
                                false);
                        break;
                }
            }
        });
    }

    @Override
    public void noCartFound() {
        presenter.ClearCounter();
        noItem = true;
        Toast.makeText(getApplicationContext(), "Anda Belum Memiliki Cart", Toast.LENGTH_LONG).show();
        String price = "Rp0";
        tvTotalProductCart.setText(price);
    }

    @Override
    public void deleteItemSuccess() {
        presenter.UpdateCounterRemoves(Integer.parseInt(order.getOrderqty()));
        int tempPrice = Integer.parseInt(order.getOrderprice()) * Integer.parseInt(order.getOrderqty()) ;
        totalPrice = totalPrice - tempPrice;
        String price = "Rp" + parse.DeNumber(String.valueOf(totalPrice));
        tvTotalProductCart.setText(price);
        if(totalPrice == 0){
            noItem = true;
        }
        adapter.removeCart(index2);

    }

    @Override
    public void deleteItemFailed() {
        Toast.makeText(getApplicationContext(), "Delete item gagal", Toast.LENGTH_LONG).show();
    }

    @Override
    public void updateCartSuccess() {
        int tempPrice = Integer.parseInt(order.getOrderprice()) ;
        if(action2 == 1){
            totalPrice = totalPrice - tempPrice;
        }else if(action2 == 2){
            totalPrice = totalPrice + tempPrice;
        }
        String price = "Rp" + parse.DeNumber(String.valueOf(totalPrice));
        tvTotalProductCart.setText(price);
        adapter.updateCart(action2, index2);
    }

    @Override
    public void updateCartFailed() {
        Toast.makeText(getApplicationContext(), "Edit item gagal", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
