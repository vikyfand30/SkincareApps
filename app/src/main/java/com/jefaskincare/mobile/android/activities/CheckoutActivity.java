package com.jefaskincare.mobile.android.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.jefaskincare.mobile.android.R;
import com.jefaskincare.mobile.android.activities.Model.Courier;
import com.jefaskincare.mobile.android.activities.Model.Shipping;
import com.jefaskincare.mobile.android.activities.Presenter.CheckoutPresenter;
import com.jefaskincare.mobile.android.activities.View.CheckoutView;
import com.jefaskincare.mobile.android.activities.transfer.bank.TransferBankSuccessActivity;
import com.jefaskincare.mobile.android.api.Parsing;
import com.jefaskincare.mobile.android.fragment.profile.Model.Address;
import com.jefaskincare.mobile.android.fragment.profile.Model.User;
import com.jefaskincare.mobile.android.fragment.shop.Model.Product;
import com.jefaskincare.mobile.android.manager.Session;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CheckoutActivity extends AppCompatActivity implements CheckoutView {

    private Boolean flag;
    private Product product;
    private CheckoutPresenter presenter;
    private int totalPayment;
    private String districtName;
    private String districtId;
    private String addressId;
    private ProgressDialog progressDialog;

    @BindView(R.id.ivBackCheckout)
    ImageView ivBack;

    @BindView(R.id.btnCheckoutNext)
    Button btnNext;

    @BindView(R.id.llAlamatPengiriman)
    LinearLayout llAlamatPengiriman;

    @BindView(R.id.tvItemAlamatPengiriman)
    TextView tvItemAlamatPengiriman;

    @BindView(R.id.tvCheckoutNama)
    TextView tvCheckoutNama;

    @BindView(R.id.tvCheckoutPhone)
    TextView tvCheckoutPhone;

    @BindView(R.id.tvCheckoutTotalPrice)
    TextView tvCheckoutTotalPrice;

    @BindView(R.id.tvCheckoutGrandTotal)
    TextView tvCheckoutGrandTotal;

    @BindView(R.id.tvCheckoutShippingCost)
    TextView tvCheckoutShippingCost;

    @BindView(R.id.tvCheckoutInvoiceShippingCost)
    TextView tvCheckoutInvoiceShippingCost;

    @BindView(R.id.btnCheckoutAddAddress)
    Button btnCheckoutAddAddress;

    @BindView(R.id.rgCheckoutAddress)
    RadioGroup rgCheckoutAddress;

    @BindView(R.id.rbRumah)
    RadioButton rbRumah;

    @BindView(R.id.rbKantor)
    RadioButton rbKantor;

    @BindView(R.id.rgCourierList)
    RadioGroup rgCourierList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);
        ButterKnife.bind(this);

        flag = getIntent().getBooleanExtra("flag", false);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Processing Order..");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        GetPrice();

        presenter = new CheckoutPresenter(this, this);
        presenter.GetAddress();

        //presenter.GetShippingCost("Jakarta Timur");

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckoutActivity.super.onBackPressed();
//                if(flag){
//                    //product = (Product) getIntent().getSerializableExtra("productImage");
//                    product = (Product) getIntent().getSerializableExtra("product");
//                    Intent i = new Intent(CheckoutActivity.this, ProductDetailActivity.class);
//                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    i.putExtra("product", product);
//                    startActivity(i);
//                }else{
//                    Intent i = new Intent(CheckoutActivity.this, CartActivity.class);
//                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    startActivity(i);
//                }
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();
               presenter.addAddressCheckout(addressId);
            }
        });

        btnCheckoutAddAddress.setOnClickListener(view -> {
            Session session = new Session(getApplicationContext());
            Parsing parse = new Parsing();
            User user = parse.ParsingUser(session.getValue(Session.KEY_LOGIN_DATA));
            Intent intent = new Intent(CheckoutActivity.this, ShippingAddressActivity.class);
            intent.putExtra("userid", user.getUserid());
            intent.putExtra("flag", flag);
            intent.putExtra("checkout", true);
            startActivityForResult(intent, 102);
        });

    }

    private void GetPrice() {
        Parsing parse = new Parsing();
        tvCheckoutShippingCost.setVisibility(View.INVISIBLE);
        tvCheckoutInvoiceShippingCost.setText("-");
        if (flag) {
            product = (Product) getIntent().getSerializableExtra("product");
            String totalPrice = "Rp" + (parse.DeNumber(product.getProductprice()));
            tvCheckoutTotalPrice.setText(totalPrice);
            totalPayment = Integer.parseInt(product.getProductprice());
            String grandTotal = "Rp" + (parse.DeNumber(
                    String.valueOf(Integer.parseInt(product.getProductprice()))
            ));

            tvCheckoutGrandTotal.setText(grandTotal);
        } else {
            totalPayment = getIntent().getIntExtra("total", 0);
            String totalPrice = "Rp" + parse.DeNumber(String.valueOf(totalPayment));
            tvCheckoutTotalPrice.setText(totalPrice);

            String grandTotal = "Rp" + (parse.DeNumber(String.valueOf(totalPayment))
            );

            tvCheckoutGrandTotal.setText(grandTotal);
        }
    }

    @Override
    public void GetAddressSuccess(User user, ArrayList<Address> address) {

        addressId = address.get(0).getAddressid();
        rgCheckoutAddress.check(rbRumah.getId());
        rbKantor.setVisibility(View.GONE);
        tvItemAlamatPengiriman.setText(address.get(0).getAddress());
        tvCheckoutNama.setText(address.get(0).getAddressname());
        tvCheckoutPhone.setText(address.get(0).getAddressphone());
        btnCheckoutAddAddress.setVisibility(View.GONE);
        districtName = address.get(0).getDistrict();
        presenter.GetDestinationId(address.get(0).getCity());
    }

    @Override
    public void NoAddress() {
        llAlamatPengiriman.setVisibility(View.GONE);
        btnCheckoutAddAddress.setVisibility(View.VISIBLE);
        flag = false;
    }

    @Override
    public void GetCart() {

    }

    @Override
    public void GetCourierSuccess(ArrayList<Courier> courierList) {
        for (int i = 0; i < courierList.size(); i++) {
            RadioButton rbn = new RadioButton(this);
            rbn.setId(1001 + i);
            rbn.setText(courierList.get(i).getCourier());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1f);
            rbn.setLayoutParams(params);
            rgCourierList.addView(rbn);
        }
        GetCourierCost();
    }

    private void GetCourierCost() {
        rgCourierList.setOnCheckedChangeListener((radioGroup, checkedId) -> {
            RadioButton rb = findViewById(checkedId);
            String courierName = rb.getText().toString().trim();
            presenter.GetShippingPrice(courierName, districtId);
        });
        presenter.GetDistrictId(districtName);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 102) {
            presenter.GetAddress();
            llAlamatPengiriman.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void CheckoutFailed(String error) {

    }

    @Override
    public void GetShippingCostSuccess(Shipping shipping) {
        Parsing parse = new Parsing();
        String theCost = "Rp" + parse.DeNumber(shipping.getPrice());
        String grandTotal = "Rp" + (parse.DeNumber(String.valueOf(Integer.parseInt(shipping.getPrice()) + totalPayment)));
        tvCheckoutShippingCost.setText(theCost);
        tvCheckoutShippingCost.setVisibility(View.VISIBLE);
        tvCheckoutInvoiceShippingCost.setText(theCost);
        tvCheckoutGrandTotal.setText(grandTotal);
        btnNext.setBackground(ContextCompat.getDrawable(CheckoutActivity.this, R.drawable.button_primary_selector));
    }

    @Override
    public void GetShippingCostFailed(String error) {
        Parsing parse = new Parsing();
        String totalPrice = "Rp" + parse.DeNumber(String.valueOf(totalPayment));
        tvCheckoutTotalPrice.setText(totalPrice);
        tvCheckoutGrandTotal.setText(totalPrice);
        tvCheckoutShippingCost.setVisibility(View.INVISIBLE);
        tvCheckoutInvoiceShippingCost.setText("Kurir Tidak Tersedia");
//        Toast.makeText(getApplicationContext(), "Gagal mengambil ongkos kirim", Toast.LENGTH_LONG).show();
    }

    @Override
    public void GetDistrictIdSuccess(String districtId) {
        this.districtId = districtId;
    }

    @Override
    public void GetDistrictIdFailed() {
        Toast.makeText(getApplicationContext(), "Gagal mendapatkan district id", Toast.LENGTH_LONG).show();
    }

    @Override
    public void FinishCheckoutSuccess() {
        progressDialog.dismiss();
        Session session = new Session(getApplicationContext());
        session.putSessionStr(Session.KEY_CART_COUNTER,"0");
        Intent i = new Intent(CheckoutActivity.this, TransferBankSuccessActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void FinishCheckoutFailed() {
        progressDialog.dismiss();
        Toast.makeText(getApplicationContext(), "Gagal checkout, silahkan coba lagi", Toast.LENGTH_LONG).show();
    }

    @Override
    public void AddOrderAddressSuccess() {
        Session session = new Session(getApplicationContext());
        String userId = session.getValue(Session.KEY_USER_ID);
        presenter.FinishCheckout(userId);
    }

    @Override
    public void AddOrderAddressFailed() {
        progressDialog.dismiss();
        Toast.makeText(getApplicationContext(), "Gagal menambahkan order address", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
