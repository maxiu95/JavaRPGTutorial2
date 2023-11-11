package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage() {

        try {

            up1 = ImageIO.read(getClass().getResourceAsStream("/player/anpanman_up1.PNG"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/anpanman_up2.PNG"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/anpanman_down1.PNG"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/anpanman_down2.PNG"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/anpanman_left1.PNG"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/anpanman_left2.PNG"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/anpanman_right1.PNG"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/anpanman_right2.PNG"));

        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {

        if(keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {

            if(keyH.upPressed) {
                direction = "up";
                y -= speed;
            } else if(keyH.downPressed) {
                y += speed;
                direction = "down";
            } else if(keyH.leftPressed) {
                x -= speed;
                direction = "left";
            } else if(keyH.rightPressed) {
                x += speed;
                direction = "right";
            }

            spriteCounter++;
            if(spriteCounter > 15) {
                if(spriteNumber == 1) {
                    spriteNumber = 2;
                } else if(spriteNumber ==2) {
                    spriteNumber = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;

        switch(direction) {
            case "up":
                if(spriteNumber == 1) {
                    image = up1;
                }
                if(spriteNumber == 2) {
                    image = up2;
                }
                break;
            case "down":
                if(spriteNumber == 1) {
                    image = down1;
                }
                if(spriteNumber == 2) {
                    image = down2;
                }
                break;
            case "left":
                if(spriteNumber == 1) {
                    image = left1;
                }
                if(spriteNumber == 2) {
                    image = left2;
                }
                break;
            case "right":
                if(spriteNumber == 1) {
                    image = right1;
                }
                if(spriteNumber == 2) {
                    image = right2;
                }
                break;
        }
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }
}