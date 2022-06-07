package com.uialert;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Main {

    public static void main(String[] args) {

        JFrame jFrame = new JFrame("No Life No Game");
        MyJPanel myJPanel = new MyJPanel();
        jFrame.setSize(Config.WIDTH, Config.HEIGHT);
        jFrame.add(myJPanel);
        jFrame.setBackground(Color.BLACK);
        jFrame.setVisible(true);
        createRepaintTimer(jFrame);
        jFrame.addMouseMotionListener(myJPanel);
        jFrame.addMouseListener(myJPanel);
    }

    private static void createRepaintTimer(final JFrame frame) {
        final Timer timer = new Timer(10, null);
        timer.addActionListener(e -> {
            if (!frame.isVisible()) {
                timer.stop();
            } else {
                frame.repaint();
                frame.getToolkit().sync();
            }
        });
        timer.start();
    }
}

class MyJPanel extends JPanel implements MouseMotionListener, MouseListener {


    Map map = new Map((int) (Config.WIDTH /3), (int) (Config.HEIGHT /3));

    @Override
    public void paint(Graphics g) {
        map.update();
        map.draw(g);
    }


    @Override
    public void mouseDragged(MouseEvent e) {
        int blocksWIDTH = map.blocks.length;
        int blocksHEIGHT = map.blocks[0].length;
        int sizeBlockX = Config.WIDTH / blocksWIDTH;
        int sizeBlockY = Config.HEIGHT / blocksHEIGHT;

        for (int xVector = -10; xVector < 20; xVector++) {
            for (int yVector = -10; yVector < 20; yVector++) {
                int x = e.getX() / sizeBlockX + xVector;
                int y = e.getY() / sizeBlockY + yVector;
                map.blocks[x][y].live = true;
            }
            map.blocks[e.getX() / sizeBlockX][e.getY() / sizeBlockY].live = true;
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
//        int blocksWIDTH = map.blocks.length;
//        int blocksHEIGHT = map.blocks[0].length;
//        int sizeBlockX = Config.WIDTH / blocksWIDTH;
//        int sizeBlockY = Config.HEIGHT / blocksHEIGHT;
//        map.blocks[e.getX() / sizeBlockX][e.getY() / sizeBlockY].live = true;

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int blocksWIDTH = map.blocks.length;
        int blocksHEIGHT = map.blocks[0].length;
        int sizeBlockX = Config.WIDTH / blocksWIDTH;
        int sizeBlockY = Config.HEIGHT / blocksHEIGHT;
        map.blocks[e.getX() / sizeBlockX][e.getY() / sizeBlockY].live = true;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int blocksWIDTH = map.blocks.length;
        int blocksHEIGHT = map.blocks[0].length;
        int sizeBlockX = Config.WIDTH / blocksWIDTH;
        int sizeBlockY = Config.HEIGHT / blocksHEIGHT;
        map.blocks[e.getX() / sizeBlockX][e.getY() / sizeBlockY].live = true;

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}