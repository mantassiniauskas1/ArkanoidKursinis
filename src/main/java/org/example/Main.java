package org.example;

import javax.swing.*;

public class Main {
    public static void main(String[] args)
    {
        JFrame obj = new JFrame();
        Gameplay gameplay = new Gameplay();
        obj.setBounds(0, 0, 500, 800);
        obj.setLocationRelativeTo(null);
        obj.setTitle("Arkanoid kursinis");
        obj.setResizable(false);
        obj.setVisible(true);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj.add(gameplay);
    }
}