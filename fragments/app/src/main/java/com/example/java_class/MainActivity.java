package com.example.java_class;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.Random;


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
        Schnittstelle.load();
        Log.d("einlesen", String.valueOf(Schnittstelle.toDoListe.size()));

        FristStartseite();
        TerminStartseite();
        ToDoStartseite();
        LiteraturStartseite();
        TippsStartseite();


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

    public void FristStartseite() {
        TextView FristStart = findViewById(R.id.FristStart);

    }

    public void TerminStartseite() {
        TextView TerminStart = findViewById(R.id.TerminStart);

        //int Termingröße = Schnittstelle.terminListe.size();
        //TerminStart.setText(Termingröße);
    }


    public void ToDoStartseite() {
        TextView ToDoStart = findViewById(R.id.ToDoStart);
        if (Schnittstelle.toDoListe.size() == 0) {
            String ToDo = "Du hast aktuell keine Aufgaben :D";
            ToDoStart.setText(ToDo);

        } else {
            int counter = 0;
            for (int i=0; i <= Schnittstelle.toDoListe.size(); i++){
                if(!Schnittstelle.toDoListe.get(i).erledigt){
                    counter++;
                }
            }

            if (counter == Schnittstelle.toDoListe.size()){
                String ToDo = "Du hast alle Aufgaben erledigt :D";
                ToDoStart.setText(ToDo);
            }
            else{
                String ToDo = counter + "/" + Schnittstelle.toDoListe.size();
                ToDoStart.setText(ToDo);
            }
        }
    }

    public void LiteraturStartseite() {
        TextView LiteraturStart = findViewById(R.id.LiteraturStart);
        if (Schnittstelle.literaturListe.size() == 0) {
            String Literatur = "Du hast aktuell keine Literatur :D";
            LiteraturStart.setText(Literatur);

        } else {

            int counter = 0;
            for (int i = 0; i <= Schnittstelle.literaturListe.size(); i++) {
                if (!Schnittstelle.literaturListe.get(i).gelesen) {
                    counter++;
                }
            }

            if (counter == Schnittstelle.literaturListe.size()) {
                String Literatur = "Du hast alles gelesen :D";
                LiteraturStart.setText(Literatur);
            } else {
                String Literatur = counter + "/" + Schnittstelle.toDoListe.size();
                LiteraturStart.setText(Literatur);
            }
        }
    }

    public void TippsStartseite() {
        TextView TippsStartseite = findViewById(R.id.TippsStart);

        int randomIndex = new Random().nextInt((getResources().getStringArray(R.array.TippsArray)).length);
        String randomTipp = (getResources().getStringArray(R.array.TippsArray))[randomIndex];
        TippsStartseite.setText(randomTipp);
    }

}

