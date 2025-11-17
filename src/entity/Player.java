package entity;

import GUI.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class Player extends Entity {

    GamePanel gp;
    PlayerInput input;

    public Player(GamePanel gp, PlayerInput input) {

        this.gp = gp;
        this.input = input;

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
        try {
            //DOWN
            downIdle = ImageIO.read(Objects.requireNonNull(getClass().getResource("/characterDown.png"),
                    "Missing /characterDown.png"));

            downMove_1 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/characterDownMove_1.png"),
                    "Missing /characterDownMove_1.png"));

            downMove_2 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/characterDownMove_2.png"),
                    "Missing /characterDownMove_2.png"));

            //UP
            upIdle = ImageIO.read(Objects.requireNonNull(getClass().getResource("/characterUp.png"),
                    "Missing /characterUp.png"));

            upMove_1 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/characterUpMove_1.png"),
                    "Missing /characterUpMove_1.png"));

            upMove_2 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/characterUpMove_2.png"),
                    "Missing /characterUpMove_2.png"));

            //RIGHT
            rightIdle = ImageIO.read(Objects.requireNonNull(getClass().getResource("/characterRight.png"),
                    "Missing /characterRight.png"));

            rightMove_1 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/characterRightMove_1.png"),
                    "Missing /characterRightMove_1.png"));

            rightMove_2 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/characterRightMove_2.png"),
                    "Missing /characterRightMove_2.png"));

            //LEFT
            leftIdle = ImageIO.read(Objects.requireNonNull(getClass().getResource("/characterLeft.png"),
                    "Missing /characterLeft.png"));

            leftMove_1 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/characterLeftMove_1.png"),
                    "Missing /characterLeftMove_1.png"));

            leftMove_2 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/characterLeftMove_2.png"),
                    "Missing /characterLeftMove_2.png"));

            //TODO: Character Doesn't have attack animations. You have sprites downloaded for this. Should be 2 images

            //attackDown
            //attackUp
            //attackLeft
            //attackRight

        } catch (Exception e) { // IOException isn’t enough—null gives IllegalArgumentException
            e.printStackTrace();
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
            default:
                break;
        }
        g2.drawImage(image, x, y, gp.tileSize * 3 / 2, gp.tileSize * 3 / 2, null);
    }
}
