package com.example.thisday;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.thisday.fragments.CreateFragment;
import com.example.thisday.fragments.FeedFragment;
import com.example.thisday.fragments.UserProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    final FragmentManager fragmentManager = getSupportFragmentManager();

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottomNavigation);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment fragment;
                switch (item.getItemId()) {
                    case R.id.action_feed:
                        fragment = new FeedFragment();
                        break;

                    case R.id.action_create:

                        fragment = new CreateFragment();
                        break;

                    case R.id.action_profile:
                        fragment = new UserProfileFragment();
                        break;
                    default:
                        fragment = new FeedFragment();
                        break;

                }

                fragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit();

                return true;
            }
        });
        bottomNavigationView.setSelectedItemId(R.id.action_feed);
    }
}