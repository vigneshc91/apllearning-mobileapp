package com.learning.apl.apllearning;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MenuActivity extends AppCompatActivity {

    private LoginService loginService;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionmenu, menu);
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
                loginService = new LoginService(this);
                loginService.userLogout();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
