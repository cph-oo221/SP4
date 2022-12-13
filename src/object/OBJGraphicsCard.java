package object;

//PLACEHOLDER

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJGraphicsCard extends SuperObject{
    public OBJGraphicsCard()
    {
        name = "Graphics Card";

        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/Graphics Card.png"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        pickUpAble = true;
    }
}
