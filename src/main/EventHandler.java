package main;

import java.awt.*;

public class EventHandler
{
    GamePanel gp;
    Rectangle eventRect;
    int eventRectDefaultX, eventRectDefaultY;

    public EventHandler(GamePanel gp)
    {
        this.gp = gp;

        eventRect = new Rectangle();
        eventRect.x = 23;
        eventRect.y = 23;
        eventRect.width = 2;
        eventRect.height = 2;
        eventRectDefaultX = eventRect.x;
        eventRectDefaultY = eventRect.y;
    }

    public void checkEvent()
    {
        if(hit(42,9,"any") == true) // Ændrede eventRow fra 10 til 9. tror måske det var out of bounds?
        {
            // event happens
            teleport();
        }
    }

    private void teleport()
    {
        gp.player.worldX = gp.tileSize * 42;
        gp.player.worldY = gp.tileSize * 17;

    }

    public boolean hit(int eventCol, int eventRow, String reqDirection)
    {
        boolean hit = false;

        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
        eventRect.x = eventCol * gp.tileSize + eventRect.x; // Ændrede gp.player.solidArea.x til eventRect.x
        eventRect.y = eventRow * gp.tileSize + eventRect.y; // Ændrede eventCol til eventRow og gp.player.solidArea.y til eventrect.y

        if(gp.player.solidArea.intersects(eventRect))
        {
            if(gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any"))
            {
                hit = true;
            }
        }

        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        eventRect.x =eventRectDefaultX;
        eventRect.y = eventRectDefaultY;

        return hit;
    }

}
