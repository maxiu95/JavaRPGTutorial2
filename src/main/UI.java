package main;

import object.OBJ_Anpan;
import object.OBJ_PicnicBasket;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {

    GamePanel gp;
    Font arial_font;
    BufferedImage anpanImage;
    BufferedImage basketImage;

    public UI(GamePanel gp) {

        this.gp = gp;
        arial_font = new Font("Arial", Font.PLAIN, 26);
        OBJ_Anpan anpan = new OBJ_Anpan();
        anpanImage = anpan.image;
        OBJ_PicnicBasket picnic = new OBJ_PicnicBasket();
        basketImage = picnic.image;

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
        }
}
