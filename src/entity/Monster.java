package entity;

import main.GamePanel;

public class Monster extends Entity
{
    GamePanel gp;
    int x;
    int y;
    public Monster(int x , int y)
    {
        this.x = x;
        this.y = y;

    }
    public void draw()
    {
        g2.drawImage(down1, x*gp.tileSize, y*gp.tileSize, gp.tileSize,gp.tileSize, null);
    }
}
