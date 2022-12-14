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
    public int mapTileNum[][][];

    public TileManager(GamePanel gp)
    {
        this.gp = gp;

        tiles = new Tile[90];

        mapTileNum = new int [gp.maxMap][gp.maxWorldCol] [gp.maxWorldRow];

        getTileImage();

        // Dungeon map: "/maps/DT.txt"
        // main hub map: "/maps/WorldMap1.txt"
        loadMap("/maps/WorldMap1.txt",0);
        loadMap("/maps/DT.txt",1);
    }

    public void getTileImage()
    {
        try
        {
            // Dungeon Floor
            tiles[0] = new Tile();
            tiles[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Dungeon Floor.png"));

            // Dungeon Floor 2
            tiles[1] = new Tile();
            tiles[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Dungeon Floor 2.png"));

            // Dungeon Wall
            tiles[2] = new Tile();
            tiles[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Dungeon Wall.png"));
            tiles[2].collision = true;

            // Dungeon Door
            tiles[3] = new Tile();
            tiles[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Dungeon Door.png"));

            // Dirt
            tiles[4] = new Tile();
            tiles[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Dirt.png"));

            // Sand
            tiles[5] = new Tile();
            tiles[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Sand.png"));

            // Tree
            tiles[6] = new Tile();
            tiles[6].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Tree.png"));
            tiles[6].collision = true;

            // Grass
            tiles[7] = new Tile();
            tiles[7].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Grass.png"));

            // DirtyWoodPlanks
            tiles[8] = new Tile();
            tiles[8].image = ImageIO.read(getClass().getResourceAsStream("/tiles/DirtyWoodPlanks.png"));

            // WoodPlanks
            tiles[9] = new Tile();
            tiles[9].image = ImageIO.read(getClass().getResourceAsStream("/tiles/woodPlanks.png"));

            // Path
            tiles[10] = new Tile();
            tiles[10].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Path.png"));

            // Water
            tiles[11] = new Tile();
            tiles[11].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Water.png"));
            tiles[11].collision = true;

            // Fire
            tiles[12] = new Tile();
            tiles[12].image = ImageIO.read(getClass().getResourceAsStream("/tiles/fire.png"));

            // Cobblestone
            tiles[13] = new Tile();
            tiles[13].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Cobblestone.png"));

            // Dungeon Door
            tiles[14] = new Tile();
            tiles[14].image = ImageIO.read(getClass().getResourceAsStream("/tiles/DungeonDoorRotateRight.png"));


        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath, int map)
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

                    mapTileNum[map][col][row] = num;
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

            int tileNum = mapTileNum[gp.currentMap][worldCol][worldRow];

            //eg. world X = 0 * 16 ,, 1*16 ,, 2*16 ...
            int worldX = worldCol * gp.tileSize;
            //eg. world X = 0 * 16 ,, 1*16 ,, 2*16 ...
            int worldY = worldRow * gp.tileSize;
            //Offset camera, so player is in the middle
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            //only draw the tiles we see + 1
            if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX
                    && worldX - gp.tileSize < gp.player.worldX + gp.player.screenX
                    && worldY + gp.tileSize > gp.player.worldY - gp.player.screenY
                    && worldY - gp.tileSize < gp.player.worldY + gp.player.screenY)

                g2.drawImage(tiles[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);

            worldCol++;
            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
        
    }
}
