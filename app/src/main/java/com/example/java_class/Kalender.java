package com.example.java_class;

import android.os.Bundle;
import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class Kalender extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_kalender, container, false);
        com.google.android.material.floatingactionbutton.FloatingActionButton fab = (com.google.android.material.floatingactionbutton.FloatingActionButton) v.findViewById(R.id.add);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.nav_host_fragment, new KalenderAdd());
                fr.commit();
            }
        });
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                ((MainActivity) getActivity()).bottomNav.setSelectedItemId(((MainActivity) getActivity()).lastItem);
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);

        ((MainActivity)getActivity()).setActionBarTitle("Kalender");

        return v;
    }


}
