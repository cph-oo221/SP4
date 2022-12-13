package main;
import object.SuperObject;
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
        arial_BOLD_40 = new Font("Arial" , Font.BOLD , 40);
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


        if (gp.gameState == gp.pauseState)
        {
            drawPauseScreen();
        }
        if (gp.gameState == gp.playState)
        {

        }
    }
    private void drawWinScreen()
    {
        g2.setFont(arial_BOLD_40);
        g2.setColor(Color.yellow);
        String text = "YOU WIN!";
        int x = getXForCenteredText(text);
        int y = gp.screenHeight/2;
        g2.drawString(text, x, y);
    }
    private void drawLossScreen()
    {
        g2.setFont(arial_BOLD_40);
        g2.setColor(Color.red);
        String text = "YOU DIED!";
        int x = getXForCenteredText(text);
        int y = gp.screenHeight/2;
        g2.drawString(text, x, y);
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
