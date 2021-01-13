package com.jefaskincare.mobile.android.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.jefaskincare.mobile.android.R;
import com.jefaskincare.mobile.android.activities.Model.ViewProduct;
import com.jefaskincare.mobile.android.activities.Presenter.ViewProductPresenter;
import com.jefaskincare.mobile.android.activities.View.ViewProductView;
import com.jefaskincare.mobile.android.activities.ViewProductActivity;
import com.jefaskincare.mobile.android.fragment.shop.Model.Category;
import com.jefaskincare.mobile.android.fragment.shop.Model.Product;

import java.util.ArrayList;

public class ViewCategoryAdapter extends RecyclerView.Adapter<ViewCategoryAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Category> categoryList;
    private ArrayList<Category> backgroundList;
    private LayoutInflater mInflater;
    private int tempPosition;
    private String flag;
    private ViewProductPresenter presenter;
    private OnItemCallback onItemCallback;

    public ViewCategoryAdapter(Context context, ArrayList<Category> categoryList, ArrayList<Category> backgroundList, String flag, ViewProductPresenter presenter) {
        this.context = context;
        this.categoryList = categoryList;
        this.backgroundList = backgroundList;
        this.mInflater = LayoutInflater.from(context);
        this.flag = flag;
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_product_type, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        tempPosition = position;
        while (tempPosition > 6) {
            tempPosition -= 7;
        }
        if (flag.equals(categoryList.get(position).getId())) {
            holder.view.setVisibility(View.VISIBLE);
        } else {
            holder.view.setVisibility(View.INVISIBLE);
        }
        holder.cvProductType.setBackgroundResource(backgroundList.get(tempPosition).getCatColor());
        holder.tvProductType.setText(categoryList.get(position).getValue());
        holder.cvProductType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.view.setVisibility(View.INVISIBLE);
                onItemCallback.onItemClicked(categoryList.get(holder.getAdapterPosition()).getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvProductType;
        CardView cvProductType;
        LinearLayout llCategory;
        View view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvProductType = itemView.findViewById(R.id.tvProductType);
            cvProductType = itemView.findViewById(R.id.cvProductType);
            llCategory = itemView.findViewById(R.id.llCategory);
            view = itemView.findViewById(R.id.vwIndicator);
        }
    }

    public void setOnClickCallback(ViewCategoryAdapter.OnItemCallback onClickCallback){
        this.onItemCallback = onClickCallback;
    }

    public interface OnItemCallback {
        void onItemClicked(String pos);
    }

    public void UpdateView(String pos){
        this.flag = pos;
        this.notifyDataSetChanged();
    }
}
