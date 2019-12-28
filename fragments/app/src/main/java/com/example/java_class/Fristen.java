package com.example.java_class;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class Fristen extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                ((MainActivity)getActivity()).bottomNav.setSelectedItemId(((MainActivity) getActivity()).lastItem);
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fristen, container, false);
    }


}
