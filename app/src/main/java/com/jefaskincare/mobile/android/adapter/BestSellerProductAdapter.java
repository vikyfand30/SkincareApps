package com.jefaskincare.mobile.android.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.jefaskincare.mobile.android.R;
import com.jefaskincare.mobile.android.api.Parsing;
import com.jefaskincare.mobile.android.fragment.shop.Model.Product;

import java.util.ArrayList;
import java.util.List;

public class BestSellerProductAdapter extends RecyclerView.Adapter<BestSellerProductAdapter.ViewHolder> {

    private ArrayList<Product> listProduct;
    private ArrayList<Product> listProduct2;
    private LayoutInflater mInflater;
    private BestSellerItemCallback bestSellerItemCallback;
    private Context context;


    public BestSellerProductAdapter(Context context, ArrayList<Product> listProduct, ArrayList<Product> listProduct2){
        this.mInflater = LayoutInflater.from(context);
        this.listProduct = listProduct;
        this.listProduct2 = listProduct2;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_best_seller, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Parsing parse = new Parsing();
        String color = listProduct.get(position).getProductBackgroundColor();
        String image = "http://34.80.174.252" + listProduct2.get(position).getProductfile();
        if(!image.equals("http://34.80.174.252null")) {
            Glide.with(context)
                    .load(image)
                    .into(holder.ivPhotoBestSeller);
        }
        String name = listProduct2.get(position).getProductname();
        String price = "Rp" + parse.DeNumber(listProduct2.get(position).getProductprice());
        holder.tvPriceBestSeller.setText(price);
        holder.tvDescBestSeller.setText(name);
        holder.cvBestSellerItems.setCardBackgroundColor(Color.parseColor(color));
        holder.cvBestSellerItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bestSellerItemCallback.BestSellerItemClicked(listProduct2.get(holder.getAdapterPosition()));
            }
        });
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvDescBestSeller;
        TextView tvPriceBestSeller;
        ImageView ivPhotoBestSeller;
        CardView cvBestSellerItems;

        ViewHolder(View itemView) {
            super(itemView);

            tvDescBestSeller = itemView.findViewById(R.id.tvDesBestSeller);
            tvPriceBestSeller = itemView.findViewById(R.id.tvPriceBestSeller);
            ivPhotoBestSeller = itemView.findViewById(R.id.ivBestSeller);
            cvBestSellerItems = itemView.findViewById(R.id.cvBestSellerItems);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
//            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    @Override
    public int getItemCount() {
        return listProduct2.size();
    }
//    private ItemClickListener mClickListener;
    public String getItem(int id) {
    return listProduct.get(id).getProductName();
}

    public void setBestSellerItemCallback(BestSellerItemCallback bestSellerItemCallback) {
        this.bestSellerItemCallback = bestSellerItemCallback;
    }

    public interface BestSellerItemCallback{
        void BestSellerItemClicked(Product product);
    }
}
