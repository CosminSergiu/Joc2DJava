package PaooGame;

import PaooGame.Tiles.Tile;
import PaooGame.entity.Entity;

public class Collision {
    Game g;
    public Collision(Game g){
        this.g=g;
    }
    public void checkTile(Entity entity){

        int entityLeftx=entity.x+entity.solidArea.x;
        int entityRightx=entity.x+entity.solidArea.x+entity.solidArea.width;
        int entityTopy=entity.y+entity.solidArea.y;
        int entityBottomy=entity.y+entity.solidArea.y+entity.solidArea.height;

        int entityLeftCol = entityLeftx/Tile.TILE_HEIGHT;
        int entityRightCol = entityRightx/Tile.TILE_HEIGHT;
        int entityTopRow = entityTopy/Tile.TILE_WIDTH;
        int entityButtomRow= entityBottomy/Tile.TILE_WIDTH;

        int tileNum1, tileNum2;

        switch (entity.direction){
            case "up":
                entityTopRow=(entityTopy - entity.speed)/Tile.TILE_WIDTH;
                tileNum1=g.map.mapTile[entityLeftCol][entityTopRow];
                tileNum2 = g.map.mapTile[entityRightCol][entityTopRow];
                if(g.map.tile[tileNum1].CollesioOn || g.map.tile[tileNum2].CollesioOn){
                    if(tileNum1 == 5 ){
                        entity.scor+=1;
                        g.map.mapTile[entityLeftCol][entityTopRow] = 0;
                    }
                    else if(tileNum2 == 5){
                        entity.scor+=1;
                        g.map.mapTile[entityRightCol][entityTopRow] = 0;
                    }
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityButtomRow=(entityBottomy + entity.speed)/Tile.TILE_WIDTH;
                tileNum1=g.map.mapTile[entityLeftCol][entityButtomRow];
                tileNum2 = g.map.mapTile[entityRightCol][entityButtomRow];
                if(g.map.tile[tileNum1].CollesioOn || g.map.tile[tileNum2].CollesioOn){
                    if(tileNum1 == 5 ){
                        entity.scor+=1;
                        g.map.mapTile[entityLeftCol][entityButtomRow] = 0;
                    }
                    else if(tileNum2 == 5){
                        entity.scor+=1;
                        g.map.mapTile[entityRightCol][entityButtomRow] = 0;
                    }
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol=(entityLeftx - entity.speed)/Tile.TILE_HEIGHT;
                tileNum1=g.map.mapTile[entityLeftCol][entityTopRow];
                tileNum2 = g.map.mapTile[entityLeftCol][entityButtomRow];
                if(g.map.tile[tileNum1].CollesioOn || g.map.tile[tileNum2].CollesioOn){
                    if(tileNum1 == 5 ){
                        entity.scor+=1;
                        g.map.mapTile[entityLeftCol][entityTopRow] = 0;
                    }
                    else if(tileNum2 == 5){
                        entity.scor+=1;
                        g.map.mapTile[entityLeftCol][entityButtomRow] = 0;
                    }
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol=(entityRightx + entity.speed)/Tile.TILE_HEIGHT;
                tileNum1=g.map.mapTile[entityRightCol][entityTopRow];
                tileNum2 = g.map.mapTile[entityRightCol][entityButtomRow];
                if(g.map.tile[tileNum1].CollesioOn || g.map.tile[tileNum2].CollesioOn){
                    if(tileNum1 == 5 ){
                        entity.scor+=1;
                        g.map.mapTile[entityRightCol][entityTopRow] = 0;
                    }
                    else if(tileNum2 == 5){
                        entity.scor+=1;
                        g.map.mapTile[entityRightCol][entityButtomRow] = 0;
                    }
                    entity.collisionOn = true;
                }
                break;
        }
    }
}
