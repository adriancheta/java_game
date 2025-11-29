package entity;

import GUI.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class Player extends Entity {

    GamePanel gp;
    PlayerInput input;
    int playerNumber;
    private boolean attacking = false;

    public Player(GamePanel gp, PlayerInput input, int playerNumber) {

        this.gp = gp;
        this.input = input;
        this.playerNumber = playerNumber;

        setDefaultValues();
        SpriteLoader.loadPlayerSprites(this, playerNumber);
    }

    public void setDefaultValues() {

        x = 100;
        y = 100;
        speed = 4;
        direction = "down";

        attacking = false;
        attackNum = 1;
        attackCounter = 0;

        spriteNum = 1;
        spriteCounter = 0;
    }

    public void update () {

        if (!attacking && input.isAttackPressed()) {
            attacking = true;
            attackNum = 1;
            attackCounter = 0;
        }

        if (attacking) {
            attackCounter++;

            if (attackCounter == 7 ) {

                if (attackNum == 1) {
                    attackNum = 2;
                } else if (attackNum == 2) {
                    attackNum = 3;
                } else if (attackNum == 3) {
                    attackNum = 4;
                } else if (attackNum == 4) {
                    attackNum = 5;
                } else if (attackNum == 5) {
                    attackNum = 6;
                } else if (attackNum == 6) {
                    attackNum = 1;
                    spriteNum = 1;
                    attacking = false;
                }

                attackCounter = 0;
            }
        }

        else {
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

                if (spriteCounter == 11) {

                    if (spriteNum == 1) {
                        spriteNum = 2;
                    } else if (spriteNum == 2) {
                        spriteNum = 3;
                    } else if (spriteNum == 3) {
                        spriteNum = 1;
                    }

                    spriteCounter = 0;
                }
            }

            else {
                spriteNum = 1;
            }
        }

    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;
        switch(direction) {

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
