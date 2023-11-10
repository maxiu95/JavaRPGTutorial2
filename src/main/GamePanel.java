package main;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    // ==*SCREEN SETTING*==
    final int originalTileSize = 16; // 16x16 tile
    final int scale = 3; // scale x3 for modern resolution

    final int tileSize = originalTileSize * scale; // 48x48 tile
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    // FPS
    int FPS = 60;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;

    // ==*SET PLAYERS DEFAULT POSITION*==
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.PINK);
        this.setDoubleBuffered(true);
        // ++*FOR KEYBOARD INPUT*==
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

//    //==*SLEEP METHOD FOR SETTING FPS*==
//    @Override
//    public void run() {
//
//        while(gameThread != null) {
//
//            double drawInterval = 1000000000 / FPS; // 0.01666 seconds
//            double nextDrawTime = System.nanoTime() + drawInterval;
//
//            // ==*UPDATE: UPDATE INFORMATION SUCH AS CHARACTER POSITION*==
//            update();
//
//            // ==*DRAW: DRAW TO THE SCREEN THE UPDATED INFORMATION*==
//            repaint(); // calls the paintComponent method
//
//            //==*SLEEP METHOD FOR SETTING FPS*==
//            try {
//                double remainingTime = nextDrawTime - System.nanoTime();
//                remainingTime = remainingTime/1000000; // convert to milliseconds
//
//                if(remainingTime < 0) {
//                    remainingTime = 0;
//                }
//
//                Thread.sleep((long) remainingTime);
//                nextDrawTime += drawInterval; // next draw time
//
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//        }
//
//    }

    // ==*DELTA METHOD FOR SETTING FPS*==
    @Override
    public void run() {

        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTIme = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while(gameThread != null) {

            currentTime = System.nanoTime();
            delta += (currentTime - lastTIme) / drawInterval;
            timer += (currentTime - lastTIme); // print fps to terminal
            lastTIme = currentTime;

            if(delta > 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if(timer >= 1000000000) {
                System.out.println("FPS" + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update() {

        if(keyH.upPressed) {
            playerY -= playerSpeed;
        } else if(keyH.downPressed) {
            playerY += playerSpeed;
        } else if(keyH.leftPressed) {
            playerX -= playerSpeed;
        } else if(keyH.rightPressed) {
            playerX += playerSpeed;
        }
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.LIGHT_GRAY);
        g2.fillRect(playerX, playerY, tileSize, tileSize);
        g2.dispose();

    }
}
