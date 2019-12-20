package com.example.java_class;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.appcompat.widget.AlertDialogLayout;
import androidx.fragment.app.FragmentTransaction;

public class LiteraturSicherheitsabfrage extends AppCompatDialogFragment {

    @Override
    public Dialog onCreateDialog( Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Löschen?")
                .setMessage("Wirklich löschen?")
                .setNegativeButton("Abbrechen", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Löschen", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Loeschen
                        Schnittstelle.literaturListe.remove(LiteraturAdapter.aufgerufen);

                        //speichere änderungen
                        Schnittstelle.saveLiteratur();


                        //neue seite laden
                        FragmentTransaction fr = getActivity().getSupportFragmentManager().beginTransaction();//getFragmentManager().beginTransaction();
                        fr.replace(R.id.nav_host_fragment,new Literatur());
                        fr.commit();
                    }
                });
        return builder.create();
    }
}
