package com.hudiilfeld.shiurdiario.models;

import java.util.List;

public class D {

    private String prefix;
    private List<Masechta> masechtot;
    private String masechet;
    private String daf;

    private String dedication;

    public D(List<Masechta> masechtot, String prefix, String dedication) {
        this.masechtot = masechtot;
        this.prefix = prefix;
        this.dedication = dedication;
    }

    public List<Masechta> getMasechtot() {
        return masechtot;
    }

    public void setMasechtot(List<Masechta> masechtot) {
        this.masechtot = masechtot;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getDedication() {
        return dedication;
    }

    public void setDedication(String dedication) {
        this.dedication = dedication;
    }

    public String getMasechet() {
        return masechet;
    }

    public void setMasechet(String masechet) {
        this.masechet = masechet;
    }

    public String getDaf() {
        return daf;
    }

    public void setDaf(String daf) {
        this.daf = daf;
    }
}
