package com.example.java_class;

import android.os.Environment;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.io.*;

public class Schnittstelle implements Serializable {
    /*
     * bei anlegen einlesen ausführen ??
     * initialisieren schreiben
     * Alle methoden
     */

    //Alle einträge als array
    static ArrayList<FristenEintrag> fristenListe = new ArrayList<>();
    static ArrayList<TerminEintrag> terminListe = new ArrayList<>();
    static ArrayList<ToDoEintrag> toDoListe = new ArrayList<>();
    static ArrayList<LiteraturEintrag> literaturListe = new ArrayList<>();
    static Datum current;
    static int lastDay;
    static int currentDay;


    static int dayToInt(String day) {
        int ret = 0;
        switch(day) {
            case "Montag":
                ret = 0;
                break;
            case "Dienstag":
                ret = 1;
                break;
            case "Mittwoch":
                ret = 2;
                break;
            case "Donnerstag":
                ret = 3;
                break;
            case "Freitag":
                ret = 4;
                break;
            case "Samstag":
                ret = 5;
                break;
            case "Sonntag":
                ret = 6;
                break;
        }
        return ret;
    }
    /*
     * Lösche alle Daten
     */
    void deleteAllFiles() {
        File fileFristen = new File("fristen.tmp");
        File fileTermine = new File("termine.tmp");
        File fileToDo = new File("toDo.tmp");
        File fileLiteratur = new File("literatur.tmp");
        fileFristen.delete();
        fileTermine.delete();
        fileToDo.delete();
        fileLiteratur.delete();
    }

    /**
     * Lade alle Listen aus den Dokumenten
     */
    /*
    static void loadTodo(Context c) {
        try {
            File file = new File(Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_MOVIES), "/" + "toDo.tmp");
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            toDoListe = (ArrayList<ToDoEintrag>) ois.readObject();
            ois.close();
            //InputStream is = c.openFileInput("toDo.txt");
            //ObjectInputStream isToDo = new ObjectInputStream(is);
            //toDoListe = (ArrayList<Schnitstelle.ToDoEintrag>) isToDo.readObject();
            //isToDo.close();
            //is.close();
            Log.d("einlesen", "erfolgreich");

        } catch (ClassNotFoundException e) {
            Log.d("einlesen", e.toString());
            e.printStackTrace();
        } catch (IOException e) {
            Log.d("einlesen", e.toString());
            e.printStackTrace();
        }
    }
    */


    static void load() {
        File fileTermine = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_MOVIES), "/" + "termine.tmp");
        File fileToDo = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_MOVIES), "/" + "toDo.tmp");
        File fileFristen = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_MOVIES), "/" + "fristen.tmp");
        File fileLiterarur = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_MOVIES), "/" + "literatur.tmp");

        try {

            FileInputStream fsLiteratur = new FileInputStream(fileLiterarur);
            ObjectInputStream isLiteratur = new ObjectInputStream(fsLiteratur);
            literaturListe = (ArrayList<LiteraturEintrag>) isLiteratur.readObject();
            isLiteratur.close();
            fsLiteratur.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            Log.d("einlesen", "fehler");
            e.printStackTrace();
        }
        try {

            FileInputStream fsToDo = new FileInputStream(fileToDo);
            ObjectInputStream isToDo = new ObjectInputStream(fsToDo);
            toDoListe = (ArrayList<ToDoEintrag>) isToDo.readObject();
            fsToDo.close();
            isToDo.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            Log.d("einlesen", "fehler");
            e.printStackTrace();
        }
        try {

            FileInputStream fsTermine = new FileInputStream(fileTermine);
            ObjectInputStream isTermine = new ObjectInputStream(fsTermine);
            terminListe = (ArrayList<TerminEintrag>) isTermine.readObject();
            isTermine.close();
            fsTermine.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            Log.d("einlesen", "fehler");
            e.printStackTrace();
        }
        try {
            FileInputStream fsFristen = new FileInputStream(fileFristen);
            ObjectInputStream isFristen = new ObjectInputStream(fsFristen);
            fristenListe = (ArrayList<FristenEintrag>) isFristen.readObject();
            isFristen.close();
            fsFristen.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            Log.d("einlesen", "fehler");
            e.printStackTrace();
        }

    }

    static void saveFristen() {
        try {

            //sortierung start: Insertion Sort verwendet
            ArrayList<FristenEintrag> fristenTmp = new ArrayList<>();

            for(int i = 0; i<fristenListe.size(); i++){
                fristenTmp.add(fristenListe.get(i));
                int cc = i;
                while(cc-1>=0){

                    if(fristenTmp.get(cc).termin.jahr < fristenTmp.get(cc-1).termin.jahr){
                        FristenEintrag tmp = fristenTmp.get(cc-1);
                        fristenTmp.set(cc-1, fristenTmp.get(cc));
                        fristenTmp.set(cc, tmp);
                        cc--;
                    } else if(fristenTmp.get(cc).termin.jahr == fristenTmp.get(cc-1).termin.jahr
                            && fristenTmp.get(cc).termin.monat < fristenTmp.get(cc-1).termin.monat){
                        FristenEintrag tmp = fristenTmp.get(cc-1);
                        fristenTmp.set(cc-1, fristenTmp.get(cc));
                        fristenTmp.set(cc, tmp);
                        cc--;
                    } else if (fristenTmp.get(cc).termin.jahr == fristenTmp.get(cc-1).termin.jahr
                            && fristenTmp.get(cc).termin.monat == fristenTmp.get(cc-1).termin.monat
                            && fristenTmp.get(cc).termin.tag < fristenTmp.get(cc-1).termin.tag){
                        FristenEintrag tmp = fristenTmp.get(cc-1);
                        fristenTmp.set(cc-1, fristenTmp.get(cc));
                        fristenTmp.set(cc, tmp);
                        cc--;
                    } else {
                        break;
                    }

                }
            }

            fristenListe = fristenTmp;
            //sortierung ende

            File file = new File(Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_MOVIES), "/" + "fristen.tmp");
            FileOutputStream fsFristen = new FileOutputStream(file);
            ObjectOutputStream isFristen = new ObjectOutputStream(fsFristen);
            isFristen.writeObject(fristenListe);
            isFristen.close();
            fsFristen.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void saveTermine() {
        try {
            //sortierung start: Insertion Sort verwendet
            ArrayList<TerminEintrag> terminTmp = new ArrayList<>();

            for(int i = 0; i<terminListe.size(); i++){
                terminTmp.add(terminListe.get(i));
                int cc = i;
                while(cc-1>=0){

                    if(terminTmp.get(cc).beginn.jahr < terminTmp.get(cc-1).beginn.jahr){
                        TerminEintrag tmp = terminTmp.get(cc-1);
                        terminTmp.set(cc-1, terminTmp.get(cc));
                        terminTmp.set(cc, tmp);
                        cc--;
                    } else if(terminTmp.get(cc).beginn.jahr == terminTmp.get(cc-1).beginn.jahr
                            && terminTmp.get(cc).beginn.monat < terminTmp.get(cc-1).beginn.monat){
                        TerminEintrag tmp = terminTmp.get(cc-1);
                        terminTmp.set(cc-1, terminTmp.get(cc));
                        terminTmp.set(cc, tmp);
                        cc--;
                    } else if (terminTmp.get(cc).beginn.jahr == terminTmp.get(cc-1).beginn.jahr
                            && terminTmp.get(cc).beginn.monat == terminTmp.get(cc-1).beginn.monat
                            && terminTmp.get(cc).beginn.tag < terminTmp.get(cc-1).beginn.tag){
                        TerminEintrag tmp = terminTmp.get(cc-1);
                        terminTmp.set(cc-1, terminTmp.get(cc));
                        terminTmp.set(cc, tmp);
                        cc--;
                    } else if (terminTmp.get(cc).beginn.jahr == terminTmp.get(cc-1).beginn.jahr
                            && terminTmp.get(cc).beginn.monat == terminTmp.get(cc-1).beginn.monat
                            && terminTmp.get(cc).beginn.tag == terminTmp.get(cc-1).beginn.tag
                            && terminTmp.get(cc).beginnZeit.stunden < terminTmp.get(cc-1).beginnZeit.stunden){
                        TerminEintrag tmp = terminTmp.get(cc-1);
                        terminTmp.set(cc-1, terminTmp.get(cc));
                        terminTmp.set(cc, tmp);
                        cc--;
                    } else if (terminTmp.get(cc).beginn.jahr == terminTmp.get(cc-1).beginn.jahr
                            && terminTmp.get(cc).beginn.monat == terminTmp.get(cc-1).beginn.monat
                            && terminTmp.get(cc).beginn.tag == terminTmp.get(cc-1).beginn.tag
                            && terminTmp.get(cc).beginnZeit.stunden == terminTmp.get(cc-1).beginnZeit.stunden
                            && terminTmp.get(cc).beginnZeit.minuten < terminTmp.get(cc-1).beginnZeit.minuten){
                        TerminEintrag tmp = terminTmp.get(cc-1);
                        terminTmp.set(cc-1, terminTmp.get(cc));
                        terminTmp.set(cc, tmp);
                        cc--;
                    } else {
                        break;
                    }

                }
            }

            terminListe = terminTmp;
            //sortierung ende

            File file = new File(Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_MOVIES), "/" + "termine.tmp");
            FileOutputStream fsTermine = new FileOutputStream(file);
            ObjectOutputStream isTermine = new ObjectOutputStream(fsTermine);
            isTermine.writeObject(terminListe);
            isTermine.close();
            fsTermine.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void saveToDo() {
        try {
            //sortierung start
            ArrayList<ToDoEintrag> toDoTmp = new ArrayList<>();

            for(int i = 0; i<toDoListe.size(); i++){
                toDoTmp.add(toDoListe.get(i));
                int cc = i;
                while(cc-1>=0){

                    if(toDoTmp.get(cc).erledigt == false && toDoTmp.get(cc-1).erledigt == true){
                        ToDoEintrag tmp = toDoTmp.get(cc-1);
                        toDoTmp.set(cc-1, toDoTmp.get(cc));
                        toDoTmp.set(cc, tmp);
                        cc--;
                    } else{
                        break;
                    }

                }
            }

            toDoListe = toDoTmp;
            //sortierung ende
            File file = new File(Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_MOVIES), "/" + "toDo.tmp");
            FileOutputStream fsToDo = new FileOutputStream(file);
            ObjectOutputStream isToDo = new ObjectOutputStream(fsToDo);
            isToDo.writeObject(toDoListe);
            isToDo.close();
            Log.d("write_my", "Erfogreich");
        } catch (IOException e) {
            Log.d("write_my", e.toString());
            e.printStackTrace();
        }
    }

    static void saveLiteratur() {
        try {

            //sortierung start
            ArrayList<LiteraturEintrag> literaturTmp = new ArrayList<>();

            for(int i = 0; i<literaturListe.size(); i++){
                literaturTmp.add(literaturListe.get(i));
                int cc = i;
                while(cc-1>=0){
                    boolean donthold = false;
                    boolean advance = false;
                    for(int j = 0; j < literaturTmp.get(cc).name.length() && j < literaturTmp.get(cc-1).name.length() && donthold == false; j++){
                        if(literaturTmp.get(cc).name.charAt(j)<literaturTmp.get(cc-1).name.charAt(j)){
                            LiteraturEintrag tmp = literaturTmp.get(cc-1);
                            literaturTmp.set(cc-1, literaturTmp.get(cc));
                            literaturTmp.set(cc, tmp);
                            cc--;
                            advance = true;
                        } else{
                            donthold = true;
                        }
                    }
                    if(donthold && !advance){
                        break;
                    }

                }
            }

            literaturListe = literaturTmp;
            //sortierung ende

            File file = new File(Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_MOVIES), "/" + "literatur.tmp");
            FileOutputStream fsLiteratur = new FileOutputStream(file);
            ObjectOutputStream isLiteratur = new ObjectOutputStream(fsLiteratur);
            isLiteratur.writeObject(literaturListe);
            isLiteratur.close();
            fsLiteratur.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * Einträge als class
     */
    class FristenEintrag implements Serializable {
        String name;
        Boolean erinnern = false;
        Datum termin;
        Datum erinnerungsTermin;
        String beschreibung;
        FristenEintrag(String name, Datum termin, Datum erinnerungsTermin, String beschreibung) {
            this.name = name;
            this.termin = termin;
            this.erinnerungsTermin = erinnerungsTermin;
            this.beschreibung = beschreibung;
        }
    }

    class TerminEintrag implements Serializable {
        String name;
        String farbe;
        String beschreibung;
        Datum beginn;
        Datum ende;
        Zeit beginnZeit;
        Zeit endeZeit;
        TerminEintrag(String name, String farbe, Datum beginn, Datum ende, Zeit beginnZeit, Zeit endeZeit, String Beschreibung) {
            this.name = name;
            this.farbe = farbe;
            this.beginn = beginn;
            this.ende = ende;
            this.beginnZeit = beginnZeit;
            this.endeZeit = endeZeit;
            this.beschreibung = Beschreibung;
        }
    }

    class ToDoEintrag implements Serializable {
        String name;
        boolean erledigt = false;
        ToDoEintrag(String name) {
            this.name = name;
        }
    }

    class LiteraturEintrag implements Serializable {
        String name;
        String autor;
        String url;
        String notizen;
        boolean gelesen = false;
        LiteraturEintrag(String name, String autor, String url, String notizen) {
            this.name = name;
            this.autor = autor;
            this.url = url;
            this.notizen = notizen;
        }
    }
}