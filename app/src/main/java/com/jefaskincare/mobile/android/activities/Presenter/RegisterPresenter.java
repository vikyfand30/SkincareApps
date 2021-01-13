package com.jefaskincare.mobile.android.activities.Presenter;

import android.content.Context;

import com.android.volley.VolleyError;
import com.jefaskincare.mobile.android.activities.Model.RegisterData;
import com.jefaskincare.mobile.android.activities.View.RegisterView;
import com.jefaskincare.mobile.android.api.ApiServices;
import com.jefaskincare.mobile.android.api.IResult;
import com.jefaskincare.mobile.android.api.ParamKey;
import com.jefaskincare.mobile.android.api.UrlKey;
import com.jefaskincare.mobile.android.api.VolleyServices;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterPresenter {

    private Context context;
    private RegisterView view;
    private IResult resultCallback;
    private RegisterData register;

    public RegisterPresenter(Context context, RegisterView view) {
        this.context = context;
        this.view = view;
    }

    public void RegisterInit(RegisterData register) {
        if (register.getPassword().equals(register.getcPassword())) {
            this.register = register;
            RegisterProcess();
        } else {
            view.PasswordNotMatch();
        }
    }

    private void RegisterProcess() {
        initResultCallback();
        ApiServices param = new ApiServices();
        VolleyServices volleyServices = new VolleyServices(resultCallback, context);
        volleyServices.postDataVolley(UrlKey.KEY_METHOD_REGISTER,
                param.Register(
                        register.getPassword(),
                        register.getEmail(),
                        register.getReferal(),
                        register.getSeller()
                        ));
    }

    private void initResultCallback() {
        resultCallback = new IResult() {
            @Override
            public void notifySuccess(JSONObject response) {
                try {
                    if(response.get(ParamKey.KEY_CODE).equals(ParamKey.KEY_SUCCESS)
                            || response.get(ParamKey.KEY_CODE).equals(ParamKey.KEY_SUCCESS_ONE)){

                        view.RegisterProcessSuccess();

                    } else {

                        view.RegisterProcessFailed(response.get(ParamKey.KEY_MESSAGE).toString());

                    }
                } catch (JSONException e) {
                    view.RegisterProcessFailed("No Data");
                }
            }

            @Override
            public void notifyError(VolleyError error) {
                view.RegisterProcessFailed(error.getMessage());
            }
        };
    }
}
