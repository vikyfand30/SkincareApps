package com.jefaskincare.mobile.android.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.jefaskincare.mobile.android.R;
import com.jefaskincare.mobile.android.activities.Model.ViewProduct;
import com.jefaskincare.mobile.android.activities.ProductDetailActivity;
import com.jefaskincare.mobile.android.api.Parsing;
import com.jefaskincare.mobile.android.fragment.shop.Model.Product;

import java.util.ArrayList;

public class ViewProductAdapter extends RecyclerView.Adapter<ViewProductAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Product> productList;
    private ArrayList<Product> productImages;
    private LayoutInflater mInflater;
    private OnItemCallback onItemCallback;

    public ViewProductAdapter(Context context, ArrayList<Product> productList){
        this.context = context;
        this.productList = productList;
        this.mInflater = LayoutInflater.from(context);
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_product_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Parsing parse = new Parsing();
        String image = "http://34.80.174.252" + productList.get(position).getProductfile();
        if(!image.equals("http://34.80.174.252null")) {
            Glide.with(context)
                    .load(image)
                    .into(holder.ivProduct);
        }
        holder.tvProductName.setText(productList.get(position).getProductname());
        String price = "Rp" + parse.DeNumber(productList.get(position).getProductprice());
        holder.tvProductPrice.setText(price);
        holder.cvProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemCallback.onItemClicked(productList.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivProduct;
        TextView tvProductName;
        TextView tvProductPrice;
        CardView cvProduct;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivProduct = itemView.findViewById(R.id.ivProductView);
            tvProductName = itemView.findViewById(R.id.tvProductNameView);
            tvProductPrice = itemView.findViewById(R.id.tvProductPriceView);
            cvProduct = itemView.findViewById(R.id.cvProductView);
        }
    }
    public void setOnClickCallback(ViewProductAdapter.OnItemCallback onClickCallback){
        this.onItemCallback = onClickCallback;
    }

    public interface OnItemCallback {
        void onItemClicked(Product product);
    }

}
