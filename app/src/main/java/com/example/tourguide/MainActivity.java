package com.example.tourguide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.tourguide.StrictMode.strictmodeclass;
import com.example.tourguide.fragement.fragment_home;
import com.example.tourguide.fragement.fragment_hotel;
import com.example.tourguide.fragement.fragment_places;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        // load the store fragment by default
        loadFragment(new fragment_home(), 1);
        strictmodeclass.StrictMode();
        checkPermission();

    }

    public void checkPermission(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
        ){//Can add more as per requirement

        }
    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.action_home:
                    fragment = new fragment_home();
                    loadFragment(fragment, 1);
                    return true;
                case R.id.action_hotel:
                    fragment = new fragment_hotel();
                    loadFragment(fragment, 2);
                    return true;
                case R.id.action_places:
                    fragment = new fragment_places();
                    loadFragment(fragment, 3);
                    return true;
                case R.id.action_news:
                    Intent intent = new Intent(getBaseContext(), NewsActivity.class);
                    startActivity(intent);
                    return true;

            }
            return false;
        }
    };

    private int position = 0;

    private void loadFragment(Fragment fragment, int position) {

        while (this.position != position) {
            // load fragment
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frame_container, fragment);
            if (this.position < position) {
                transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_from_right);
            } else {
                transaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_from_left);
            }
            this.position = position;
            transaction.addToBackStack(null);
            transaction.detach(fragment);
            transaction.attach(fragment);
            transaction.commit();
        }

    }
}