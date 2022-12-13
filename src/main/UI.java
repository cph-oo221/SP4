package main;

import object.OBJGraphicsCard;

import java.awt.*;

import java.awt.Font;
import java.awt.image.BufferedImage;

public class UI
{

    GamePanel gp;
    Font arial_BOLD_40;
    BufferedImage tempImage;
    public UI(GamePanel gp)
    {
        this.gp = gp;
        arial_BOLD_40 = new Font("Arial" , Font.BOLD , 40);
        OBJGraphicsCard OBJGraphicsCard = new OBJGraphicsCard();
        tempImage = OBJGraphicsCard.image;
    }

    public void draw(Graphics2D g2)
    {
        g2.setFont(arial_BOLD_40);
        g2.setColor(Color.white);
        g2.drawImage(tempImage , gp.tileSize/2 , gp.tileSize/2 , gp.tileSize, gp.tileSize, null);
        g2.drawImage(tempImage , gp.tileSize/2 , gp.tileSize/2 , gp.tileSize, gp.tileSize, null);
        g2.drawString(": "+gp.player.hasGraphicsCard, 80, 67);
    }
}
