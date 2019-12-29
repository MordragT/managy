package com.example.java_class;

import android.os.Environment;
import android.util.Log;
import java.util.ArrayList;
import java.io.*;

public class Schnittstelle implements Serializable {
    /*
     * bei anlegen einlesen ausführen ??
     * initialisieren schreiben
     * Alle methoden
     */

    //Alle einträge als array
    static ArrayList<AbgabenEintrag> abgabenListe = new ArrayList<>();
    static ArrayList<TerminEintrag> terminListe = new ArrayList<>();
    static ArrayList<ToDoEintrag> toDoListe = new ArrayList<>();
    static ArrayList<LiteraturEintrag> literaturListe = new ArrayList<>();

    /*
     * Lösche alle Daten
     */
    void deleteAllFiles() {
        File fileAbgaben = new File("abgaben.tmp");
        File fileTermine = new File("termine.tmp");
        File fileToDo = new File("toDo.tmp");
        File fileLiteratur = new File("literatur.tmp");
        fileAbgaben.delete();
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
        File fileAbgaben = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_MOVIES), "/" + "abgaben.tmp");
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
            FileInputStream fsAbgaben = new FileInputStream(fileAbgaben);
            ObjectInputStream isAbgaben = new ObjectInputStream(fsAbgaben);
            abgabenListe = (ArrayList<AbgabenEintrag>) isAbgaben.readObject();
            isAbgaben.close();
            fsAbgaben.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            Log.d("einlesen", "fehler");
            e.printStackTrace();
        }

    }

    static void saveAbgaben() {
        try {
            File file = new File(Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_MOVIES), "/" + "abgaben.tmp");
            FileOutputStream fsAbgaben = new FileOutputStream(file);
            ObjectOutputStream isAbgaben = new ObjectOutputStream(fsAbgaben);
            isAbgaben.writeObject(abgabenListe);
            isAbgaben.close();
            fsAbgaben.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void saveTermine() {
        try {
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
    class AbgabenEintrag implements Serializable {
        String name = "NAME_LEER";
        Boolean erinnern = false;
        Datum termin;
        Datum erinnerungsTermin;
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