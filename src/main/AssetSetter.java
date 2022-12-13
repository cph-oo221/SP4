package main;

import object.*;

public class AssetSetter
{
    private GamePanel gp;
    public AssetSetter(GamePanel gp)
    {
        this.gp=gp;
    }
    public void setObject()
    {
        int mapNum = 0;
        gp.obj[mapNum][0] = new OBJComputer(this.gp);
        gp.obj[mapNum][0].worldX = 9 * gp.tileSize;
        gp.obj[mapNum][0].worldY = 24 * gp.tileSize;

        gp.obj[mapNum][1] = new OBJSign();
        gp.obj[mapNum][1].worldX = 41 * gp.tileSize;
        gp.obj[mapNum][1].worldY = 20 * gp.tileSize;

        gp.obj[mapNum][3] = new OBJSpeedBoost();
        gp.obj[mapNum][3].worldX = 38 * gp.tileSize;
        gp.obj[mapNum][3].worldY = 40 * gp.tileSize;

        // if you want to add on map 1
        mapNum = 1;
        gp.obj[mapNum][2] = new OBJGraphicsCard();
        gp.obj[mapNum][2].worldX = 9 * gp.tileSize;
        gp.obj[mapNum][2].worldY = 40 * gp.tileSize;
    }

    public void setNewObject()
    {
        int mapNum = 1;
        if(gp.obj[mapNum][2] == null && gp.player.hasRAM == 0 && gp.winCount==1)
        {
            gp.obj[mapNum][4] = new OBJRam();
            gp.obj[mapNum][4].worldX = 23 * gp.tileSize;
            gp.obj[mapNum][4].worldY = 29 * gp.tileSize;
        }
    }
}
