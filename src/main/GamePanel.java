package main;

import entity.BrickWall;
import entity.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel implements Runnable {

    //Screen Settings
    final int originalTileSize = 16;  //16 pixels (too small)
    final int scale = 4;

    public final int tileSize = originalTileSize * scale;  //actual tile size = 16 * 4

    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol;  //768 pixels
    final int screenHeight = tileSize * maxScreenRow;  //576 pixels

    int FPS = 60;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    Player player = new Player(this, keyH);
    BrickWall brick = new BrickWall(this);

    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.white);
        this.setDoubleBuffered(true);  // improves rendering, overall game performance

        this.addKeyListener(keyH);
        this.setFocusable(true);  // with this, game panel ca "focus" to recieve key inputs

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

        player.update();
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        brick.draw(g2);
        player.draw(g2);
        g2.dispose();

    }

}
