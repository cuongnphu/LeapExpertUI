package com.leap.pageobject.utils;


import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class GenericUtils {

    private static String symbols = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    /**
     * Generate random code
     * @return
     */
    public static String genCode(int lenght, boolean isContainsUpperCase, boolean isContainsLowerCase, boolean isContainsDigit){
        Random r = new Random();
        while(true) {
            char[] password = new char[lenght];
            boolean hasUpper = false, hasLower = false, hasDigit = false, hasSpecial = false;
            for(int i=0; i<password.length; i++) {
                char ch = symbols.charAt(r.nextInt(symbols.length()));
                if(isContainsUpperCase && Character.isUpperCase(ch))
                    hasUpper = true;
                else if(isContainsLowerCase && Character.isLowerCase(ch))
                    hasLower = true;
                else if(isContainsDigit && Character.isDigit(ch))
                    hasDigit = true;
                password[i] = ch;
            }
            if(hasUpper && hasLower && hasDigit) {
                return new String(password);
            }
        }
    }

    /**
     * Thread to sleep timeout (milliseconds)
     * @param timeOut
     */
    public static void wait(int timeOut) {
        try {
            Thread.sleep(timeOut);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get random mailinator & storeName
     * @param name
     * @return
     */
    public static List<String> randomMailinator(String name){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Long random =  timestamp.getTime();

        // Get randomEmail & nameStore
        String randomEmail = name + random.toString() + "@mailinator.com";
        String nameStore = name + random.toString();
        List<String> stringList = new ArrayList<>();

        // Add to List
        stringList.add(randomEmail);
        stringList.add(nameStore);

        return stringList;
    }

    /**
     * Generate phone number
     */
    public static long generatePhoneNumber() {
        long phoneNumber = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
        return phoneNumber;
    }

    /**
     * Conver date dd-mm-yyyy to timestamp
     * @param date "01-01-1990"
     * @return String timestamp
     */
    public static String convertDateToTimestamp(String date){
        SimpleDateFormat formatter = new SimpleDateFormat("dd-mm-yyyy");
        Date tempdate = null;
        try {
            tempdate = (Date)formatter.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long mills = tempdate.getTime();
        return String.valueOf(mills);
    }

    /**
     * Conver timestamp to date dd-mm-yyyy
     * @param timestamp
     * @return String date
     */
    public static String convertTimestampToDate(String timestamp){
        long time = Long.parseLong(timestamp);
        Date date=new Date(time);
        return date.toString();
    }

    public static int getRandomNumberInRange(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }
}
