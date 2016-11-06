package com.learning.apl.apllearning;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class DashboardActivity extends MenuActivity {

    private Button viewSubjectsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        viewSubjectsBtn = (Button) findViewById(R.id.viewSubjects);
        final Intent subjectIntent = new Intent(this, SubjectActivity.class);

        viewSubjectsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(subjectIntent);
            }
        });
    }

}
