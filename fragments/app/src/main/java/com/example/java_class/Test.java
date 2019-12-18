package com.example.java_class;

public class Test {
    Test() { }

    Test(Boolean b, String titel) {
        this.b = b;
        this.titel = titel;
    }

    public Boolean getB() {
        return b;
    }

    public String getTitel() {
        return titel;
    }

    public void setB(Boolean b) {
        this.b = b;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    Boolean b;
    String titel;
}
