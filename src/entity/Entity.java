package entity;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity
{
    GamePanel gp;
    public int worldX, worldY;
    public int speed;

    public BufferedImage up1, up2, up3, down1, down2, down3, left1, left2, right1, right2;
    public String direction;

    public int spriteCounter = 0;
    public int spriteNumber = 1;

    public Rectangle solidArea;

    public int solidAreaDefaultX, solidAreaDefaultY;

    public boolean collisionOn = false;
}
