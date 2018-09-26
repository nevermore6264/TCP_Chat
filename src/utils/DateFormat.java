/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Khoand
 */
public class DateFormat {

    private static ThreadLocal<SimpleDateFormat> outDateFormatHolder = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };

    public static String formatDate(Date date) throws ParseException {
        return outDateFormatHolder.get().format(date);
    }
    
    public static Date parse(String date) throws ParseException {
        return outDateFormatHolder.get().parse(date);
    }    
    
}
