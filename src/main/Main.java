package main;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        JFrame window = new JFrame();
        GamePanel gamePanel = new GamePanel();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Anpanman 2D Adventure: あんぱんさがし");

        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.setupGame();
        gamePanel.startGameThread();
    }
}
