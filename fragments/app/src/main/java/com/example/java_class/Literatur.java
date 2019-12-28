package com.example.java_class;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class Literatur extends Fragment {

    static ListView literatur_items_list;
    static ArrayList<Test> literatur_items = new ArrayList<>();

    public void overid_layout_for_adapter(){
        FragmentTransaction fr = getFragmentManager().beginTransaction();
        fr.replace(R.id.nav_host_fragment,new Literatur());
        fr.commit();
    }
    static public void literatur_onclick_checkbox(View vv , int position){                                             //checkbox klick event
        //bekomme raus welche box geklickt wurde
        //ListView lv = vv.findViewById(R.id.todo_items_list);
        //int position = lv.getPositionForView(vv);

        //change checkbox status in den satein
        Schnittstelle.literaturListe.get(position).gelesen = !Schnittstelle.literaturListe.get(position).gelesen ;

        //speichere änderungen
        Schnittstelle.saveLiteratur();

        //seite neu laden                           this,getContext()
        LiteraturAdapter adapter = new LiteraturAdapter(vv.getContext() , R.layout.fragment_literatur_adapter, Schnittstelle.literaturListe);
       literatur_items_list.setAdapter(adapter);
        //to_do_adapter fragment_to_do_adapter = new to_do_adapter(this , R.layout.fragment_to_do_adapter , todo_items);

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_literatur, container, false);


        com.google.android.material.floatingactionbutton.FloatingActionButton fab = (com.google.android.material.floatingactionbutton.FloatingActionButton) v.findViewById(R.id.floatingActionButton_literatur);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //to_do.to_to_onclick_titel(v , position);
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.nav_host_fragment,new LiteraturAdd());
                fr.commit();
            }
        });
        literatur_items_list = v.findViewById(R.id.literatur_items_list);

       literatur_items.add(new Test(true,"todo1"));
       literatur_items.add(new Test(true,"todo1"));
       literatur_items.add(new Test(true,"todo1"));
       literatur_items.add(new Test(true,"todo1"));
       literatur_items.add(new Test(true,"todo1"));
       literatur_items.add(new Test(true,"todo1"));
       literatur_items.add(new Test(true,"todo1"));



        LiteraturAdapter adapter = new LiteraturAdapter(this.getContext() , R.layout.fragment_literatur_adapter, Schnittstelle.literaturListe);

        literatur_items_list.setAdapter(adapter);

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
