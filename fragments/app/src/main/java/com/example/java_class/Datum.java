package com.example.java_class;

import androidx.annotation.NonNull;

import java.io.Serializable;

//Hier -1 stat null ?
//Wird von allen klassen mit zeiten benutzt
public class Datum implements Serializable {
    int stunden = -1;
    int minuten = -1;
    int tag = -1;
    int monat = -1;
    int jahr = -1;

    //Konstrucktoren
    Datum() {
    }

    Datum(int tag, int monat, int jahr) {
        this.tag = tag;
        this.monat = monat;
        this.jahr = jahr;
    }

    Datum(int stunden, int minuten, int tag, int monat, int jahr) {
        this.tag = tag;
        this.monat = monat;
        this.jahr = jahr;
        this.stunden = stunden;
        this.minuten = minuten;
    }

    //Getter und Setter
    // Anmerkung: wenn nicht auf public gesetzt keinen nutzen ?
        /*
        void setStunden(int data) {
            this.stunden = data;
        }

        void setMinuten(int data) {
            this.minuten = data;
        }

        void setTag(int data) {
            this.tag = data;
        }

        void setMonat(int data) {
            this.monat = data;
        }

        void setJahr(int data) {
            this.jahr = data;
        }

        int getStunden() {
            return this.stunden;
        }

        int getMinuten() {
            return this.minuten;
        }

        int getTag() {
            return this.tag;
        }

        int getMonat() {
            return this.monat;
        }

        int getJahr() {
            return this.jahr;
        }
        */

    //To String

    /**
     * @return nur das Datum format: tt.mm.jjjj
     */
    @Override
    @NonNull
    public String toString() {
        return tag + "." + monat + "." + jahr;
    }

    /**
     * @return Uhrzeit und  Datum format: hh:mm tt.mm.jjjj
     */
    public String toStringZeit() {
        return stunden + ":" + minuten + " " + tag + "." + monat + "." + jahr;
    }
}