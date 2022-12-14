package object;

import main.DialogueHandler;
import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class SuperObject
{
    public GamePanel gp;
    public BufferedImage image;

    DialogueHandler dhandler;
    boolean show_dialogue = false;
    int dialogue_timer = 0;
    String dialogue_text = "Hej";
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
        this.dhandler = new DialogueHandler();
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

        if (show_dialogue)
        {
            dialogue_timer++;
            dhandler.drawDialogue(screenX - 80, screenY - 120, dialogue_text, g2);

            if (dialogue_timer > 120)
            {
                show_dialogue = false;
                dialogue_timer = 0;
            }
        }

    }

    public void interact()
    {
        show_dialogue = true;
    }

    public boolean isPickUpAble()
    {
        return pickUpAble;
    }

}
