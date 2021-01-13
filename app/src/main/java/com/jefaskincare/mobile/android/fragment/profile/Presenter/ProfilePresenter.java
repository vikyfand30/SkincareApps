package com.jefaskincare.mobile.android.fragment.profile.Presenter;

import android.content.Context;
import android.provider.ContactsContract;
import android.util.Log;

import com.android.volley.VolleyError;
import com.jefaskincare.mobile.android.api.ApiServices;
import com.jefaskincare.mobile.android.api.IResult;
import com.jefaskincare.mobile.android.api.ParamKey;
import com.jefaskincare.mobile.android.api.Parsing;
import com.jefaskincare.mobile.android.api.UrlKey;
import com.jefaskincare.mobile.android.api.VolleyServices;
import com.jefaskincare.mobile.android.fragment.profile.Model.User;
import com.jefaskincare.mobile.android.fragment.profile.Model.UserData;
import com.jefaskincare.mobile.android.fragment.profile.View.ProfileView;
import com.jefaskincare.mobile.android.manager.Session;

import org.json.JSONException;
import org.json.JSONObject;

public class ProfilePresenter {

    private ProfileView.View view;
    private Context context;
    private Session session;
    private Parsing parse;
    private IResult profileCallback = null;
    private VolleyServices volleyServices;
    private ApiServices params;
    private User user;

    public ProfilePresenter(Context context, ProfileView.View view){
        this.context = context;
        this.view = view;
        session = new Session(context);
        parse = new Parsing();
        params = new ApiServices();
    }

    public void SetProfile(){
        user = parse.ParsingUser(session.getValue(Session.KEY_LOGIN_DATA));
        GetAddress(user);
    }

    private void GetAddress(User user){
        InitAddressCallback();
        volleyServices = new VolleyServices(profileCallback, context);
        volleyServices.postDataVolley(UrlKey.KEY_METHOD_ADDRESS_USER,
                params.AddressUser(user.getUserid()));
    }

    private void InitAddressCallback() {
        profileCallback = new IResult() {
            @Override
            public void notifySuccess(JSONObject response) {
                try{
                    if(response.get(ParamKey.KEY_CODE).equals(ParamKey.KEY_SUCCESS)
                            || response.get(ParamKey.KEY_CODE).equals(ParamKey.KEY_SUCCESS_ONE)){

                        view.SetProfile(user,
                                parse.ParsingAddresses(response));

                    }else{
                        view.SetUserOnly(user);
                    }
                }catch (JSONException e){
                    view.GetDataFailed("Something Wrong, please try again");
                    Log.e("Error ", e.getMessage());
                }
            }

            @Override
            public void notifyError(VolleyError error) {
                view.GetDataFailed(error.getMessage());
            }
        };
    }

}
