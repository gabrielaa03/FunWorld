package com.gabrielaangebrandt.funworld.base;

import android.text.format.DateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Converter {
    public static long getTimeInLong(String format) {
        SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");
        Date dateObj = null;
        try {
            dateObj = sdf.parse(format);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long newScore = dateObj.getTime();
        return Math.abs(newScore);
    }

    public static String getLongtoTime(long l) {
        long ll = l * (-1);
        return DateFormat.format("mm:ss", new Date(ll)).toString();
    }
}
