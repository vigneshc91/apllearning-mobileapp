package com.learning.apl.apllearning;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by shiva on 6/11/16.
 */

public class SubjectService {

    private final Context context;
    private final SharedPreferences prefs;
    private RequestQueue subjectRequestQueue;
    private String getSubjectsUrl = AppConstants.HOST_NAME + AppConstants.GET_SUBJECTS_URL;

    public SubjectService(Context context){
        this.context = context;
        prefs = context.getSharedPreferences(AppConstants.USER_PREF, Context.MODE_PRIVATE);
        subjectRequestQueue =  Volley.newRequestQueue(this.context);
    }

    public void getSubjectsList(final SubjectListAdapter subjectListAdapter){
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, getSubjectsUrl, null,
                new Response.Listener<JSONObject>(){
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Response", response.toString());
                        try{
                            JSONObject ret = new JSONObject(response.toString());
                            String status = ret.getString("status");
                            if(status.equals("true")){
                                JSONArray result = ret.getJSONArray("result");
                                List<SubjectModel> subjectList = new ArrayList<SubjectModel>();
                                for (int i = 0; i < result.length(); i++) {
                                    SubjectModel subject = new SubjectModel();
                                    JSONObject subjectJson = result.getJSONObject(i);

                                    subject.setSubjectId(subjectJson.getString("id"));
                                    subject.setGrade(subjectJson.getString("grade"));
                                    subject.setName(subjectJson.getString("name"));
                                    subject.setCreatedAt(subjectJson.getString("created_at"));
                                    subject.setUpdatedAt(subjectJson.getString("updated_at"));
                                    subjectList.add(subject);
                                }
                                subjectListAdapter.addAll(subjectList);
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
                    parameters.put(AppConstants.GRADE, prefs.getString(AppConstants.GRADE, ""));
                    parameters.put(AppConstants.START, 0);
                    parameters.put(AppConstants.SIZE, AppConstants.MAX_VALUE);
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

        subjectRequestQueue.add(request);
    }
}
