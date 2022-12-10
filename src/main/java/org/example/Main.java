package org.example;

import javax.swing.*;

public class Main {
    public static void main(String[] args)
    {
        JFrame obj = new JFrame();
        Gameplay gameplay = new Gameplay();
        obj.setBounds(0, 0, Constants.WIDTH, Constants.HEIGHT);
        obj.setLocationRelativeTo(null);
        obj.setTitle(Constants.TITLE);
        obj.setResizable(false);
        obj.setVisible(true);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj.add(gameplay);
    }
}