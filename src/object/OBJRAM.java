package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJRAM extends SuperObject
{
    public OBJRAM()
    {
        name = "RAM";
        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/Temp.png"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
