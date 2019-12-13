package com.example.java_class;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class to_do_adapter extends ArrayAdapter<Test> { //<____________________________________________________________________________________________________________________

    private static final String TAG = "to_do_adapter";
    private Context mContext;
    int mRecorce;

    public to_do_adapter(@NonNull Context context, int resource, ArrayList<Test> objects) { // <________________________________________________________________________________
        super(context , resource , objects);


        this.mContext = context;
        this.mRecorce = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String Titel = getItem(position).getTitel(); // array(positio0n)<- ist später eine klasse darum  .getXXX() anfügen
        boolean boool = getItem(position).getB();

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mRecorce , parent , false);

        TextView tyName = (TextView) convertView.findViewById(R.id.todo_titel);
        CheckBox tyDone = (CheckBox) convertView.findViewById(R.id.to_do_checkbox);

        tyName.setText(Titel);
        int black = Color.BLACK; // if abfrage ob es haken gesetzt ist wenn ja dan grau machen
        int grey = Color.GRAY;
        if(boool) {
            tyName.setTextColor(grey);
            tyDone.setChecked(true);
        }
        else
            tyName.setTextColor(black);


        return convertView;



    }
}
