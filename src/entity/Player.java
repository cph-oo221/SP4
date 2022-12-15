package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class Player extends Entity
{
    public boolean invincible = false;
    public int invincibleCounter = 0;
    GamePanel gp;
    public KeyHandler keyH;

    public int screenX;
    public int screenY;
    public int hasGraphicsCard = 1;
    public int hasRAM = 0;
    private int cooldown_count = 0;
    private boolean cooldown = false;
    public int maxHP;
    public int HP = 32;
    public int currentCollison = 999;
    public Player(GamePanel gp, KeyHandler keyH)
    {
        this.gp = gp;
        this.keyH = keyH;
        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);


        solidArea = new Rectangle();
        solidArea.x = (int) (gp.tileSize * 0.4);
        solidArea.y = (int) (gp.tileSize * 0.4);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = (int) (gp.tileSize * 0.4);
        solidArea.height = (int) (gp.tileSize * 0.6);

        maxHP = HP;

        setDefaulValues();
        getPlayerImage();
    }
    public void getPlayerImage()
    {
        try
        {
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/Player Up1.png"));
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/Player Up1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/Player Up2.png"));
            up3 = ImageIO.read(getClass().getResourceAsStream("/player/Player Up3.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/Player down1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/Player down2.png"));
            down3 = ImageIO.read(getClass().getResourceAsStream("/player/Player down3.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/Player Left1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/Player Left2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/Player Right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/Player Right2.png"));


        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void setDefaulValues()
    {
        worldX = gp.tileSize * 36;
        worldY = gp.tileSize * 8;
        speed = 6;
        direction = "up";
        HP = maxHP;
        hasGraphicsCard = 0;
        hasRAM = 0;
    }

    public void update()
    {
        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed)
        {
            if (keyH.upPressed)
            {
                direction = "up";

            } else if (keyH.downPressed)
            {
                direction = "down";

            } else if (keyH.leftPressed)
            {
                direction = "left";

            } else if (keyH.rightPressed)
            {
                direction = "right";
            }

            //CHECK TILE COLLISION
            collisionOn = false;
            gp.cChecker.checkTile(this);

            //CHECK OBJECT COLLISION
            currentCollison = gp.cChecker.checkObject(this, true);

            //check event
            gp.eventH.checkEvent();

            gp.cChecker.checkKasseFyr(this);


            // INTERACTION ON COLLIDED OBJECT WiTH E PRESS


            //pickUpObject(objectIndex);
            gp.eventH.invincibilityFrames();

            //IF COLLISION IS FALSE, PLAYER CAN MOVE
            if (!collisionOn)
            {
                switch (direction)
                {
                    case "up" -> worldY -= speed;
                    case "down" -> worldY += speed;
                    case "left" -> worldX -= speed;
                    case "right" -> worldX += speed;
                }
            }

            spriteCounter++;
            if (spriteCounter > 12)
            {
                if (spriteNumber == 1)
                {
                    spriteNumber = 2;
                } else if (spriteNumber == 2)
                {
                    spriteNumber = 1;
                }
                spriteCounter = 0;
            }
        }
        // OBJECT INTERACTION ON E PRESS
        if (keyH.ePressed && !cooldown)
        {
            cooldown = true;
            if (currentCollison < gp.obj[1].length && gp.obj[gp.currentMap][currentCollison] != null && currentCollison != 999)
            {
                if (gp.obj[gp.currentMap][currentCollison].isPickUpAble())
                {
                    pickUpObject(currentCollison);
                }
                else
                {
                    gp.obj[gp.currentMap][currentCollison].interact();
                }
            }
        }
        if (cooldown)
        {
            cooldown_count++;
            if (cooldown_count > 19)
            {
                cooldown_count = 0;
                cooldown = false;
            }
        }

    }

    public void pickUpObject(int i)
    {
        if (i != 999)
        {
            String objectName = gp.obj[gp.currentMap][i].name;
            switch (objectName)
            {
                case "Graphics Card":
                    hasGraphicsCard++;
                    gp.playSE(1);
                    gp.obj[gp.currentMap][i] = null;
                    break;
                case "RAM":
                    hasRAM++;
                    gp.playSE(1);
                    gp.obj[gp.currentMap][i] = null;
                    break;
                case "SpeedBoost":
                    speed += 2;
                    gp.obj[gp.currentMap][i] = null;
            }
        }

    }

    public void draw(Graphics2D g2)
    {
        BufferedImage image = null;
        switch (direction)
        {
            case "up":
                if (spriteNumber == 1)
                {
                    image = up1;
                }
                if (spriteNumber == 2)
                {
                    image = up2;
                }
                break;

            case "down":
                if (spriteNumber == 1)
                {
                    image = down1;
                }
                if (spriteNumber == 2)
                {
                    image = down2;
                }
                break;


            case "left":
                if (spriteNumber == 1)
                {
                    image = left1;
                }
                if (spriteNumber == 2)
                {
                    image = left2;
                }
                break;

            case "right":
                if (spriteNumber == 1)
                {
                    image = right1;
                }
                if (spriteNumber == 2)
                {
                    image = right2;
                }
                break;
        }

        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}