package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJRam extends SuperObject
{

    public OBJRam()
    {
        name = "RAM";
        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/Ram1.png"));

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        collision=false;
    }
    public void interact()
    {
        System.out.println("No interaction here");
    }
}