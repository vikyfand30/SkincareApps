package com.jefaskincare.mobile.android.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.jefaskincare.mobile.android.MainActivity;
import com.jefaskincare.mobile.android.R;
import com.jefaskincare.mobile.android.activities.Presenter.LoginPresenter;
import com.jefaskincare.mobile.android.activities.View.LoginView;
import com.jefaskincare.mobile.android.fragment.profile.Model.User;
import com.jefaskincare.mobile.android.fragment.shop.Model.Product;
import com.jefaskincare.mobile.android.manager.Session;

import java.util.ArrayList;

import br.com.simplepass.loadingbutton.customViews.CircularProgressButton;
import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements LoginView.View {

    private LoginPresenter presenter;
    private Session session;
    private int qty = 0;

    @BindView(R.id.etLoginUsername)
    EditText etLoginUsername;

    @BindView(R.id.etLoginPassword)
    EditText etLoginPassword;

    @BindView(R.id.tvRegist)
    TextView tvRegist;

    @BindView(R.id.btnLogin)
    CircularProgressButton btnLogin;

    @BindView(R.id.tvForgotPassword)
    TextView tvForgotPassword;

    @BindView(R.id.tvDialogLogin)
    TextView tvDialogLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);


        presenter = new LoginPresenter(this, this);
        session = new Session(this);

//        addTextWatcher(etLoginUsername);
//        addTextWatcher(etLoginPassword);

        initContent();
    }


    private void initContent() {

        String message = "LOGIN";
        btnLogin.setText(message);

//        dialog = new ProgressDialog(this);

        tvRegist.setOnClickListener(view -> {
            Intent i = new Intent(LoginActivity.this, RegistrationActivity.class);
            startActivity(i);
        });

        btnLogin.setOnClickListener(view -> {
            btnLogin.startAnimation();

//                etLoginUsername.setText("test2@gmail.com");
//                etLoginPassword.setText("1234");

            String username = etLoginUsername.getText().toString().trim();
            String password = etLoginPassword.getText().toString().trim();

            presenter.LoginProcess(username, password);

        });

        tvForgotPassword.setOnClickListener(view -> {
            Intent i = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
            startActivity(i);
        });
    }

    @Override
    public void LoginSuccess(User user) {
        presenter.getCart(user);
    }

    @Override
    public void LoginFailed(String error) {
        stopButtonAnimation();
        tvDialogLogin.setVisibility(View.VISIBLE);
    }

    @Override
    public void getCartSuccess(ArrayList<Product> productList, int totalPayment) {

        for(int i = 0; i < productList.size() ; i++){
            qty = qty + Integer.parseInt(productList.get(i).getOrderqty());
        }
        session.putSessionStr(Session.KEY_CART_COUNTER, String.valueOf(qty));
        GoToMainActivity();

    }

    private void GoToMainActivity() {
        stopButtonAnimation();
        tvDialogLogin.setVisibility(View.GONE);
        session.putLoginOk(Session.LOGIN_OK, true);
        Intent i = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void noCartFound() {
        stopButtonAnimation();
        session.putLoginOk(Session.LOGIN_OK, true);
        Intent i = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(i);
    }

//    public void addTextWatcher(final EditText input) {
//        input.addTextChangedListener(new TextWatcher() {
//
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                validateSignInForm();
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//
//        });
//    }


    //    public void validateSignInForm(){
//            String email = etLoginUsername.getText().toString();
//            String pass = etLoginPassword.getText().toString();
//
//            if (FormValidation.required(email) && FormValidation.validEmail(email) && (FormValidation.required(pass) && (FormValidation.validPassword(pass))))
//            {
//                isFormValidationSuccess = true;
//                btnLogin.setBackground(ContextCompat.getDrawable(LoginActivity.this, R.drawable.button_primary_selector));
//            }else{
//                isFormValidationSuccess =false;
//                btnLogin.setBackground(ContextCompat.getDrawable(LoginActivity.this, R.drawable.button_selector_selected));
//            }
//    }
    private void stopButtonAnimation() {
        btnLogin.revertAnimation();
    }


}
