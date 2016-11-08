package com.learning.apl.apllearning;

import android.app.Activity;
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
    private String logoutUrl = AppConstants.HOST_NAME + AppConstants.LOGOUT_URL;
    private String getLoggedInUserUrl = AppConstants.HOST_NAME + AppConstants.GET_LOGGED_IN_USER_URL;

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
                            if(status.equals("true")){
                                Toast.makeText(context,"Login success", Toast.LENGTH_SHORT).show();
                                JSONObject result = ret.getJSONObject("result");
                                SharedPreferences.Editor editor = prefs.edit();
                                UserModel user = new UserModel();
                                user.setUserId(result.getString("id"));
                                user.setUserName(result.getString("user_name"));
                                user.setUserType(result.getString("user_type"));
                                user.setGradeId(result.getString("grade_id"));
                                user.setSection(result.getString("status"));
                                user.setCreatedAt(result.getString("created_at"));
                                user.setUpdatedAt(result.getString("updated_at"));
                                user.setToken(result.getString("token"));
                                user.setGrade(result.getString("grade"));
                                user.setSection(result.getString("section"));
                                editor.putString(AppConstants.USER_NAME, user.getUserName());
                                editor.putString(AppConstants.USER_ID, user.getUserId());
                                editor.putString(AppConstants.TOKEN, user.getToken());
                                editor.putString(AppConstants.GRADE, user.getGrade());
                                editor.putBoolean(AppConstants.IS_USER_LOGGED_IN, true);
                                editor.commit();
                                Intent dashboardIntent = new Intent(context, DashboardActivity.class);
                                dashboardIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                context.startActivity(dashboardIntent);
                                ((Activity)context).finish();
                            } else {
                                String result = ret.getString("result");
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

    public void isUserLoggedIn(){
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, getLoggedInUserUrl, null,
                new Response.Listener<JSONObject>(){
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Response", response.toString());
                        try{
                            JSONObject ret = new JSONObject(response.toString());
                            String status = ret.getString("status");
                            SharedPreferences.Editor editor = prefs.edit();
                            if(status.equals("true")){
                                Intent dashboardIntent = new Intent(context, DashboardActivity.class);
                                context.startActivity(dashboardIntent);
                                ((Activity)context).finish();
                            } else {
                                editor.clear();
                                editor.commit();
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

    public void userLogout(){
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, logoutUrl, null,
                new Response.Listener<JSONObject>(){
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Response", response.toString());
                        try{
                            JSONObject ret = new JSONObject(response.toString());
                            String status = ret.getString("status");
                            SharedPreferences.Editor editor = prefs.edit();
                            if(status.equals("true")){
                                editor.clear();
                                editor.commit();
                                Intent loginIntent = new Intent(context, LoginActivity.class);
                                context.startActivity(loginIntent);
                                ((Activity)context).finish();
                            } else {
                                Toast.makeText(context, ErrorConstants.LOGIN_FAILED, Toast.LENGTH_SHORT).show();
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
