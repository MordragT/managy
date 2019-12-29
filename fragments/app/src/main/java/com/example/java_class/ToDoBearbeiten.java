package com.example.java_class;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;


public class ToDoBearbeiten extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_to_do_bearbeiten, container, false);

        //setzt den text ins fenster (titel)
        final EditText titel = (EditText) v.findViewById(R.id.titel);
        String s = Schnittstelle.toDoListe.get(ToDoAdapter.aufgerufen).name;
        Log.d("aufgerufen", s);
        titel.setText(s);


        //onklick listener löchen
        Button loeschen = (Button) v.findViewById(R.id.loeschen);
        loeschen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToDoSicherheitsabfrage n = new ToDoSicherheitsabfrage();
                n.show(getFragmentManager(), "sicherheitsabfrage");
            }
        });

        final Button speichern = (Button) v.findViewById(R.id.speichern);//für onklicklistener
        //final EditText titel = (EditText) v.findViewById(R.id.to_do_add_EditText);//um eingetragene werte zu bekommen

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
        speichern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //neuen eintrag schreiben
                if (titel.getText().toString().length() > 0) {
                    Schnittstelle.toDoListe.get(ToDoAdapter.aufgerufen).name = titel.getText().toString();


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
                    Snackbar error = Snackbar.make(v, "Bitte überprüfe deine Eingaben", 1024);
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
