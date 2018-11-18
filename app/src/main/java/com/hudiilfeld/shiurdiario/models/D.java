package com.hudiilfeld.shiurdiario.models;

import java.util.List;

public class D {

    private String prefix;
    private List<Masechta> masechtot;

    public D(List<Masechta> masechtot, String prefix) {
        this.masechtot = masechtot;
        this.prefix = prefix;
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
}
