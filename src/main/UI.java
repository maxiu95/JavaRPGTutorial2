package main;

import object.OBJ_Anpan;
import object.OBJ_PicnicBasket;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {

    GamePanel gp;
    Font arial_font;
    Font arial_font2;
    BufferedImage anpanImage;
    BufferedImage basketImage;

    // message settings
    public boolean messageOn = false;
    public String message = "";

    public UI(GamePanel gp) {

        this.gp = gp;
        arial_font = new Font("Arial", Font.PLAIN, 26);
        OBJ_Anpan anpan = new OBJ_Anpan();
        anpanImage = anpan.image;
        OBJ_PicnicBasket picnic = new OBJ_PicnicBasket();
        basketImage = picnic.image;

        arial_font2 = new Font("Arial", Font.PLAIN, 16);

    }

    public void showMessage(String text) {

        message = text;
        messageOn = true;
    }

        public void draw(Graphics2D g2) {

            g2.setFont(arial_font);
            g2.setColor(Color.WHITE);
            // ==*ANPAN COUNTER*==
            g2.drawImage(anpanImage, gp.tileSize/2, gp.tileSize/3, gp.tileSize, gp.tileSize, null);
            g2.drawString("x " + gp.player.hasAnpan, 53, 35);
            // ==*PICNIC BASKET COUNTER*==
            g2.drawImage(basketImage, gp.tileSize/2, gp.tileSize+gp.tileSize/2, gp.tileSize, gp.tileSize, null);
            g2.drawString("x " + gp.player.picnicAnpan, 53, 71);
            // ==*MESSAGE*==
            if(messageOn) {
                g2.fillRect(gp.tileSize/2, gp.tileSize * 9, gp.tileSize*15, gp.tileSize*2);
                g2.setFont(arial_font2);
                g2.setColor(Color.BLACK);
                g2.drawString(message, gp.tileSize, gp.tileSize*10+5);

            }
        }
}
