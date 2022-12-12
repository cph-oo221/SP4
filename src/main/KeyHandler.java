package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed;
    public boolean enterPressed, escapePressed;

    public void enterPressed(KeyEvent e)
    {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_ENTER)
        {
            enterPressed = true;
        }
    }

    public void escapePressed(KeyEvent e)
    {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_ESCAPE)
        {
            escapePressed = true;
        }
    }


    @Override
    public void keyTyped(KeyEvent e) {
        //Not needed
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W) {upPressed=true;}
        if(code == KeyEvent.VK_S) {downPressed=true;}
        if(code == KeyEvent.VK_A) {leftPressed=true;}
        if(code == KeyEvent.VK_D) {rightPressed=true;}


    }

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W) {upPressed=false;}
        if(code == KeyEvent.VK_S) {downPressed = false;}
        if(code == KeyEvent.VK_A) {leftPressed=false;}
        if(code == KeyEvent.VK_D) {rightPressed=false;}

    }
}