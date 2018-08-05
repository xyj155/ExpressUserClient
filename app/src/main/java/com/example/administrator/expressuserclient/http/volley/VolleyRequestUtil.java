package com.example.administrator.expressuserclient.http.volley;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

/**
 * Created by Administrator on 2018/7/19/019.
 */

public class VolleyRequestUtil {
    public static JsonObjectRequest Request(String api, final VolleyRequestCllBack volleyRequest) {
        return new JsonObjectRequest(api, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        volleyRequest.onSuccess(response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                try {
                    volleyRequest.onError(error.getMessage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * @param api
     * @param param
     * @param volleyRequest
     * @return
     */
    public static StringRequest RequestWithParams(String api, final Map<String, String> param, final VolleyRequestCllBack volleyRequest) {
        return new StringRequest(Request.Method.POST, api, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                volleyRequest.onSuccess(response);
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                try {
                    volleyRequest.onError(error.getMessage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }) {
            @Override
            public Map<String, String> getParams() throws AuthFailureError {
                return param;
            }
        };
    }

    public static JsonObjectRequest doJsonVolleyRequest(String jsonObject, final VolleyRequestCllBack volleyRequestCllBack) {
        return new JsonObjectRequest(Request.Method.POST, "http://openapi.tuling123.com/openapi/api/v2", jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, "response -> " + response.toString());
                        volleyRequestCllBack.onSuccess(response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
                volleyRequestCllBack.onError(error.getMessage());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json; charset=UTF-8");
                return headers;
            }
        };
    }


}
