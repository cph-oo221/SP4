package main;

import entity.Monster;
import entity.Player;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import object.SuperObject;


public class GamePanel extends JPanel implements Runnable{


    //Meget af koden er udsprunget af stor inspiration fra _link_

    //SCREEN SETTINGS
    Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
    public int width = (int) size.getWidth();
    public int height = (int) size.getHeight();
    private final int originalTileSize = 16; // 16*16 pixel tile
    public int scale = 3;
    public int tileSize = originalTileSize * scale; //48*48 pixel tile
    public int maxScreenColumn = 16;
    public int maxScreenRow = 12;
    public int screenWidth = tileSize * maxScreenColumn; // 768 pixels
    public int screenHeight = tileSize * maxScreenRow; // 576 pixels

    //World Settings
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;

    // can ceate 10 different maps
    public final int maxMap = 10;

    // current map number
    public int currentMap = 0;

    //FPS
    public int FPS = 60;

    //Win the game
    int winCount = 0;

    //SYSTEM

    public TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler(this);

    Sound music = new Sound();
    Sound se = new Sound();

    public EventHandler eventH = new EventHandler(this);

    public CollisionChecker cChecker = new CollisionChecker(this);

    public AssetSetter aSetter = new AssetSetter(this);

    public UI ui = new UI(this);
    public EventHandler eHandler = new EventHandler(this);

    Thread gameThread;

    //ENTITY AND OBJECT
    public Player player = new Player(this, keyH);
    public SuperObject obj[][] = new SuperObject[maxMap][10];
    public Monster kasseFyr = new Monster(this);

    public int gameState;
    public final int playState = 1;
    public final int pauseState = 2;
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
        System.out.println(width);
        System.out.println(height);
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
                try
                {
                    update();
                }
                catch (IOException e)
                {
                    throw new RuntimeException(e);
                }
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

    public void update() throws IOException
    {
        if(gameState == playState)
        {
            player.update();
            aSetter.setNewObject();
            eventH.checkEvent();
            eventH.win();
            eventH.playerDeath();
            if(currentMap == 1)
            {
                kasseFyr.update();
            }

        }
        if(gameState == pauseState)
        {
            //PAUSE
        }
        if(gameState == lossState)
        {
            // When gameState is lossState
            // (Q) quit or (R) restart, can now be pressed
            if (keyH.qPressed)
            {
                System.out.println("Game is shutting down...");
                System.exit(0);
            }
            if(keyH.rPressed)
            {
                // reset game
                gameRestart();
            }
        }
        if(gameState == winState)
        {
            // When gameState is lossState
            // (Q) quit or (R) restart, can now be pressed
            if (keyH.qPressed)
            {
                System.out.println("Game is shutting down...");
                System.exit(0);
            }
            if(keyH.rPressed)
            {
                // reset game
                gameRestart();
            }
        }

    }

    private void gameRestart()
    {
        // stop music that are runing
        stopMusic();

        // defaultValues set players def. values
        player.setDefaulValues();

        // setupgame
        setupGame();

        // Set win counter back to default value
        winCount = 0;

        // Set the map to 0 if player died in map 1 (Dungeon map)
        currentMap = 0;
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

        if(currentMap==1)
        {
            kasseFyr.draw(g2);
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

    public void win()
    {
        if(gameState == winState)
        {
            stopMusic();
            playMusic(6);
            ui.drawWinScreen();
        }
    }

    public void countWinPoints()
    {
        winCount++;
        if(winCount == 2)
        {
            gameState = winState;
        }
    }
}
