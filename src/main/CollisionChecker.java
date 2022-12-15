package main;

import entity.Entity;

public class CollisionChecker
{
    GamePanel gp;
    public CollisionChecker(GamePanel gp)
    {
        this.gp = gp;
    }

    public void checkTile(Entity entity)
    {
        if(entity.direction != null)
        {
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX/gp.tileSize;
        int entityRightCol = entityRightWorldX/gp.tileSize;
        int entityTopRow = entityTopWorldY/gp.tileSize;
        int entityBottomRow = entityBottomWorldY/gp.tileSize;

        int tileNum1, tileNum2;

        switch(entity.direction)
        {
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityTopRow];
                if (gp.tileM.tiles[tileNum1].collision || gp.tileM.tiles[tileNum2].collision)
                {
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityBottomRow];
                if (gp.tileM.tiles[tileNum1].collision || gp.tileM.tiles[tileNum2].collision)
                {
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityBottomRow];
                if (gp.tileM.tiles[tileNum1].collision || gp.tileM.tiles[tileNum2].collision)
                {
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityBottomRow];
                if (gp.tileM.tiles[tileNum1].collision || gp.tileM.tiles[tileNum2].collision)
                {
                    entity.collisionOn = true;
                }
                break;
        }
        }
    }
    public void checkKasseFyr(Entity  player)
    {
        //get entity's position
        player.solidArea.x = player.worldX + player.solidArea.x;
        player.solidArea.y = player.worldY + player.solidArea.y;
        //get objects's position
       gp.kasseFyr.solidArea.x = gp.kasseFyr.worldX + gp.kasseFyr.solidArea.x;
        gp.kasseFyr.solidArea.y = gp.kasseFyr.worldY + gp.kasseFyr.solidArea.y;
        switch (player.direction)
        {
            case "up":
                player.solidArea.y -= player.speed;
                if (player.solidArea.intersects(gp.kasseFyr.solidArea))
                {
                    player.collisionOn = true;
                    gp.kasseFyr.collisionOn = true;
                    gp.eventH.damage1();
                }
                break;
            case "down":
                player.solidArea.y += player.speed;
                if (player.solidArea.intersects(gp.kasseFyr.solidArea))
                {
                    player.collisionOn = true;
                    gp.kasseFyr.collisionOn = true;
                    gp.eventH.damage1();
                }
                break;
            case "left":
                player.solidArea.x -= player.speed;
                if (player.solidArea.intersects(gp.kasseFyr.solidArea))
                {
                    player.collisionOn = true;
                    gp.kasseFyr.collisionOn = true;
                    gp.eventH.damage1();
                }
                break;
            case "right":
                player.solidArea.x += player.speed;
                if (player.solidArea.intersects(gp.kasseFyr.solidArea))
                {
                    player.collisionOn = true;
                    gp.kasseFyr.collisionOn = true;
                    gp.eventH.damage1();
                }
                break;
        }
        player.solidArea.x = player.solidAreaDefaultX;
        player.solidArea.y = player.solidAreaDefaultY;
        gp.kasseFyr.solidArea.x = gp.kasseFyr.solidAreaDefaultX;
        gp.kasseFyr.solidArea.y = gp.kasseFyr.solidAreaDefaultY;
    }
    public int checkObject(Entity entity, boolean player)
    {
        int index = 999;

        for(int i = 0; i < gp.obj[1].length-1 ; i++)
        {
            if(gp.obj[gp.currentMap][i] != null)
            {
                //get entity's position
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;
                //get objects's position
                gp.obj[gp.currentMap][i].solidArea.x = gp.obj[gp.currentMap][i].worldX + gp.obj[gp.currentMap][i].solidArea.x;
                gp.obj[gp.currentMap][i].solidArea.y = gp.obj[gp.currentMap][i].worldY + gp.obj[gp.currentMap][i].solidArea.y;

                switch (entity.direction)
                {
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        if (entity.solidArea.intersects(gp.obj[gp.currentMap][i].solidArea))
                        {
                            if(gp.obj[gp.currentMap][i].collision)
                            {
                                entity.collisionOn = true;
                            }
                            if(player)
                            {
                                index = i;
                            }

                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        if (entity.solidArea.intersects(gp.obj[gp.currentMap][i].solidArea))
                        {
                            if(gp.obj[gp.currentMap][i].collision)
                            {
                                entity.collisionOn = true;
                            }
                            if(player)
                            {
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if (entity.solidArea.intersects(gp.obj[gp.currentMap][i].solidArea))
                        {
                            if(gp.obj[gp.currentMap][i].collision)
                            {
                                entity.collisionOn = true;
                            }
                            if(player)
                            {
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        if (entity.solidArea.intersects(gp.obj[gp.currentMap][i].solidArea))
                        {
                            if(gp.obj[gp.currentMap][i].collision)
                            {
                                entity.collisionOn = true;
                            }
                            if(player)
                            {
                                index = i;
                            }
                        }
                        break;
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.obj[gp.currentMap][i].solidArea.x = gp.obj[gp.currentMap][i].solidAreaDefaultX;
                gp.obj[gp.currentMap][i].solidArea.y = gp.obj[gp.currentMap][i].solidAreaDefaultY;
            }
        }
        return index;
    }
}
