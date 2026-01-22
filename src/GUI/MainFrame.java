package GUI;

import main.GamePanel;
import user.UserManager;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private CardLayout cardLayout = new CardLayout();
    private JPanel root = new JPanel(cardLayout);

    private UserManager userManager = new UserManager();

    public MainFrame() {

        setTitle("Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        MenuPanel menu = new MenuPanel(this);
        root.add(menu, "MENU");

        add(root);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void startGame() {

        GamePanel gamePanel = new GamePanel();
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

    public void showLogin() {

        LoginPanel login = new LoginPanel(this);
        root.add(login, "LOGIN");
        cardLayout.show(root, "LOGIN");
    }
}