package com.learning.apl.apllearning;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class SubjectActivity extends MenuActivity {

    private ListView subjectListView;
    private SubjectService subjectService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);

        subjectListView = (ListView) findViewById(R.id.subjectListView);
        ArrayList<SubjectModel> subjectList = new ArrayList<SubjectModel>();
        SubjectListAdapter subjectListAdapter = new SubjectListAdapter(this, subjectList);
        subjectListView.setAdapter(subjectListAdapter);

        final Intent subjectIntent = new Intent(this, MaterialActivity.class);

        subjectService = new SubjectService(this);
        subjectService.getSubjectsList(subjectListAdapter);

        subjectListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Object obj = subjectListView.getItemAtPosition(i);
                SubjectModel subject = (SubjectModel) obj;
                subjectIntent.putExtra(AppConstants.SUBJECT_ID, subject.getSubjectId());
                startActivity(subjectIntent);
            }
        });
    }
}
