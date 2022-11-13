package org.example;

import java.awt.*;

public class MapGenerator {
    public boolean map[][];
    public int brickWidth;
    public int brickHeight;

    public MapGenerator(int row, int col) {
        map = new boolean[row][col];
        for(int i = 0; i < map.length; i++)
            for(int j = 0; j < map[0].length; j++)
                map[i][j] = true;

        brickWidth = 450 / col;
        brickHeight = 150 / row;
    }

    public void draw(Graphics2D g){
        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map[0].length; j++) {
                if(map[i][j]) {
                    g.setColor(Color.white);
                    g.fillRect(j * brickWidth + 20, i * brickHeight + 50, brickWidth, brickHeight);

                    g.setStroke(new BasicStroke(3));
                    g.setColor(Color.black);
                    g.drawRect(j * brickWidth + 20, i * brickHeight + 50, brickWidth, brickHeight);
                }
            }
        }
    }

    public void setBrickFlag(boolean flag, int row, int col) {
        map[row][col] = flag;
    }

}
