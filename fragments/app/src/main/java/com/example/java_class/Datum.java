package com.example.java_class;

import androidx.annotation.NonNull;

import java.io.Serializable;

//Hier -1 stat null ?
//Wird von allen klassen mit zeiten benutzt
public class Datum implements Comparable {
    int tag;
    int monat;
    int jahr;
    Datum() {}
    Datum(int tag, int monat, int jahr) {
        this.tag = tag;
        this.monat = monat;
        this.jahr = jahr;
    }
    //To String

    /**
     * @return nur das Datum format: tt.mm.jjjj
     */
    @Override
    @NonNull
    public String toString() {
        return tag + "." + monat + "." + jahr;
    }

    @Override
    public boolean equals(Object other) {
        if(!(other instanceof Datum)) return false;
        else return this.tag == ((Datum) other).tag
                && this.monat == ((Datum) other).monat
                && this.jahr == ((Datum) other).jahr;
    }

    @Override
    public int compareTo(Object other) {
        if(!(other instanceof Datum)) return -1;
        else if(this.equals(other)) return 0;
        else if(this.jahr < ((Datum) other).jahr) return -1;
        else if(this.jahr > ((Datum) other).jahr) return 1;
        else {
            if(this.monat < ((Datum) other).monat) return -1;
            else if(this.monat > ((Datum) other).monat) return 1;
            else {
                if(this.tag < ((Datum) other).tag) return -1;
                else if(this.tag > ((Datum) other).tag) return 1;
            }
        return -1;
        }
    }
}
