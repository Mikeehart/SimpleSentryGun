package com.mutated;

import com.mutated.Controller.TriggerController;

import java.io.InputStream;
import java.util.Scanner;

class Main {

    public static void main(String[] args) {
        System.out.println("Sentry online");
        Runtime runtime = Runtime.getRuntime();
        TriggerController trigger = new TriggerController();

        Scanner scanner = new Scanner(System.in);
        InputStream input;
        String result;
        String newResult = "0";

        int userInput;

        try {
            //sets currently used GPIO pins to input or output mode
            runtime.exec("gpio mode 4 out");
            runtime.exec("gpio mode 1 out");
            runtime.exec("gpio mode 2 in");


           do {
               System.out.println("Enter 1 for manual mode");
               System.out.println("Enter 2 for sentry mode");
               System.out.println("Enter 3 to quit");
               userInput = scanner.nextInt();

               if (userInput == 1) {
                   System.out.println("MANUAL MODE");
                   System.out.println("Enter 1 to toggle trigger on/off");
                   System.out.println("Enter 2 for main menu");
                   userInput = scanner.nextInt();

                   while (userInput != 2) {
                       if (userInput == 1) {
                           trigger.toggle(runtime);
                       }
                       userInput = scanner.nextInt();
                   }
               }
               else if(userInput == 2) {
                   System.out.println("SENTRY MODE");
                   System.out.println("Enter 1 for main menu");

                   while (true){

                       //call pir event listener function here
                       input = runtime.exec("gpio read 2").getInputStream();
                       Scanner s = new Scanner(input).useDelimiter("\\A");
                       result = s.hasNext() ? s.next() : "";

                       System.out.println("this is the result: " + result);
                       if(!result.equals(newResult)){
                           trigger.toggle(runtime);
                       }
                       newResult = result;
                       Thread.sleep(500);

                   }
               }
           }
           while(userInput != 3);

        } catch (Exception e) {
            System.out.println("Exception caught: " + e.getMessage());
        }


    }
}
