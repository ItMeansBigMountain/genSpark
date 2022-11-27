package main;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.*;


public class Main extends JFrame implements KeyListener, ActionListener {

    Human human;

    Random random = new Random();

    public Main() {
        // create the human
        this.human = new Human(this, random);

        // timer for redrawing the screen
        Timer timer = new Timer(150, this);
        timer.start();

        // timer for drawing goblins on the screen
        java.util.Timer RandomGoblin_Spawn_timer = new java.util.Timer();
        Goblin clone = new Goblin(this.human, random);
        RandomGoblin_Spawn_timer.schedule(clone, 0, 3000);

        // window creation & drawing
        add(this.human);
        setTitle("Human vs. Goblins");
        setSize(525, 525);
        this.addKeyListener(this);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int c = e.getKeyCode();

        // right arrow pressed
        if (c == 39) {
            this.human.setDirection("right");
        }

        // left arrow pressed
        else if (c == 37) {
            this.human.setDirection("left");
        }

        // up arrow pressed
        else if (c == 38) {
            this.human.setDirection("up");
        }

        // down arrow pressed
        else if (c == 40) {
            this.human.setDirection("down");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // redraw the screen
        repaint();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(Main::new);
    }

}