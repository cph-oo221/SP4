package main;

import java.awt.*;

public class EventHandler
{
    GamePanel gp;
    Rectangle eventRect[][][];
    int eventRectDefaultX, eventRectDefaultY;

    public EventHandler(GamePanel gp)
    {
        this.gp = gp;

        eventRect = new Rectangle[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];

        int map = 0;
        int col = 0;
        int row = 0;

        while (map < gp.maxMap && col < gp.maxWorldCol && row < gp.maxWorldRow)
        {
            eventRect[map][col][row] = new Rectangle();
            eventRect[map][col][row].x = 23;
            eventRect[map][col][row].y = 23;
            eventRect[map][col][row].width = 2;
            eventRect[map][col][row].height = 2;
            eventRectDefaultX = eventRect[map][col][row].x;
            eventRectDefaultY = eventRect[map][col][row].y;

            col++;
            if (col == gp.maxWorldCol)
            {
                col = 0;
                row++;
            }

            if (row == gp.maxWorldRow)
            {
                row = 0;
                map++;
            }
        }

    }

    public void checkEvent()
    {
        if (hit(0, 30, 39, "any"))
        {
            // event happens
            teleport(1, 36, 8);
            toDungeon();
        }
        else if (hit(1, 36, 6, "any"))
        {
            // event happens
            teleport(0, 29, 39);
            toHubWorld();
        }
        // Fire in dungeon
        setFire();
    }

    public void setFire()
    {
        // Fire placed at entrance/exit dungeon
        if(hit(1, 40, 7, "any")) {damage();}
        if(hit(1, 33, 7, "any")) {damage();}
        if(hit(1, 32, 8, "any")) {damage();}
        if(hit(1, 39, 9, "any")) {damage();}

        // Fire room entrance
        if(hit(1, 21, 17, "any")) {damage();}
        if(hit(1, 21, 20, "any")) {damage();}

        // Fire room bottom 3
        if(hit(1, 19, 19, "any")) {damage();}
        if(hit(1, 15, 19, "any")) {damage();}
        if(hit(1, 9, 19, "any")) {damage();}

        // Fire room top 3
        if(hit(1, 19, 15, "any")) {damage();}
        if(hit(1, 13, 15, "any")) {damage();}
        if(hit(1, 8, 15, "any")) {damage();}

        //Fire room mid
        if(hit(1, 11, 16, "any")) {damage();}
        if(hit(1, 15, 17, "any")) {damage();}

        if(hit(1, 14, 18, "any")) {damage();}
        if(hit(1, 11, 18, "any")) {damage();}


    }

    private void toDungeon()
    {
        gp.stopMusic();
        gp.playMusic(4);
    }

    public void toHubWorld()
    {
        gp.stopMusic();
        gp.playMusic(0);
    }


    public void playerDeath()
    {
        if ( gp.gameState == gp.lossState)
        {
            gp.stopMusic();
            gp.playMusic(5);
        }
    }
    public void damage()
    {
        if(!gp.player.invincible)
        {
            gp.player.HP -= 4;
            System.out.println("your hp " + gp.player.HP);
            gp.playSE(7);
            gp.player.invincible = true;
            if (gp.player.HP <= 0)
            {
                gp.gameState = gp.lossState;
                System.out.println("GameOver!");
            }
        }
    }

    public void invincibilityFrames()
    {
        if(gp.player.invincible)
        {
            gp.player.invincibleCounter++;
        }
        if(gp.player.invincibleCounter >= 60)
        {
            gp.player.invincibleCounter = 0;
            gp.player.invincible = false;
        }
    }
    private void teleport(int map, int col, int row)
    {
        gp.currentMap = map;
        gp.player.worldX = col * gp.tileSize;
        gp.player.worldY = row * gp.tileSize;

    }

    public boolean hit(int map, int col, int row, String reqDirection)
    {
        boolean hit = false;
        if(map == gp.currentMap)
        {
            gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
            gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
            eventRect[map][col][row].x = col * gp.tileSize + eventRect[map][col][row].x;
            eventRect[map][col][row].y = row * gp.tileSize + eventRect[map][col][row].y;

            if(gp.player.solidArea.intersects(eventRect[map][col][row]))
            {
                if(gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any"))
                {
                    hit = true;
                }
            }

            gp.player.solidArea.x = gp.player.solidAreaDefaultX;
            gp.player.solidArea.y = gp.player.solidAreaDefaultY;
            eventRect[map][col][row].x = eventRectDefaultX;
            eventRect[map][col][row].y = eventRectDefaultY;
        }



        return hit;
    }


    public void win()
    {
        if(gp.gameState == gp.winState)
        {
            gp.stopMusic();
            gp.playMusic(6);
        }
    }
}
