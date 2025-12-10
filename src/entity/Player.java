package entity;

import main.GamePanel;
import input.PlayerInput;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends PlayerActions {

    GamePanel gp;
    PlayerInput input;
    int playerNumber;
    public boolean attacking = false;

    public Player(GamePanel gp, PlayerInput input, int playerNumber) {

        this.gp = gp;
        this.input = input;
        this.playerNumber = playerNumber;

        setDefaultValues();
        SpriteLoader.loadPlayerSprites(this, playerNumber);
    }

    public void setDefaultValues() {

        health = 100;
        speed = 4;

        if (playerNumber == 1) {
            x = 100;
            y = 100;
        } else {
            x = 200;
            y = 200;
        }
        direction = "down";

        bodyHitbox = new Rectangle(x, y,28,40);
        bodyHitboxOffsetX = 10;
        bodyHitboxOffsetY = 5;
        HitboxMap.insertPlayerHitbox(this, bodyHitbox);

        attacking = false;
        attackNum = 1;
        attackCounter = 0;

        spriteNum = 1;
        spriteCounter = 0;
    }

    public void update () {

        if (health > 0) {  // If player is alive, perform actions

            if (!attacking && input.isAttackPressed()) { // Perform normal actions
                attacking = true;
                attackNum = 1;
                attackCounter = 0;
            }

            if (attacking) { // Player is attacking

                attackCounter++;
                if (attackCounter == 7) {
                    attackNum++;

                    if (attackNum == 2) { // create the hitbox in attackNum 2
                        switch (direction) {
                            case "up":
                                Rectangle attackBox = new Rectangle(x+4, y+4, 40, gp.tileSize*2/3); // Attack box for up attack aprox (change later)
                                break;

                            case "down":
                                Rectangle attackBox = new Rectangle(x-, y+4, 40, gp.tileSize*2/3);
                                break;

                            case "left":
                                break;

                            case "right":
                                attackHitbox.x = x + gp.tileSize;
                                attackHitbox.y = y + gp.tileSize / 2;
                                break;

                            default:
                                break;
                        }
                    }  else if (attackNum == 5) {
                        attackBox = null;
                    }
                    // delete the hitbox in attackNum 5

                    if (attackNum == 6) {
                        attackNum = 1;
                        spriteNum = 1;
                        attacking = false;
                    }

                    attackCounter = 0;
                }
            } else {
                attackNum = 1;
            }

            if (!input.isAttackPressed() && !attacking) {
                if (input.isUpPressed() || input.isDownPressed() || input.isLeftPressed() || input.isRightPressed()) {
                    if (input.isUpPressed()) {
                        direction = "up";
                        y -= speed;
                    } else if (input.isDownPressed()) {
                        direction = "down";
                        y += speed;
                    } else if (input.isLeftPressed()) {
                        direction = "left";
                        x -= speed;
                    } else if (input.isRightPressed()) {
                        direction = "right";
                        x += speed;
                    }

                    spriteCounter++;

                    if (spriteCounter == 10) {
                        spriteNum++;

                        if (spriteNum == 3) {
                            spriteNum = 1;
                        }

                        spriteCounter = 0;
                    }
                } else {
                    spriteNum = 1;
                }
            }
            bodyHitbox.x = x + bodyHitboxOffsetX;
            bodyHitbox.y = y + bodyHitboxOffsetY;

        } else {
            HitboxMap.removePlayerHitbox(this);
        }
    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;

        switch (direction) {

            case "up":
                if (spriteNum == 1) {
                    image = upIdle;
                }
                if (spriteNum == 2) {
                    image = upMove_1;
                }
                if (spriteNum == 3) {
                    image = upMove_2;
                }
                if (attacking) {
                    if (attackNum == 1) {
                        image = upAttack_1;
                    }
                    if (attackNum == 2) {
                        image = upAttack_2;
                    }
                    if (attackNum == 3) {
                        image = upAttack_3;
                    }
                    if (attackNum == 4) {
                        image = upAttack_4;
                    }
                    if (attackNum == 5) {
                        image = upAttack_5;
                    }
                    if (attackNum == 6) {
                        image = upAttack_6;
                    }
                }
                break;

            case "down":
                if (spriteNum == 1) {
                    image = downIdle;
                }
                if (spriteNum == 2) {
                    image = downMove_1;
                }
                if (spriteNum == 3) {
                    image = downMove_2;
                }
                if (attacking) {
                    if (attackNum == 1) {
                        image = downAttack_1;
                    }
                    if (attackNum == 2) {
                        image = downAttack_2;
                    }
                    if (attackNum == 3) {
                        image = downAttack_3;
                    }
                    if (attackNum == 4) {
                        image = downAttack_4;
                    }
                    if (attackNum == 5) {
                        image = downAttack_5;
                    }
                    if (attackNum == 6) {
                        image = downAttack_6;
                    }
                }
                break;

            case "left":
                if (spriteNum == 1) {
                    image = leftIdle;
                }
                if (spriteNum == 2) {
                    image = leftMove_1;
                }
                if (spriteNum == 3) {
                    image = leftMove_2;
                }
                if (attacking) {
                    if (attackNum == 1) {
                        image = leftAttack_1;
                    }
                    if (attackNum == 2) {
                        image = leftAttack_2;
                    }
                    if (attackNum == 3) {
                        image = leftAttack_3;
                    }
                    if (attackNum == 4) {
                        image = leftAttack_4;
                    }
                    if (attackNum == 5) {
                        image = leftAttack_5;
                    }
                    if (attackNum == 6) {
                        image = leftAttack_6;
                    }
                }
                break;

            case "right":
                if (spriteNum == 1) {
                    image = rightIdle;
                }
                if (spriteNum == 2) {
                    image = rightMove_1;
                }
                if (spriteNum == 3) {
                    image = rightMove_2;
                }
                if (attacking) {
                    if (attackNum == 1) {
                        image = rightAttack_1;
                    }
                    if (attackNum == 2) {
                        image = rightAttack_2;
                    }
                    if (attackNum == 3) {
                        image = rightAttack_3;
                    }
                    if (attackNum == 4) {
                        image = rightAttack_4;
                    }
                    if (attackNum == 5) {
                        image = rightAttack_5;
                    }
                    if (attackNum == 6) {
                        image = rightAttack_6;
                    }
                }
                break;

            default:
                break;
            }
        g2.drawImage(image, x, y, gp.tileSize * 3 / 2, gp.tileSize * 3 / 2, null);
    }
}
