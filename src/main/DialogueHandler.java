package main;


import java.awt.*;

public class DialogueHandler
{

    Font comic_sans;
    public DialogueHandler()
    {

        comic_sans = new Font("Comic", Font.BOLD, 13);
    }


    public void drawDialogue(int x, int y, String msg, Graphics2D g2) // max 182 characters!
    {
        g2.setColor(Color.black);
        g2.fillRoundRect(x, y, 208, 108, 50, 50);

        // drawPolygon ??

        g2.setColor(Color.white);
        g2.fillRoundRect(x + 4, y + 4, 200, 100, 50, 50);

        g2.setColor(Color.black);
        g2.setFont(comic_sans);

        int last_character = 0;
        int y_offset = 20;

        if (msg.length() > 26)
        {
            do
            {
                g2.drawString(msg.substring(last_character, last_character + 26), x + 20, y + y_offset);
                y_offset += 12;
                last_character += 26;

            } while (msg.substring(last_character).length() > 26);
        }


        g2.drawString(msg.substring(last_character), x + 20, y + y_offset);

        // check if msg is more than 26 characters long.
        // if not, display message
        // if is, display character up until 26th character and add 20 to y offset to start new line
        // new msg is substring from last character
        // check if ms is

        // g2.drawString(msg, x + 20, y + 20);
    }
}
