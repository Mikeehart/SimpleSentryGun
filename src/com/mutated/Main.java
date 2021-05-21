package com.mutated;

import com.mutated.Controller.PirController;
import com.mutated.Controller.TriggerController;

import java.util.Scanner;

class Main {

    public static void main(String[] args) {
        System.out.println("SENTRY ONLINE");
        Runtime runtime = Runtime.getRuntime();
        TriggerController trigger = new TriggerController();
        PirController pir = new PirController();

        Scanner scanner = new Scanner(System.in);
        int userInput;

        try {
            //sets currently used GPIO pins to input or output mode
            runtime.exec("gpio mode 4 out");
            runtime.exec("gpio mode 1 out");
            runtime.exec("gpio mode 2 in");
            runtime.exec("gpio mode 2 down");


           do {
               System.out.println("Enter 1 for manual mode");
               System.out.println("Enter 2 for sentry mode");
               System.out.println("Enter 3 to quit \n");
               userInput = scanner.nextInt();

               if (userInput == 1) {
                   System.out.println("MANUAL MODE");
                   System.out.println("Enter 1 to toggle trigger on/off");
                   System.out.println("Enter 2 for main menu \n");
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
                   System.out.println("Press ctrl-c to quit \n");
                   System.out.println("Searching for target...");
                   pir.pirSensorTrip(runtime);
               }
           }
           while(userInput != 3);

        } catch (Exception e) {
            System.out.println("Exception caught: " + e.getMessage());
        }


    }
}
