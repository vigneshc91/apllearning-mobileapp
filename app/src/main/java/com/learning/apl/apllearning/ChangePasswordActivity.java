package com.learning.apl.apllearning;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

public class ChangePasswordActivity extends MenuActivity {

    private EditText oldPasswordText, newPasswordText, confirmNewPasswordText;
    private Button changePasswordBtn;
    private ProgressBar changePasswordProgressBar;
    private ChangePasswordService changePasswordService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        oldPasswordText = (EditText) findViewById(R.id.oldPassword);
        newPasswordText = (EditText) findViewById(R.id.newPassword);
        confirmNewPasswordText = (EditText) findViewById(R.id.confirmNewPassword);
        changePasswordProgressBar = (ProgressBar) findViewById(R.id.changePasswordProgressBar);
        changePasswordBtn = (Button) findViewById(R.id.changePasswordButton);

        changePasswordService = new ChangePasswordService(this);

        final AlertDialog.Builder errorAlertBuilder = new AlertDialog.Builder(this);
        errorAlertBuilder.setTitle(ErrorConstants.ERROR);
        errorAlertBuilder.setPositiveButton("OK", null);
        final AlertDialog errorAlertDialog = errorAlertBuilder.create();

        changePasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String oldPassword = oldPasswordText.getText().toString();
                String newPassword = newPasswordText.getText().toString();
                String confirmNewPassword = confirmNewPasswordText.getText().toString();

                if(oldPassword.isEmpty() || newPassword.isEmpty() || confirmNewPassword.isEmpty()){
                    errorAlertDialog.setMessage(ErrorConstants.REQUIRED_FIELDS_EMPTY);
                    errorAlertDialog.show();
                } else {
                    if(!newPassword.equals(confirmNewPassword)){
                        errorAlertDialog.setMessage(ErrorConstants.PASSWORD_NOT_MATCH);
                        errorAlertDialog.show();
                    } else {
                        changePasswordBtn.setEnabled(false);
                        changePasswordProgressBar.setVisibility(View.VISIBLE);
                        changePasswordService.chanePassword(oldPassword, newPassword, changePasswordBtn, changePasswordProgressBar);
                    }
                }
            }
        });
    }
}
