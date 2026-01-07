package world;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class GroundManager {

    private GamePanel gp;

    private BufferedImage groundSheet;
    private BufferedImage[] groundTiles;

    private int groundTileOriginalSize;
    private int sheetColumns;
    private int sheetRows;
    private int groundTileCount;

    private int floorTileId;
    private int wallTileId;

    private int[][] groundMap;

    public GroundManager(GamePanel gp) {

        this.gp = gp;

        groundTileOriginalSize = 16;

        loadGroundSheet();
        sliceGroundTiles();

        floorTileId = 62;
        wallTileId = 20;

        createSimpleMap();
    }

    private void loadGroundSheet() {
        try {
            groundSheet = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Ground.png")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sliceGroundTiles() {

        sheetColumns = groundSheet.getWidth() / groundTileOriginalSize;
        sheetRows = groundSheet.getHeight() / groundTileOriginalSize;

        groundTileCount = sheetColumns * sheetRows;
        groundTiles = new BufferedImage[groundTileCount];

        int tileIndex = 0;

        for (int row = 0; row < sheetRows; row++) {
            for (int col = 0; col < sheetColumns; col++) {

                int tileX = col * groundTileOriginalSize;
                int tileY = row * groundTileOriginalSize;

                groundTiles[tileIndex] = groundSheet.getSubimage(tileX, tileY, groundTileOriginalSize, groundTileOriginalSize);
                tileIndex++;
            }
        }
    }

    private void createSimpleMap() {

        int mapRows = gp.maxScreenRow;
        int mapColumns = gp.maxScreenCol;

        groundMap = new int[mapRows][mapColumns];

        for (int row = 0; row < mapRows; row++) {
            for (int col = 0; col < mapColumns; col++) {
                groundMap[row][col] = floorTileId;
            }
        }

        for (int col = 0; col < mapColumns; col++) {
            groundMap[0][col] = wallTileId;
            groundMap[mapRows - 1][col] = wallTileId;
        }

        for (int row = 0; row < mapRows; row++) {
            groundMap[row][0] = wallTileId;
            groundMap[row][mapColumns - 1] = wallTileId;
        }
    }

    public void draw(Graphics2D g2) {

        int mapRows = groundMap.length;
        int mapColumns = groundMap[0].length;

        for (int row = 0; row < mapRows; row++) {
            for (int col = 0; col < mapColumns; col++) {

                int tileId = groundMap[row][col];

                int drawX = col * gp.tileSize;
                int drawY = row * gp.tileSize;

                g2.drawImage(groundTiles[tileId], drawX, drawY, gp.tileSize, gp.tileSize, null);
            }
        }
    }

    public boolean isCollision(int hitboxX, int hitboxY, int hitboxWidth, int hitboxHeight) {

        int leftX = hitboxX;
        int rightX = hitboxX + hitboxWidth - 1;
        int topY = hitboxY;
        int bottomY = hitboxY + hitboxHeight - 1;

        return isWallAtPosition(leftX, topY)
                || isWallAtPosition(rightX, topY)
                || isWallAtPosition(leftX, bottomY)
                || isWallAtPosition(rightX, bottomY);
    }

    private boolean isWallAtPosition(int pixelX, int pixelY) {

        int col = pixelX / gp.tileSize;
        int row = pixelY / gp.tileSize;

        if (row < 0 || col < 0 || row >= groundMap.length || col >= groundMap[0].length) {
            return true;
        }

        int tileId = groundMap[row][col];

        return tileId == wallTileId;
    }
}