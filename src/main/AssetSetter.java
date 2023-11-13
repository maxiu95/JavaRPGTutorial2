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
        gp.obj[1].worldX = 18 * gp.tileSize; // 19
        gp.obj[1].worldY = 20 * gp.tileSize; // 21
    }
}
