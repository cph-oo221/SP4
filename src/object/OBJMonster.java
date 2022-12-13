package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJMonster extends SuperObject
{

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
}
