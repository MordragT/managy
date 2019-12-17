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

public class sicherheitsabfrage_to_do extends AppCompatDialogFragment {

    @Override
    public Dialog onCreateDialog( Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Löchen?")
                .setMessage("Wirklich löschen?")
                .setNegativeButton("abbrechen", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Löschen", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //schen
                        schnitstelle.to_do_liste.remove(to_do_adapter.aufgerufen);

                        //speichere änderungen
                        schnitstelle.save_to_do();

                        //neue seite laden
                        FragmentTransaction fr = getActivity().getSupportFragmentManager().beginTransaction();//getFragmentManager().beginTransaction();
                        fr.replace(R.id.nav_host_fragment,new to_do());
                        fr.commit();
                    }
                });
        return builder.create();
    }
}
