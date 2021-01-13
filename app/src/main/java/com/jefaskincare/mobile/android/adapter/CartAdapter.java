package com.jefaskincare.mobile.android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jefaskincare.mobile.android.R;
import com.jefaskincare.mobile.android.api.Parsing;
import com.jefaskincare.mobile.android.fragment.shop.Model.Product;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Product> productList;
    private LayoutInflater mInflater;
    private OnItemCallback itemCallback;

    public CartAdapter(Context context, ArrayList<Product> productList){
        this.context = context;
        this.productList = productList;
        this.mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_cart, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.ViewHolder holder, int position) {
        Parsing parse = new Parsing();
        holder.ivProductCart.setImageResource(R.drawable.img_product_01);
        holder.tvCartProductName.setText(productList.get(position).getProductname());

        String qty = "Qty : " + productList.get(position).getOrderqty();
        holder.tvCartProductQty.setText(qty);
        holder.tvCurrCart.setText(productList.get(position).getOrderqty());

        String price = "Rp" + parse.DeNumber(productList.get(position).getOrdersub());
        holder.tvCartProductPrice.setText(price);

        holder.ivCartDelete.setOnClickListener(view -> {
            itemCallback.onItemClicked(productList.get(holder.getAdapterPosition()), 0, position);
        });

        holder.ivCartMin.setOnClickListener(view -> {
            itemCallback.onItemClicked(productList.get(holder.getAdapterPosition()), 1, position);
        });

        holder.ivCartAdd.setOnClickListener(view -> {
            itemCallback.onItemClicked(productList.get(holder.getAdapterPosition()), 2, position);
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivProductCart;
        ImageView ivCartDelete;
        ImageView ivCartAdd;
        ImageView ivCartMin;
        TextView tvCartProductName;
        TextView tvCartProductQty;
        TextView tvCartProductPrice;
        TextView tvCurrCart;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivProductCart = itemView.findViewById(R.id.ivProductCart);
            ivCartDelete = itemView.findViewById(R.id.ivCartDelete);
            ivCartMin = itemView.findViewById(R.id.ivCartMin);
            ivCartAdd = itemView.findViewById(R.id.ivCartAdd);
            tvCartProductName = itemView.findViewById(R.id.tvCartProductName);
            tvCartProductPrice = itemView.findViewById(R.id.tvCartProductPrice);
            tvCartProductQty = itemView.findViewById(R.id.tvCartProductQty);
            tvCurrCart = itemView.findViewById(R.id.tvCurrCart);
        }
    }

    public void setOnClickCallback(OnItemCallback onClickCallback){
        this.itemCallback = onClickCallback;
    }

    public interface OnItemCallback {
        void onItemClicked(Product product, int action, int index);
    }

    public void updateCart(int action, int index){
        Product product = productList.get(index);
        int qty = Integer.parseInt(product.getOrderqty());
        if (action == 1){
            qty -= 1;
        }else if (action == 2){
            qty += 1;
        }
        product.setOrderqty(String.valueOf(qty));
        productList.set(index, product);
        notifyItemChanged(index);
    }

    public void removeCart(int index){
        productList.remove(index);
        notifyItemRemoved(index);
    }

}
