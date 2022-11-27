package Experimental;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class GameFunctionality implements KeyListener {


    //KEYBOARD LISTENER OVER-RIDE     FOR THE GUI
    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println("The key Typed was: " + e.getKeyChar());
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.isActionKey())
            System.exit(0);
        System.out.println("The key Pressed was: " + e.getKeyChar());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("The key Released was: " + e.getKeyChar());
    }

    public Frame INIT_SCREEN() {
        //Setting the Frame and Labels
        Frame f = new Frame("Humans vs. Goblins");
        f.setLayout(new GridLayout(10, 10));
        f.setSize(500, 500);
        f.setVisible(true);

        //Creating and adding the key listener
        GameFunctionality k = this;
        f.addKeyListener(k);
        return f;
    }







    //CONSOLE APPLICATION
    public boolean validateCharacterSelect(String choice) {
        try {
            int input = Integer.parseInt(choice);
            if (input <= 0 || input >= 5) {
                System.out.println("Please enter a valid option!\n");
                return false;
            }
        } catch (Exception e) {
            System.out.println("Please enter a valid option!\n");
            return false;
        }


        return true;
    }

    public int[] coordinate(String choice) {

        if (choice.equals("w")) {
            System.out.println("hit w!");
        } else if (choice.equals("a")) {
            System.out.println("hit! a");
        } else if (choice.equals("s")) {
            System.out.println("hit! s");
        } else if (choice.equals("d")) {
            System.out.println("hit! d");
        } else if (choice.equals(".")) {
            System.out.println("quit!");
        }

        return new int[2];
    }


}
