package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.*;

public class Gameplay extends JPanel implements KeyListener, ActionListener {
    private boolean play;
    private int score;
    private int totalBricks;
    private final Timer timer;
    private int delay;
    private int playerX;
    private int ballPosX;
    private int ballPosY;
    private int ballXDir;
    private int ballYDir;
    private MapGenerator mapGen;

    private void init() {
        play = false;
        mapGen = new MapGenerator(6, 5);
        score = 0;
        totalBricks = 30;
        delay = 8;
        playerX = 200;
        ballPosX = (int)(Math.random()*(400-10+1)+10);
        ballPosY = (int)(Math.random()*(670-550+1)+550);
        ballXDir = -2;
        ballYDir = -3;
    }


    public Gameplay() {
        init();
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();
    }

    public void paint(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0,0,500,800);

        mapGen.draw((Graphics2D) g);

        g.setColor(Color.cyan);
        g.fillRect(playerX, 700, 100, 6);

        g.setColor(Color.cyan);
        g.setFont(new Font("serif", Font.BOLD, 25));
        g.drawString(""+score, 450, 30);

        g.setColor(Color.yellow);
        g.fillOval(ballPosX, ballPosY, 15, 15);

        if(totalBricks <= 0)
            Win(g);

        if(ballPosY > 800)
            Lose(g);

        g.dispose();
    }

    private void Lose(Graphics g) {
        play = false;
        ballXDir = 0;
        ballYDir = 0;
        g.setColor(Color.red);
        g.setFont(new Font("serif", Font.BOLD, 35));
        g.drawString("GAME OVER", 125, 400);
        g. drawString("FINAL SCORE: " + score, 110, 450);
        g.drawString("PRESS R TO RESTART", 65, 500);
    }

    private void Win(Graphics g) {
        totalBricks = 0;
        play = false;
        ballXDir = 0;
        ballYDir = 0;
        g.setColor(Color.green);
        g.setFont(new Font("serif", Font.BOLD, 35));
        g.drawString("VICTORY", 160, 400);
        g.drawString("FINAL SCORE: " + score, 110, 450);
        g.drawString("PRESS R TO RESTART", 65, 500);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        if(play) {

            if(new Rectangle(ballPosX, ballPosY, 15, 15).intersects(new Rectangle(playerX, 700, 100, 6)))
                ballYDir = -ballYDir;

            arrayLoop: for(int i = 0; i < mapGen.map.length; i++) {
                for(int j = 0; j < mapGen.map[0].length; j++) {
                    if(mapGen.map[i][j]) {
                        int brickX = j * mapGen.brickWidth + 20;
                        int brickY = i * mapGen.brickHeight + 50;
                        int brickWidth = mapGen.brickWidth;
                        int brickHeight = mapGen.brickHeight;

                        Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
                        Rectangle ballRect = new Rectangle(ballPosX, ballPosY, 15, 15);

                        if(ballRect.intersects(rect)) {
                            mapGen.setBrickFlag(false, i, j);
                            totalBricks--;
                            score += 1;

                            if(ballPosX + 8 <= rect.x || ballPosX + 8 >= rect.x + rect.width)
                                ballXDir = -ballXDir;
                            else
                                ballYDir = -ballYDir;

                            break arrayLoop;
                        }
                    }
                }
            }

            ballPosX += ballXDir;
            ballPosY += ballYDir;

            if (ballPosX < 0)
                ballXDir = -ballXDir;
            if (ballPosY < 0)
                ballYDir = -ballYDir;
            if (ballPosX > 470)
                ballXDir = -ballXDir;

        }
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_D) {
            if(playerX >= 370)
                playerX = 370;
            else
                moveRight();
        }

        if (e.getKeyCode() == KeyEvent.VK_A) {
            if(playerX <= 10)
                playerX = 10;
            else
                moveLeft();
        }

        if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
            System.exit(0);

        if (e.getKeyCode() == KeyEvent.VK_R && !play) {
            init();
            repaint();
        }

        if (e.getKeyCode() == KeyEvent.VK_F1)
            Win(this.getGraphics());
    }

    public void moveRight() {
        play = true;
        playerX += 20;
    }

    public void moveLeft() {
        play = true;
        playerX -= 20;
    }

    @Override
    public void keyReleased(KeyEvent e) {}

}
