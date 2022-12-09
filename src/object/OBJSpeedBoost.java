package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJSpeedBoost extends SuperObject
{
    public OBJSpeedBoost()
    {
        name = "SpeedBoost";
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
