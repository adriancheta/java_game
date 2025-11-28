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

    public Player(GamePanel gp, PlayerInput input, int playerNumber) {

        this.gp = gp;
        this.input = input;
        this.playerNumber = playerNumber;

        setDefaultValues();
        createPlayerImage();
    }

    public void setDefaultValues() {

        x = 100;
        y = 100;
        speed = 4;
        direction = "down";
    }

    public void createPlayerImage() {
        if (playerNumber == 1) {
            try {
                //DOWN
                downIdle = ImageIO.read(Objects.requireNonNull(getClass().getResource("/character1Down.png"),
                        "Missing /character1Down.png"));
                downMove_1 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/character1DownMove_1.png"),
                        "Missing /character1DownMove_1.png"));
                downMove_2 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/character1DownMove_2.png"),
                        "Missing /character1DownMove_2.png"));

                //UP
                upIdle = ImageIO.read(Objects.requireNonNull(getClass().getResource("/character1Up.png"),
                        "Missing /character1Up.png"));
                upMove_1 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/character1UpMove_1.png"),
                        "Missing /character1UpMove_1.png"));
                upMove_2 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/character1UpMove_2.png"),
                        "Missing /character1UpMove_2.png"));

                //RIGHT
                rightIdle = ImageIO.read(Objects.requireNonNull(getClass().getResource("/character1Right.png"),
                        "Missing /character1Right.png"));
                rightMove_1 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/character1RightMove_1.png"),
                        "Missing /character1RightMove_1.png"));
                rightMove_2 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/character1RightMove_2.png"),
                        "Missing /character1RightMove_2.png"));

                //LEFT
                leftIdle = ImageIO.read(Objects.requireNonNull(getClass().getResource("/character1Left.png"),
                        "Missing /character1Left.png"));
                leftMove_1 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/character1LeftMove_1.png"),
                        "Missing /character1LeftMove_1.png"));
                leftMove_2 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/character1LeftMove_2.png"),
                        "Missing /character1LeftMove_2.png"));

                //ATTACK
                downAttack_1 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/character1DownAttack_1.png"),
                        "Missing /character1DownAttack_1.png"));
                downAttack_2 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/character1DownAttack_2.png"),
                        "Missing /character1DownAttack_2.png"));
                downAttack_3 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/character1DownAttack_3.png"),
                        "Missing /character1DownAttack_3.png"));
                downAttack_4 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/character1DownAttack_4.png"),
                        "Missing /character1DownAttack_4.png"));
                downAttack_5 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/character1DownAttack_5.png"),
                        "Missing /character1DownAttack_5.png"));
                downAttack_6 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/character1DownAttack_6.png"),
                        "Missing /character1DownAttack_6.png"));

                //TODO: character1 Doesn't have attack animations. You have sprites downloaded for this. Should be 2 images
            } catch (Exception e) { // IOException isn’t enough—null gives IllegalArgumentException
                e.printStackTrace();
            }
        } else {
            try {
                //DOWN
                downIdle = ImageIO.read(Objects.requireNonNull(getClass().getResource("/character2Down.png"),
                        "Missing /character2Down.png"));

                downMove_1 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/character2DownMove_1.png"),
                        "Missing /character2DownMove_1.png"));

                downMove_2 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/character2DownMove_2.png"),
                        "Missing /character2DownMove_2.png"));

                //UP
                upIdle = ImageIO.read(Objects.requireNonNull(getClass().getResource("/character2Up.png"),
                        "Missing /character2Up.png"));

                upMove_1 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/character2UpMove_1.png"),
                        "Missing /character2UpMove_1.png"));

                upMove_2 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/character2UpMove_2.png"),
                        "Missing /character2UpMove_2.png"));

                //RIGHT
                rightIdle = ImageIO.read(Objects.requireNonNull(getClass().getResource("/character2Right.png"),
                        "Missing /character2Right.png"));

                rightMove_1 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/character2RightMove_1.png"),
                        "Missing /character2RightMove_1.png"));

                rightMove_2 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/character2RightMove_2.png"),
                        "Missing /character2RightMove_2.png"));

                //LEFT
                leftIdle = ImageIO.read(Objects.requireNonNull(getClass().getResource("/character2Left.png"),
                        "Missing /character2Left.png"));

                leftMove_1 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/character2LeftMove_1.png"),
                        "Missing /character2LeftMove_1.png"));

                leftMove_2 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/character2LeftMove_2.png"),
                        "Missing /character2LeftMove_2.png"));

                //ATTACK


                //TODO: character2 Doesn't have attack animations. You have sprites downloaded for this. Should be 2 images
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void update () {

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

        if (input.isAttackPressed()) {
            if (direction.equals("down")) {

            }
        }

        else spriteNum = 1;
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
                break;

            case "downAttack":
                if (attackNum == 1) {
                    image = downAttack_1;
                }
                if (attackNum == 2) {
                    image = downAttack_2;
                }
                break;

            default:
                break;
        }
        g2.drawImage(image, x, y, gp.tileSize * 3 / 2, gp.tileSize * 3 / 2, null);
    }
}
