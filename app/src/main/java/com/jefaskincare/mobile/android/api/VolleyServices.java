package com.jefaskincare.mobile.android.api;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class VolleyServices {

    private IResult resultCallback = null;
    private Context context;

    public VolleyServices(IResult resultCallback, Context context){
        this.resultCallback = resultCallback;
        this.context =  context;
    }

    public void postDataVolley(String url, final JSONObject param) {
        String baseUrl = "http://34.80.174.252/v1/" + url;
        try {
            JsonObjectRequest jr = new JsonObjectRequest(Request.Method.POST, baseUrl,param, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
//                    dialog.dismiss();
                    if (resultCallback != null)
                        resultCallback.notifySuccess(response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
//                    dialog.dismiss();
                    if (resultCallback != null)
                        resultCallback.notifyError(error);
                }
            }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("Lang", "en");
                    params.put("IMEI", "2432432432432");
                    params.put("ApiId", "Bearer muslim");
                    params.put("ApiKey", "Bearer A387856cA1513c77");
                    params.put("Timestamp", "2017-12-21 18:18:00");
                    params.put("Signature", "Bearer 9fcfc1d6794b2b32df30dfa4880ca92af1be7b51");
                    params.put("dev", "1");
                    return params;
                }
            };
            jr.setRetryPolicy(new DefaultRetryPolicy(
                    30000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            Volley.newRequestQueue(context).add(jr);

        } catch (Exception e) {

        }
    }

    public void getDataVolley(String url) {
        String baseUrl = "http://34.80.174.252/v1/" + url;
        try {
            JsonObjectRequest jr = new JsonObjectRequest(Request.Method.GET, baseUrl, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
//                    dialog.dismiss();
                    if (resultCallback != null)
                        resultCallback.notifySuccess(response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
//                    dialog.dismiss();
                    if (resultCallback != null)
                        resultCallback.notifyError(error);
                }
            }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("Lang", "en");
                    params.put("IMEI", "2432432432432");
                    params.put("ApiId", "Bearer muslim");
                    params.put("ApiKey", "Bearer A387856cA1513c77");
                    params.put("Timestamp", "2017-12-21 18:18:00");
                    params.put("Signature", "Bearer 9fcfc1d6794b2b32df30dfa4880ca92af1be7b51");
                    params.put("dev", "1");
                    return params;
                }
            };
            Volley.newRequestQueue(context).add(jr);

        } catch (Exception e) {

        }
    }

    public void postDataVolleyApi(String url, final JSONObject param) {
        String baseUrl = url;
        try {
            JsonObjectRequest jr = new JsonObjectRequest(Request.Method.POST, baseUrl,param, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
//                    dialog.dismiss();
                    if (resultCallback != null)
                        resultCallback.notifySuccess(response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
//                    dialog.dismiss();
                    if (resultCallback != null)
                        resultCallback.notifyError(error);
                }
            }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();

                    return params;
                }
            };
            Volley.newRequestQueue(context).add(jr);

        } catch (Exception e) {

        }
    }
}
