package com.hudiilfeld.shiurdiario.models;

public class Daf {

    private String masechet, daf, date, duration,
            hebmonth, hebdate, hebyear, prefix, sqldate;


    public Daf(String masechet, String daf, String date, String duration,
               String hebmonth, String hebdate, String hebyear,
               String prefix, String sqldate) {
        this.masechet = masechet;
        this.daf = daf;
        this.date = date;
        this.duration = duration;
        this.hebmonth = hebmonth;
        this.hebdate = hebdate;
        this.hebyear = hebyear;
        this.prefix = prefix;
        this.sqldate = sqldate;
    }

    public String getMasechet() {
        return masechet;
    }

    public String getDaf() {
        return daf;
    }

    public String getDate() {
        return date;
    }

    public String getDuration() {
        return duration;
    }

    public String getHebmonth() {
        return hebmonth;
    }

    public String getHebdate() {
        return hebdate;
    }

    public String getHebyear() {
        return hebyear;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getSqldate() {
        return sqldate;
    }
}
