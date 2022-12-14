package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJMonster extends SuperObject
{


    public int speed = 2;
    int counter = 0;

    public OBJMonster()
    {
        name = "Monster";

        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/cool_monster.jpeg"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        collision=true;
    }

    @Override
    public void interact()
    {
        System.out.println("The monster might eat you! ");
    }

    
    @Override
    public void movement()
    {
        if(counter < 60)
        {
            worldY+= speed;
        }
        else if (counter > 120)
        {
            worldY -= speed;
        } else if (counter <= 240)
        {
            counter = 0;
        }
        counter ++;


    }
}
