package com.jefaskincare.mobile.android.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.jefaskincare.mobile.android.MainActivity;
import com.jefaskincare.mobile.android.R;
import com.jefaskincare.mobile.android.activities.Model.ViewProduct;
import com.jefaskincare.mobile.android.activities.Presenter.ViewProductPresenter;
import com.jefaskincare.mobile.android.activities.View.ViewProductView;
import com.jefaskincare.mobile.android.adapter.ViewCategoryAdapter;
import com.jefaskincare.mobile.android.adapter.ViewProductAdapter;
import com.jefaskincare.mobile.android.fragment.shop.Model.Category;
import com.jefaskincare.mobile.android.fragment.shop.Model.Product;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewProductActivity extends AppCompatActivity implements ViewProductView.View {

    private ViewProductPresenter presenter;

    @BindView(R.id.rvViewProductType)
    RecyclerView rvViewProductType;

    @BindView(R.id.rvViewProductTypeItem)
    RecyclerView rvViewProductTypeItems;

    @BindView(R.id.ivBackViewProduct)
    ImageView ivBack;

    String flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_product);
        ButterKnife.bind(this);

        initContent();
        presenter = new ViewProductPresenter(this, this);
        flag = getIntent().getStringExtra("pos");
        presenter.GetViewCategory();
    }

    public void initContent(){

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewProductActivity.super.onBackPressed();
//                Intent i = new Intent(ViewProductActivity.this, MainActivity.class);
//                startActivity(i);
            }
        });
    }

    @Override
    public void SetRVCategory(ArrayList<Category> categoryList, ArrayList<Category> backgroundList) {
        rvViewProductType.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        ViewCategoryAdapter categoryAdapter = new ViewCategoryAdapter(this, categoryList, backgroundList, flag, presenter);
        rvViewProductType.setAdapter(categoryAdapter);
        categoryAdapter.setOnClickCallback(new ViewCategoryAdapter.OnItemCallback() {
            @Override
            public void onItemClicked(String pos) {
                flag = pos;
                presenter.GetViewProduct(flag);
                categoryAdapter.UpdateView(flag);
            }
        });
        presenter.GetViewProduct(flag);
    }

    @Override
    public void SetRVProduct(ArrayList<Product> productListImage, ArrayList<Product> productList, String pos) {
        flag = pos;
        rvViewProductTypeItems.setLayoutManager(new GridLayoutManager(this, 2));
        ViewProductAdapter gridProductAdapter = new ViewProductAdapter(this, productList);
        rvViewProductTypeItems.setAdapter(gridProductAdapter);
        gridProductAdapter.setOnClickCallback(new ViewProductAdapter.OnItemCallback() {
            @Override
            public void onItemClicked(Product product) {
                Intent intent = new Intent(ViewProductActivity.this, ProductDetailActivity.class);
                intent.putExtra("product",product);
                intent.putExtra("pos", pos);
                intent.putExtra("flag", 1);
                startActivity(intent);
            }
        });
    }

    @Override
    public void GetProductFailed(String error) {
        Toast.makeText(getApplicationContext(), error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
