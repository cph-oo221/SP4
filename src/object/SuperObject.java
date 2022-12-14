package object;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class SuperObject
{
    public GamePanel gp;
    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;
    public int solidAreaW = 48;
    public int solidAreaH = 48;
    public int solidAreaX = 1;
    public int solidAreaY = 1;
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;

    public boolean pickUpAble = false;
    public Rectangle solidArea;

    public SuperObject()
    {
        this.solidArea = new Rectangle(solidAreaX,solidAreaY,solidAreaW,solidAreaH);
    }


    public void draw(Graphics2D g2, GamePanel gp)
    {
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        //only draw the tiles we see + 1
        if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY)
        {
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }

    }

    public void interact()
    {
        System.out.println( "This is: " + name);
    }

    public boolean isPickUpAble()
    {
        return pickUpAble;
    }

    public void movement(){};
}
