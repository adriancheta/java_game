package GUI;

import user.UserManager;

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {

    private final MainFrame frame;
    private final GameMode mode;

    private final JTextField p1Field = new JTextField(10);
    private final JTextField p2Field = new JTextField(10);
    private final JLabel statusLabel = new JLabel(" ");

    public LoginPanel(MainFrame frame, GameMode mode) {

        this.frame = frame;
        this.mode = mode;

        setLayout(new BorderLayout());

        this.setPreferredSize(new Dimension(300, 100));

        JPanel center = new JPanel();

        if (mode == GameMode.SINGLE_PLAYER) {
            center.setLayout(new GridLayout(2, 2, 5, 5));
            center.add(new JLabel("Player name:"));
            center.add(p1Field);

            JButton loginBtn = new JButton("Login");
            JButton registerBtn = new JButton("Register");

            loginBtn.addActionListener(e -> handleSingleLogin(false));
            registerBtn.addActionListener(e -> handleSingleLogin(true));

            center.add(loginBtn);
            center.add(registerBtn);
        } else {
            center.setLayout(new GridLayout(2, 3, 5, 5));

            center.add(new JLabel("Player 1 name:"));
            center.add(p1Field);
            JButton p1Register = new JButton("Register");
            p1Register.addActionListener(e -> handleRegister(p1Field.getText()));
            center.add(p1Register);

            center.add(new JLabel("Player 2 name:"));
            center.add(p2Field);
            JButton p2Register = new JButton("Register");
            p2Register.addActionListener(e -> handleRegister(p2Field.getText()));
            center.add(p2Register);
        }

        JButton startBtn = new JButton("Start Game");
        startBtn.addActionListener(e -> startGameIfReady());

        add(center, BorderLayout.CENTER);
        add(startBtn, BorderLayout.SOUTH);
        add(statusLabel, BorderLayout.NORTH);
    }

    private void handleSingleLogin(boolean register) {

        String name = p1Field.getText().trim();
        if (name.isEmpty()) {
            statusLabel.setText("Enter a name");
            return;
        }

        UserManager um = frame.getUserManager();

        try {
            if (register) {
                um.register(name);
                statusLabel.setText("Registered " + name);
            } else {
                if (um.login(name) == null) {
                    statusLabel.setText("User not found, register first");
                } else {
                    statusLabel.setText("Logged in as " + name);
                }
            }
        } catch (IllegalArgumentException ex) {
            statusLabel.setText(ex.getMessage());
        }
    }

    private void handleRegister(String name) {

        name = name.trim();
        if (name.isEmpty()) {
            statusLabel.setText("Enter a name");
            return;
        }

        try {
            frame.getUserManager().register(name);
            statusLabel.setText("Registered " + name);
        } catch (IllegalArgumentException ex) {
            statusLabel.setText(ex.getMessage());
        }
    }

    private void startGameIfReady() {

        String p1 = p1Field.getText().trim();
        if (p1.isEmpty()) {
            statusLabel.setText("Player 1 must log in / register");
            return;
        }

        if (mode == GameMode.SINGLE_PLAYER) {
            GameConfig config = new GameConfig(GameMode.SINGLE_PLAYER, p1, null);
            frame.startGame(config);
        } else {
            String p2 = p2Field.getText().trim();
            if (p2.isEmpty()) {
                statusLabel.setText("Player 2 must log in / register");
                return;
            }
            GameConfig config = new GameConfig(GameMode.LOCAL_COOP, p1, p2);
            frame.startGame(config);
        }
    }
}
