package com.mutated.Controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

//Controller class for passive infrared sensor.
public class PirController {

    private int sensorTripCounter;
    private String newResult;
    private String result;
    private TriggerController trigger;

    public PirController() {
        sensorTripCounter = 0;
        newResult = "0";
        result = "0";
        trigger = new TriggerController();
    }

    //This function continuously compares the value from a GPIO input stream that receives input (1 or 0) from
    // an infrared sensor against a previously stored value. if the values are different, the pir sensor
    // has either been tripped, or reset after a trip. the trigger is toggled accordingly.
    public void pirSensorTrip(Runtime runtime) throws InterruptedException, IOException {

        //TODO: implement multithreading solution to allow user input to exit loop
        while (true) {

            InputStream input = runtime.exec("gpio read 2").getInputStream();
            Scanner s = new Scanner(input).useDelimiter("\\A");
            result = s.hasNext() ? s.next() : "0";

            if (!newResult.equals(result)) {
                    if (sensorTripCounter % 2 == 0){
                        System.out.println("Target acquired");
                        trigger.toggle(runtime);
                    }
                    else {
                        trigger.toggle(runtime);
                        System.out.println("Searching for target...");
                    }

                ++sensorTripCounter;
            }

            Thread.sleep(500);
            newResult = result;
        }
    }

}
