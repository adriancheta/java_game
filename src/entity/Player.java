package entity;

import main.GamePanel;
import input.PlayerInput;

import java.util.ArrayList;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends PlayerActions {

    GamePanel gp;
    public PlayerInput input;
    int playerNumber;
    public boolean attacking = false;
    public ArrayList<Player> playersHitInCurrentAttack = new ArrayList<>();

    public Player(GamePanel gp, PlayerInput input, int playerNumber) {

        this.gp = gp;
        this.input = input;
        this.playerNumber = playerNumber;

        setDefaultValues();
        SpriteLoader.loadPlayerSprites(this, playerNumber);
    }

    public void setDefaultValues() {

        maxHealth = 100;
        health = maxHealth;
        speed = 4;

        if (playerNumber == 1) {
            x = 100;
            y = 100;
        } else {
            x = 200;
            y = 200;
        }
        direction = "down";

        bodyHitbox = new Rectangle(0, 0, 28, 40);
        bodyHitboxOffsetX = 10;
        bodyHitboxOffsetY = 5;

        attackHitbox = new Rectangle(0, 0, 40, gp.tileSize * 2 / 3);
        attackHitboxActive = false;

        attacking = false;
        attackNum = 1;
        attackCounter = 0;

        spriteNum = 1;
        spriteCounter = 0;
    }

    public void update () {

        if (health > 0) {  // If player is alive, perform actions

            if (dashCooldownFrames > 0) {
                dashCooldownFrames--;
            }

            if (!attacking && input.isAttackPressed()) { // Perform normal actions
                attacking = true;
                attackNum = 1;
                attackCounter = 0;
                attackHitboxActive = false;

                resetPlayersHitInCurrentAttack();
            }

            if (attacking) {
                attackCounter++;

                if (attackCounter == 7) {
                    attackCounter = 0;
                    attackNum++;

                    if (attackNum > 6) {
                        attackNum = 1;
                        spriteNum = 1;
                        attacking = false;
                        attackHitboxActive = false;
                    }
                }
            } else {
                attackNum = 1;
            }

            if (attacking && attackNum >= 2 && attackNum <= 4) {
                attackHitboxActive = true;

                switch (direction) {
                    case "up":
                        attackHitbox.x = x + gp.tileSize / 4;
                        attackHitbox.y = y - attackHitbox.height;
                        break;
                    case "down":
                        attackHitbox.x = x + gp.tileSize / 4;
                        attackHitbox.y = y + gp.tileSize;
                        break;
                    case "left":
                        attackHitbox.x = x - attackHitbox.width;
                        attackHitbox.y = y + gp.tileSize / 4;
                        break;
                    case "right":
                        attackHitbox.x = x + gp.tileSize;
                        attackHitbox.y = y + gp.tileSize / 4;
                        break;
                }
            } else {
                attackHitboxActive = false;
            }

            if (!input.isAttackPressed() && !attacking) {
                if (input.isUpPressed() || input.isDownPressed() || input.isLeftPressed() || input.isRightPressed()) {

                    int nextX = x;
                    int nextY = y;

                    if (input.isUpPressed()) {
                        direction = "up";
                        nextY -= speed;
                    } else if (input.isDownPressed()) {
                        direction = "down";
                        nextY += speed;
                    } else if (input.isLeftPressed()) {
                        direction = "left";
                        nextX -= speed;
                    } else if (input.isRightPressed()) {
                        direction = "right";
                        nextX += speed;
                    }

                    int nextHitboxX = nextX + bodyHitboxOffsetX;
                    int nextHitboxY = nextY + bodyHitboxOffsetY;

                    boolean collision = gp.groundM.isCollision(nextHitboxX, nextHitboxY, bodyHitbox.width, bodyHitbox.height);

                    if (!collision) {
                        x = nextX;
                        y = nextY;
                    }

                    spriteCounter++;

                    if (spriteCounter == 10) {
                        spriteNum++;

                        if (spriteNum > 3) {
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
            attackHitboxActive = false;
        }
    }

    public void resetPlayersHitInCurrentAttack() {
        playersHitInCurrentAttack.clear();
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