package com.example.cybercrime_helpline.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.cybercrime_helpline.R;
import com.google.android.material.navigation.NavigationView;

public class DashboardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout dashboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dashboard = findViewById(R.id.dashboard_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, dashboard, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        dashboard.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment selectedFragment = null;
        switch (item.getItemId()) {
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new HomeFragment()).commit();
                break;
            case R.id.nav_profile:
                ViewProfileFragment profileFragment = new ViewProfileFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        profileFragment).commit();
                break;
            case R.id.nav_cases:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new CasesFragment()).commit();
                break;
            case R.id.nav_share:
                Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_location:
                Toast.makeText(this, "Your Location", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.nav_logout:
                Intent intent1=new Intent(DashboardActivity.this, LoginActivity.class);
                startActivity(intent1);
                finish();
                Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show();

        }

        dashboard.closeDrawer(GravityCompat.START);
        if (selectedFragment!=null)
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    selectedFragment).commit();

        return true;
    }

    @Override
    public void onBackPressed() {
        if (dashboard.isDrawerOpen(GravityCompat.START)) {
            dashboard.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


}



