package main;

import entity.Entity;

public class CollisionChecker {

    private GamePanel gp;

    public CollisionChecker(GamePanel gp) {

        this.gp = gp;
    }

    public void checkTile(Entity entity) {

        //==*WORK OUT COLLISION BOX*==
        // -- coords --
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        // -- col/row numbers
        int entityLeftCol = entityLeftWorldX / gp.tileSize;
        int entityRightCol = entityRightWorldX / gp.tileSize;
        int entityTopRow = entityTopWorldY / gp.tileSize;
        int entityBottomRow = entityBottomWorldY / gp.tileSize;

        int tileNum1, tileNum2; // check two tiles per direction (checking tip of right/left collision box)

        switch(entity.direction) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize; // predicts where player will be after movement
                tileNum1 = gp.tileM.mapTileNumber[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNumber[entityRightCol][entityTopRow];
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true; // colliding with tile
                }
                break;
            case "down":

                break;
            case "left":

                break;
            case "right":

                break;
        }
    }
}
