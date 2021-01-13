package com.jefaskincare.mobile.android.fragment.profile;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jefaskincare.mobile.android.R;
import com.jefaskincare.mobile.android.activities.ShippingAddressActivity;
import com.jefaskincare.mobile.android.adapter.AddressAdapter;
import com.jefaskincare.mobile.android.fragment.profile.Model.Address;
import com.jefaskincare.mobile.android.fragment.profile.Model.ProfileList;
import com.jefaskincare.mobile.android.fragment.profile.Model.User;
import com.jefaskincare.mobile.android.fragment.profile.Presenter.ProfilePresenter;
import com.jefaskincare.mobile.android.fragment.profile.View.ProfileView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileFragment extends Fragment implements ProfileView.View {


//    private ArrayList<String> list = new ArrayList<String>();
    private ProfilePresenter presenter;

    @BindView(R.id.rlName)
    RelativeLayout rlName;

    @BindView(R.id.rvProfileList)
    RecyclerView rv;

    @BindView(R.id.rvShippingAddress)
    RecyclerView rvShippingAddress;

    @BindView(R.id.tvProfileName)
     TextView tvProfileName;

    @BindView(R.id.tvProfilePhoneNumber)
    TextView tvProfilePhoneNumber;

    @BindView(R.id.btnChangeAddress)
    Button btnChangeAddress;


    private ProfileListAdapter adapter;
    private ArrayList<ProfileList> profileListArrayList;
    private String userid;
    private String addressid;
    private boolean flag;


    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, v);

        addData();

        adapter = new ProfileListAdapter(getContext(), profileListArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(adapter);

        presenter = new ProfilePresenter(getContext(), this);
        rlName.setVisibility(View.INVISIBLE);
        presenter.SetProfile();

        btnChangeAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (flag){
                    Intent intent = new Intent(getContext(), ShippingAddressActivity.class);
                    intent.putExtra("userid", userid);
                    intent.putExtra("addressid", addressid);
                    intent.putExtra("flag", false);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(getContext(), ShippingAddressActivity.class);
                    intent.putExtra("userid", userid);
                    intent.putExtra("addressid", addressid);
                    intent.putExtra("flag", flag);
                    startActivity(intent);
                }

            }
        });

//        ProfileAdapter adapter = new ProfileAdapter(getActivity(), list);
//        lvProfile.setAdapter(adapter);

        return v;
    }

    @Override
    public void SetProfile(User user, ArrayList<Address> addresses) {

        //sudah set addresss

        if(addresses.size() > 1){
            flag = true;
            userid = user.getUserid();
            addressid = addresses.get(0).getAddressid();

            rlName.setVisibility(View.VISIBLE);

            tvProfileName.setText(addresses.get(0).getAddressname());
            tvProfilePhoneNumber.setText(addresses.get(0).getAddressphone());
            btnChangeAddress.setVisibility(View.GONE);

            AddAddress(user, addresses);
        }else{
            flag = true;
            userid = user.getUserid();
            addressid = addresses.get(0).getAddressid();

            rlName.setVisibility(View.VISIBLE);

            tvProfileName.setText(addresses.get(0).getAddressname());
            tvProfilePhoneNumber.setText(addresses.get(0).getAddressphone());
            btnChangeAddress.setText("Add More Address");

            AddAddress(user, addresses);
        }

    }

    private void AddAddress(User user, ArrayList<Address> addresses) {

        AddressAdapter addressAdapter = new AddressAdapter(getContext(), addresses, user);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvShippingAddress.setLayoutManager(layoutManager);
        rvShippingAddress.setAdapter(addressAdapter);
    }

    @Override
    public void SetUserOnly(User user) {

        //belum set address
        flag = false;
        userid = user.getUserid();

        tvProfileName.setText(user.getEmail());
        tvProfilePhoneNumber.setText(" - ");
        rlName.setVisibility(View.VISIBLE);

        btnChangeAddress.setText("Enter Address");

    }

    @Override
    public void GetDataFailed(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_LONG).show();
    }


    void addData(){

        profileListArrayList = new ArrayList<>();
        profileListArrayList.add(new ProfileList("Data Diri"));
        profileListArrayList.add(new ProfileList("Order History"));
        profileListArrayList.add(new ProfileList("Referal"));
        profileListArrayList.add(new ProfileList("Logout"));

    }
}
