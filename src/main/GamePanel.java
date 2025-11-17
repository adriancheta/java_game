package main;

import entity.*;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    final int originalTileSize = 16;
    final int scale = 4;

    public final int tileSize = originalTileSize * scale;

    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
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
    Player player = new Player(this, input);

    public GamePanel(GameConfig config) {

        this.config = config;

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.white);

        this.addKeyListener(keyH);
        this.setFocusable(true);
        requestFocusInWindow();
        this.setDoubleBuffered(true);

        if (config.mode == GameMode.SINGLE_PLAYER) {
            player1 = new Player(this, keyH);
        } else {
            player1 = new Player(this, keyH);
            player2 = new Player(this, padH);
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

        padH.update();       // poll gamepad once per frame

        player1.update();
        if (player2 != null) {
            player2.update();
        }
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

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
}
