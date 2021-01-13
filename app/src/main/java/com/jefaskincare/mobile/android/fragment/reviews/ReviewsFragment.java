package com.jefaskincare.mobile.android.fragment.reviews;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jefaskincare.mobile.android.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ReviewsFragment extends Fragment {

    @BindView(R.id.rvReview)
    RecyclerView rv;

    ReviewsAdapter adapter;
    private ArrayList<ReviewsData> reviewsDataArrayList;

    public ReviewsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_reviews, container, false);
        ButterKnife.bind(this, v);

        addData();

        adapter = new ReviewsAdapter(reviewsDataArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(adapter);

        return v;
    }

    private void addData() {
        reviewsDataArrayList = new ArrayList<>();
        reviewsDataArrayList.add(new ReviewsData("JEFA Skincare packages all their products in recyclable materials and…offer a unique, high quality brand that we feel a perfect match for the excellence of The Royal Ballet.’\n" +
                "The Royal Ballet, London","June, 2019", "Puji Astuti, Distributor Riau."));
        reviewsDataArrayList.add(new ReviewsData("JEFA Skincare packages all their products in recyclable materials and…offer a unique, high quality brand that we feel a perfect match for the excellence of The Royal Ballet.’\n" +
                "The Royal Ballet, London", "June, 2019", "SIti Badriyah, Distributor Padang."));
        reviewsDataArrayList.add(new ReviewsData("JEFA Skincare packages all their products in recyclable materials and…offer a unique, high quality brand that we feel a perfect match for the excellence of The Royal Ballet.’\n" +
                "The Royal Ballet, London", "June, 2019", "June, 2019"));
        reviewsDataArrayList.add(new ReviewsData("JEFA Skincare packages all their products in recyclable materials and…offer a unique, high quality brand that we feel a perfect match for the excellence of The Royal Ballet.’\n" +
                "The Royal Ballet, London", "June, 2019","Nila Hasfani, Customer."));
        reviewsDataArrayList.add(new ReviewsData("JEFA Skincare packages all their products in recyclable materials and…offer a unique, high quality brand that we feel a perfect match for the excellence of The Royal Ballet.’\n" +
                "The Royal Ballet, London","June, 2019", "Puji Astuti, Distributor Riau."));
        reviewsDataArrayList.add(new ReviewsData("JEFA Skincare packages all their products in recyclable materials and…offer a unique, high quality brand that we feel a perfect match for the excellence of The Royal Ballet.’\n" +
                "The Royal Ballet, London", "June, 2019", "SIti Badriyah, Distributor Padang."));


    }
}