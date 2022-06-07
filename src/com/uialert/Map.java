package com.uialert;

import java.awt.*;

public class Map {

    Block[][] blocks;

    public Map(int x, int y) {
        blocks = new Block[x][y];
        for (int xI = 0; xI < x; xI++) {
            for (int yI = 0; yI < y; yI++) {
                blocks[xI][yI] = new Block();

            }
        }
    }

    void update() {
        for (int x = 0; x < blocks.length; x++) {
            for (int y = 0; y < blocks[0].length; y++) {
                int countLifeBlock = countBlock(x, y);
                blocks[x][y].countAroundLife = countLifeBlock;
                if (countLifeBlock <= 1) {
                    blocks[x][y].live = false;
                } else if (countLifeBlock < 3) {

                } else if (countLifeBlock == 3) {
                    blocks[x][y].live = true;
                } else {
                    blocks[x][y].live = false;
                }
            }
        }
    }

    int countBlock(int xCenter, int yCenter) {
        int count = 0;
        for (int xVector = -1; xVector < 2; xVector++) {
            for (int yVector = -1; yVector < 2; yVector++) {
                int x = xCenter + xVector;
                int y = yCenter + yVector;
                if (x < 0 || blocks.length - 1 < x) {
                    continue;
                }
                if (y < 0 || blocks[0].length - 1 < y) {
                    continue;
                }
                Block b = blocks[x][y];
                if (b.live) {
                    count++;
                }
            }
        }
        return count;
    }

    void draw(Graphics g) {
        int blocksWIDTH = blocks.length;
        int blocksHEIGHT = blocks[0].length;
        int sizeBlockX = Config.WIDTH / blocksWIDTH;
        int sizeBlockY = Config.HEIGHT / blocksHEIGHT;
        for (int x = 0; x < blocksWIDTH; x++) {
            for (int y = 0; y < blocksHEIGHT; y++) {
                Block b = blocks[x][y];
                if (b.live) {

                    int color = 0xff000000
                            | (int) (0) << 16
                            | (int) ((255 / b.countAroundLife) *2) << 8
                            | (int) (0);

                    g.setColor(new Color(color));
                } else {
                    g.setColor(Color.BLACK);
                }

                g.fillRect(sizeBlockX * x, sizeBlockY * y, sizeBlockX, sizeBlockY);

            }
        }
    }
}
