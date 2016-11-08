package com.learning.apl.apllearning;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MaterialActivity extends AppCompatActivity {

    private ListView materialListView;
    private String subjectId;
    private MaterialService materialService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            subjectId = extras.getString(AppConstants.SUBJECT_ID);
        }

        materialListView = (ListView) findViewById(R.id.materialListView);
        ArrayList<MaterialModel> materialList = new ArrayList<MaterialModel>();
        MaterialListAdapter materialListAdapter = new MaterialListAdapter(this, materialList);
        materialListView.setAdapter(materialListAdapter);

        materialService = new MaterialService(this);
        materialService.getMaterialsList(materialListAdapter, subjectId, AppConstants.INITIAL_START_VALUE, AppConstants.SIZE_VALUE);

        final Intent materialIntent = new Intent(this, MaterialViewActivity.class);

        materialListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Object obj = materialListView.getItemAtPosition(i);
                MaterialModel material = (MaterialModel) obj;
                materialIntent.putExtra(AppConstants.MATERIAL_URL, AppConstants.MATERIAL_URL + material.getUrl());
                startActivity(materialIntent);
            }
        });


    }
}
