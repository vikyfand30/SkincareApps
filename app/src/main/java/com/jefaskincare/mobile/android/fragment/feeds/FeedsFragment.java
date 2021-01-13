package com.jefaskincare.mobile.android.fragment.feeds;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jefaskincare.mobile.android.R;
import com.jefaskincare.mobile.android.adapter.FeedsAdapter;
import com.jefaskincare.mobile.android.fragment.feeds.model.Feeds;
import com.jefaskincare.mobile.android.fragment.feeds.presenter.FeedsFragmentPresenter;
import com.jefaskincare.mobile.android.fragment.feeds.view.FeedsFragmentView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FeedsFragment extends Fragment implements FeedsFragmentView.View {

    private FeedsFragmentPresenter presenter;

    @BindView(R.id.rvMedia)
    RecyclerView rvMedia;

    public FeedsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_feeds, container, false);
        ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new FeedsFragmentPresenter(getContext(), this);
        presenter.GetFeedsImage();
    }

    @Override
    public void SetRV(ArrayList<Feeds> dataFeed) {
        rvMedia.setLayoutManager(new GridLayoutManager(getContext(), 3));
        FeedsAdapter adapter = new FeedsAdapter(getContext(), dataFeed);
        rvMedia.setAdapter(adapter);
    }
}

