package main;

import world.GroundManager;
import GUI.GameConfig;
import GUI.GameMode;
import entity.*;
import input.CombinedInput;
import input.PlayerInput;
import input.GamepadHandler;
import input.KeyHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable {

    public GroundManager groundM;

    final int originalTileSize = 16;
    final int scale = 4;

    public BufferedImage dash_1, dash_2, dash_3, dash_4, dash_5, dash_6;

    public final int tileSize = originalTileSize * scale;

    public int maxScreenCol = 16;
    public int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol;  //1024 pixels
    final int screenHeight = tileSize * maxScreenRow;  //768 pixels

    int FPS = 60;

    Thread gameThread;

    private GameConfig config;

    private Player player1;
    private Player player2;

    KeyHandler keyH = new KeyHandler();
    GamepadHandler padH = new GamepadHandler();
    PlayerInput input = new CombinedInput(keyH, padH);

    public java.util.List<Player> players = new ArrayList<>();

    public GamePanel(GameConfig config) {

        this.config = config;
        SpriteLoader.loadDashSprites(this);

        groundM = new GroundManager(this);

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.white);

        this.addKeyListener(keyH);
        this.setFocusable(true);
        requestFocusInWindow();
        this.setDoubleBuffered(true);

        if (config.mode == GameMode.SINGLE_PLAYER) {  //SINGLE_PLAYER
            PlayerInput inputSingle = new CombinedInput(keyH, padH);
            player1 = new Player(this, inputSingle, 1);
            players.add(player1);
        } else {                                      //LOCAL_COOP
            player1 = new Player(this, keyH, 1);
            player2 = new Player(this, padH, 2);
            players.add(player1);
            players.add(player2);
        }
    }

    public void startGameThread() {

        gameThread = new Thread(this);
        gameThread.start();
    }

    public void run() {

        double drawInterval = 1000000000 / FPS;  // How many times it draws the screen/second
        double nextDrawTime = System.nanoTime() + drawInterval;  // Current system time + drawInterval

        while (gameThread != null) {

            update();
            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();  // How much time remaining until the next draw time
                remainingTime /= 1000000;                                 // Conversion to milliseconds needed

                if (remainingTime < 0) {
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;

            }  catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update() {

        padH.update(); // Poll gamepad

        player1.update();
        if (player2 != null) {
            player2.update();
        }

        checkPlayerHits();
        checkDash();

        for (dashAnimation fx : dashEffects) {
            fx.frameCounter++;
            if (fx.frameCounter >= 5) { // Change frame every 5 ticks
                fx.frameCounter = 0;
                fx.frame++;

                if (fx.frame > 6) {
                    fx.finished = true;
                }
            }
        }

        dashEffects.removeIf(fx -> fx.finished);
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        groundM.draw(g2); // DRAW BACKGROUND FIRST

        for (dashAnimation fx : dashEffects) {
            BufferedImage img = null;
            switch (fx.frame) {
                case 1 -> img = dash_1;
                case 2 -> img = dash_2;
                case 3 -> img = dash_3;
                case 4 -> img = dash_4;
                case 5 -> img = dash_5;
                case 6 -> img = dash_6;
            }
            if (img != null) {
                g2.drawImage(img, fx.x, fx.y, tileSize, tileSize, null);
            }
        }

        player1.draw(g2);
        if (player2 != null) {
            player2.draw(g2);
        }

        g2.dispose();
    }

    @Override
    public void addNotify() {
        super.addNotify();
        requestFocusInWindow();
    }

    private void checkPlayerHits() {
        for (Player attacker : players) {
            if (!attacker.attacking || !attacker.attackHitboxActive) {
                continue;
            }

            for (Player target : players) {
                if (target == attacker) continue;
                if (target.health <= 0) continue;

                if (attacker.attackHitbox.intersects(target.bodyHitbox)) {
                    target.takeDamage(10);
                }
            }
        }
    }

    private void checkDash() {
        for (Player player : players) {

            if (player.attacking || player.attackHitboxActive) {
                continue;
            }
            if (player.dashCooldownFrames > 0 || !player.input.isDashPressed()) {
                continue;
            }

            player.dash();
            player.dashCooldownFrames = 60 * 3;

            dashEffects.add(new dashAnimation(player.x, player.y));
        }
    }

    private java.util.List<dashAnimation> dashEffects = new ArrayList<>();

    private static class dashAnimation {
        int x, y;
        int frame = 1;
        int frameCounter = 0;
        boolean finished = false;

        dashAnimation(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}