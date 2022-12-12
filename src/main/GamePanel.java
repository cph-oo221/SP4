package main;

import entity.Player;
import tile.TileManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


import object.SuperObject;
public class GamePanel extends JPanel implements Runnable{

    //SCREEN SETTINGS
    private final int originalTileSize = 16; // 16*16 pixel tile
    private final int scale = 3;
    public final int tileSize = originalTileSize * scale; //48*48 pixel tile

    public final int maxScreenColumn = 16;
    public final int maxScreenRow = 12;

    public final int screenWidth = tileSize * maxScreenColumn; // 768 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    //World Settings
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;

    //FPS
    private int FPS = 60;

    //Win the game
    int winCount = 0;

    //SYSTEM

    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();

    Sound music = new Sound();
    Sound se = new Sound();

    public CollisionChecker cChecker = new CollisionChecker(this);

    public AssetSetter aSetter = new AssetSetter(this);

    public UI ui = new UI(this);
    Thread gameThread;

    //ENTITY AND OBJECT
    public Player player = new Player(this, keyH);
    public SuperObject obj[] = new SuperObject[10];



    public GamePanel()
    {
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

    }
    public void setupGame()
    {
        aSetter.setObject();
        playMusic(2);
    }

    public void startGameThread()
    {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        // DELTA METHOD
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;


        while (gameThread != null) {

            //Check system time
            currentTime = System.nanoTime();

            //How much time has passed / drawInterval (0.016)
            delta += (currentTime - lastTime) / drawInterval;
            //set the timer to the change from last time to current time
            timer += (currentTime - lastTime);

            //lastTime becomes currentTime
            lastTime = currentTime;

            //When delta = drawInterval we update and repaint , then reset delta
            if (delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }
            if (timer >= 1000000000) {
                System.out.println("FPS : " + drawCount);
                System.out.println("ActiveRect: "+player.activeRect);
                System.out.println("X: "+player.worldX/tileSize);
                System.out.println("Y: "+player.worldY/tileSize);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update(){
        player.update();
        aSetter.setNewObject();
    }

    public void paintComponent(Graphics g){

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        //TILE DRAWER
        tileM.draw(g2);
        //OBJECT DRAWER
        for(int i = 0; i < obj.length; i++)
        {
            if(obj[i] != null)
            {
                obj[i].draw(g2,this);
            }
        }
        drawRectangle(g2);
        //PLAYER DRAWER
        player.draw(g2);
        ui.draw(g2);


        g2.dispose();
    }

    public void drawRectangle(Graphics2D g2)
    {
        Rectangle drawThis = player.interactTangleUp;
        g2.setColor(Color.red);
        g2.drawRect(player.interactTangleUp.x , player.interactTangleUp.y , player.interactTangleUp.width , player.interactTangleUp.height);
        g2.drawRect(player.interactTangleDown.x , player.interactTangleDown.y , player.interactTangleDown.width , player.interactTangleDown.height);
        g2.drawRect(player.interactTangleLeft.x , player.interactTangleLeft.y , player.interactTangleLeft.width , player.interactTangleLeft.height);
        g2.drawRect(player.interactTangleRight.x , player.interactTangleRight.y , player.interactTangleRight.width , player.interactTangleRight.height);
    }
    public void playMusic(int i)
    {
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic()
    {
        music.stop();
    }

    public void playSE(int i)
    {
        se.setFile(i);
        se.play();
    }

    public void countWinPoints()
    {
        winCount++;
        if(winCount == 2)
        {
            System.out.println("YOU WON THE GAME");
        }
    }

    public Image victoryScreen()
    {
        changeScreen();

        BufferedImage img = null;
        try
        {
            img = ImageIO.read(getClass().getResourceAsStream("/tiles/winScreen.png"));
        }
        catch (IOException o)
        {
            o.printStackTrace();
        }
        Graphics2D g2 = (Graphics2D) getGraphics();

        // Draw image, victory screen
        g2.drawImage(img, 0, 0, screenWidth, screenHeight, null);


        // Draw text, "You won the game"
        g2.setFont(new Font("Arial" , Font.BOLD , 40));
        g2.setColor(Color.white);
        g2.drawString("You won the game!", 185 , 100);

        restartGame(g2);
        exitGame(g2);

        // close the graphics
        g2.dispose();

        return img;
    }

    private void changeScreen()
    {
        this.setVisible(false);
    }

    public void restartGame(Graphics2D g2)
    {
        g2.setFont(new Font("Arial" , Font.BOLD , 20));
        g2.setColor(Color.white);
        g2.drawString("Press enter to restart", 200 , 200);

        if(keyH.enterPressed)
        {
            System.out.println("Restarting game");
        }
    }

    public void exitGame(Graphics2D g2)
    {
        // Draw text, "Exit game"
        g2.setFont(new Font("Arial" , Font.BOLD , 20));
        g2.setColor(Color.white);
        g2.drawString("Press escape to exit", 200 , 250);

        // Exit the game
        if (keyH.escapePressed)
        {
            System.out.println("Exiting game");
            System.exit(0);
        }
    }

    public void endgame()
    {
        // close the old game
        gameThread = null;
        // open the victory screen
        JFrame frame = new JFrame("Endgame");
        frame.setSize(screenWidth,screenHeight);
        frame.setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        frame.add(panel);

        JButton exit = new JButton("Exit");
        panel.add(exit);
        frame.setVisible(true);
    }
}
