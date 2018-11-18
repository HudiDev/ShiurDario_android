package com.hudiilfeld.shiurdiario;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    public static String getCurrentDate() {
        Date date = new Date();
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }
}
