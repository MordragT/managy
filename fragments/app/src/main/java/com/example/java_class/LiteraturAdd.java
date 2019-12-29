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


public class LiteraturAdd extends Fragment {

    private EditText titel, autor, url, notizen;
    private Button speichern;

    private boolean titelBool = false, autorBool = false;

    private boolean validator() {
        if (titelBool && autorBool) {
            speichern.setAlpha(1f);
            return true;
        }
        speichern.setAlpha(.5f);
        return false;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_literatur_add, container, false);

        //Onklick listener
        Button abbrechen = (Button) v.findViewById(R.id.abbrechen);

        speichern = (Button) v.findViewById(R.id.speichern);//für onklicklistener
        speichern.setAlpha(.5f);

        titel = (EditText) v.findViewById(R.id.titel);//um eingetragene werte zu bekommen
        autor = (EditText) v.findViewById(R.id.autor);
        url = (EditText) v.findViewById(R.id.url);
        notizen = (EditText) v.findViewById(R.id.notizen);

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
                    titelBool = true;
                    //titel.setBackgroundColor(getResources().getColor(R.color.green));
                } else {
                    titelBool = false;
                    //titel.setBackgroundColor(getResources().getColor(R.color.red));
                }
                validator();
            }
        });
        autor.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!autor.getText().toString().isEmpty()) {
                    autorBool = true;
                    //titel.setBackgroundColor(getResources().getColor(R.color.green));
                } else {
                    autorBool = false;
                    //titel.setBackgroundColor(getResources().getColor(R.color.red));
                }
                validator();
            }
        });
        abbrechen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //to_do.to_to_onclick_titel(v , position);
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.nav_host_fragment, new ToDo());
                fr.commit();
            }
        });
        speichern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validator()) {
                    //neuen eintrag schreiben

                    Schnittstelle.LiteraturEintrag e = new Schnittstelle().new LiteraturEintrag(
                            titel.getText().toString(),
                            autor.getText().toString(),
                            url.getText().toString(),
                            notizen.getText().toString()
                    );
                    Schnittstelle.literaturListe.add(e);

                    //speichere änderungen
                    Schnittstelle.saveLiteratur();

                    //seite neu laden
                    FragmentTransaction fr = getFragmentManager().beginTransaction();
                    fr.replace(R.id.nav_host_fragment, new Literatur());
                    fr.commit();
                } else {
                    /*
                    int error = getResources().getColor(R.color.Error);
                    titel.setBackgroundColor(error);
                    titel.setHint("Darf nicht leer sein!");
                    autor.setBackgroundColor(error);
                    autor.setHint("Darf nicht leer sein!");
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
                fr.replace(R.id.nav_host_fragment, new Literatur());
                fr.commit();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);
        return v;
    }

}
