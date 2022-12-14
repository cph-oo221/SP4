package entity;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Monster extends Entity
{
    GamePanel gp;
    int x;
    int y;
    int speed = 2;
    BufferedImage image = getImage();
    int counter = 0;

    public Monster(int x , int y)
    {
        this.x = x;
        this.y = y;

    }
    public void draw(Graphics2D g2)
    {
        if(down1 != null)
        {
            g2.drawImage(down1, x * gp.tileSize, y * gp.tileSize, gp.tileSize, gp.tileSize, null);
        }
    }

    public BufferedImage getImage()
    {
        try

        {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/cool_monster.jpeg"));
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        return image;
    }
    public void movement()
    {
        if(counter < 60)
        {
            y+= speed;
        }
        else if (counter > 120)
        {
            y -= speed;
        } else if (counter >= 240)
        {
            counter = 0;
        }
        counter ++;
    }
}
