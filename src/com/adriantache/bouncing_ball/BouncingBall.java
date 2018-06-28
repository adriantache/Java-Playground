package com.adriantache.bouncing_ball;

import java.util.Random;

import static com.adriantache.utils.Utils.*;

public class BouncingBall {

    public static void main() {
        printDescription("This is a program that simulates a ball bouncing on\n" +
                "a straight surface randomly filled with spikes. The \n" +
                "program takes the speed of the ball as input and tells \n" +
                "you whether the ball will be able to come to a stop\n" +
                "safely or get popped by a spike.");

        int speed = getSpeed();

        boolean[] fieldOfSpikes = generateField(speed + (4 - speed % 4));

        int lastPosition = processBallPath(speed, fieldOfSpikes);

        if (lastPosition >= 0) {
            System.out.println("Ball stopped successfully at position " + (lastPosition + 1));
        } else {
            System.out.println("Ball popped at position " + (Math.abs(lastPosition) + 1));
        }

        System.out.println();
        printField(lastPosition, fieldOfSpikes);

        backToMain();
    }

    private static int getSpeed() {
        System.out.println("Please enter initial ball speed:");

        String speedValues = takeInput(true);

        int speed = 0;

        try {
            speed = Integer.valueOf(speedValues.trim());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        if (speed == 0) {
            System.out.println("Illegal speed value!");
            return getSpeed();
        }

        return speed;
    }

    private static void printField(int lastPosition, boolean[] fieldOfSpikes) {
        boolean popped = lastPosition < 0;

        lastPosition = Math.abs(lastPosition);

        System.out.print('|');

        for (int i = 0; i < fieldOfSpikes.length; i++) {
            if (i == lastPosition) {
                if (popped) System.out.print('X');
                else System.out.print('O');
            } else if (fieldOfSpikes[i]) System.out.print('^');
            else System.out.print('~');

            if ((i + 1) % 4 == 0) System.out.print('|');
        }
    }

    private static int processBallPath(int speed, boolean[] fieldOfSpikes) {
        int ballPosition = -1;

        if (speed <= 4) {
            if (!fieldOfSpikes[speed]) return speed;
            else return -speed;
        } else
            while (speed >= 0) {
                if (speed >= 4) {
                    ballPosition += 4;
                    if (fieldOfSpikes[ballPosition]) return -ballPosition;
                    else speed -= 4;
                } else {
                    if (!fieldOfSpikes[ballPosition + speed]) return ballPosition + speed;
                    else return -(ballPosition + speed);
                }
            }

        return 0;
    }

    private static boolean[] generateField(int size) {
        if (size < 4) size = 4;

        boolean[] fieldOfSpikes = new boolean[size];
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            fieldOfSpikes[i] = random.nextBoolean();
        }

        return fieldOfSpikes;
    }
}
