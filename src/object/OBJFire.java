package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJFire extends SuperObject
{
    public OBJFire()
    {
        name = "Fire";
        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/Fire.png"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        collision = true;

    }
}
