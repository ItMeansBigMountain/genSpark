package Experimental;

import java.util.HashMap;
import java.util.Scanner;

public class Multithreading_OBJECT {


    public void display_Screen_Thread(int[][] game_board , HashMap game_key ) {
        while (true) {
            for (int i = 0; i < game_board.length; i++) {
                for (int y = 0; y < game_board[i].length; y++) {
                    System.out.print( String.valueOf(i) + game_key.get(game_board[i][y]) + "\t");
                }
                System.out.println();
            }
        }
    }


    public void display_input_Thread(String choice , GameFunctionality g ) {
        g.coordinate(choice);
    }


    public void run() {
        try {
            // Displaying the thread that is running
            System.out.println("Thread " + Thread.currentThread().getId() + " is running");
        } catch (Exception e) {
            // Throwing an exception
            System.out.println("Exception is caught");
        }
        System.out.println(Thread.currentThread().getId() + " COMPLETE");
    }


}
