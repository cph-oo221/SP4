package main;

import entity.Player;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;


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

    // can ceate 10 different maps
    public final int maxMap = 10;

    // current map number
    public int currentMap = 0;

    //FPS
    private int FPS = 60;

    //Win the game
    int winCount = 0;

    //SYSTEM

    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler(this);

    Sound music = new Sound();
    Sound se = new Sound();

    EventHandler eventH = new EventHandler(this);

    public CollisionChecker cChecker = new CollisionChecker(this);

    public AssetSetter aSetter = new AssetSetter(this);

    public UI ui = new UI(this);
    Thread gameThread;

    //ENTITY AND OBJECT
    public Player player = new Player(this, keyH);
    public SuperObject obj[][] = new SuperObject[maxMap][10];

    public int gameState;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dungeonState = 3;
    public final int winState = 4;
    public final int lossState = 5;



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
        gameState = playState;
        aSetter.setObject();
        playMusic(0);
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
                System.out.println("X: "+player.worldX/tileSize);
                System.out.println("Y: "+player.worldY/tileSize);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update(){
        if(gameState == playState)
        {
            player.update();
            aSetter.setNewObject();
            eventH.checkEvent();
            eventH.win();
            eventH.playerDeath();
        }
        if(gameState == pauseState)
        {
            //PAUSE
        }

    }

    public void paintComponent(Graphics g){

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        //TILE DRAWER
        tileM.draw(g2);
        //OBJECT DRAWER
        for(int i = 0; i < obj[currentMap].length; i++)
        {
            if(obj[currentMap][i] != null)
            {
                obj[currentMap][i].draw(g2,this);
            }
        }

        //PLAYER DRAWER
        player.draw(g2);
        ui.draw(g2);


        g2.dispose();
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

    public void objectInteraction(int index)
    {
        obj[currentMap][index].interact();

    }

    public void checkComputer()
    {

    }
}
