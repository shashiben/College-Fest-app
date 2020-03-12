package com.cse.cynosure;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.cse.cynosure.Fragments.EventsFragment;
import com.cse.cynosure.Fragments.HomeFragment;
import com.cse.cynosure.Fragments.NotificationFragment;
import com.cse.cynosure.Fragments.RegisterFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    EventsFragment eventsFragment;
    HomeFragment homeFragment;
    NotificationFragment notificationFragment;
    RegisterFragment registerFragment;
    BottomNavigationView bnv;
   Toolbar toolbar;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        registerFragment=new RegisterFragment();
        eventsFragment=new EventsFragment();
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        homeFragment=new HomeFragment();
        notificationFragment=new NotificationFragment();
        bnv=findViewById(R.id.bnv);

        replaceFragment(homeFragment);
        bnv.inflateMenu(R.menu.bottom_menu);
        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.bottom_home:
                        replaceFragment(homeFragment);
                        return true;
                    case R.id.bottom_event:
                        replaceFragment(eventsFragment);
                        return true;
                    case R.id.bottom_notification:
                        replaceFragment(notificationFragment);
                        return true;
                    case R.id.bottom_register:
                        replaceFragment(registerFragment);
                        return true;
                    default:
                        return false;
                }

            }
        });

    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_container, fragment);
        fragmentTransaction.commit();
    }

}
