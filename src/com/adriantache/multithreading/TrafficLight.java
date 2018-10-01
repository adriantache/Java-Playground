package com.adriantache.multithreading;

import static com.adriantache.utils.Utils.backToMain;

enum TrafficLightColours {RED, GREEN, YELLOW}

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

        backToMain();
    }
}

class TrafficLightSimulator implements Runnable {
    private TrafficLightColours tlc;
    private boolean stop = false;
    private boolean changed = false;

    TrafficLightSimulator() {
        tlc = TrafficLightColours.RED;
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
                        Thread.sleep(4000);
                        break;
                    case GREEN:
                        Thread.sleep(5000);
                        break;
                    case YELLOW:
                        Thread.sleep(1000);
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
        switch (tlc) {
            case YELLOW:
                tlc = TrafficLightColours.RED;
                break;
            case RED:
                tlc = TrafficLightColours.GREEN;
                break;
            case GREEN:
                tlc = TrafficLightColours.YELLOW;
                break;
            default:
                System.out.println("ERROR!");
                break;
        }

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
