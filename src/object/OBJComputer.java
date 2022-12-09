package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJComputer extends SuperObject
{


    public OBJComputer()
    {
        super.solidArea.height = 30;
        name = "Computer";
        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/Computer.png"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        collision = true;
    }


}
