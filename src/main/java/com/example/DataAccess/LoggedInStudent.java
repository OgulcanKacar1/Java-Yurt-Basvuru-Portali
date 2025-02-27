package com.example.DataAccess;

public class LoggedInStudent {
    private static int ogrenciID;
    private static String ad;

    public static int getOgrenciID() {
        return ogrenciID;
    }

    public static void setOgrenciID(int id){
        ogrenciID = id;
    }

    public static String getAd() {
        return ad;
    }

    public static void setAd(String ogrenciAd){
        ad = ogrenciAd;
    }

}
