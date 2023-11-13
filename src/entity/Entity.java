package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {

    public int worldX, worldY; // players position on the world map
    public int speed;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;

    public int spriteCounter = 0;
    public int spriteNumber = 1;

    // set collision area
    public Rectangle solidArea; // creates an abstract rectangle
    public boolean collisionOn = false;
}
