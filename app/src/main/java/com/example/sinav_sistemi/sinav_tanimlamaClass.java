package com.example.sinav_sistemi;

public class sinav_tanimlamaClass {
    private String ekleyenhocamail,ders,kaçKolay,kaçOrta,kaçZor,kolaysoruPuan,ortasoruPuan,zorsoruPuan,sinavSuresi;
    private Integer baslamaSaati,bitisSaati;
    public sinav_tanimlamaClass(String ekleyenhocamail, String ders, Integer baslamaSaati, Integer bitisSaati,String sinavSuresi, String kaçKolay, String kaçOrta, String kaçZor, String kolaysoruPuan, String ortasoruPuan, String zorsoruPuan) {
        this.ekleyenhocamail = ekleyenhocamail;
        this.ders = ders;
        this.baslamaSaati = baslamaSaati;
        this.bitisSaati = bitisSaati;
        this.kaçKolay = kaçKolay;
        this.kaçOrta = kaçOrta;
        this.kaçZor=kaçZor;
        this.kolaysoruPuan=kolaysoruPuan;
        this.ortasoruPuan=ortasoruPuan;
        this.zorsoruPuan=zorsoruPuan;
        this.sinavSuresi=sinavSuresi;
    }
    public sinav_tanimlamaClass(){

    }

    public sinav_tanimlamaClass(String ders, String ekleyenhocamail, Integer baslamaSaati, Integer bitisSaati, String sinavSuresi) {
        this.ekleyenhocamail = ekleyenhocamail;
        this.ders = ders;
        this.baslamaSaati = baslamaSaati;
        this.bitisSaati = bitisSaati;
        this.sinavSuresi=sinavSuresi;

    }


    public String getEkleyenhocamail() {
        return ekleyenhocamail;
    }

    public void setEkleyenhocamail(String ekleyenhocamail) {
        this.ekleyenhocamail = ekleyenhocamail;
    }
    public String getDers() {
        return ders;
    }

    public void setDers(String ders) {
        this.ders = ders;
    }

    public Integer getBaslamaSaati() {
        return baslamaSaati;
    }

    public void setBaslamaSaati(Integer baslamaSaati) {
        this.baslamaSaati = baslamaSaati;
    }

    public Integer getBitisSaati() {
        return bitisSaati;
    }

    public void setBitisSaati(Integer bitisSaati) {
        this.bitisSaati = bitisSaati;
    }
    public String getSinavSuresi() {
        return sinavSuresi;
    }

    public void setSinavSuresi(String sinavSuresi) {
        this.sinavSuresi = sinavSuresi;
    }

    public String getKaçKolay() {
        return kaçKolay;
    }

    public void setKaçKolay(String kaçKolay) {
        this.kaçKolay = kaçKolay;
    }

    public String getKaçOrta() {
        return kaçOrta;
    }

    public void setKaçOrta(String kaçOrta) {
        this.kaçOrta = kaçOrta;
    }

    public String getKaçZor() {
        return kaçZor;
    }

    public void setKaçZor(String kaçZor) {
        this.kaçZor = kaçZor;
    }

    public String getKolaysoruPuan() {
        return kolaysoruPuan;
    }

    public void setKolaysoruPuan(String kolaysoruPuan) {
        this.kolaysoruPuan = kolaysoruPuan;
    }

    public String getOrtasoruPuan() {
        return ortasoruPuan;
    }

    public void setOrtasoruPuan(String ortasoruPuan) {
        this.ortasoruPuan = ortasoruPuan;
    }

    public String getZorsoruPuan() {
        return zorsoruPuan;
    }

    public void setZorsoruPuan(String zorsoruPuan) {
        this.zorsoruPuan = zorsoruPuan;
    }
}
