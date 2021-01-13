package com.jefaskincare.mobile.android.activities.Presenter;

import android.content.Context;
import android.util.Log;

import com.android.volley.VolleyError;
import com.jefaskincare.mobile.android.activities.View.NewPasswordView;
import com.jefaskincare.mobile.android.api.ApiServices;
import com.jefaskincare.mobile.android.api.IResult;
import com.jefaskincare.mobile.android.api.ParamKey;
import com.jefaskincare.mobile.android.api.UrlKey;
import com.jefaskincare.mobile.android.api.VolleyServices;

import org.json.JSONException;
import org.json.JSONObject;

public class NewPasswordPresenter {
    private Context context;
    private NewPasswordView view;
    private IResult resultCallback;

    public NewPasswordPresenter(Context context, NewPasswordView view){
        this.context = context;
        this.view = view;
    }

    public void NewPasswordInit(String newPassword){
        initResultCallback();
        ApiServices param = new ApiServices();
        VolleyServices volleyServices = new VolleyServices(resultCallback, context);
        volleyServices.postDataVolley(UrlKey.KEY_METHOD_CHANGE_PASSWORD,
                param.ChangePassword(
                        "userid",
                        "oldpassword",
                        newPassword));
    }

    private void initResultCallback() {
        resultCallback = new IResult() {
            @Override
            public void notifySuccess(JSONObject response) {
                try{
                    if(response.get(ParamKey.KEY_CODE).equals(ParamKey.KEY_SUCCESS)
                            || response.get(ParamKey.KEY_CODE).equals(ParamKey.KEY_SUCCESS_ONE)){
                        view.ChangePasswordSuccess();
                    }else{
                        view.ChangePasswordFailed();
                    }
                }catch (JSONException e){
                    Log.e("Error ", e.getMessage());

                }

            }

            @Override
            public void notifyError(VolleyError error) {

            }
        };
    }

}
