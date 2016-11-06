package com.learning.apl.apllearning;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

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

public class MaterialService {
    private final Context context;
    private final SharedPreferences prefs;
    private RequestQueue materialRequestQueue;
    private String getMaterialsUrl = AppConstants.HOST_NAME + AppConstants.GET_MATERIALS_URL;

    public MaterialService(Context context){
        this.context = context;
        prefs = context.getSharedPreferences(AppConstants.USER_PREF, Context.MODE_PRIVATE);
        materialRequestQueue =  Volley.newRequestQueue(this.context);
    }

    public void getMaterialsList(final MaterialListAdapter materialListAdapter, final String subjectId, final int start, final int size){
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, getMaterialsUrl, null,
                new Response.Listener<JSONObject>(){
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Response", response.toString());
                        try{
                            JSONObject ret = new JSONObject(response.toString());
                            String status = ret.getString("status");
                            if(status.equals("true")){
                                JSONArray result = ret.getJSONArray("result");
                                List<MaterialModel> materailList = new ArrayList<MaterialModel>();
                                for (int i = 0; i < result.length(); i++) {
                                    MaterialModel material = new MaterialModel();
                                    JSONObject materialJson = result.getJSONObject(i);

                                    material.setMaterialId(materialJson.getString("id"));
                                    material.setUserId(materialJson.getString("user_id"));
                                    material.setSubjectId(materialJson.getString("subject_id"));
                                    material.setTitle(materialJson.getString("title"));
                                    material.setUrl(materialJson.getString("url"));
                                    material.setDescription(materialJson.getString("Description"));
                                    material.setCreatedAt(materialJson.getString("created_at"));
                                    material.setUpdatedAt(materialJson.getString("updated_at"));
                                    materailList.add(material);
                                }
                                materialListAdapter.addAll(materailList);
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
                    parameters.put(AppConstants.GRADE_ID, prefs.getString(AppConstants.GRADE_ID, ""));
                    parameters.put(AppConstants.SUBJECT_ID, subjectId);
                    parameters.put(AppConstants.START, start);
                    parameters.put(AppConstants.SIZE, size);
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

        materialRequestQueue.add(request);
    }

}
