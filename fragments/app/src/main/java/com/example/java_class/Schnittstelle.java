package com.example.java_class;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

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
        File fileAbgaben = new File("abgaben.s");
        File fileTermine = new File("termine.s");
        File fileToDo = new File("toDo.s");
        File fileLiteratur = new File("literatur.s");
        fileAbgaben.delete();
        fileTermine.delete();
        fileToDo.delete();
        fileLiteratur.delete();
    }

    /**
     * Lade alle Listen aus den Dokumenten
     */
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
            isTermine.writeObject(abgabenListe);
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
        Datum termin = new Datum();
        Datum erinnerungsTermin = new Datum();
    }

    class TerminEintrag implements Serializable {
        String name = "NAME_LEER";
        String farbe = "blue";
        String beschreibung = "";
        Datum beginn = new Datum();
        Datum ende = new Datum();
    }

    class ToDoEintrag implements Serializable {
        String name = "NAME_LEER";
        boolean erledigt = false;
    }

    class LiteraturEintrag implements Serializable {
        String name = "NAME_LEER";
        String autor = "NAME_LEER";
        String url = "NAME_LEER";
        String notizen = "NAME_LEER";
        boolean gelesen = false;
    }
}