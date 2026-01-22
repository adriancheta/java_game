package GUI;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {

    public MenuPanel(MainFrame frame) {

        setPreferredSize(new Dimension(384, 288));
        setLayout(new GridLayout(3, 1, 10, 10));

        JButton playBtn   = new JButton("Play");

        playBtn.addActionListener(e -> frame.showLogin());

        add(new JLabel("Choose mode:", SwingConstants.CENTER));
        add(playBtn);
    }
}