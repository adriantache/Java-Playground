package com.adriantache.multithreading;

import static com.adriantache.multithreading.TrafficLightColours.*;
import static com.adriantache.utils.Utils.backToMain;

enum TrafficLightColours {
    RED(4000), GREEN(5000), YELLOW(1000);
    private int delay;

    TrafficLightColours(int delay) {
        this.delay = delay;
    }

    int getDelay() {
        return this.delay;
    }
}

//main annotation types:
//@Retention(value = RetentionPolicy.CLASS)
//@Documented
//@Target(ElementType.ANNOTATION_TYPE)
//@Inherited
//@Override
//@Deprecated
//@SafeVarargs
//@FunctionalInterface

//define a custom annotation
@interface Values {
    String value();

    int count();
}

public class TrafficLight {
    TrafficLight() {
        throw new AssertionError("Class should not be instantiated!");
    }

    public static void main() {
        System.out.println("Starting traffic light.");

        TrafficLightSimulator tls = new TrafficLightSimulator();
        Thread thread = new Thread(tls);
        thread.start();

        for (int i = 0; i < 9; i++) {
            System.out.println(tls.getColour());
            tls.waitForChange();
        }

        tls.setStop();
    }
}

class TrafficLightSimulator implements Runnable {
    private TrafficLightColours tlc;
    private boolean stop = false;
    private boolean changed = false;

    TrafficLightSimulator() {
        tlc = RED;
    }

    void setStop() {
        stop = true;
    }

    @Override
    public void run() {
        while (!stop) {
            try {
                switch (tlc) {
                    case RED:
                        Thread.sleep(RED.getDelay());
                        break;
                    case GREEN:
                        Thread.sleep(GREEN.getDelay());
                        break;
                    case YELLOW:
                        Thread.sleep(YELLOW.getDelay());
                        break;
                    default:
                        System.out.println("ERROR!");
                        break;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            switchColour();
        }
    }

    //and use the custom annotation
    @Values(value = "RED_YELLOW_GREEN", count = 3)
    private synchronized void switchColour() {
        int ordinal = tlc.ordinal();

        ordinal = ordinal == 2 ? 0 : ++ordinal;

        tlc = TrafficLightColours.values()[ordinal];

        changed = true;
        notifyAll();
    }

    synchronized void waitForChange() {
        try {
            while (!changed) wait();
            changed = false;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    synchronized TrafficLightColours getColour() {
        return tlc;
    }
}
