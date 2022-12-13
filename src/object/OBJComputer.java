package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJComputer extends SuperObject {
    GamePanel gp;
    public OBJComputer(GamePanel gp) {
        this.gp = gp;
        super.solidArea.height = 30;
        name = "Computer";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/Computer.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
            collision = true;
    }

    @Override
    public void interact()
    {
        if(gp.player.hasRAM > 0)
        {
            System.out.println("you put in " + gp.player.hasRAM + " RAM.");
            gp.player.hasRAM--;
            // computer wincount ++
            gp.countWinPoints();
        }
        if(gp.player.hasGraphicsCard > 0)
        {
            System.out.println("you put in " + gp.player.hasGraphicsCard + " Graphics Card.");
            gp.player.hasGraphicsCard--;
            // computer wincount ++
            gp.countWinPoints();

        }
        System.out.println("This is your computer");
        gp.checkComputer();
    }
}
