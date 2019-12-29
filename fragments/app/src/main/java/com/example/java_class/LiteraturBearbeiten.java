package com.example.java_class;

import android.os.Bundle;
import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;


public class LiteraturBearbeiten extends Fragment {

    private Button speichern;
    private EditText titel, autor, url, notizen;

    private boolean titelBool = true, autorBool = true;

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
        View v = inflater.inflate(R.layout.fragment_literatur_bearbeiten, container, false);

        speichern = (Button) v.findViewById(R.id.speichern);
        titel = (EditText) v.findViewById(R.id.titel);
        autor = (EditText) v.findViewById(R.id.autor);
        url = (EditText) v.findViewById(R.id.url);
        notizen = (EditText) v.findViewById(R.id.notizen);

        titel.setText(Schnittstelle.literaturListe.get(LiteraturAdapter.aufgerufen).name);
        autor.setText(Schnittstelle.literaturListe.get(LiteraturAdapter.aufgerufen).autor);
        url.setText(Schnittstelle.literaturListe.get(LiteraturAdapter.aufgerufen).url);
        notizen.setText(Schnittstelle.literaturListe.get(LiteraturAdapter.aufgerufen).notizen);


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
        //onklick listener löschen
        Button loeschen = (Button) v.findViewById(R.id.löschen);
        loeschen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LiteraturSicherheitsabfrage n = new LiteraturSicherheitsabfrage();
                n.show(getFragmentManager(), "sicherheitsabfrage");
            }
        });
        speichern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validator()) {

                    Schnittstelle.literaturListe.get(LiteraturAdapter.aufgerufen).name = titel.getText().toString();
                    Schnittstelle.literaturListe.get(LiteraturAdapter.aufgerufen).autor = autor.getText().toString();
                    Schnittstelle.literaturListe.get(LiteraturAdapter.aufgerufen).url = url.getText().toString();
                    Schnittstelle.literaturListe.get(LiteraturAdapter.aufgerufen).notizen = notizen.getText().toString();


                    //speichere änderungen
                    Schnittstelle.saveLiteratur();

                    //seite neu laden
                    FragmentTransaction fr = getFragmentManager().beginTransaction();
                    fr.replace(R.id.nav_host_fragment, new Literatur());
                    fr.commit();
                } else {
                    /*
                    int error = getResources().getColor(R.color.Error);
                    titel1.setBackgroundColor(error);
                    titel1.setHint("Darf nicht leer sein!");
                    autor1.setBackgroundColor(error);
                    autor1.setHint("Darf nicht leer sein!");
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
