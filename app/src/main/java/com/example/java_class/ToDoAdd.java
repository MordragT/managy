package com.example.java_class;

import android.os.Bundle;
import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.snackbar.Snackbar;

public class ToDoAdd extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_to_do_add, container, false);

        //Onklick listener
        Button abbrechen = (Button) v.findViewById(R.id.abbrechen);
        abbrechen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ToDo.to_to_onclick_titel(v , position);
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.nav_host_fragment, new ToDo());
                fr.commit();
            }
        });


        final Button speichern = (Button) v.findViewById(R.id.speichern);//für onklicklistener
        final EditText titel = (EditText) v.findViewById(R.id.titel);//um eingetragene werte zu bekommen

        titel.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!titel.getText().toString().isEmpty()) {
                    speichern.setAlpha(1f);
                    //titel.setBackgroundColor(getResources().getColor(R.color.green));
                } else {
                    speichern.setAlpha(.5f);
                    //titel.setBackgroundColor(getResources().getColor(R.color.red));
                }
            }
        });

        

        speichern.setAlpha(.5f);

        speichern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //eingestelte werte auslesen
                if (titel.getText().toString().length() > 0) {
                    //neuen eintrag schreiben
                    Schnittstelle.ToDoEintrag e = new Schnittstelle().new ToDoEintrag(titel.getText().toString());
                    Schnittstelle.toDoListe.add(e);

                    //speichere änderungen
                    Schnittstelle.saveToDo();

                    //seite neu laden
                    FragmentTransaction fr = getFragmentManager().beginTransaction();
                    fr.replace(R.id.nav_host_fragment, new ToDo());
                    fr.commit();
                } else {
                    /*
                    int error = getResources().getColor(R.color.Error);
                    titel.setBackgroundColor(error);
                    titel.setHint("darf nicht leer sein");
                     */

                    Snackbar error = Snackbar.make(v, "Es fehlen: Titel ", 1024);
                    //Snackbar error = Snackbar.make(v, "Bitte überprüfe deine Eingaben", 1024);
                    error.show();
                }
            }
        });

        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.nav_host_fragment, new ToDo());
                fr.commit();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);
        return v;
    }

}
