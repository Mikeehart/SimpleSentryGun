package com.mutated;

import com.mutated.Controller.TriggerController;

import java.util.Scanner;

class Main {

    public static void main(String[] args) {
        System.out.println("Sentry online");
        Runtime runtime = Runtime.getRuntime();
        TriggerController trigger = new TriggerController();

        System.out.println("Enter 1 to toggle trigger on/off");
        System.out.println("Enter 2 to quit");
        Scanner scanner = new Scanner(System.in);
        int userInput = scanner.nextInt();



        try {
        //sets currently used GPIO pins to input or output mode
        runtime.exec("gpio mode 4 out");
        runtime.exec("gpio mode 1 out");

        while(userInput != 2) {
            if (userInput == 1) {
                trigger.toggle(runtime);
            }
            userInput = scanner.nextInt();
        }

        } catch (Exception e) {
            System.out.println("Exception caught: " + e.getMessage());
        }


    }
}
