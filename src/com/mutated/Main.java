package com.mutated;

import com.mutated.Controller.TriggerController;

import java.util.Scanner;

class Main {

    public static void main(String[] args) {
        System.out.println("Sentry online");
        Runtime runtime = Runtime.getRuntime();
        TriggerController trigger = new TriggerController();

        System.out.println("Enter 1 to fire");
        System.out.println("Enter 2 to cease fire");
        System.out.println("Enter 3 to quit");
        Scanner scanner = new Scanner(System.in);
        int userInput = scanner.nextInt();



        try {
        //sets currently used GPIO pins to input or output mode
        runtime.exec("gpio mode 4 out");
        runtime.exec("gpio mode 1 out");

        while(userInput != 3) {
            if (userInput == 1) {
                System.out.println(trigger.fire(runtime));
            } else if (userInput == 2) {
                System.out.println(trigger.ceaseFire(runtime));
            }
            userInput = scanner.nextInt();
        }

        } catch (Exception e) {
            System.out.println("Exception caught: " + e.getMessage());
        }


    }
}
