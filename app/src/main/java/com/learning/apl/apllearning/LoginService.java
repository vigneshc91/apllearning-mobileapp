package com.learning.apl.apllearning;

import android.os.AsyncTask;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by shiva on 2/11/16.
 */

public class LoginService {

    private String loginUrl = AppConstants.HOST_NAME + AppConstants.LOGIN_URL;

    public void UserLogin(RequestQueue loginRequestQueue, final String userName, final String password){

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, loginUrl, null,
                new Response.Listener<JSONObject>(){
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Response", response.toString());
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error.response", error.toString());
                    }
                }
        ){

            @Override
            public byte[] getBody() {
                JSONObject parameters = new JSONObject();
                String body = null;
                try {
                    parameters.put(AppConstants.USER_NAME, userName);
                    parameters.put(AppConstants.PASSWORD, password);
                    body = parameters.toString();
                } catch (Exception e){
                    e.printStackTrace();
                }

                try {
                    return  body.toString().getBytes("utf-8");

                } catch (Exception e){
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> parameters = new HashMap<String, String>();
                parameters.put("Content-Type", "application/x-www-form-urlencoded");
                return parameters;
            }
        };

        loginRequestQueue.add(request);
    }

}
