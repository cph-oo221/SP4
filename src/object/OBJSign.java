package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJSign extends SuperObject
{

    GamePanel gp;
    public OBJSign(GamePanel gp)
    {
        this.gp = gp;
        name = "Sign";
        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/JesperFrontSmoke1.png"));
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
        dialogue_text = "2 sek. Ryger lige.";
        show_dialogue = true;
    }
}
