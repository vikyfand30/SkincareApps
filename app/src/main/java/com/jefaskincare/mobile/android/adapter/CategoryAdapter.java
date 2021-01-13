package com.jefaskincare.mobile.android.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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
import com.jefaskincare.mobile.android.activities.ViewProductActivity;
import com.jefaskincare.mobile.android.fragment.shop.Model.Category;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private ArrayList<Category> categoryList;
    private ArrayList<Category> backgroundList;
    private LayoutInflater minflater;
    private Context context;
    private CategoryItemCallback categoryItemCallback;
    private int tempPosition;

    public CategoryAdapter(Context context, ArrayList<Category> categoryList, ArrayList<Category> backgroundList){
        this.minflater = LayoutInflater.from(context);
        this.context = context;
        this.categoryList = categoryList;
        this.backgroundList = backgroundList;

    }

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = minflater.inflate(R.layout.item_product_type, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, final int position) {
        tempPosition = position;
            while(tempPosition > 6){
                tempPosition -= 7;
            }
        if (position == 0){
            String setText = categoryList.get(position).getValue() + " >";
            holder.tvProductType.setText(setText);
        }else{
            holder.tvProductType.setText(categoryList.get(position).getValue());
        }
        holder.cvProductType.setBackgroundResource(backgroundList.get(tempPosition).getCatColor());
        holder.cvProductType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ViewProductActivity.class);
                intent.putExtra("pos", categoryList.get(position).getId());
                context.startActivity(intent);
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

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvProductType = itemView.findViewById(R.id.tvProductType);
            cvProductType = itemView.findViewById(R.id.cvProductType);
            llCategory = itemView.findViewById(R.id.llCategory);
        }
    }

    public void setCategoryItemCallback(CategoryItemCallback categoryItemCallback){
        this.categoryItemCallback = categoryItemCallback;
    }

    public interface CategoryItemCallback {
        void CategoryItemClicked(Category category);
    }
}
