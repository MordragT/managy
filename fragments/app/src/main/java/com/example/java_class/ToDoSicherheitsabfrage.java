package com.example.java_class;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.FragmentTransaction;

public class ToDoSicherheitsabfrage extends AppCompatDialogFragment {

    @Override
    public Dialog onCreateDialog( Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Löschen?")
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
                        Schnittstelle.toDoListe.remove(ToDoAdapter.aufgerufen);

                        //speichere änderungen
                        Schnittstelle.saveToDo();

                        //neue seite laden
                        FragmentTransaction fr = getActivity().getSupportFragmentManager().beginTransaction();//getFragmentManager().beginTransaction();
                        fr.replace(R.id.nav_host_fragment,new ToDo());
                        fr.commit();
                    }
                });
        return builder.create();
    }
}
