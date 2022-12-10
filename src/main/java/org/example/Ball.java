package org.example;

import java.awt.*;

public class Ball implements IPaintable {
    private int x, y;
    private final int size = 15;
    private final int speed;
    private int dirX, dirY;

    public Ball() {
        this.x = 400;
        this.y = 300;
        //this.y = 20;
        this.speed = 1;
        this.dirX = 2;
        this.dirY = 3;
    }

    public void move() {
        this.x = this.x + this.speed * this.dirX;
        this.y = this.y + this.speed * this.dirY;
        
        if (this.x < 0)
            setDirX(-this.dirX);
        if (this.y < 0)
            setDirY(-this.dirY);
        if (this.x > Constants.RIGHTMOST_POSITION)
            setDirX(-this.dirX);
    }

    public Rectangle getRectangle() {
        return new Rectangle(this.x, this.y, this.size, this.size);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSize() {
        return size;
    }

    public int getSpeed() {
        return speed;
    }

    public int getDirX() {
        return dirX;
    }

    public void setDirX(int dirX) {
        this.dirX = dirX;
    }

    public int getDirY() {
        return dirY;
    }

    public void setDirY(int dirY) {
        this.dirY = dirY;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.yellow);
        g.fillOval(this.x, this.y, this.size, this.size);
    }
}
