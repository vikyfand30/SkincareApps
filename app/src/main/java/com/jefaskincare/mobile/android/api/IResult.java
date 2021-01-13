package com.jefaskincare.mobile.android.api;

import com.android.volley.VolleyError;

import org.json.JSONObject;

public interface IResult {
    void notifySuccess(JSONObject response);
    void notifyError(VolleyError error);
}
