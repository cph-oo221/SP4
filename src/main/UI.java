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

    Graphics2D g2;
    public UI(GamePanel gp)
    {
        this.gp = gp;
        arial_BOLD_40 = new Font("Arial", Font.BOLD, 40);
        OBJGraphicsCard OBJGraphicsCard = new OBJGraphicsCard();
        OBJRam OBJRAM = new OBJRam();
        graphicsCardImage = OBJGraphicsCard.image;
        RAMimage = OBJRAM.image;
    }

    public void draw(Graphics2D g2)
    {
        this.g2 = g2;
        g2.setFont(arial_BOLD_40);
        g2.setColor(Color.white);
        g2.drawImage(graphicsCardImage, gp.tileSize / 2, gp.tileSize / 2, gp.tileSize, gp.tileSize, null);
        g2.drawString(": " + gp.player.hasGraphicsCard, 80, 67);
        g2.drawImage(RAMimage, gp.tileSize / 2, (int) (1.3 * gp.tileSize), gp.tileSize, gp.tileSize, null);
        g2.drawString(": " + gp.player.hasRAM, 80, 67 + 48);

        drawHpBar(150, 40);

        // UI fps
        fpsCounter(680, 50);

        // UI Player Location
        drawPlayerLocation(680, 70);

        if (gp.gameState == gp.pauseState)
        {
            drawPauseScreen();
        }
        if (gp.gameState == gp.playState)
        {
            //ORNDES AF GAMEPANEL
        }
        if (gp.gameState == gp.winState)
        {
            drawWinScreen();
        }
        if (gp.gameState == gp.lossState)
        {
            drawLossScreen();
        }



    }

    public void drawWinScreen()
    {
        g2.setFont(arial_BOLD_40);
        g2.setColor(Color.yellow);
        String text = "YOU WIN!";
        int x = getXForCenteredText(text);
        int y = gp.screenHeight / 2;
        g2.drawString(text, x, y);

        // after win options
        String restartText = "(R) Restart";
        int restartX = 100;
        int restartY = 500;
        g2.drawString(restartText, restartX, restartY);

        String quitText = "(Q) Quit";
        int quitX = 480;
        int quitY = 500;
        g2.drawString(quitText, quitX, quitY);
    }

    public void drawLossScreen()
    {
        g2.setFont(arial_BOLD_40);
        g2.setColor(Color.red);
        String text = "YOU DIED!";
        int x = getXForCenteredText(text);
        int y = gp.screenHeight / 2;
        g2.drawString(text, x, y);

        // after loss options
        String restartText = "(R) Restart";
        int restartX = 100;
        int restartY = 500;
        g2.drawString(restartText, restartX, restartY);

        String quitText = "(Q) Quit";
        int quitX = 480;
        int quitY = 500;
        g2.drawString(quitText, quitX, quitY);
    }

    private void drawHpBar(int x, int y)
    {
        int hpX = x + 3;
        int hpY = y + 3;
        g2.setColor(Color.black);
        g2.fillRect(x - 5, y - 5, (int) 6 * gp.player.maxHP + 15, 40);
        g2.setColor(Color.darkGray);
        g2.fillRect(x, y, (int) 6 * gp.player.maxHP + 5, 30);
        g2.setColor(Color.green);
        for (int i = 0; i < gp.player.HP; i++)
        {
            g2.fillRect(hpX, hpY, 5, 24);
            hpX += 6;
        }
    }

    public void fpsCounter(int posX, int posY)
    {
        Font font = new Font("Arial", Font.BOLD, 20);
        g2.setFont(font);
        g2.setColor(Color.white);
        g2.drawString("FPS: " + gp.FPS, posX, posY);
    }

    public void drawPlayerLocation(int posX, int posY)
    {
        Font font = new Font("Arial" , Font.BOLD , 17);
        g2.setFont(font);
        g2.setColor(Color.white);
        g2.drawString("X: " + gp.player.worldX / gp.tileSize, posX, posY);
        g2.drawString("Y: " + gp.player.worldY / gp.tileSize, posX, posY + 25);
    }


    public void drawPauseScreen()
    {
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN , 80));
        String text = "PAUSED";
        int x = getXForCenteredText(text);
        int y = gp.screenHeight/2;
        g2.drawString(text, x, y);
    }
    public int getXForCenteredText(String text)
    {
        int x;
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        x = gp.screenWidth/2 - length/2;
        return x;
    }

}
