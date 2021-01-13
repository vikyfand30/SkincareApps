package com.jefaskincare.mobile.android.fragment.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jefaskincare.mobile.android.MainActivity;
import com.jefaskincare.mobile.android.R;
import com.jefaskincare.mobile.android.activities.ShippingAddressActivity;
import com.jefaskincare.mobile.android.fragment.profile.Model.Address;
import com.jefaskincare.mobile.android.fragment.profile.Model.User;
import com.jefaskincare.mobile.android.fragment.profile.Presenter.ProfileDetailPresenter;
import com.jefaskincare.mobile.android.fragment.profile.View.ProfileDetailView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileDetailAddressActivity extends AppCompatActivity implements ProfileDetailView {

    @BindView(R.id.tvProfileDetailAddressNamaPenerima)
    TextView tvProfileDetailAddressNamaPenerima;

    @BindView(R.id.tvProfileDetailAddressNomorTelp)
    TextView tvProfileDetailAddressNomorTelp;

    @BindView(R.id.tvProfileDetailAddressProvinsi)
    TextView tvProfileDetailAddressProvinsi;

    @BindView(R.id.tvProfileDetailAddressKota)
    TextView tvProfileDetailAddressKota;

    @BindView(R.id.tvProfileDetailAddressKecamatan)
    TextView tvProfileDetailAddressKecamatan;

    @BindView(R.id.tvProfileDetailAddressKodePos)
    TextView tvProfileDetailAddressKodePos;

    @BindView(R.id.tvProfileDetailAddressAlamatLengkap)
    TextView tvProfileDetailAddressAlamatLengkap;

    @BindView(R.id.ivBackProfileDetailAddress)
    ImageView ivBackProfileDetailAddress;

    @BindView(R.id.btnEditDetailAdddress)
    Button btnEditDetailAddress;

    @BindView(R.id.btnDeleteDetailAddress)
    Button btnDeleteDetailAddress;

    private ProfileDetailPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_detail_address);
        ButterKnife.bind(this);

        presenter = new ProfileDetailPresenter(this, this);

        User user = (User)getIntent().getSerializableExtra("user");
        Address address = (Address)getIntent().getSerializableExtra("address");


        tvProfileDetailAddressNamaPenerima.setText(address.getAddressname());
        tvProfileDetailAddressNomorTelp.setText(address.getAddressphone());
        tvProfileDetailAddressProvinsi.setText(address.getProv());
        tvProfileDetailAddressKota.setText(address.getCity());
        tvProfileDetailAddressKecamatan.setText(address.getDistrict());
        tvProfileDetailAddressKodePos.setText(address.getZipcode());
        tvProfileDetailAddressAlamatLengkap.setText(address.getAddress());

        ivBackProfileDetailAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileDetailAddressActivity.this, MainActivity.class);
                intent.putExtra("FRAGMENT_KEY", 4);
                startActivity(intent);
            }
        });

        btnEditDetailAddress.setOnClickListener(view ->{
            Intent intent = new Intent(this, ShippingAddressActivity.class);
            intent.putExtra("userid", user.getUserid());
            intent.putExtra("addressid", address.getAddressid());
            intent.putExtra("address", address);
            intent.putExtra("flag", true);
            startActivity(intent);
        });

        btnDeleteDetailAddress.setOnClickListener(view -> {
            presenter.removeAddress(user, address);
        });
    }

    @Override
    public void deleteAddressSuccess() {
        Toast.makeText(this, "Sukses Menghapus Alamat", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(ProfileDetailAddressActivity.this, MainActivity.class);
        intent.putExtra("FRAGMENT_KEY", 4);
        startActivity(intent);
        finish();
    }

    @Override
    public void deleteAddressFailed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
