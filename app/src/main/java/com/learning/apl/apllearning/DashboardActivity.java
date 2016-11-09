package com.learning.apl.apllearning;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DashboardActivity extends MenuActivity {

    private Button viewSubjectsBtn;
    private TextView userNameTextView;
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        sharedPreferences = getSharedPreferences(AppConstants.USER_PREF, Context.MODE_PRIVATE);
        String userName = sharedPreferences.getString(AppConstants.USER_NAME, "");

        userNameTextView = (TextView) findViewById(R.id.userNameTextView);
        userNameTextView.setText("Welcome " + userName);
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
