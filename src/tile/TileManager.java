package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {

    GamePanel gp;
    Tile[] tile; // array to store tiles
    int mapTileNumber[][]; // store the tile numbers from map.txt file

    public TileManager(GamePanel gp) {

        this.gp = gp;
        tile = new Tile[10];
        mapTileNumber = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap("/maps/anpan_map.txt");
    }

    public void getTileImage() {

        try {

            tile[0] = new Tile(); // grass
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));
            tile[1] = new Tile(); // water
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));
            tile[2] = new Tile(); // wall
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
            tile[3] = new Tile(); // tree
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tree.png"));
            tile[4] = new Tile(); // dirt
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/dirt.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String mapFile) {

        try {

            InputStream is = getClass().getResourceAsStream(mapFile); // import text file
            BufferedReader br = new BufferedReader(new InputStreamReader(is)); // read contents of file

            int col = 0;
            int row = 0;

            // loops the text file, reading line by line splitting data per " " and parsing as int to map array.
            while(col < gp.maxWorldCol && row < gp.maxWorldRow) {

                String line = br.readLine();

                while(col < gp.maxWorldCol) {

                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNumber[col][row] = num;
                    col++;
                }
                if(col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {

        int worldCol = 0;
        int worldRow = 0;

        // loop to draw from the map array
        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {

            int tileNum = mapTileNumber[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            // calculate player location on map
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            // so map renders as you move instead of rendering whole map at once to improve performance
            if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                    worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                    worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                    worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

                g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }

            worldCol++;

            if(worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
