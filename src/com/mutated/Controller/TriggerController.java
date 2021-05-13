package com.mutated.Controller;

import java.io.IOException;

public class TriggerController {

    private int primerState = 0;
    private int triggerState = 0;
    private boolean isFiring = false;

    //function to switch first (primer) trigger relay
    private String togglePrimer(Runtime runtime) throws IOException {

        //toggles primer trigger relay
        primerState = 1 - primerState;
        runtime.exec("gpio write 4 " + primerState);

        if(primerState == 1)
            return "sentry armed... ready to fire";
        else{
            return "sentry disarmed";
        }
    }

    //function to switch fire trigger relay
    private String toggleTrigger(Runtime runtime) throws IOException {

        //toggles fire trigger relay
        triggerState = 1 - triggerState;
        runtime.exec("gpio write 1 " + triggerState);

        if(triggerState == 1)
            return "engaging target";
        else{
            return "target neutralized";
        }
    }

    //the following functions are for toggling both relays in the required sequence.
    //Because the sentry gun has a primer trigger that MUST be activated prior
    //to activating the firing trigger, and released in the reverse order,
    //the toggle trigger functions will only be called here in a controlled order.
    private boolean fire(Runtime runtime) throws IOException, InterruptedException {

        System.out.println(togglePrimer(runtime));
        Thread.sleep(3000);
        System.out.println(toggleTrigger(runtime));
        isFiring = true;

        return isFiring;
    }

    private boolean ceaseFire(Runtime runtime) throws IOException, InterruptedException {

        System.out.println(toggleTrigger(runtime));
        Thread.sleep(1000);
        System.out.println(togglePrimer(runtime));
        isFiring = false;

        return isFiring;
    }

    public boolean toggle(Runtime runtime) throws IOException, InterruptedException {

        if(isFiring)
            return fire(runtime);
        else
            return ceaseFire(runtime);

    }



}
