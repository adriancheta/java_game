package entity;

import java.awt.image.BufferedImage;

public class Entity {

    public int x, y;
    public int speed;

    public BufferedImage downIdle, upIdle, leftIdle, rightIdle;
    public BufferedImage downMove_1, upMove_1, leftMove_1, rightMove_1;
    public BufferedImage downMove_2, upMove_2, leftMove_2, rightMove_2;
    public String direction;

    public int spriteNum = 1;
    public int spriteCounter = 0;
}
