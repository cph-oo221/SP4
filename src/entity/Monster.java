package entity;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Monster extends Entity
{
    GamePanel gp;
    int speed = 2;
    public BufferedImage image;
    int timer=0;
    public Monster(GamePanel gp)
    {
        this.gp = gp;

        solidArea= new Rectangle();
        solidArea.x = 0;
        solidArea.y = 0;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = gp.tileSize;
        solidArea.height = gp.tileSize;

        setDefaultStats();
        getImage();

    }

    public void setDefaultStats()
    {
        worldX = 11*gp.tileSize;
        worldY = 40*gp.tileSize;
        speed = 2;
        direction = "down";
    }
    public void getImage()
    {
        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/cool_monster.jpeg"));
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2)
    {

        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;


        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);


    }

    public void update ()
    {

        //CHECK TILE COLLISION
        collisionOn = false;
        gp.cChecker.checkTile(this);
        movement();

    }


    public void movement()
    {
        if (worldY == 40 * gp.tileSize)
        {
            direction = "down";
        }
        if (worldY == 42 * gp.tileSize)
        {
            direction = "up";
        }
        if (direction == "down")
        {
            worldY += speed;
        } else if (direction == "up")
        {
            worldY -= speed;
        }
        timer++;

        if(solidArea.intersects(gp.player.solidArea) && timer > 30)
        {
            //gp.player.spin();
            System.out.println("collision");

            if (direction == "up")
            {
                System.out.println("switching direction to down");
                direction = "down";
            } else if (direction == "down")
            {
                System.out.println("switching direction to up");
                direction = "up";
            }
            timer = 0;
        }
    }
}
