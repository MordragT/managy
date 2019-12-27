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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav_bar);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        //einlesen der datein
        //Schnittstelle.load(this);
        Schnittstelle.load();
        Log.d("einlesen", String.valueOf(Schnittstelle.toDoListe.size()));

        FristStartseite();
        //TerminStartseite();
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
                            selectedFragment = new Startseite();
                            break;
                        case R.id.navigation_kalender:
                            selectedFragment = new Kalender();
                            break;
                        case R.id.navigation_to_do:
                            selectedFragment = new ToDo();
                            break;
                        case R.id.navigation_abgabe:
                            selectedFragment = new Fristen();
                            break;
                        case R.id.navigation_literatur:
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
        int Termingröße = Schnittstelle.terminListe.size();
        TerminStart.setText(Termingröße);
    }

    public void ToDoStartseite() {
        TextView ToDoStart = findViewById(R.id.ToDoStart);
        if(Schnittstelle.toDoListe.size()== 0){
            ToDoStart.setText("Es gibt nichts zu tun :D");
        }
        else{
            int ToDogröße = Schnittstelle.toDoListe.size();
            ToDoStart.setText(ToDogröße);
        }

    }

    public void LiteraturStartseite(){
        TextView LiteraturStart = findViewById(R.id.LiteraturStart);
        if(Schnittstelle.literaturListe.size() == 0){
            LiteraturStart.setText("Es gibt nichts zu lesen :D");
        }
        else{
            int Literaturgröße = Schnittstelle.literaturListe.size();
            LiteraturStart.setText(Schnittstelle.literaturListe.size());
        }

    }

    public void TippsStartseite() {
        TextView TippsStartseite = findViewById(R.id.TippsStart);
        //TippsStartseite.setText("klappt");
        //TippsStartseite.setText(getResources().getString(R.string.TippStart));

        int randomIndex = new Random().nextInt((getResources().getStringArray(R.array.TippsArray)).length);
        String randomTipp = (getResources().getStringArray(R.array.TippsArray))[randomIndex];
        TippsStartseite.setText(randomTipp);

        //Random r = new Random();
        //String myRandString = r.nextInt(R.array.TippsArray.);

    }

}

