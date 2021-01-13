package com.jefaskincare.mobile.android.fragment.profile.Presenter;

import android.content.Context;
import android.util.Log;

import com.android.volley.VolleyError;
import com.jefaskincare.mobile.android.api.ApiServices;
import com.jefaskincare.mobile.android.api.IResult;
import com.jefaskincare.mobile.android.api.ParamKey;
import com.jefaskincare.mobile.android.api.UrlKey;
import com.jefaskincare.mobile.android.api.VolleyServices;
import com.jefaskincare.mobile.android.fragment.profile.Model.Address;
import com.jefaskincare.mobile.android.fragment.profile.Model.User;
import com.jefaskincare.mobile.android.fragment.profile.View.ProfileDetailView;

import org.json.JSONException;
import org.json.JSONObject;

public class ProfileDetailPresenter {

    private Context context;
    private ProfileDetailView view;
    private IResult removeCallback;
    private VolleyServices volleyServices;
    private ApiServices params;

    public ProfileDetailPresenter(Context context, ProfileDetailView view){
        this.context = context;
        this.view = view;
        params = new ApiServices();

    }

    public void removeAddress(User user,
                              Address address){
        initRemoveResultCallback();
        volleyServices = new VolleyServices(removeCallback, context);
        volleyServices.postDataVolley(UrlKey.KEY_METHOD_ADDRESS_REMOVE,
                params.AddressRemove(
                        user.getUserid(),
                        address.getAddressid()));
    }

    private void initRemoveResultCallback() {
        removeCallback = new IResult() {
            @Override
            public void notifySuccess(JSONObject response) {
                try{
                    if(response.get(ParamKey.KEY_CODE).equals(ParamKey.KEY_SUCCESS)
                            || response.get(ParamKey.KEY_CODE).equals(ParamKey.KEY_SUCCESS_ONE)){

                        view.deleteAddressSuccess();
                    }else{
                        view.deleteAddressFailed(response.get(ParamKey.KEY_MESSAGE).toString());
                    }
                }catch (JSONException e){
                    view.deleteAddressFailed("Something Wrong, please try again");
                    Log.e("Error ", e.getMessage());
                }
            }

            @Override
            public void notifyError(VolleyError error) {
                view.deleteAddressFailed("Server Error, please try again");
            }
        };
    }
}
