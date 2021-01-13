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
import com.bumptech.glide.request.RequestOptions;
import com.jefaskincare.mobile.android.R;
import com.jefaskincare.mobile.android.api.Parsing;
import com.jefaskincare.mobile.android.fragment.shop.Model.Product;
import com.jefaskincare.mobile.android.activities.ProductDetailActivity;

import java.util.ArrayList;
import java.util.List;

public class ItemProductAdapter extends RecyclerView.Adapter<ItemProductAdapter.ViewHolder> {

    private OnItemCallback onItemCallback;
    private Context context;
    private ArrayList<Product> productList;
    private ArrayList<Product> productList2;
    private LayoutInflater minflate;

    public ItemProductAdapter (Context context, ArrayList<Product> productList, ArrayList<Product> productList2){
        this.context = context;
        this.productList = productList;
        this.productList2 = productList2;
        this.minflate = LayoutInflater.from(context);

    }

    @NonNull
    @Override
    public ItemProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = minflate.inflate(R.layout.item_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemProductAdapter.ViewHolder holder, final int position) {

        //holder.ivProduct.setImageResource(productList.get(position).getProductImage());
        Parsing parse = new Parsing();
        String image = "http://34.80.174.252" + productList2.get(position).getProductfile();
        if(!image.equals("http://34.80.174.252null")) {
            Glide.with(context)
                    .load(image)
                    .into(holder.ivProduct);
        }
//        }else{
//           // holder.ivProduct.setImageResource(productList.get(position).getProductImage());
//        }
        holder.tvProductName.setText(productList2.get(position).getProductname());
        String price = "Rp" + parse.DeNumber(productList2.get(position).getProductprice());
        holder.tvProductPrice.setText(price);
        holder.cvProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemCallback.onItemClicked(productList2.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList2.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivProduct;
        TextView tvProductName;
        TextView tvProductPrice;
        CardView cvProduct;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivProduct = itemView.findViewById(R.id.ivProduct);
            tvProductName = itemView.findViewById(R.id.tvProductName);
            tvProductPrice = itemView.findViewById(R.id.tvProductPrice);
            cvProduct = itemView.findViewById(R.id.cvProduct);
        }
    }

    public void setOnClickCallback(ItemProductAdapter.OnItemCallback onClickCallback){
        this.onItemCallback = onClickCallback;
    }

    public interface OnItemCallback {
        void onItemClicked(Product product);
    }
}
