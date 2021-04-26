package com.example.java_class;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class Fristen extends Fragment {

    static ListView fristenItemsList;

    static public void getOnClickedBox(View vv, int position) {
        Schnittstelle.fristenListe.get(position).erinnern = !Schnittstelle.fristenListe.get(position).erinnern;
        Schnittstelle.saveFristen();
        FristenAdapter adapter = new FristenAdapter(vv.getContext(), R.layout.fragment_fristen_adapter, Schnittstelle.fristenListe);
        fristenItemsList.setAdapter(adapter);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_fristen, container, false);
        com.google.android.material.floatingactionbutton.FloatingActionButton fab = (com.google.android.material.floatingactionbutton.FloatingActionButton) v.findViewById(R.id.add_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.nav_host_fragment, new FristenAdd());
                fr.commit();
            }
        });
        fristenItemsList = v.findViewById(R.id.liste);
        FristenAdapter adapter = new FristenAdapter(this.getContext(), R.layout.fragment_fristen_adapter, Schnittstelle.fristenListe);
        fristenItemsList.setAdapter(adapter);

        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                ((MainActivity) getActivity()).bottomNav.setSelectedItemId(((MainActivity) getActivity()).lastItem);
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);

        ((MainActivity)getActivity()).setActionBarTitle("Fristen");

        return v;
    }

}
