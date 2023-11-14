package main;

import object.OBJ_Anpan;
import object.OBJ_PicnicBasket;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {

    GamePanel gp;
    Font uiFont, messageFont, gameEndMessageFont, gameCongratulationsFont;
    BufferedImage anpanImage;
    BufferedImage basketImage;

    // message settings
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;

    // game end
    public boolean gameFinished = false;

    public UI(GamePanel gp) {

        this.gp = gp;
        uiFont = new Font("Sans Serif", Font.PLAIN, 26);
        OBJ_Anpan anpan = new OBJ_Anpan();
        anpanImage = anpan.image;
        OBJ_PicnicBasket picnic = new OBJ_PicnicBasket();
        basketImage = picnic.image;

        messageFont = new Font("Sans Serif", Font.PLAIN, 16);
        gameEndMessageFont = new Font("Sans Serif", Font.PLAIN, 40);
        gameCongratulationsFont = new Font("Sans Serif", Font.BOLD, 45);
    }

    public void showMessage(String text) {

        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {

        if(gameFinished) {

            g2.setFont(gameEndMessageFont);
            g2.setColor(Color.white);

            String text;
            int textLength;
            int x, y;

            text = "Let's have a picnic!";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2 + (gp.tileSize*3);
            g2.drawString(text, x, y);

            g2.setFont(gameCongratulationsFont);
            g2.setColor(Color.ORANGE);

            text = "CONGRATULATIONS!";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2 - (gp.tileSize*3);
            g2.drawString(text, x, y);

        } else {

            g2.setFont(uiFont);
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
                g2.setFont(messageFont);
                g2.setColor(Color.BLACK);
                g2.drawString(message, gp.tileSize, gp.tileSize*10+5);

                // ==*MESSAGE TIMER*==
                messageCounter++;
                if(messageCounter > 90) {
                    messageCounter = 0;
                    messageOn = false;
                }
            }
        }
    }
}
