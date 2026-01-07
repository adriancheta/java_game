package main;

import GUI.MainFrame;
import javax.swing.*;
import java.io.File;

public class Main {

    public static void main(String[] args) {

        String nativeFolderPath = new File("lib/natives").getAbsolutePath();
        System.setProperty("net.java.games.input.librarypath", nativeFolderPath);

        SwingUtilities.invokeLater(MainFrame::new);
    }
}