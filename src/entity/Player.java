package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{



    GamePanel gp;
    KeyHandler keyH;

    public int screenX;
    public int screenY;
    public int hasTicket;

    int tileSize;

    public Rectangle interactTangleUp = new Rectangle();
    public int TangleUpDefaultX;
    public int TangleUpDefaultY;
    public Rectangle interactTangleDown = new Rectangle();
    public int TangleDownDefaultX;
    public int TangleDownDefaultY;
    public Rectangle interactTangleLeft = new Rectangle();
    public int TangleLeftDefaultX;
    public int TangleLeftDefaultY;
    public Rectangle interactTangleRight = new Rectangle();
    public int TangleRightDefaultX;
    public int TangleRightDefaultY;

    public Rectangle activeRect;

    public Player(GamePanel gp, KeyHandler keyH)
    {
        this.gp = gp;
        this.keyH = keyH;
        tileSize = gp.tileSize;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea= new Rectangle();
        solidArea.x = (int) (gp.tileSize*0.4);
        solidArea.y = (int) (gp.tileSize*0.4);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = (int) (gp.tileSize*0.4);
        solidArea.height = (int) (gp.tileSize*0.6);


        interactTangleUp.x = (screenX);
        interactTangleUp.y = (screenY-tileSize);
        TangleUpDefaultX = interactTangleUp.x;
        TangleUpDefaultY = interactTangleUp.y;
        interactTangleUp.width = (tileSize);
        interactTangleUp.height = (2*tileSize);

        interactTangleDown.x = (screenX);
        interactTangleDown.y = (screenY);
        TangleUpDefaultX = interactTangleDown.x;
        TangleUpDefaultY = interactTangleDown.y;
        interactTangleDown.width = (tileSize);
        interactTangleDown.height = (2*tileSize);

        interactTangleLeft.x = (screenX-tileSize);
        interactTangleLeft.y = (screenY);
        TangleUpDefaultX = interactTangleLeft.x;
        TangleUpDefaultY = interactTangleLeft.y;
        interactTangleLeft.width = (2*tileSize);
        interactTangleLeft.height = (tileSize);

        interactTangleRight.x = (screenX);
        interactTangleRight.y = (screenY);
        TangleUpDefaultX = interactTangleRight.x;
        TangleUpDefaultY = interactTangleRight.y;
        interactTangleRight.width = (2*tileSize);
        interactTangleRight.height = (tileSize);


        setDefaulValues();
        getPlayerImage();
    }

    public void getPlayerImage()
    {
        try{
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/Player Up1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/Player Up2.png"));
            up3 = ImageIO.read(getClass().getResourceAsStream("/player/Player Up3.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/Player down1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/Player down2.png"));
            down3 = ImageIO.read(getClass().getResourceAsStream("/player/Player down3.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/Player Left1.png"));
            left2= ImageIO.read(getClass().getResourceAsStream("/player/Player Left2.png"));
            right1= ImageIO.read(getClass().getResourceAsStream("/player/Player Right1.png"));
            right2= ImageIO.read(getClass().getResourceAsStream("/player/Player Right2.png"));


        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    public void setDefaulValues ()
    {
        worldX = gp.tileSize * 36;
        worldY = gp.tileSize * 8;
        speed = 4;
        direction="up";
    }
    public void update(){
        if( keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed)
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
            int objectIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objectIndex);

            switch (direction)
            {
                case "up" : activeRect = interactTangleUp;break;
                case "down" : activeRect = interactTangleDown; break;
                case "left" : activeRect = interactTangleLeft; break;
                case "right" : activeRect = interactTangleRight; break;
            }

            //IF COLLISION IS FALSE, PLAYER CAN MOVE
            if(collisionOn == false)
            {
                switch (direction)
                {
                    case "up": worldY -= speed; break;
                    case "down": worldY += speed; break;
                    case "left": worldX -= speed; break;
                    case "right": worldX += speed; break;
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

    }

    public void interact()
    {
        if (keyH.ePressed)
        {
            System.out.println("Du trykker E");
            int index = checkObjectInteract(this);
            System.out.println(index);
            gp.objectInteraction(index);
        }
    }

    public int checkObjectInteract(Player player)
    {
        int index = 999;
        for (int i = 0; i < gp.obj.length - 1; i++)
        {
            if (gp.obj[i] != null && activeRect != null)
            {
                if (activeRect.intersects(gp.obj[i].solidArea))
                {
                    index = i;
                }
            }
        }
        return index;
    }

    public void pickUpObject(int i)
    {
        if (i != 999)
        {
            String objectName = gp.obj[i].name;
            switch(objectName)
            {
                case "Temp" :
                    hasTicket++;
                    gp.playSE(1);
                    gp.obj[i] = null;
                    break;
                case "RAM" :
                    hasTicket++;
                    gp.playSE(1);
                    gp.obj[i] = null;
                    break;
                case "Computer" :
                    if(hasTicket > 0)
                    {
                        gp.playSE(2);

                        hasTicket--;
                        // computer wincount ++
                        gp.countWinPoints();
                    }
                    break;
                case "Sign" :
                    if(hasTicket > 0)
                    {
                        gp.playSE(2);

                        hasTicket--;
                    }
                    break;
                case "SpeedBoost" :
                    speed += 2;
                    gp.obj[i] = null;
            }
        }

    }
    public void draw(Graphics2D g2)
        {
            BufferedImage image = null;
            switch (direction)
            {
                case "up":
                    if(spriteNumber == 1) {
                        image = up1;
                    }
                    if(spriteNumber == 2)
                    {
                        image = up2;
                    }
                    break;

                case "down":
                    if(spriteNumber == 1) {
                        image = down1;
                    }
                    if(spriteNumber == 2)
                    {
                        image = down2;
                    }
                    break;


                case "left":
                    if(spriteNumber == 1) {
                        image = left1;
                    }
                    if(spriteNumber == 2)
                    {
                        image = left2;
                    }
                    break;

                case "right":
                    if(spriteNumber == 1) {
                        image = right1;
                    }
                    if(spriteNumber == 2)
                    {
                        image = right2;
                    }
                    break;
            }

            g2.drawImage(image, screenX, screenY, gp.tileSize,gp.tileSize, null);
        }
}