package com.learning.apl.apllearning;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MenuActivity extends AppCompatActivity {

    private LoginService loginService;
    private AlertDialog.Builder logoutAlertBuilder;
    private AlertDialog logoutAlertDialog;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionmenu, menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        logoutAlertBuilder = new AlertDialog.Builder(this);
        logoutAlertBuilder.setTitle(ErrorConstants.ALERT);
        logoutAlertBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                loginService = new LoginService(MenuActivity.this);
                loginService.userLogout();
            }
        });
        logoutAlertBuilder.setNegativeButton("Cancel", null);
        logoutAlertDialog = logoutAlertBuilder.create();
        logoutAlertDialog.setMessage(ErrorConstants.LOGOUT_CONFIRM);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.dashboardMenu:
                Intent dashboardIntent = new Intent(this, DashboardActivity.class);
                startActivity(dashboardIntent);
                return true;
            case R.id.changePasswordMenu:
                Intent changePasswordIntent = new Intent(this, ChangePasswordActivity.class);
                startActivity(changePasswordIntent);
                return true;
            case R.id.logoutMenu:
                logoutAlertDialog.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
