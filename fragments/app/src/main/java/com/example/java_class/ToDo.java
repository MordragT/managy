package com.example.java_class;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;


public class ToDo extends Fragment {

    static ListView toDoItemList;
    static ArrayList<Test> toDoItems = new ArrayList<>();

    public void overrideAdapterLayout() {
        FragmentTransaction fr = getFragmentManager().beginTransaction();
        fr.replace(R.id.nav_host_fragment, new ToDo());
        fr.commit();
    }

    /*
     * bekomme raus welche box geklickt wurde
     */
    static public void getOnClickedBox(View vv, int position) {
        //checkbox klick event
        // ListView lv = vv.findViewById(R.id.todoItemList);
        // int position = lv.getPositionForView(vv);

        //change checbox status in den datein
        Schnittstelle.toDoListe.get(position).erledigt = !Schnittstelle.toDoListe.get(position).erledigt;

        //speichere änderungen
        Schnittstelle.saveToDo();

        //seite neu laden                           this,getContext()
        ToDoAdapter adapter = new ToDoAdapter(vv.getContext(), R.layout.fragment_to_do_adapter, Schnittstelle.toDoListe);
        toDoItemList.setAdapter(adapter);
        //ToDoAdapter fragment_ToDoAdapter = new ToDoAdapter(this , R.layout.fragment_ToDoAdapter , toDoItems);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_to_do, container, false);


        com.google.android.material.floatingactionbutton.FloatingActionButton fab = (com.google.android.material.floatingactionbutton.FloatingActionButton) v.findViewById(R.id.floatingActionButton_to_do);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //to_do.to_to_onclick_titel(v , position);
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.nav_host_fragment, new ToDoAdd());
                fr.commit();
            }
        });


        toDoItemList = v.findViewById(R.id.todo_items_list);

        ToDoAdapter adapter = new ToDoAdapter(this.getContext(), R.layout.fragment_to_do_adapter, Schnittstelle.toDoListe);
        toDoItemList.setAdapter(adapter);

        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                ((MainActivity)getActivity()).bottomNav.setSelectedItemId(((MainActivity) getActivity()).lastItem);
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);

        return v;
    }

}