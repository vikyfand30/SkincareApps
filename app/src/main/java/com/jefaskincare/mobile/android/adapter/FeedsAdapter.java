package com.jefaskincare.mobile.android.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jefaskincare.mobile.android.R;
import com.jefaskincare.mobile.android.activities.DetailMediaActivity;
import com.jefaskincare.mobile.android.fragment.feeds.model.Feeds;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class FeedsAdapter extends RecyclerView.Adapter<FeedsAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Feeds> dataFeed;
    private LayoutInflater mInflate;

    public FeedsAdapter(Context context, ArrayList<Feeds>dataFeed){
        this.context = context;
        this.dataFeed = dataFeed;
        this.mInflate = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = mInflate.inflate(R.layout.item_media, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.ivMedia.setImageResource(dataFeed.get(position).getFeedImage());
        holder.ivMedia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, DetailMediaActivity.class);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataFeed.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivMedia;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivMedia = itemView.findViewById(R.id.ivMedia);
        }

    }
}
