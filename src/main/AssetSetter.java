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
        gp.obj[0]=new OBJComputer(this.gp);
        gp.obj[0].worldX = 9 * gp.tileSize;
        gp.obj[0].worldY = 24 * gp.tileSize;

        gp.obj[1]=new OBJSign();
        gp.obj[1].worldX = 41 * gp.tileSize;
        gp.obj[1].worldY = 20 * gp.tileSize;

        gp.obj[2] = new OBJTicket();
        gp.obj[2].worldX = 8 * gp.tileSize;
        gp.obj[2].worldY = 25 * gp.tileSize;

        gp.obj[3]=new OBJSpeedBoost();
        gp.obj[3].worldX = 38 * gp.tileSize;
        gp.obj[3].worldY = 40 * gp.tileSize;

    }

    public void setNewObject()
    {
        if(gp.obj[2] == null && gp.player.hasTicket == 1 && gp.winCount==0)
        {
            gp.obj[4] = new OBJRam();
            gp.obj[4].worldX = 15 * gp.tileSize;
            gp.obj[4].worldY = 25 * gp.tileSize;
        }
    }
}
