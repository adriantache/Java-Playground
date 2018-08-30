package com.adriantache.knight_chess_board;

import com.adriantache.utils.Utils;

import java.util.Arrays;
import java.util.Random;

import static com.adriantache.utils.Utils.printDescription;

public class KnightChessBoard {
    private static final int EMPTY = 0;
    private static final int KNIGHT_VISITED = 1;
    private static int[][] chessboard = new int[8][8];
    private static int currentI;
    private static int currentJ;
    private static boolean noMoves = false;
    private static int movesMade = 0;


    private KnightChessBoard() {
        throw new IllegalStateException("Utility class");
    }

    public static void main() {
        printDescription("This is a program that finds the minimum number of \n" +
                "moves a horse must make on a chessboard to cover the \n" +
                "entire chessboard only once.");

        //fill array with 0
        for (int i = 0; i < 8; i++) {
            Arrays.fill(chessboard[i], EMPTY);
        }

        //put knight in random initial position
        Random random = new Random();
        currentI = random.nextInt(8);
        currentJ = random.nextInt(8);
        chessboard[currentI][currentJ] = KNIGHT_VISITED;

        //print out chessboard
        printOutChessboard();

        //simulate random moves
        simulateRandomMoves();

        //go to main
        resetGame();
        Utils.backToMain();
    }

    private static void resetGame(){
        chessboard = new int[8][8];
        noMoves = false;
        movesMade = 0;
    }

    private static void printOutChessboard() {
        //playing with some console colouring
        String green = "\u001B[32m";
        String purple = "\u001B[35m";
        String black = "\u001B[30m";
        String purpleBG = "\u001B[45m";
        String reset = "\u001B[0m";

        System.out.println(green + "  a b c d e f g h ");
        for (int i = 0; i < 8; i++) {
            System.out.print(green + (i + 1) + " " + reset);
            for (int j = 0; j < 8; j++) {
                if (i == currentI && j == currentJ) System.out.print(purpleBG + black + chessboard[i][j] + reset + " ");
                else
                    System.out.print((chessboard[i][j] == 1 ? purple + chessboard[i][j] + reset : chessboard[i][j]) + " ");
            }
            System.out.println();
        }

        System.out.println();
    }

    private static void simulateRandomMoves() {
        if (noMoves) {
            System.out.println("Board is full after " + movesMade + " moves.");
            System.out.println();
            printOutChessboard();
        } else if (isBoardFull()) {
            System.out.println("Board is solved after " + movesMade + " moves.");
            System.out.println();
            printOutChessboard();
        } else {
            moveKnightSequentially();
            simulateRandomMoves();
        }
    }

    private static void moveKnightSequentially() {
        int[][] possibleMoves = {{-1, -2}, {+1, -2}, {+2, -1}, {+2, +1}, {+1, +2}, {-1, +2}, {-2, +1}, {-2, -1}};
        int cursor = 0;

        for (int i = cursor; i < possibleMoves.length; i++) {
            if (currentI + possibleMoves[i][0] >= 0 && currentI + possibleMoves[i][0] < 8
                    && currentJ + possibleMoves[i][1] >= 0 && currentJ + possibleMoves[i][1] < 8
                    && chessboard[currentI + possibleMoves[i][0]][currentJ + possibleMoves[i][1]] != 1
                    && !nextMoveStuck(currentI + possibleMoves[i][0],currentI + possibleMoves[i][1])) {
                currentI += possibleMoves[i][0];
                currentJ += possibleMoves[i][1];
                chessboard[currentI][currentJ] = KNIGHT_VISITED;
                movesMade++;
                break;
            } else cursor++;
        }

        if (cursor == 8) noMoves = true;
    }

    private static boolean nextMoveStuck(int i, int j){
        int[][] possibleMoves = {{-1, -2}, {+1, -2}, {+2, -1}, {+2, +1}, {+1, +2}, {-1, +2}, {-2, +1}, {-2, -1}};

        for (int k = 0; k < 8; k++) {
            if (i + possibleMoves[k][0] >= 0 && i + possibleMoves[k][0] < 8
                    && j + possibleMoves[k][1] >= 0 && j + possibleMoves[k][1] < 8
                    && chessboard[i + possibleMoves[k][0]][j + possibleMoves[k][1]] != 1) return false;
        }

        return true;
    }

    private static boolean isBoardFull() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (chessboard[i][j] == 0) return false;
            }
        }

        return true;
    }
}
