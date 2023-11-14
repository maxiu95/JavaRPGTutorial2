package main;

import entity.Player;
import object.SuperObject;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    // ==*SCREEN SETTING*==
    final int originalTileSize = 32; // 32x32 tile
    final int scale = 1; // scale x3 for modern resolution

    public final int tileSize = originalTileSize * scale; // 32x32 tile
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; // 512 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 384 pixels

    // ==*WORLD SETTINGS*==
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    // FPS
    int FPS = 30;

    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public Player player = new Player(this, keyH);
    public SuperObject obj[] = new SuperObject[15]; // can display up to 15 objects

    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        // ++*FOR KEYBOARD INPUT*==
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame() {

        aSetter.setObject();
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
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update() {

        player.update();
    }
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // ==*TILE*==
        tileM.draw(g2);
        // ==*OBJECT*==
        for(int i = 0; i < obj.length; i++) {
            if(obj[i] != null) {
                obj[i].draw(g2, this);
            }
        }
        // ==*PLAYER*==
        player.draw(g2);

        g2.dispose();

    }
}
