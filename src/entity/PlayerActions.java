package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PlayerActions extends Entity {

    public Rectangle bodyHitbox;
    public int bodyHitboxOffsetX;
    public int bodyHitboxOffsetY;

    public int dashCooldownFrames = 0;

    public Rectangle attackHitbox;
    public boolean attackHitboxActive;

    public BufferedImage upIdle, upMove_1, upMove_2;
    public BufferedImage upAttack_1, upAttack_2, upAttack_3, upAttack_4, upAttack_5, upAttack_6;

    public BufferedImage downIdle, downMove_1, downMove_2;
    public BufferedImage downAttack_1, downAttack_2, downAttack_3, downAttack_4, downAttack_5, downAttack_6;

    public BufferedImage rightIdle, rightMove_1, rightMove_2;
    public BufferedImage rightAttack_1, rightAttack_2, rightAttack_3, rightAttack_4, rightAttack_5, rightAttack_6;

    public BufferedImage leftIdle, leftMove_1, leftMove_2;
    public BufferedImage leftAttack_1, leftAttack_2, leftAttack_3, leftAttack_4, leftAttack_5, leftAttack_6;

    public void takeDamage(int damage) {
        health -= damage;
    }

    public void dash() {
        switch(direction) {
            case "up":
                y -= 175;
                break;
            case "down":
                y += 175;
                break;
            case "left":
                x -= 175;
                break;
            case "right":
                x += 175;
                break;
            default:
                break;
        }
    }
}
