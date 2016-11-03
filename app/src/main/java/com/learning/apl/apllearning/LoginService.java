package com.learning.apl.apllearning;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
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
 * Created by shiva on 2/11/16.
 */

public class LoginService {

    private final Context context;
    private final SharedPreferences prefs;
    private RequestQueue loginRequestQueue;
    private String loginUrl = AppConstants.HOST_NAME + AppConstants.LOGIN_URL;

    public LoginService(Context context){
        this.context = context;
        prefs = context.getSharedPreferences(AppConstants.USER_PREF, Context.MODE_PRIVATE);
        loginRequestQueue =  Volley.newRequestQueue(this.context);
    }

    public void userLogin(final String userName, final String password){

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, loginUrl, null,
                new Response.Listener<JSONObject>(){
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Response", response.toString());
                        try{
                            JSONObject ret = new JSONObject(response.toString());
                            String status = ret.getString("status");
                            String result = ret.getString("result");
                            if(status.equals("true")){
                                Toast.makeText(context,"Login success", Toast.LENGTH_SHORT).show();
                                SharedPreferences.Editor editor = prefs.edit();
                                editor.putBoolean(AppConstants.IS_USER_LOGGED_IN, true);
                                editor.commit();
                                Intent dashboardIntent = new Intent(context, DashboardActivity.class);
                                dashboardIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                context.startActivity(dashboardIntent);
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

    public boolean isUserLoggedIn(){
        return prefs.getBoolean(AppConstants.IS_USER_LOGGED_IN, false);
    }

}
