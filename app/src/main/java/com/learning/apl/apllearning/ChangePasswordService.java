package com.learning.apl.apllearning;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

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
 * Created by shiva on 6/11/16.
 */

public class ChangePasswordService {

    private Context context;
    private SharedPreferences prefs;
    private RequestQueue changePasswordRequestQueue;
    private String changePasswordUrl = AppConstants.HOST_NAME + AppConstants.CHANGE_PASSWORD_URL;

    public ChangePasswordService(Context context){
        this.context = context;
        prefs = context.getSharedPreferences(AppConstants.USER_PREF, Context.MODE_PRIVATE);
        changePasswordRequestQueue =  Volley.newRequestQueue(this.context);
    }

    public void chanePassword(final String oldPassword, final String newPassword){

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, changePasswordUrl, null,
                new Response.Listener<JSONObject>(){
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Response", response.toString());
                        try{
                            JSONObject ret = new JSONObject(response.toString());
                            String status = ret.getString("status");
                            String result = ret.getString("result");
                            if(status.equals("true")){
                                Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e){
                            e.printStackTrace();
                        }
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
                    parameters.put(AppConstants.TOKEN, prefs.getString(AppConstants.TOKEN, ""));
                    parameters.put(AppConstants.OLD_PASSWORD, oldPassword);
                    parameters.put(AppConstants.NEW_PASSWORD, newPassword);
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

        changePasswordRequestQueue.add(request);
    }
}
