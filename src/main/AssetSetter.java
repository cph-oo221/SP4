package main;

import object.OBJComputer;
import object.OBJRAM;
import object.OBJSign;
import object.OBJTicket;

public class AssetSetter
{
    private GamePanel gp;
    public AssetSetter(GamePanel gp)
    {
        this.gp=gp;
    }
    public void setObject()
    {
        gp.obj[0]=new OBJComputer();
        gp.obj[0].worldX = 9 * gp.tileSize;
        gp.obj[0].worldY = 24 * gp.tileSize;

        gp.obj[1]=new OBJSign();
        gp.obj[1].worldX = 41 * gp.tileSize;
        gp.obj[1].worldY = 20 * gp.tileSize;

        gp.obj[2] = new OBJTicket();
        gp.obj[2].worldX = 8 * gp.tileSize;
        gp.obj[2].worldY = 25 * gp.tileSize;

        gp.obj[3]=new OBJRAM();
        gp.obj[3].worldX = 38 * gp.tileSize;
        gp.obj[3].worldY = 40 * gp.tileSize;
    }
}
