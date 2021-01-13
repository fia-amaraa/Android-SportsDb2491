package com.praktek.sportsdb2491.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.praktek.sportsdb2491.R;
import com.praktek.sportsdb2491.ui.main.FavoriteFragment;
import com.praktek.sportsdb2491.ui.main.LastEventsFragment;
import com.praktek.sportsdb2491.ui.main.NextEventsFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final FragmentManager fragmentManager = getSupportFragmentManager();

        // define your fragments here
        final Fragment fragment1 = new LastEventsFragment();
        final Fragment fragment2 = new NextEventsFragment();
        final Fragment fragment3 = new FavoriteFragment();

        // handle navigation selection
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment fragment;
                        switch (item.getItemId()) {
                            case R.id.nav_last:
                                fragment = fragment1;
                                break;
                            case R.id.nav_next:
                                fragment = fragment2;
                                break;
                            case R.id.nav_favorite:
                            default:
                                fragment = fragment3;
                                break;
                        }
                        fragmentManager.beginTransaction().replace(R.id.page_container, fragment).commit();
                        return true;
                    }
                });
        // Set default selection
        bottomNavigationView.setSelectedItemId(R.id.nav_last);
    }

}