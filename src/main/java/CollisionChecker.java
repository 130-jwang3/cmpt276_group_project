package main.java;

import main.java.entity.*;

public class CollisionChecker {
    GamePanel gamePanel;
    public CollisionChecker(GamePanel gamePanel){
        this.gamePanel=gamePanel;
    }

    public void checkTile(Entity entity){
        int entityPublicLeftX=entity.x+entity.solidArea.x;
        int entityPublicRightX=entity.x+entity.solidArea.x+entity.solidArea.width;
        int entityPublicTopY=entity.y+entity.solidArea.y;
        int entityPublicDownY=entity.y+entity.solidArea.y+entity.solidArea.height;

        int entityLeftCol=entityPublicLeftX/gamePanel.tileSize;
        int entityRightCol=entityPublicRightX/gamePanel.tileSize;
        int entityTopRow=entityPublicTopY/gamePanel.tileSize;
        int entityBottomRow=entityPublicDownY/gamePanel.tileSize;

        int tileNum1,tileNum2;
        switch (entity.direction) {
            case "up" -> {
                entityTopRow = (entityPublicTopY - entity.speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gamePanel.tileManager.mapTileNum[entityRightCol][entityTopRow];
                if (gamePanel.tileManager.tile[tileNum1].collision || gamePanel.tileManager.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
            }
            case "down" -> {
                entityBottomRow = (entityPublicDownY + entity.speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gamePanel.tileManager.mapTileNum[entityRightCol][entityBottomRow];
                if (gamePanel.tileManager.tile[tileNum1].collision || gamePanel.tileManager.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
            }
            case "left" -> {
                entityLeftCol = (entityPublicLeftX - entity.speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
                if (gamePanel.tileManager.tile[tileNum1].collision || gamePanel.tileManager.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
            }
            case "right" -> {
                entityRightCol = (entityPublicRightX + entity.speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gamePanel.tileManager.mapTileNum[entityRightCol][entityBottomRow];
                if (gamePanel.tileManager.tile[tileNum1].collision || gamePanel.tileManager.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
            }
        }
    }
    public int checkObject(Entity entity,boolean player){
        int index=999;

        for(int i=0;i<gamePanel.obj.length;i++){
            if(gamePanel.obj[i]!=null){
                entity.solidArea.x=entity.x+entity.solidArea.x;
                entity.solidArea.y=entity.y+entity.solidArea.y;
                gamePanel.obj[i].solidArea.x=gamePanel.obj[i].x+gamePanel.obj[i].solidArea.x;
                gamePanel.obj[i].solidArea.y=gamePanel.obj[i].y+gamePanel.obj[i].solidArea.y;
                switch (entity.direction) {
                    case "up" -> {
                        entity.solidArea.y -= entity.speed;
                        if (entity.solidArea.intersects(gamePanel.obj[i].solidArea)) {
                            if(gamePanel.obj[i].collision){
                                entity.collisionOn=true;
                            }
                            if(player){
                                index=i;
                            }
                        }
                    }
                    case "down" -> {
                        entity.solidArea.y += entity.speed;
                        if (entity.solidArea.intersects(gamePanel.obj[i].solidArea)) {
                            if(gamePanel.obj[i].collision){
                                entity.collisionOn=true;
                            }
                            if(player){
                                index=i;
                            }
                        }
                    }
                    case "left" -> {
                        entity.solidArea.x -= entity.speed;
                        if (entity.solidArea.intersects(gamePanel.obj[i].solidArea)) {
                            if(gamePanel.obj[i].collision){
                                entity.collisionOn=true;
                            }
                            if(player){
                                index=i;
                            }
                        }
                    }
                    case "right" -> {
                        entity.solidArea.x += entity.speed;
                        if (entity.solidArea.intersects(gamePanel.obj[i].solidArea)) {
                            if(gamePanel.obj[i].collision){
                                entity.collisionOn=true;
                            }
                            if(player){
                                index=i;
                            }
                        }
                    }
                }
                entity.solidArea.x=entity.solidAreaDefaultX;
                entity.solidArea.y=entity.solidAreaDefaultY;
                gamePanel.obj[i].solidArea.x=gamePanel.obj[i].solidAreaDefaultX;
                gamePanel.obj[i].solidArea.y=gamePanel.obj[i].solidAreaDefaultY;
            }
        }

        return index;
    }
}
