package object;

//PLACEHOLDER

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJTicket extends SuperObject{
    public OBJTicket()
    {
        name = "Temp";

        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/ComputerDel.png"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        pickUpAble = true;
    }
    public void interact()
    {
        System.out.println("No interaction here");
    }
}
