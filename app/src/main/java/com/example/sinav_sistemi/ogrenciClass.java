package com.example.sinav_sistemi;

public class ogrenciClass {
    private String ogradsoyad;
    private String bolum;
    private String numara;
    private String sifre;

    public ogrenciClass(String ogradsoyad, String bolum, String numara, String sifre) {
        this.ogradsoyad = ogradsoyad;
        this.bolum = bolum;
        this.numara = numara;
        this.sifre = sifre;
    }

    public ogrenciClass() {

    }

    public String getOgradsoyad() {
        return ogradsoyad;
    }

    public void setOgradsoyad(String ogradsoyad) {
        this.ogradsoyad = ogradsoyad;
    }

    public String getNumara() {
        return numara;
    }

    public void setNumara(String numara) {
        this.numara = numara;
    }

    public String getBolum() {
        return bolum;
    }

    public void setBolum(String bolum) {
        this.bolum = bolum;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }
}