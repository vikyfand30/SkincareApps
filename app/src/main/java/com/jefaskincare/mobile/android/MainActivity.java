package com.jefaskincare.mobile.android;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.google.android.material.snackbar.Snackbar;
import com.jefaskincare.mobile.android.activities.CartActivity;
import com.jefaskincare.mobile.android.activities.BaseChatActivity;
import com.jefaskincare.mobile.android.activities.LoginActivity;
import com.jefaskincare.mobile.android.fragment.chat.ChatFragment;
import com.jefaskincare.mobile.android.fragment.failed.DisconnectFragment;
import com.jefaskincare.mobile.android.fragment.feeds.FeedsFragment;
import com.jefaskincare.mobile.android.fragment.profile.ProfileFragment;
import com.jefaskincare.mobile.android.fragment.reviews.ReviewsFragment;
import com.jefaskincare.mobile.android.fragment.shop.ShopFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.jefaskincare.mobile.android.manager.Session;
import com.jefaskincare.mobile.android.utils.NetworkUtil;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.nikartm.support.ImageBadgeView;

public class MainActivity extends AppCompatActivity {

    private Session session;

    @BindView(R.id.ivHomeCart)
    ImageBadgeView  ivHomeCart;

    @BindView(R.id.nav_view)
    BottomNavigationView navView;

    private int fragmentPos;
    Snackbar snackbarwithbutton;
    View.OnClickListener customSnackbarClick;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        session = new Session(this);
        GetBadge();

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navView.setItemIconTintList(null);
        navView.setItemTextAppearanceActive(R.font.opensans_semibold);

        Intent intent = getIntent();
        int key;
        key = intent.getIntExtra("FRAGMENT_KEY", 0);

        ivHomeCart.setOnClickListener(view -> {
            Intent intent1 = new Intent(MainActivity.this, CartActivity.class);
            intent1.putExtra("from", true);
            intent1.putExtra("FRAGMENT_KEY", fragmentPos);
            startActivity(intent1);
        });

        customSnackbarClick = view -> snackbarwithbutton.dismiss();

            GetFragment(key);

    }

    private void GetFragment(int key) {
        if (key == 0) {
            displayFragment(R.id.frame_container, new ShopFragment());

        } else if (key == 1) {
            displayFragment(R.id.frame_container, new ReviewsFragment());
            navView.getMenu().getItem(1).setChecked(true);

        } else if (key == 2) {
            displayFragment(R.id.frame_container, new ChatFragment());
            navView.getMenu().getItem(2).setChecked(true);

        } else if (key == 3) {
            displayFragment(R.id.frame_container, new FeedsFragment());
            navView.getMenu().getItem(3).setChecked(true);
        } else if (key == 4) {
            displayFragment(R.id.frame_container, new ProfileFragment());
            navView.getMenu().getItem(4).setChecked(true);
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_shop:
                    if (NetworkUtil.isNetworkAvailableHome(MainActivity.this)) {
                        if (fragmentPos != 0) {
                            displayFragment(R.id.frame_container, new ShopFragment());
                            fragmentPos = 0;
                        }
                        return true;
                    }else {
                        displayFragment(R.id.frame_container, new DisconnectFragment());
                        toastDisconnect();
                        return true;
                    }

                case R.id.navigation_reviews:
                    if (NetworkUtil.isNetworkAvailableHome(MainActivity.this)){
                        if (fragmentPos != 1){
                            displayFragment(R.id.frame_container, new ReviewsFragment());
                            fragmentPos = 1;
                        }
                        return true;

                    }
                    else {
                        displayFragment(R.id.frame_container, new DisconnectFragment());
                        toastDisconnect();
                        return true;
                    }

                case R.id.navigation_chat:
                    fragmentPos = 0;
                    Intent i = new Intent(MainActivity.this, BaseChatActivity.class);
                    startActivity(i);
                    return true;

                case R.id.navigation_feeds:
                    if (NetworkUtil.isNetworkAvailableHome(MainActivity.this)) {
                        if (fragmentPos != 3) {
                            displayFragment(R.id.frame_container, new FeedsFragment());
                            fragmentPos = 3;
                        }
                        return true;
                    }else {
                        displayFragment(R.id.frame_container, new DisconnectFragment());
                        toastDisconnect();
                        return true;
                    }

                case R.id.navigation_profile:
                    if (NetworkUtil.isNetworkAvailableHome(MainActivity.this)) {
                        if (fragmentPos != 4) {
                            fragmentPos = 4;
                            if (session.getLoginOk(Session.LOGIN_OK)) {
                                displayFragment(R.id.frame_container, new ProfileFragment());
                            } else {
                                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                                startActivity(intent);
                            }
                        }
                        return true;
                    }
                    else {
                        displayFragment(R.id.frame_container, new DisconnectFragment());
                        toastDisconnect();
                        return true;
                    }
            }

            return false;

        }
    };


    public void displayFragment(int fragmentResourceID, Fragment fragment) {

        try {
            showFragment(fragmentResourceID, fragment);
        } catch (IllegalStateException e) {
            e.printStackTrace();

            showFragmentAllowingStateLoss(fragment, fragmentResourceID);
        }
    }

    public void showFragmentAllowingStateLoss(Fragment fragment, int fragmentResourceID) {

        if (fragment != null) {
            FragmentManager mFragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
            fragmentTransaction.replace(fragmentResourceID, fragment);
            fragmentTransaction.detach(fragment);
            fragmentTransaction.attach(fragment);
            fragmentTransaction.commitAllowingStateLoss();
        }
    }

    public void showFragment(int fragmentResourceID, Fragment fragment) {
        FragmentManager mFragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        {
                transaction.replace(fragmentResourceID, fragment);
                transaction.commit();
//            }
        }
    }

    public void toastDisconnect(){
        snackbarwithbutton = Snackbar.make(findViewById(android.R.id.content),"No Internet Connection", Snackbar.LENGTH_LONG)
                .setAction("RETRY", customSnackbarClick)
                .setActionTextColor(Color.WHITE);
        View snackView = snackbarwithbutton.getView();
        FrameLayout.LayoutParams parentParams = (FrameLayout.LayoutParams) snackView.getLayoutParams();
        snackView.setBackgroundResource(R.drawable.rounded_popup_black);
        parentParams.setMargins(20,20,20,200);
        snackbarwithbutton.show();
    }

    private void GetBadge(){
        int counter = Integer.parseInt(session.getValue(Session.KEY_CART_COUNTER));
        if (counter > 0){
            ivHomeCart.setBadgeValue(counter);
            ivHomeCart.visibleBadge(true);
        }else{
            ivHomeCart.setBadgeValue(counter);
            ivHomeCart.visibleBadge(false);
        }

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        GetBadge();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        GetFragment(0);
    }
}
