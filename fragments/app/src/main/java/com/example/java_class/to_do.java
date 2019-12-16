package com.example.java_class;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

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


public  class to_do extends Fragment {

    static ListView todo_items_list ;
    static ArrayList<Test> todo_items = new ArrayList<>();

     public void overid_layout_for_adapter(){
        FragmentTransaction fr = getFragmentManager().beginTransaction();
        fr.replace(R.id.nav_host_fragment,new to_do());
        fr.commit();
    }
    static public void to_to_onclick_checkbox(View vv , int position){                                             //checkbox klick event
        //bekomme raus welche box geklickt wurde
        //ListView lv = vv.findViewById(R.id.todo_items_list);
        //int position = lv.getPositionForView(vv);


        //change checbox status in den satein
        Test tmp = todo_items.get(position);
        tmp.setB(!tmp.getB());
        todo_items.set(position,tmp );

        //seite neu laden                           this,getContext()
        to_do_adapter adapter = new to_do_adapter(vv.getContext() , R.layout.fragment_to_do_adapter, todo_items);
        todo_items_list.setAdapter(adapter);
        //to_do_adapter fragment_to_do_adapter = new to_do_adapter(this , R.layout.fragment_to_do_adapter , todo_items);

        //test konsolenausgabe
        String t  =  String.valueOf(position);
        Log.d("checkbox",t);
        //test ausgabe 2
        String t2 = String.valueOf(todo_items.get(position).getB());
        Log.d("checkbox",t2);
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
                fr.replace(R.id.nav_host_fragment,new to_do_add());
                fr.commit();
            }
        });




        todo_items_list = v.findViewById(R.id.todo_items_list);

        todo_items.add(new Test(true,"todo1"));
        todo_items.add(new Test(false,"todo2"));
        todo_items.add(new Test(true,"todo3"));
        todo_items.add(new Test(true,"todo3"));
        todo_items.add(new Test(true,"todo3"));
        todo_items.add(new Test(true,"todo3"));
        todo_items.add(new Test(true,"todo3"));
        todo_items.add(new Test(true,"todo3"));
        todo_items.add(new Test(true,"todo3"));
        todo_items.add(new Test(true,"todo3"));
        todo_items.add(new Test(true,"todo3"));
        todo_items.add(new Test(true,"todo3"));
        todo_items.add(new Test(true,"todo3"));
        todo_items.add(new Test(true,"todo3"));
        todo_items.add(new Test(true,"todo3"));
        todo_items.add(new Test(true,"todo3"));
        todo_items.add(new Test(true,"todo3"));
        todo_items.add(new Test(true,"todo3"));
        todo_items.add(new Test(true,"todo3"));
        todo_items.add(new Test(true,"todo3"));
        todo_items.add(new Test(true,"todo3"));
        todo_items.add(new Test(true,"todo3"));
        todo_items.add(new Test(true,"todo3"));
        todo_items.add(new Test(true,"todo3"));
        todo_items.add(new Test(true,"todo3"));
        todo_items.add(new Test(true,"todo3"));
        todo_items.add(new Test(true,"todo3"));


        to_do_adapter adapter = new to_do_adapter(this.getContext() , R.layout.fragment_to_do_adapter, todo_items);

        todo_items_list.setAdapter(adapter);


        return v;
    }

}