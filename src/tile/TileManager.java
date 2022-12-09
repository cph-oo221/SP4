package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager
{
    GamePanel gp;
    public Tile[] tiles;
    public int mapTileNum[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;

        tiles = new Tile[10];

        mapTileNum = new int [gp.maxWorldCol] [gp.maxWorldRow];

        getTileImage();

        loadMap("/maps/WorldMap1.txt");
    }

    public void getTileImage() {
        try {
            tiles[0] = new Tile();
            tiles[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Dungeon Floor.png"));

            tiles[1] = new Tile();
            tiles[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Dungeon Floor 2.png"));

            tiles[2] = new Tile();
            tiles[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Dungeon Wall.png"));


            tiles[3] = new Tile();
            tiles[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Dungeon Door.png"));

            tiles[4] = new Tile();
            tiles[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Dirt.png"));

            tiles[5] = new Tile();
            tiles[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Sand.png"));

            tiles[6] = new Tile();
            tiles[6].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Tree.png"));
            tiles[6].collision = true;

            tiles[7] = new Tile();
            tiles[7].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Grass.png"));

            tiles[8] = new Tile();
            tiles[8].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Water.png"));
            tiles[8].collision = true;

            tiles[9] = new Tile();
            tiles[9].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Path.png"));


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath)
    {
        try
        {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col < gp.maxWorldCol && row < gp.maxWorldRow)
            {
                String line = br.readLine();

                while(col < gp.maxWorldCol)
                {
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gp.maxWorldCol)
                {
                    col = 0;
                    row++;
                }
            }
            br.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2)
    {
        //2-Dimensional Array method
        int worldCol = 0;
        int worldRow = 0;


        //while loop drawing tiles
        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow)
        {

            int tileNum = mapTileNum[worldCol][worldRow];

            //eg. world X = 0 * 16 ,, 1*16 ,, 2*16 ...
            int worldX = worldCol * gp.tileSize;
            //eg. world X = 0 * 16 ,, 1*16 ,, 2*16 ...
            int worldY = worldRow * gp.tileSize;
            //Offset camera, so player is in the middle
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            //only draw the tiles we see + 1
                 if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                    worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                    worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                    worldY - gp.tileSize < gp.player.worldY + gp.player.screenY)
            {
                g2.drawImage(tiles[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }

            worldCol++;
            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }


        /* // Home made method with for loops
        for(int i = 0; i < gp.tileSize * gp.maxScreenColumn; i+=gp.tileSize)
        {
            g2.drawImage(tiles[2].image, i, 0, gp.tileSize, gp.tileSize, null);
        }
        for(int i = 0; i < gp.tileSize * gp.maxScreenRow; i += gp.tileSize)
        {
            g2.drawImage(tiles[2].image, 0, i, gp.tileSize, gp.tileSize, null);
        }
        for(int i = 0; i < gp.tileSize * gp.maxScreenRow; i += gp.tileSize)
        {
            g2.drawImage(tiles[2].image, (gp.maxScreenColumn*gp.tileSize)-gp.tileSize, i, gp.tileSize, gp.tileSize, null);
        }
        for(int i = 0; i < gp.tileSize * gp.maxScreenColumn; i += gp.tileSize)
        {
            g2.drawImage(tiles[2].image, i, (gp.maxScreenRow*gp.tileSize)-gp.tileSize, gp.tileSize, gp.tileSize, null);
        }

        int counter = 0;
        for(int i = gp.tileSize ; i < (gp.maxScreenRow * gp.tileSize) - gp.tileSize ; i += gp.tileSize) {

            for (int j = gp.tileSize; j < ( gp.tileSize * gp.maxScreenColumn )- gp.tileSize; j += gp.tileSize) {
                if( counter % 2 == 0)
                {
                    g2.drawImage(tiles[0].image, j, i, gp.tileSize, gp.tileSize, null);
                }
                if(counter % 2 != 0)
                {
                    g2.drawImage(tiles[1].image, j , i, gp.tileSize, gp.tileSize, null);
                }
                counter++;
            }
            counter++;
        }
    }

         */

    }
}
