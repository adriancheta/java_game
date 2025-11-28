package entity;

import java.awt.image.BufferedImage;

public class Entity {

    public int x, y;
    public int speed;

    public BufferedImage downIdle, upIdle, leftIdle, rightIdle;
    public BufferedImage downMove_1, upMove_1, leftMove_1, rightMove_1;
    public BufferedImage downMove_2, upMove_2, leftMove_2, rightMove_2;

    public BufferedImage downAttack_1, downAttack_2, downAttack_3, downAttack_4, downAttack_5, downAttack_6;
    public BufferedImage upAttack_1, upAttack_2, upAttack_3, upAttack_4, upAttack_5, upAttack_6;
    public BufferedImage leftAttack_1, leftAttack_2, leftAttack_3, leftAttack_4, leftAttack_5, leftAttack_6;
    public BufferedImage rightAttack_1, rightAttack_2, rightAttack_3, rightAttack_4, rightAttack_5, rightAttack_6;

    public String direction;

    public int spriteNum = 1;
    public int spriteCounter = 0;

    public int attackNum = 1;
    public int attackCounter = 0;
}
