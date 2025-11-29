package entity;

import java.awt.image.BufferedImage;

public class Entity {

    public int x, y;
    public int speed;

    public BufferedImage upIdle, upMove_1, upMove_2;
    public BufferedImage upAttack_1, upAttack_2, upAttack_3, upAttack_4, upAttack_5, upAttack_6;

    public BufferedImage downIdle, downMove_1, downMove_2;
    public BufferedImage downAttack_1, downAttack_2, downAttack_3, downAttack_4, downAttack_5, downAttack_6;

    public BufferedImage rightIdle, rightMove_1, rightMove_2;
    public BufferedImage rightAttack_1, rightAttack_2, rightAttack_3, rightAttack_4, rightAttack_5, rightAttack_6;

    public BufferedImage leftIdle, leftMove_1, leftMove_2;
    public BufferedImage leftAttack_1, leftAttack_2, leftAttack_3, leftAttack_4, leftAttack_5, leftAttack_6;

    public String direction;

    public int spriteNum = 1;
    public int spriteCounter = 0;

    public int attackNum = 1;
    public int attackCounter = 0;
}
