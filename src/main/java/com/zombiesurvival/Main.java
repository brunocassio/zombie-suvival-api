package com.zombiesurvival;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by bru9isk on 06/01/17.
 */
public class Main {

    public static void main(String[] args) {

        Date dta = new Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.ms'Z");

        String abc = df.format(dta);
        System.out.println(abc);

    }
}
