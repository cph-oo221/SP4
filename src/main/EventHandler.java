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
        if(hit(0,30,39,"any") == true)
        {
            // event happens
            teleport(1, 36, 8);
            toDungeon();
        }
        else if(hit(1,36,6,"any") == true)
        {
            // event happens
            teleport(0, 29, 39);
            toHubWorld();
        }
    }

    private void toDungeon()
    {
        gp.stopMusic();
        gp.playMusic(4);
    }
    private void toHubWorld()
    {
        gp.stopMusic();
        gp.playMusic(0);
    }
<<<<<<< Updated upstream
=======

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
        if(gp.player.invincible == false)
        {
            gp.player.HP -= 4;
            System.out.println("your hp " + gp.player.HP);
            gp.player.invincible = true;
            if (gp.player.HP <= 0)
            {
                gp.gameState = gp.lossState;
                System.out.println("GameOver!");
            }
        }
    }
>>>>>>> Stashed changes

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
