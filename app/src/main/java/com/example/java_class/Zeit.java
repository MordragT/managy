package com.example.java_class;

import java.io.Serializable;

public class Zeit implements Serializable,Comparable {
    int stunden;
    int minuten;

    Zeit(int stunden, int minuten) {
        this.stunden = stunden;
        this.minuten = minuten;
    }

    @Override
    public String toString() {
        return stunden + ":" + minuten;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Zeit)) return false;
        return this.stunden == ((Zeit) other).stunden
                && this.minuten == ((Zeit) other).minuten;
    }

    @Override
    public int compareTo(Object other) {
        if (!(other instanceof Zeit)) return -1;
        else if (this.equals(other)) return 0;
        else if (this.stunden < ((Zeit) other).stunden) return -1;
        else if (this.stunden > ((Zeit) other).stunden) return 1;
        else {
            if (this.minuten < ((Zeit) other).minuten) return -1;
            else if (this.minuten > ((Zeit) other).minuten) return 1;
        }
        return -1;
    }
}