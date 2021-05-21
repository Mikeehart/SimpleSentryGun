package com.mutated.Controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class PirController {

    private int sensorTripCounter;
    private String newResult;
    private TriggerController trigger;

    public PirController() {
        sensorTripCounter = 0;
        newResult = "0";
        trigger = new TriggerController();
    }


    public void pirSensorTrip(Runtime runtime) throws InterruptedException, IOException {

        InputStream input = runtime.exec("gpio read 2").getInputStream();
        Scanner s = new Scanner(input).useDelimiter("\\A");
        String result = s.hasNext() ? s.next() : "0";

        while (true) {

            if (!newResult.equals(result)) {
                if (sensorTripCounter > 0) {
                    if (sensorTripCounter % 2 != 0){
                        System.out.println("Target acquired");
                        trigger.toggle(runtime);
                    }
                    else {
                        trigger.toggle(runtime);
                        System.out.println("Searching for target...");
                    }
                }

                ++sensorTripCounter;
            }

            Thread.sleep(500);
            newResult = result;
        }
    }

}
