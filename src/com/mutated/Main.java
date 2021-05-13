package com.mutated;

import com.mutated.Controller.TriggerController;

import java.util.Scanner;

class Main {

    public static void main(String[] args) {
        System.out.println("Sentry online");
        Runtime runtime = Runtime.getRuntime();
        TriggerController trigger = new TriggerController();

        System.out.println("Enter f to fire");
        System.out.println("Enter c to cease fire");
        System.out.println("Enter q to quit");
        Scanner input = new Scanner(System.in);
        String userInput = input.nextLine();



        try {
        //sets currently used GPIO pins to input or output mode
        runtime.exec("gpio mode 4 out");
        runtime.exec("gpio mode 1 out");

            while(userInput != "q")
            {
                if(userInput == "f"){
                    trigger.fire(runtime);
                }
                else if(userInput == "c"){
                    trigger.ceaseFire(runtime);
                }
            }

        } catch (Exception e) {
            System.out.println("Exception caught: " + e.getMessage());
        }


    }
}
