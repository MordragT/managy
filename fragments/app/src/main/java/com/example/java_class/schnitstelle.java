package com.example.java_class;

import java.util.ArrayList;
import java.util.List;

import java.io.*;

public class schnitstelle implements Serializable{
    //bei anlegen einlesen ausführen ??
    //inizialisiren schreiben

    //Alle methoden
    void DELETE_ALL_FILES(){
        File f_abgaben = new File("abgaben.s");
        File f_termine = new File("termine.s");
        File f_to_do = new File("to_do.s");
        File f_literatur = new File("literatur.s");
        f_abgaben.delete();
        f_termine.delete();
        f_to_do.delete();
        f_literatur.delete();
    }
    /**
     * laed alle listen aus den dokumenten
     */
    void load() {
        try {
            FileInputStream fs_abgaben = new FileInputStream("abgaben.s");
            ObjectInputStream is_abgaben = new ObjectInputStream(fs_abgaben);
            abgaben_list = (ArrayList<abgaben_eintrag>) is_abgaben.readObject();
            is_abgaben.close();
            fs_abgaben.close();
            FileInputStream fs_termine = new FileInputStream("termine.s");
            ObjectInputStream is_termine = new ObjectInputStream(fs_termine);
            termine_liste = (ArrayList<termine_eintrag>) is_termine.readObject();
            is_termine.close();
            fs_termine.close();
            FileInputStream fsto_do = new FileInputStream("to_do.s");
            ObjectInputStream isto_do = new ObjectInputStream(fsto_do);
            to_do_liste = (ArrayList<to_do_eintrag>) isto_do.readObject();
            isto_do.close();
            fsto_do.close();
            FileInputStream fs_literatur = new FileInputStream("literatur.s");
            ObjectInputStream is_literatur = new ObjectInputStream(fs_literatur);
            literatur_liste = (ArrayList<literatur_eintrag>) is_literatur.readObject();
            is_literatur.close();
            fs_literatur.close();
        }catch(ClassNotFoundException e) {
            e.printStackTrace();
        }catch(IOException e) {
            e.printStackTrace();
        }

    }
    void save_abgaben() {
        try {
            FileOutputStream fs_abgaben = new FileOutputStream("abgaben.s");
            ObjectOutputStream is_abgaben = new ObjectOutputStream(fs_abgaben);
            is_abgaben.writeObject(abgaben_list);
            is_abgaben.close();
            fs_abgaben.close();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
    void save_termine() {
        try {
            FileOutputStream fs_termine = new FileOutputStream("abgaben.s");
            ObjectOutputStream is_termine = new ObjectOutputStream(fs_termine);
            is_termine.writeObject(abgaben_list);
            is_termine.close();
            fs_termine.close();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
    void save_to_do() {
        try {
            FileOutputStream fs_to_do = new FileOutputStream("abgaben.s");
            ObjectOutputStream is_to_do = new ObjectOutputStream(fs_to_do);
            is_to_do.writeObject(abgaben_list);
            is_to_do.close();
            fs_to_do.close();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
    void save_literatur() {
        try {
            FileOutputStream fs_literatur = new FileOutputStream("abgaben.s");
            ObjectOutputStream is_literatur = new ObjectOutputStream(fs_literatur);
            is_literatur.writeObject(abgaben_list);
            is_literatur.close();
            fs_literatur.close();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    //Alle einträge als array
    ArrayList<abgaben_eintrag>	abgaben_list	= new ArrayList<>();
    ArrayList<termine_eintrag>	termine_liste	= new ArrayList<>();
    ArrayList<to_do_eintrag>	to_do_liste		= new ArrayList<>();
    ArrayList<literatur_eintrag>literatur_liste	= new ArrayList<>();

    //Einträge als clss
    class abgaben_eintrag implements Serializable  {
        String name = "NAME_LEER";
        Boolean erinnern = false ;
        my_date termin = new my_date();
        my_date erinnerungs_termin = new my_date();
    }
    class termine_eintrag implements Serializable {
        String name = "NAME_LEER";
        my_date von = new my_date();
        my_date bis = new my_date();
    }
    class to_do_eintrag implements Serializable {
        String name = "NAME_LEER";
        boolean erledigt = false;
    }
    class literatur_eintrag implements Serializable {
        String name = "NAME_LEER";
        String autor = "NAME_LEER";
        boolean gelesen = false;
    }

    //Hier -1 stat null ?
    //Wird von allen klassen mit zeiten benutzt
    class my_date implements Serializable
    {
        int stunden = -1	;
        int minuten = -1	;
        int tag		= -1 ;
        int monat	= -1 ;
        int jahr	= -1 ;

        //Konstrucktoren
        my_date(){}
        my_date(int tag , int monat , int jahr)
        {
            this.tag = tag ;
            this.monat = monat ;
            this.jahr = jahr ;
        }
        my_date(int stunden ,int minuten ,int tag , int monat , int jahr)
        {
            this.tag = tag ;
            this.monat = monat ;
            this.jahr = jahr ;
            this.stunden = stunden ;
            this.minuten = minuten ;
        }

        //Getter und Setter
        void set_stunden(int data)
        {
            this.stunden = data;
        }
        void set_minuten(int data)
        {
            this.minuten = data;
        }
        void set_tag(int data)
        {
            this.tag = data;
        }
        void set_monat(int data)
        {
            this.monat = data;
        }
        void set_jahr(int data)
        {
            this.jahr = data;
        }
        int get_stunden()
        {
            return this.stunden;
        }
        int get_inuten()
        {
            return this.minuten;
        }
        int get_tag()
        {
            return this.tag;
        }
        int get_monat()
        {
            return this.monat;
        }
        int get_jahr()
        {
            return this.jahr;
        }

        //To String
        /**
         * returnt nur dass Datum format: tt.mm.jjjj
         * @return
         */
        String toString_date()
        {
            return Integer.toString(tag)+"."+Integer.toString(monat)+"."+Integer.toString(jahr);
        }
        /**
         * returnt Uhrzeit und  Datum format: hh:mm tt.mm.jjjj
         * @return
         */
        String toString_time_date()
        {
            return Integer.toString(stunden)+":"+Integer.toString(minuten)+" "+Integer.toString(tag)+"."+Integer.toString(monat)+"."+Integer.toString(jahr);


        }
    }
}