package com.example.java_class;

import android.os.Bundle;
import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class Literatur extends Fragment {

    static ListView literaturItemsList;

    /*
    static ArrayList<Test> literaturItems = new ArrayList<>();
    public void overrideLayoutForAdapter(){
        FragmentTransaction fr = getFragmentManager().beginTransaction();
        fr.replace(R.id.nav_host_fragment,new Literatur());
        fr.commit();
    }
    */

    static public void getOnClickedBox(View vv , int position){                                             //checkbox klick event
        //bekomme raus welche box geklickt wurde
        //ListView lv = vv.findViewById(R.id.todo_items_list);
        //int position = lv.getPositionForView(vv);

        //change checkbox status in den satein
        Schnittstelle.literaturListe.get(position).gelesen = !Schnittstelle.literaturListe.get(position).gelesen ;

        //speichere änderungen
        Schnittstelle.saveLiteratur();

        //seite neu laden                           this,getContext()
        LiteraturAdapter adapter = new LiteraturAdapter(vv.getContext() , R.layout.fragment_literatur_adapter, Schnittstelle.literaturListe);
       literaturItemsList.setAdapter(adapter);
        //to_do_adapter fragment_to_do_adapter = new to_do_adapter(this , R.layout.fragment_to_do_adapter , todo_items);

    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
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
        literaturItemsList = v.findViewById(R.id.literatur_items_list);
        LiteraturAdapter adapter = new LiteraturAdapter(this.getContext() , R.layout.fragment_literatur_adapter, Schnittstelle.literaturListe);
        literaturItemsList.setAdapter(adapter);

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
