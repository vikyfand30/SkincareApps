package com.jefaskincare.mobile.android.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.snackbar.Snackbar;
import com.jefaskincare.mobile.android.R;
import com.jefaskincare.mobile.android.activities.Presenter.ProductDetailPresenter;
import com.jefaskincare.mobile.android.activities.View.ProductDetailView;
import com.jefaskincare.mobile.android.api.Parsing;
import com.jefaskincare.mobile.android.fragment.shop.Model.Product;
import com.jefaskincare.mobile.android.manager.Session;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.nikartm.support.ImageBadgeView;

public class ProductDetailActivity extends AppCompatActivity implements ProductDetailView {

    @BindView(R.id.ivProductDetail)
    ImageView ivProductDetail;

    @BindView(R.id.tvProductDetailName)
    TextView tvProductDetailName;

    @BindView(R.id.tvProductDetailPrice)
    TextView tvProductDetailPrice;

    @BindView(R.id.tvProductDetailDesc)
    TextView tvProductDetailDesc;

    @BindView(R.id.tvAddToCart)
    TextView tvAddToCart;

    @BindView(R.id.btnBuyNow)
    Button btnBuyNow;

    @BindView(R.id.ivBackProductDetail)
    ImageView ivBack;

    @BindView(R.id.rlDetailProduct)
    RelativeLayout rlDetail;

    @BindView(R.id.ivDetailProductCart)
    ImageBadgeView ivDetailProductCart;

    @BindView(R.id.shimmerProductDetail)
    ShimmerFrameLayout mShimmerViewContainer;

    Snackbar snackbarwithbutton;
    View.OnClickListener customSnackbarClick;
    View.OnClickListener customSnackbarClickFailed;

    private String produkId;
    private Product product;
    private ProductDetailPresenter presenter;
    int flag = 0;
    private Session session;
    private boolean buy = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        ButterKnife.bind(this);
        presenter = new ProductDetailPresenter(this, this);

        initContent();
        ContentProcess();

    }

    private void ContentProcess() {
        rlDetail.setVisibility(View.GONE);
        flag = getIntent().getIntExtra("flag", 0);
        product = (Product) getIntent().getSerializableExtra("product");
        presenter.GetProductDetail(product.getProductid());
    }

    private void initContent() {

        session = new Session(getApplicationContext());

        if(session.getLoginOk(Session.LOGIN_OK)){
            GetBadge();
        }

        tvAddToCart.setOnClickListener(view -> {
            if (session.getLoginOk(Session.LOGIN_OK)) {
                presenter.addCart(produkId, product.getProductprice(), "1");
            } else {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }

        });


        ivDetailProductCart.setOnClickListener(view -> {
            Intent intent = new Intent(ProductDetailActivity.this, CartActivity.class);
            intent.putExtra("from", false);
            intent.putExtra("product", product);
            startActivity(intent);
        });

        ivBack.setOnClickListener(view -> {
            if (flag == 0) {
                ProductDetailActivity.super.onBackPressed();
//                    Intent i = new Intent(ProductDetailActivity.this, MainActivity.class);
//                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    startActivity(i);
            } else {
                ProductDetailActivity.super.onBackPressed();
//                    String pos = getIntent().getStringExtra("pos");
//                    Intent i = new Intent(ProductDetailActivity.this, ViewProductActivity.class);
//                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    i.putExtra("pos", pos);
//                    startActivity(i);
            }

        });

        btnBuyNow.setOnClickListener(view -> {
            if (session.getLoginOk(Session.LOGIN_OK)) {
//                Intent i = new Intent(ProductDetailActivity.this, CartActivity.class);
//                startActivity(i);
                buy = true;
                presenter.addCart(produkId, product.getProductprice(), "1");
//                Intent intent = new Intent(getApplicationContext(), CheckoutActivity.class);
//                intent.putExtra("flag", true);
//                intent.putExtra("product", product);
//                startActivity(intent);
            } else {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }

        });

        customSnackbarClick = v -> {
            Intent i = new Intent(ProductDetailActivity.this, CartActivity.class);
            startActivity(i);
        };

        customSnackbarClickFailed = view -> {
//               snackbarwithbutton.dismiss();
            presenter.addCart(produkId, product.getProductprice(), "1");
        };

    }

    @Override
    public void GetProductSuccess(Product product) {
        Parsing parse = new Parsing();
        mShimmerViewContainer.stopShimmer();
        mShimmerViewContainer.setVisibility(View.GONE);
        String image = "http://34.80.174.252" + product.getProductfile();
        if (!image.equals("http://34.80.174.252null")) {
            Glide.with(this)
                    .load(image)
                    .apply(new RequestOptions().override(140, 123))
                    .into(ivProductDetail);
        }
        tvProductDetailName.setText(product.getProductname());
        String price = "Rp" + parse.DeNumber(product.getProductprice());
        tvProductDetailPrice.setText(price);
        tvProductDetailDesc.setText(product.getProductdesc());
        mShimmerViewContainer.stopShimmer();
        mShimmerViewContainer.setVisibility(View.GONE);
        rlDetail.setVisibility(View.VISIBLE);
        this.product = product;
        produkId = product.getProductid();

    }

    @Override
    public void AddToCartSuccess() {
////        Toast.makeText(this, "Item Added", Toast.LENGTH_SHORT).show();
        if(buy){
            GetBadge();
            buy = false;
            Intent i = new Intent(ProductDetailActivity.this, CartActivity.class);
            startActivity(i);
        }else{
            toastAdded();
            GetBadge();
            mShimmerViewContainer.stopShimmer();
            mShimmerViewContainer.setVisibility(View.GONE);
        }


    }

    @Override
    public void AddToCartFailed() {
        toastFailed();
    }

    @Override
    public void GetProductFailed(String error) {

//        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
        mShimmerViewContainer.stopShimmer();
        mShimmerViewContainer.setVisibility(View.GONE);
    }

    @Override
    public void onResume() {
        super.onResume();
        mShimmerViewContainer.startShimmer();
        mShimmerViewContainer.setVisibility(View.GONE);
    }

    @Override
    public void onPause() {
        mShimmerViewContainer.stopShimmer();
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void toastAdded() {
        snackbarwithbutton = Snackbar.make(findViewById(android.R.id.content), "Product added to cart", Snackbar.LENGTH_LONG)
                .setAction("View Cart", customSnackbarClick)
                .setActionTextColor(Color.WHITE);
        View snackView = snackbarwithbutton.getView();
        FrameLayout.LayoutParams parentParams = (FrameLayout.LayoutParams) snackView.getLayoutParams();
        snackView.setBackgroundResource(R.drawable.rounded_popup_black);
        parentParams.setMargins(20, 20, 20, 250);
        snackbarwithbutton.show();
    }

    public void toastFailed() {
        snackbarwithbutton = Snackbar.make(findViewById(android.R.id.content), "Failed added to cart", Snackbar.LENGTH_LONG)
                .setAction("RETRY", customSnackbarClickFailed)
                .setActionTextColor(Color.WHITE);
        View snackView = snackbarwithbutton.getView();
        FrameLayout.LayoutParams parentParams = (FrameLayout.LayoutParams) snackView.getLayoutParams();
        snackView.setBackgroundResource(R.drawable.rounded_popup_red);
        parentParams.setMargins(20, 20, 20, 250);
        snackbarwithbutton.show();
    }

    private void GetBadge(){
        int counter = Integer.parseInt(session.getValue(Session.KEY_CART_COUNTER));
        if (counter > 0){
            ivDetailProductCart.setBadgeValue(counter);
            ivDetailProductCart.visibleBadge(true);
        }else{
            ivDetailProductCart.setBadgeValue(counter);
            ivDetailProductCart.visibleBadge(false);
        }

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        GetBadge();
    }
}
