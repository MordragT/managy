package com.example.java_class;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialogFragment;
import java.util.ArrayList;


public class to_do extends Fragment {

    private ListView todo_items_list ;
    ArrayList<Test> todo_items = new ArrayList<>();


    public void testee(View v){                                             //checkbox klick event
        //bekomme raus welche box geklickt wurde
        ListView lv = getView().findViewById(R.id.todo_items_list);


        int position = lv.getPositionForView(v);


        //change checbox status in den satein
        Test tmp = todo_items.get(position);
        tmp.setB(!tmp.getB());
        todo_items.set(position,tmp );

        //seite neu laden
        to_do_adapter adapter = new to_do_adapter(this.getContext() , R.layout.fragment_to_do_adapter, todo_items);
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