package main;

import object.OBJ_Anpan;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {

        gp.obj[0] = new OBJ_Anpan();
        gp.obj[0].worldX = 31 * gp.tileSize; // 31
        gp.obj[0].worldY = 16 * gp.tileSize; // 16

        gp.obj[1] = new OBJ_Anpan();
        gp.obj[1].worldX = 18 * gp.tileSize; // 18
        gp.obj[1].worldY = 20 * gp.tileSize; // 20

        gp.obj[2] = new OBJ_Anpan();
        gp.obj[2].worldX = 48 * gp.tileSize;
        gp.obj[2].worldY = 42 * gp.tileSize;

        gp.obj[3] = new OBJ_Anpan();
        gp.obj[3].worldX = 28 * gp.tileSize;
        gp.obj[3].worldY = 45 * gp.tileSize;

        gp.obj[4] = new OBJ_Anpan();
        gp.obj[4].worldX = 2 * gp.tileSize;
        gp.obj[4].worldY = 42 * gp.tileSize;

        gp.obj[5] = new OBJ_Anpan();
        gp.obj[5].worldX = 5 * gp.tileSize;
        gp.obj[5].worldY = 36 * gp.tileSize;

        gp.obj[6] = new OBJ_Anpan();
        gp.obj[6].worldX = 4 * gp.tileSize;
        gp.obj[6].worldY = 27 * gp.tileSize;

        gp.obj[7] = new OBJ_Anpan();
        gp.obj[7].worldX = 44 * gp.tileSize;
        gp.obj[7].worldY = 22 * gp.tileSize;

        gp.obj[8] = new OBJ_Anpan();
        gp.obj[8].worldX = 46 * gp.tileSize;
        gp.obj[8].worldY = 2 * gp.tileSize;

        gp.obj[8] = new OBJ_Anpan();
        gp.obj[8].worldX = 14 * gp.tileSize;
        gp.obj[8].worldY = 1 * gp.tileSize;
    }
}
