package com.company;

import java.io.IOException;
import java.util.Scanner;

class Main {

    public static void main(String[] args) {
        System.out.println("Sentry online");
        try{

        Runtime runtime = Runtime.getRuntime();
        runtime.exec("gpio mode 4 out");
        runtime.exec("gpio write 4 1");
        Thread.sleep(2000);
        runtime.exec("gpio write 4 0");

        } catch (Exception e) {
            System.out.println("Exception caught: " + e.getMessage());
        }


    }
}
