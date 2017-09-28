package com.xyzmastercrd.www;

/**
 * Created by GRV on 9/22/2017.
 */

public class data {

    int image;
    String name,Amt,date;

    public data(/*int image,*/ String name, String Amt,String date) {

        this.name = name;
        this.Amt = Amt;
        this.date = date;
    }

    public int getImage() {
        return image;
    }

    public String getName() {
        return name;
    }
    public String getDate() {
        return date;
    }
    public String getAmt() {
        return Amt;
    }
}