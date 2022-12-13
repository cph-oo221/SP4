package main;

import object.OBJGraphicsCard;
import object.OBJRam;

import java.awt.*;

import java.awt.Font;
import java.awt.image.BufferedImage;

public class UI
{

    GamePanel gp;
    Font arial_BOLD_40;
    BufferedImage graphicsCardImage;
    BufferedImage RAMimage;
    public UI(GamePanel gp)
    {
        this.gp = gp;
        arial_BOLD_40 = new Font("Arial" , Font.BOLD , 40);
        OBJGraphicsCard OBJGraphicsCard = new OBJGraphicsCard();
        OBJRam OBJRAM = new OBJRam();
        graphicsCardImage = OBJGraphicsCard.image;
        RAMimage = OBJRAM.image;
    }

    public void draw(Graphics2D g2)
    {

        g2.setFont(arial_BOLD_40);
        g2.setColor(Color.white);
        g2.drawImage(graphicsCardImage , gp.tileSize/2 , gp.tileSize/2 , gp.tileSize, gp.tileSize, null);
        g2.drawString(": "+gp.player.hasGraphicsCard, 80, 67);
        g2.drawImage(RAMimage , gp.tileSize/2 , (int) (1.3*gp.tileSize) , gp.tileSize, gp.tileSize, null);
        g2.drawString(": "+gp.player.hasGraphicsCard, 80, 67+48);
    }
}
