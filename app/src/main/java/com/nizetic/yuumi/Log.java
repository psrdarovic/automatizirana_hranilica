package com.nizetic.yuumi;

public class Log {
    private String ime;
    private String vrijeme;

    public Log(String ime, String vrijeme) {
        this.ime = ime;
        this.vrijeme = vrijeme;
    }

    public String getIme() {
        return ime;
    }

    public String getVrijeme() {
        return vrijeme;
    }
}
