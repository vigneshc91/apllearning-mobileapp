package com.learning.apl.apllearning;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText userNameText;
    private EditText passwordText;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userNameText = (EditText) findViewById(R.id.userName);
        passwordText = (EditText) findViewById(R.id.userPassword);
        loginButton = (Button) findViewById(R.id.loginButton);

        AlertDialog.Builder errorAlertBuilder = new AlertDialog.Builder(this);
        errorAlertBuilder.setMessage(ErrorConstants.REQUIRED_FIELDS_EMPTY).setTitle(ErrorConstants.ERROR);
        errorAlertBuilder.setPositiveButton("OK", null);
        final AlertDialog errorAlertDialog = errorAlertBuilder.create();


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userNameString = userNameText.getText().toString();
                String passwordString = passwordText.getText().toString();

                if(userNameString.isEmpty() || passwordString.isEmpty()){
                    errorAlertDialog.show();
                } else {
                    Toast.makeText(LoginActivity.this, "Login Clicked", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
