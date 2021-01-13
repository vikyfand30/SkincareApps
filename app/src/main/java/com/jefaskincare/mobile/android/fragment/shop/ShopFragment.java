package com.jefaskincare.mobile.android.fragment.shop;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.jefaskincare.mobile.android.R;
import com.jefaskincare.mobile.android.activities.ProductDetailActivity;
import com.jefaskincare.mobile.android.adapter.BestSellerProductAdapter;
import com.jefaskincare.mobile.android.adapter.CategoryAdapter;
import com.jefaskincare.mobile.android.adapter.ItemProductAdapter;
import com.jefaskincare.mobile.android.api.Parsing;
import com.jefaskincare.mobile.android.fragment.shop.Model.Banner;
import com.jefaskincare.mobile.android.fragment.shop.Model.Category;
import com.jefaskincare.mobile.android.fragment.shop.Model.Product;
import com.jefaskincare.mobile.android.fragment.shop.Presenter.ShopFragmentPresenter;
import com.jefaskincare.mobile.android.fragment.shop.View.ShopFragmentView;
import com.jefaskincare.mobile.android.manager.Session;
import com.jefaskincare.mobile.android.utils.BannerSliderUtil;
import com.jefaskincare.mobile.android.utils.PicassoImageLoadingService;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;
import ss.com.bannerslider.Slider;

public class ShopFragment extends Fragment implements ShopFragmentView.View {

//    @BindView(R.id.carouselView)
//    CarouselView carouselView;

    @BindView(R.id.carouselView)
    Slider slider;

    @BindView(R.id.rv)
    RecyclerView recyclerView;

    @BindView(R.id.rvMenuTop)
    RecyclerView recyclerViewCategory;

    @BindView(R.id.rvMenuBot)
    RecyclerView recyclerViewProduct;

    private ShimmerFrameLayout mShimmerViewContainer;


//    int[]sampleImage ={R.drawable.img_banner_1, R.drawable.img_banner_2};
    ShopFragmentPresenter presenter;

    public ShopFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_shop, container, false);
        ButterKnife.bind(this, v);
        Slider.init(new PicassoImageLoadingService(getContext()));

//        carouselView.setPageCount(sampleImage.length);
//        carouselView.setImageListener(imageListener);
        mShimmerViewContainer = v.findViewById(R.id.shimmer_view_container);

        presenter = new ShopFragmentPresenter(getContext(), this);

        presenter.init();

        return v;
    }

//    ImageListener imageListener = new ImageListener() {
//        @Override
//        public void setImageForPosition(int position, ImageView imageView) {
//            imageView.setImageResource(sampleImage[position]);
//        }
//    };


    @Override
    public void setRvBestSeller(ArrayList<Product> productList, ArrayList<Product> productList2) {
        if (getActivity() != null){
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
            BestSellerProductAdapter bestSellerAdapter = new BestSellerProductAdapter(getContext(), productList, productList2);
            recyclerView.setAdapter(bestSellerAdapter);
            mShimmerViewContainer.stopShimmer();
            mShimmerViewContainer.setVisibility(View.GONE);
            bestSellerAdapter.setBestSellerItemCallback(new BestSellerProductAdapter.BestSellerItemCallback() {

                @Override
                public void BestSellerItemClicked(Product product) {
                    Intent intent = new Intent(getContext(), ProductDetailActivity.class);
                    intent.putExtra("product",product);
                    intent.putExtra("flag", 0);
                    startActivity(intent);
                }
            });
        }
    }

    @Override
    public void setRvCategory(ArrayList<Category> categoryList, ArrayList<Category> backgroundList) {
        if (getActivity() != null){
            recyclerViewCategory.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
            CategoryAdapter categoryAdapter = new CategoryAdapter(getContext(),categoryList, backgroundList);
            recyclerViewCategory.setAdapter(categoryAdapter);
            categoryAdapter.setCategoryItemCallback(new CategoryAdapter.CategoryItemCallback() {
                @Override
                public void CategoryItemClicked(Category category) {

                }
            });
        }
    }

    @Override
    public void setRvItemsProduct(ArrayList<Product> productList, ArrayList<Product> productList2) {
        if (getActivity() != null){
            recyclerViewProduct.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
            ItemProductAdapter productAdapter = new ItemProductAdapter(getContext(),productList, productList2);
            recyclerViewProduct.setAdapter(productAdapter);
            productAdapter.setOnClickCallback(new ItemProductAdapter.OnItemCallback() {
                @Override
                public void onItemClicked(Product product) {
                    Intent intent = new Intent(getContext(), ProductDetailActivity.class);
                    intent.putExtra("product",product);
                    intent.putExtra("flag", 0);
                    startActivity(intent);
                }
            });
        }
    }

    @Override
    public void setBanner(ArrayList<Banner> bannerList) {
        slider.postDelayed(() -> {
            slider.setAdapter(new BannerSliderUtil(bannerList));
            slider.setSelectedSlide(1);
            slider.setLoopSlides(true);
            slider.setAnimateIndicators(true);
            slider.setInterval(2000);
        }, 1500);
    }


    @Override
    public void getCategoryFailed(String message) {
        mShimmerViewContainer.stopShimmer();
        mShimmerViewContainer.setVisibility(View.GONE);
        Log.e("Error Message ", message);
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
        mShimmerViewContainer.stopShimmer();
        mShimmerViewContainer.setVisibility(View.GONE);
    }

    @Override
    public void getProductFailed(String message) {
        mShimmerViewContainer.stopShimmer();
        mShimmerViewContainer.setVisibility(View.GONE);
        Log.e("Error Message ", message);
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
        mShimmerViewContainer.stopShimmer();
        mShimmerViewContainer.setVisibility(View.GONE);
    }

    @Override
    public void getBannerFailed(String message) {

    }

    @Override
    public void onResume() {
        super.onResume();
        mShimmerViewContainer.startShimmer();
    }

    @Override
    public void onPause() {
        mShimmerViewContainer.stopShimmer();
        super.onPause();
    }

}