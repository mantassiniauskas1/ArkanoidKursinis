package org.example;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Paddle implements IListener, IPaintable {
    private final int width;
    private final int height;
    private int x;
    private int y;
    private int distanceToMove;
    public Paddle() {
        this.width = 100;
        this.height = 6;
        this.x = 200;
        this.y = 700;
        this.distanceToMove = 20;
    }

    public Rectangle getRectangle() {
        return new Rectangle(this.x, this.y, this.width, this.height);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
                if (this.x <= Constants.LEFTMOST_POSITION)
                    this.x = Constants.LEFTMOST_POSITION;
                else
                    this.setX(this.getX() - this.distanceToMove);
                break;
            case KeyEvent.VK_D:
                if (this.x >= Constants.RIGHTMOST_POSITION - this.width)
                    this.x = Constants.RIGHTMOST_POSITION - this.width;
                else
                    this.setX(this.getX() + this.distanceToMove);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.cyan);
        g.fillRect(this.x, this.y, this.width, this.height);
    }
}
