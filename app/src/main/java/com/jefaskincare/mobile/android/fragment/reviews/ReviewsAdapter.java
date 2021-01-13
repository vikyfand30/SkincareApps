package com.jefaskincare.mobile.android.fragment.reviews;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.jefaskincare.mobile.android.R;

import java.util.ArrayList;

public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.ReviewsViewHolder> {


    private ArrayList<ReviewsData> dataList;

    public ReviewsAdapter(ArrayList<ReviewsData> dataList){
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public ReviewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_review, parent, false);
        return new ReviewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewsViewHolder holder, int position) {
        holder.tvReviewsName.setText(dataList.get(position).getName());
        holder.tvReviewsDesc.setText(dataList.get(position).getDesc());
        holder.tvReviewsDate.setText(dataList.get(position).getDate());
    }
    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class ReviewsViewHolder extends RecyclerView.ViewHolder{
        private TextView tvReviewsDesc;
        private  TextView tvReviewsDate;
        private  TextView tvReviewsName;
        CardView cvReviews;

        public ReviewsViewHolder(View itemView) {
            super(itemView);
            tvReviewsDate = (TextView) itemView.findViewById(R.id.tvReviewDate);
            cvReviews = (CardView) itemView.findViewById(R.id.cvReviews);
            tvReviewsDesc = (TextView) itemView.findViewById(R.id.tvReviewDesc);
            tvReviewsName = (TextView) itemView.findViewById(R.id.tvReviewName);
        }
    }
}
