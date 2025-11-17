package main;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {

    public MenuPanel(MainFrame frame) {

        setPreferredSize(new Dimension(1024, 768));
        setLayout(new GridLayout(3, 1, 10, 10));

        JButton singleBtn = new JButton("Single Player");
        JButton coopBtn   = new JButton("Local Co-op (2 players)");

        singleBtn.addActionListener(e -> frame.showLogin(GameMode.SINGLE_PLAYER));
        coopBtn.addActionListener(e -> frame.showLogin(GameMode.LOCAL_COOP));

        add(new JLabel("Choose mode:", SwingConstants.CENTER));
        add(singleBtn);
        add(coopBtn);
    }
}