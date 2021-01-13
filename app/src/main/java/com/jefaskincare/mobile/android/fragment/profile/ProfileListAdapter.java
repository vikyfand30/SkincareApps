package com.jefaskincare.mobile.android.fragment.profile;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jefaskincare.mobile.android.MainActivity;
import com.jefaskincare.mobile.android.R;
import com.jefaskincare.mobile.android.activities.ProfileDetailActivity;
import com.jefaskincare.mobile.android.activities.View.ReferralActivity;
import com.jefaskincare.mobile.android.db.DBHelper;
import com.jefaskincare.mobile.android.fragment.profile.Model.ProfileList;
import com.jefaskincare.mobile.android.fragment.profile.View.ProfileDetailView;
import com.jefaskincare.mobile.android.manager.Session;

import java.util.ArrayList;

public class ProfileListAdapter extends RecyclerView.Adapter<ProfileListAdapter.ProfileListViewHolder> {

    private Context context;
    private ArrayList<ProfileList> dataList;

    public ProfileListAdapter(Context context, ArrayList<ProfileList> dataList) {
        this.context = context;
        this.dataList = dataList;
    }


    @NonNull
    @Override
    public ProfileListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_list_profile, parent, false);
        return new ProfileListViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ProfileListViewHolder holder, int position) {

        holder.tvProfileItem.setText(dataList.get(position).getItem());
        if (position == 3) {
            holder.tvProfileItem.setOnClickListener(view -> {
                Session session = new Session(context);
                //deleteDatabase();
                session.putLoginOk(Session.LOGIN_OK, false);
                session.putSessionStr(Session.KEY_CART_COUNTER, "0");
                session.putSessionStr(Session.KEY_ORDER_ID, "");
                session.putSessionStr(Session.KEY_USER_ID, "");
                session.putSessionStr(Session.KEY_LOGIN_DATA, "");
                session.putSessionStr(Session.KEY_ORDER_DETAIL_ID, "");
                Intent intent = new Intent(context, MainActivity.class);
                context.startActivity(intent);
                ((Activity) context).finish();
            });
        }
        if (position == 2) {
            holder.tvProfileItem.setOnClickListener(view -> {
                Intent i = new Intent(context, ReferralActivity.class);
                context.startActivity(i);
            });
        }

        if (position == 0) {
            holder.tvProfileItem.setOnClickListener(view -> {
                Intent i = new Intent(context, ProfileDetailActivity.class);
                context.startActivity(i);
            });
        }
    }

    private void deleteDatabase() {
        DBHelper db = new DBHelper(context);
        db.deleteDB();
        db.close();
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class ProfileListViewHolder extends RecyclerView.ViewHolder {
        private TextView tvProfileItem;

        public ProfileListViewHolder(View itemView) {
            super(itemView);
            tvProfileItem = (TextView) itemView.findViewById(R.id.tvProfileItem);
        }
    }
}


