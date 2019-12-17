package com.example.java_class;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;


import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav_bar);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        //einlesen der datein
        //schnitstelle.load(this);
        schnitstelle.load_todo(this);
        Log.d("einlesen",String.valueOf(schnitstelle.to_do_liste.size()));


    }


   /*Nav bar onclick funktion*/
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()){
                        case R.id.navigation_home:
                            selectedFragment =new startseite();break;
                        case R.id.navigation_kalender:
                            selectedFragment =new kalender();break;
                        case R.id.navigation_to_do:
                            selectedFragment =new to_do();break;
                        case R.id.navigation_abgabe:
                            selectedFragment =new fristen();break;
                        case R.id.navigation_literatur:
                            selectedFragment = new literatur();break;

                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,selectedFragment).commit();
                    return true;
                }
            };



}

