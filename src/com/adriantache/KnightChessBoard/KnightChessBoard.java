package com.adriantache.KnightChessBoard;

import java.util.Arrays;
import java.util.Random;

import static com.adriantache.utils.Utils.printDescription;

public class KnightChessBoard {
    private KnightChessBoard() {
        throw new IllegalStateException("Utility class");
    }

    public static void main() {
        int[][] chessboard = new int[8][8];
        final int EMPTY = 0;
        final int KNIGHT_VISITED = 1;

        printDescription("This is a program that finds the minimum number of \n" +
                "moves a horse must make on a chessboard to cover the \n" +
                "entire chessboard only once.");

        //fill array with 0
        for (int i = 0; i < 8; i++) {
            Arrays.fill(chessboard[i], EMPTY);
        }

        //put knight in random initial position
        Random random = new Random();
        chessboard[random.nextInt(8)][random.nextInt(8)] = KNIGHT_VISITED;

        //print out chessboard
        printOutChessboard(chessboard);

    }

    private static void printOutChessboard(int[][] chessboard) {
        System.out.println("  a b c d e f g h ");
        for (int i = 0; i < 8; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < 8; j++) {
                System.out.print(chessboard[i][j] + " ");
            }
            System.out.println();
        }
    }
}
