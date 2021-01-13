package com.jefaskincare.mobile.android.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;



//import com.google.android.libraries.places.api.model.Place;
import com.jefaskincare.mobile.android.MainActivity;
import com.jefaskincare.mobile.android.R;
import com.jefaskincare.mobile.android.activities.Model.City;
import com.jefaskincare.mobile.android.activities.Model.District;
import com.jefaskincare.mobile.android.activities.Model.Province;
import com.jefaskincare.mobile.android.activities.Presenter.AddressPresenter;
import com.jefaskincare.mobile.android.activities.View.AddressView;
import com.jefaskincare.mobile.android.fragment.profile.Model.Address;
//import com.jefaskincare.mobile.android.manager.MapActivity;
//import com.jefaskincare.mobile.android.utils.PlacesFieldSelector;
//import com.rtchagas.pingplacepicker.PingPlacePicker;
import com.schibstedspain.leku.LocationPickerActivity;
import com.schibstedspain.leku.LocationPickerActivityKt;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.util.ArrayList;

import static com.schibstedspain.leku.LocationPickerActivityKt.LATITUDE;
import static com.schibstedspain.leku.LocationPickerActivityKt.LOCATION_ADDRESS;
import static com.schibstedspain.leku.LocationPickerActivityKt.LONGITUDE;

public class ShippingAddressActivity extends AppCompatActivity implements AddressView, AdapterView.OnItemSelectedListener {

    private static final int REQUST_CODE = 101;
    private EditText etNamaPenerima;
    private EditText etNomorTelp;
    private ImageView ivBackShippingAddress;
    private SearchableSpinner spinnerProvince;
    private SearchableSpinner spinnerCity;
    private SearchableSpinner spinnerDistrict;
    private Spinner spinnerPostalCode;
    private EditText etAlamatLengkap;
    private Button btnSave;
    private Button btnGetAddress;
    private AddressPresenter presenter;
    private String[] postal;
    private ArrayList<Province> provinces;
    private ArrayList<City> cities;
    private ArrayList<District> districts;
    private String districtID;
    private String zip;
    private Address address;
    private String selectedAddress;
    private boolean flag;
    private double latitude;
    private double longitude;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipping_address);
        dialog = new ProgressDialog(this);
        ivBackShippingAddress = findViewById(R.id.ivBackShippingAddress);
        etNamaPenerima = findViewById(R.id.etNamaPenerima);
        etNomorTelp = findViewById(R.id.etNomorTelp);
        etAlamatLengkap = findViewById(R.id.etAlamatLengkap);
        spinnerProvince = findViewById(R.id.spinnerProvince);
        spinnerCity = findViewById(R.id.spinnerCity);
        spinnerDistrict = findViewById(R.id.spinnerDistrict);
        spinnerPostalCode = findViewById(R.id.spinnerPostalCode);

        btnGetAddress = findViewById(R.id.btnGetAddress);
        btnGetAddress.setOnClickListener(view -> {
//            Intent i  = new Intent(ShippingAddressActivity.this, MapActivity.class);
//            startActivityForResult(i, REQUST_CODE);

            Intent locationPickerIntent = new LocationPickerActivity.Builder()
//                    .withLocation(41.4036299, 2.1743558)
                    .withGeolocApiKey("AIzaSyDVhuphA3Kcf1rNr2AOXBKuOAYN_gN7gCM")
//                    .withSearchZone("es_ES")
//                    .withSearchZone(SearchZoneRect(LatLng(26.525467, -18.910366), LatLng(43.906271, 5.394197)))
                    .withDefaultLocaleSearchZone()
                    .shouldReturnOkOnBackPressed()
//                    .withStreetHidden()
//                    .withCityHidden()
//                    .withZipCodeHidden()
//                    .withSatelliteViewHidden()
//                    .withGooglePlacesEnabled()
//                    .withGoogleTimeZoneEnabled()
//                    .withVoiceSearchHidden()
//                    .withUnnamedRoadHidden()
                    .build(getApplicationContext());

            startActivityForResult(locationPickerIntent, 101);
        });


        btnSave = findViewById(R.id.btnSave);
        btnSave.setClickable(false);
        flag = getIntent().getBooleanExtra("flag", false);

        presenter = new AddressPresenter(this, this);
        InitSpinner();
    }

    private void InitSpinner() {

        boolean checkout = getIntent().getBooleanExtra("checkout", false);
        if (checkout){
            flag = false;
        }

        if(flag){
            address = (Address) getIntent().getSerializableExtra("address");
            etAlamatLengkap.setText(address.getAddress());
            etNamaPenerima.setText(address.getAddressname());
            etNomorTelp.setText(address.getAddressphone());
            latitude = Double.parseDouble(address.getLatitude());
            longitude = Double.parseDouble(address.getLongitude());

//            String[] province = new String[]{address.getProv()};
//            ArrayAdapter<String> adapterProvince = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, province);
//            spinnerProvince.setAdapter(adapterProvince);

            String[] city = new String[]{address.getCity()};
            ArrayAdapter<String> adapterCity = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, city);
            spinnerCity.setAdapter(adapterCity);

            String[] district = new String[]{address.getDistrict()};
            ArrayAdapter<String> adapterDistrict = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, district);
            spinnerDistrict.setAdapter(adapterDistrict);

            postal = new String[]{address.getZipcode()};
            ArrayAdapter<String> adapterPostal = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, postal);
            spinnerPostalCode.setAdapter(adapterPostal);

            presenter.GetProvince();

        }else{
            String[] province = new String[]{"Pilih Provinsi"};
            ArrayAdapter<String> adapterProvince = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, province);
            spinnerProvince.setAdapter(adapterProvince);

            String[] city = new String[]{"Pilih Kabupaten / Kota"};
            ArrayAdapter<String> adapterCity = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, city);
            spinnerCity.setAdapter(adapterCity);

            String[] district = new String[]{"Pilih Kecamatan"};
            ArrayAdapter<String> adapterDistrict = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, district);
            spinnerDistrict.setAdapter(adapterDistrict);

            postal = new String[]{"Pilih Kode Pos"};
            ArrayAdapter<String> adapterPostal = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, postal);
            spinnerPostalCode.setAdapter(adapterPostal);

            presenter.GetProvince();
        }


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.setMessage("Menyimpan Alamat");
                dialog.show();
                if(flag){
                    if(latitude != 0.0){
                        presenter.ChangeAddress(
                                getIntent().getStringExtra("userid"),
                                etAlamatLengkap.getText().toString().trim(),
                                getIntent().getStringExtra("addressid"),
                                districtID,
                                zip,
                                latitude,
                                longitude,
                                etNamaPenerima.getText().toString().trim(),
                                etNomorTelp.getText().toString().trim());
                    }else{
                        dialog.dismiss();
                        Toast.makeText(getApplicationContext(), "Harap Memilih Alamat dari Map", Toast.LENGTH_LONG).show();
                    }
                }else{
                    if(latitude != 0.0){
                        presenter.AddAddress(
                                getIntent().getStringExtra("userid"),
                                etAlamatLengkap.getText().toString().trim(),
                                districtID,
                                zip,
                                latitude,
                                longitude,
                                etNamaPenerima.getText().toString().trim(),
                                etNomorTelp.getText().toString().trim());
                    }else{
                        dialog.dismiss();
                        Toast.makeText(getApplicationContext(), "Harap Memilih Alamat dari Map", Toast.LENGTH_LONG).show();
                    }

                }

            }
        });

        ivBackShippingAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShippingAddressActivity.super.onBackPressed();
//                Intent intent = new Intent(ShippingAddressActivity.this, MainActivity.class);
//                intent.putExtra("FRAGMENT_KEY", 4);
//                startActivity(intent);
            }
        });

        etAlamatLengkap.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int count, int i2) {
                if (i >= 5) {
                    btnSave.setBackgroundColor(getResources().getColor(R.color.golden));
                    btnSave.setClickable(true);
                }else{
                    btnSave.setBackgroundColor(getResources().getColor(R.color.white_four));
                    btnSave.setClickable(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() >= 5) {
                    btnSave.setBackgroundColor(getResources().getColor(R.color.golden));
                    btnSave.setClickable(true);
                }else{
                    btnSave.setBackgroundColor(getResources().getColor(R.color.white_four));
                    btnSave.setClickable(false);
                }
            }
        });
    }

    @Override
    public void GetProvinceSuccess(ArrayList<Province> provinceList) {

        if(flag){
            Province province = new Province();
            province.setProvId("0");
            province.setProvName(address.getProv());
            provinceList.remove(0);
            provinceList.add(0, province);
            presenter.GetDistrictId(address.getDistrict());
        }
        provinces = new ArrayList<>(provinceList);
        ArrayAdapter<Province> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, provinceList);
        spinnerProvince.setAdapter(adapter);
        spinnerProvince.setOnItemSelectedListener(this);

    }

    @Override
    public void GetCitySuccess(ArrayList<City> citieList) {
        cities = new ArrayList<>(citieList);
        ArrayAdapter<City> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, citieList);
        spinnerCity.setAdapter(adapter);
        spinnerCity.setOnItemSelectedListener(this);
    }

    @Override
    public void GetDistrictSuccess(ArrayList<District> districtList) {
        if(flag){
            districtID = districtList.get(1).getDistrictId();
            zip = districtList.get(1).getPostalCode();
        }else{
            districts = new ArrayList<>(districtList);
            ArrayAdapter<District> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, districtList);
            spinnerDistrict.setAdapter(adapter);
            spinnerDistrict.setOnItemSelectedListener(this);
        }

    }

    @Override
    public void GetPostalCode(String postalCode) {
        zip = postalCode;
        postal = new String[]{"Pilih Kode Pos", postalCode};
        ArrayAdapter<String> adapterPostal = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, postal);
        spinnerPostalCode.setAdapter(adapterPostal);
    }

    @Override
    public void ChangeAddressSuccess() {
        boolean checkout = getIntent().getBooleanExtra("checkout", false);
        dialog.dismiss();
        if(checkout){
            Intent intent=new Intent();
            setResult(102,intent);
            finish();
        }else{
            if (flag){
                Toast.makeText(getApplicationContext(), "Berhasil Mengganti Alamat", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(ShippingAddressActivity.this, MainActivity.class);
                intent.putExtra("FRAGMENT_KEY", 4);
                startActivity(intent);
            }else{
                Toast.makeText(getApplicationContext(), "Berhasil Menambahkan Alamat", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(ShippingAddressActivity.this, MainActivity.class);
                intent.putExtra("FRAGMENT_KEY", 4);
                startActivity(intent);
            }
        }

    }


    @Override
    public void GetDataFailed(String error) {
        dialog.dismiss();
        Toast.makeText(getApplicationContext(), error, Toast.LENGTH_LONG).show();
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch (adapterView.getId()) {
            case R.id.spinnerProvince:
                if (!provinces.get(i).getProvId().equals("0")) {
                    presenter.GetCity(provinces.get(i).getProvId());
                }
                break;

            case R.id.spinnerCity:
                if (!cities.get(i).getCityId().equals("0")) {
                    presenter.GetDistrict(cities.get(i).getCityId());
                }
                break;

            case R.id.spinnerDistrict:
                if (!districts.get(i).getDistrictId().equals("0")) {
                    districtID = districts.get(i).getDistrictId();
                    presenter.GetPostalCode(districts.get(i));
                }
                break;
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUST_CODE) {
            if (resultCode == RESULT_OK) {
                assert data != null;
//                String fullAddress = data.getStringExtra("address");
//                latitude = data.getDoubleExtra("latitude",0);
//                longitude = data.getDoubleExtra("longitude",0);
//                etAlamatLengkap.setText(fullAddress);

                latitude = data.getDoubleExtra(LATITUDE, 0.0);
//                Log.d("LATITUDE****", latitude.toString())
                longitude = data.getDoubleExtra(LONGITUDE, 0.0);
//                Log.d("LONGITUDE****", longitude.toString())
                selectedAddress = data.getStringExtra(LOCATION_ADDRESS);
                etAlamatLengkap.setText(selectedAddress);
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
