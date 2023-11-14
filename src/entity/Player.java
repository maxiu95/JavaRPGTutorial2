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

    public final int screenX; // where we draw the player on the screen
    public final int screenY;
    int hasAnpan = 0;
    int picnicAnpan = 0;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        // ==*CENTRE PLAYER ON SCREEN*==
        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        // ==*COLLISION AREA*==
        solidArea = new Rectangle(6, 12, 24, 18);
        // interact with objects
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        worldX = 24 * gp.tileSize; // 25
        worldY = 25 * gp.tileSize; // 26
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

        // ==*UPDATE MOVEMENT*==
        if(keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {

            if(keyH.upPressed) {
                direction = "up";

            } else if(keyH.downPressed) {

                direction = "down";
            } else if(keyH.leftPressed) {

                direction = "left";
            } else if(keyH.rightPressed) {

                direction = "right";
            }

            // ==* CHECK TILE COLLISION*==
            collisionOn = false;
            gp.cChecker.checkTile(this);

            // ==*CHECK OBJECT COLLISION*==
            int objIndex = gp.cChecker.checkObject(this, true);
            pickupObject(objIndex);

            // ==* IF COLLISION IS FALSE, PLAYER CAN MOVE
            if(collisionOn == false) {

                switch (direction) {
                    case "up": worldY -= speed; break;
                    case "down": worldY += speed; break;
                    case "left": worldX -= speed; break;
                    case "right": worldX += speed; break;
                }
            }

            // ==*SPRITE ANIMATION*==
            spriteCounter++;
            if(spriteCounter > 15) { // sprite updates every 15 frames
                if(spriteNumber == 1) {
                    spriteNumber = 2;
                } else if(spriteNumber ==2) {
                    spriteNumber = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    // ==*OBJECT INTERACTION*==
    public void pickupObject(int i) {

        if(i != 999) {

            String objName = gp.obj[i].name;

            switch(objName) {
                case "Anpan":
                    hasAnpan++;
                    gp.obj[i] = null;
                    System.out.println("Anpan: " + hasAnpan);
                    System.out.println("Picnic Anpan: " + picnicAnpan +"\n");
                    break;
                case "PicnicBasket":
                    if(hasAnpan > 0) {
                        picnicAnpan += hasAnpan;
                        hasAnpan = 0;
                    }
                    System.out.println("Anpan: " + hasAnpan);
                    System.out.println("Picnic Anpan: " + picnicAnpan + "\n");
                    if(picnicAnpan == 10) {
                        System.out.println("CONGRATULATIONS! YOU FOUND ALL THE ANPAN!!");
                        System.out.println("NOW WE CAN ENJOY THE PICNIC!\n");
                    }
                    break;
                // ==*TEMP NPC CODE*==
                case "Ojisan":
                    System.out.println("HEY ANPANMAN, CAN YOU COLLECT ALL 10 ANPAN AND PUT THEM IN THE PICNIC BASKET FOR ME?");
                    System.out.println("GOOD LUCK!\n");
                    break;
                case "CurrypanMan":
                    System.out.println("HOW'S IT GOING ANPANMAN?");
                    System.out.println("DID YOU FIND ALL THE ANPAN YET?\n");
                    break;
                case "ShokupanMan":
                    System.out.println("BOY I CAN'T WAIT FOR THE PICNIC ANPANMAN!");
                    System.out.println("DID YOU FIND ALL THE ANPAN YET?\n");
                    break;
            }
        }
    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;

        // ==*SPRITE ANIMATION*==
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
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}
