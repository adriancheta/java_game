package entity;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.Objects;

public class BrickWall extends Entity{

    GamePanel gp;

    public BrickWall (GamePanel gp) {
        this.gp = gp;
        createBrickImage();
    }

    public void createBrickImage() {
        try (InputStream is = getClass().getResourceAsStream("/brickWall.jpg")) {
            BufferedImage img = ImageIO.read(Objects.requireNonNull(is, "Missing /brickWall.jpg"));
            upIdle = img; // reuse for now
        } catch (Exception e) { // IOException isn’t enough—null gives IllegalArgumentException
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {

        BufferedImage img = upIdle;
        g2.drawImage(img, 0, 0, gp.tileSize, gp.tileSize, null);

        for (int x = 0; x < 16; x++) {
            g2.drawImage(img, x * 64, 0, gp.tileSize, gp.tileSize, null);
        }

        for (int x = 0; x < 16; x++) {
            g2.drawImage(img, x * 64, 704, gp.tileSize, gp.tileSize, null);
        }

        for (int y = 1; y < 11; y++) {
            g2.drawImage(img, 0, y * 64, gp.tileSize, gp.tileSize, null);
        }

        for (int y = 1; y < 11; y++) {
            g2.drawImage(img, 960, y * 64, gp.tileSize, gp.tileSize, null);
        }
    }
}