package com.jefaskincare.mobile.android.fragment.profile.View;

import com.jefaskincare.mobile.android.fragment.profile.Model.Address;
import com.jefaskincare.mobile.android.fragment.profile.Model.User;

import java.util.ArrayList;

public class ProfileView {

    public interface View{
        void SetProfile(User user, ArrayList<Address> addresses);
        void SetUserOnly(User user);
        void GetDataFailed(String error);
    }
}
