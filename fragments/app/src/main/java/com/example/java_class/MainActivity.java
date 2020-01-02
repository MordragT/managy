package com.example.java_class;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {


    public BottomNavigationView bottomNav;
    public int lastItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNav = findViewById(R.id.bottom_nav_bar);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        //einlesen der datein
        //Schnittstelle.load(this);
        //Schnittstelle.load();
        Log.d("einlesen", String.valueOf(Schnittstelle.toDoListe.size()));
    }


    /*Nav bar onclick funktion*/
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.navigation_home:
                            lastItem = bottomNav.getSelectedItemId();
                            selectedFragment = new Startseite();
                            break;
                        case R.id.navigation_kalender:
                            lastItem = bottomNav.getSelectedItemId();
                            selectedFragment = new Kalender();
                            break;
                        case R.id.navigation_to_do:
                            lastItem = bottomNav.getSelectedItemId();
                            selectedFragment = new ToDo();
                            break;
                        case R.id.navigation_abgabe:
                            lastItem = bottomNav.getSelectedItemId();
                            selectedFragment = new Fristen();
                            break;
                        case R.id.navigation_literatur:
                            lastItem = bottomNav.getSelectedItemId();
                            selectedFragment = new Literatur();
                            break;

                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, selectedFragment).commit();
                    return true;
                }
            };
}