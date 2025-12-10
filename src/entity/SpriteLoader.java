package entity;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.Objects;

public final class SpriteLoader {

    private SpriteLoader() {

    }

    public static BufferedImage loadSprite(String path) {
        try {
            return ImageIO.read(Objects.requireNonNull(SpriteLoader.class.getResource(path),
                    "Missing" + path));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void loadPlayerSprites(Player player, int playerNumber) {
        try {
            player.downIdle = loadSprite("/character" + playerNumber + "Down.png");
            player.downMove_1 = loadSprite("/character" + playerNumber + "DownMove_1.png");
            player.downMove_2 = loadSprite("/character" + playerNumber + "DownMove_2.png");

            //UP
            player.upIdle = loadSprite("/character" + playerNumber + "Up.png");
            player.upMove_1 = loadSprite("/character" + playerNumber + "UpMove_1.png");
            player.upMove_2 = loadSprite("/character" + playerNumber + "UpMove_2.png");

            //RIGHT
            player.rightIdle = loadSprite("/character" + playerNumber + "Right.png");
            player.rightMove_1 = loadSprite("/character" + playerNumber + "RightMove_1.png");
            player.rightMove_2 = loadSprite("/character" + playerNumber + "RightMove_2.png");

            //LEFT
            player.leftIdle = loadSprite("/character" + playerNumber + "Left.png");
            player.leftMove_1 = loadSprite("/character" + playerNumber + "LeftMove_1.png");
            player.leftMove_2 = loadSprite("/character" + playerNumber + "LeftMove_2.png");

            //ATTACK UP
            player.upAttack_1 = loadSprite("/character" + playerNumber + "UpAttack_1.png");
            player.upAttack_2 = loadSprite("/character" + playerNumber + "UpAttack_2.png");
            player.upAttack_3 = loadSprite("/character" + playerNumber + "UpAttack_3.png");
            player.upAttack_4 = loadSprite("/character" + playerNumber + "UpAttack_4.png");
            player.upAttack_5 = loadSprite("/character" + playerNumber + "UpAttack_5.png");
            player.upAttack_6 = loadSprite("/character" + playerNumber + "UpAttack_6.png");

            //ATTACK DOWN
            player.downAttack_1 = loadSprite("/character" + playerNumber + "DownAttack_1.png");
            player.downAttack_2 = loadSprite("/character" + playerNumber + "DownAttack_2.png");
            player.downAttack_3 = loadSprite("/character" + playerNumber + "DownAttack_3.png");
            player.downAttack_4 = loadSprite("/character" + playerNumber + "DownAttack_4.png");
            player.downAttack_5 = loadSprite("/character" + playerNumber + "DownAttack_5.png");
            player.downAttack_6 = loadSprite("/character" + playerNumber + "DownAttack_6.png");

            //ATTACK RIGHT
            player.rightAttack_1 = loadSprite("/character" + playerNumber + "RightAttack_1.png");
            player.rightAttack_2 = loadSprite("/character" + playerNumber + "RightAttack_2.png");
            player.rightAttack_3 = loadSprite("/character" + playerNumber + "RightAttack_3.png");
            player.rightAttack_4 = loadSprite("/character" + playerNumber + "RightAttack_4.png");
            player.rightAttack_5 = loadSprite("/character" + playerNumber + "RightAttack_5.png");
            player.rightAttack_6 = loadSprite("/character" + playerNumber + "RightAttack_6.png");

            //ATTACK LEFT
            player.leftAttack_1 = loadSprite("/character" + playerNumber + "LeftAttack_1.png");
            player.leftAttack_2 = loadSprite("/character" + playerNumber + "LeftAttack_2.png");
            player.leftAttack_3 = loadSprite("/character" + playerNumber + "LeftAttack_3.png");
            player.leftAttack_4 = loadSprite("/character" + playerNumber + "LeftAttack_4.png");
            player.leftAttack_5 = loadSprite("/character" + playerNumber + "LeftAttack_5.png");
            player.leftAttack_6 = loadSprite("/character" + playerNumber + "LeftAttack_6.png");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
