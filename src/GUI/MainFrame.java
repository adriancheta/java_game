package GUI;

import user.UserManager;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private CardLayout cardLayout = new CardLayout();
    private JPanel root = new JPanel(cardLayout);

    private UserManager userManager = new UserManager();

    public MainFrame() {

        setTitle("Yeah");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        MenuPanel menu = new MenuPanel(this);
        root.add(menu, "MENU");

        add(root);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void startGame(GameConfig config) {

        GamePanel gamePanel = new GamePanel(config);
        root.add(gamePanel, "GAME");
        cardLayout.show(root, "GAME");

        gamePanel.setFocusable(true);
        gamePanel.requestFocusInWindow();

        SwingUtilities.invokeLater(gamePanel::requestFocusInWindow);

        gamePanel.startGameThread();
        pack();
    }

    public UserManager getUserManager() {

        return userManager;
    }

    public void showLogin(GameMode mode) {

        LoginPanel login = new LoginPanel(this, mode);
        root.add(login, "LOGIN");
        cardLayout.show(root, "LOGIN");
    }
}