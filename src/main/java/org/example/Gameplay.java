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
    private MapGenerator mapGen;
    private Paddle paddle;
    private Ball ball;

    private void init() {
        play = true;
        mapGen = new MapGenerator(6, 5);
        score = 0;
        totalBricks = 30;
        delay = 8;
        paddle = new Paddle();
        ball = new Ball();
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
        g.fillRect(paddle.getX(), 700, 100, 6);

        g.setColor(Color.cyan);
        g.setFont(new Font("serif", Font.BOLD, 25));
        g.drawString(""+score, 450, 30);

        g.setColor(Color.yellow);
        g.fillOval(ball.getX(), ball.getY(), ball.getSize(), ball.getSize());

        if(totalBricks <= 0)
            Win(g);

        if(ball.getY() > 800)
            Lose(g);

        g.dispose();
    }

    private void Lose(Graphics g) {
        play = false;
        ball.setDirX(0);
        ball.setDirY(0);
        g.setColor(Color.red);
        g.setFont(new Font("serif", Font.BOLD, 35));
        g.drawString("GAME OVER", 125, 400);
        g. drawString("FINAL SCORE: " + score, 110, 450);
        g.drawString("PRESS R TO RESTART", 65, 500);
    }

    private void Win(Graphics g) {
        totalBricks = 0;
        play = false;
        ball.setDirX(0);
        ball.setDirY(0);
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

            ball.move();

            if(new Rectangle(ball.getX(), ball.getY(), 15, 15).intersects(new Rectangle(paddle.getX(), 700, 100, 6)))
                ball.setDirY(-ball.getDirY());

            arrayLoop: for(int i = 0; i < mapGen.map.length; i++) {
                for(int j = 0; j < mapGen.map[0].length; j++) {
                    if(mapGen.map[i][j]) {
                        int brickX = j * mapGen.brickWidth + 20;
                        int brickY = i * mapGen.brickHeight + 50;
                        int brickWidth = mapGen.brickWidth;
                        int brickHeight = mapGen.brickHeight;

                        Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
                        Rectangle ballRect = new Rectangle(ball.getX(), ball.getY(), 15, 15);

                        if(ballRect.intersects(rect)) {
                            mapGen.setBrickFlag(false, i, j);
                            totalBricks--;
                            score += 1;

                            if(ball.getX() + 8 <= rect.x || ball.getX() + 8 >= rect.x + rect.width)
                                ball.setDirX(-ball.getDirX());
                            else
                                ball.setDirY(-ball.getDirY());

                            break arrayLoop;
                        }
                    }
                }
            }


            if (ball.getX() < 0)
                ball.setDirX(-ball.getDirX());
            if (ball.getY() < 0)
                ball.setDirY(-ball.getDirY());
            if (ball.getX() > 470)
                ball.setDirX(-ball.getDirX());

        }
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        paddle.keyPressed(e);
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
            System.exit(0);

        if (e.getKeyCode() == KeyEvent.VK_R && !play) {
            init();
            repaint();
        }

        if (e.getKeyCode() == KeyEvent.VK_F1)
            Win(this.getGraphics());
    }


    @Override
    public void keyReleased(KeyEvent e) {}

}
