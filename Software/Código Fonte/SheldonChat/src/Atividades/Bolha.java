/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Atividades;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.swing.JOptionPane;

/**
 *
 * @author Breno
 */
public class Bolha implements Runnable {

    GameFrame game;
    int x, y, xDirection, yDirection, xIn, yIn;
    boolean dir;

    Bolha() {
        x = 80;
        y = 540;
        xIn = x;
        yIn = y;
        xDirection = -1;
        yDirection = -1;
    }

    public void setBoolean(boolean dir) {
        this.dir = dir;
    }

    public void move() {

        x += xDirection;
        y += yDirection;

        /*if (xIn - x >= 40 & xDirection == -1 & yDirection == 1) {
         xDirection = 1;
         yDirection = 1;
         }*/
        if (xIn - x >= 150 & xDirection == -1 & yDirection == -1) {
            xDirection = 1;
            yDirection = -1;
        }
        /*if (x >= 378 & xDirection == 1 & yDirection == 1) {
         xDirection = -1;
         yDirection = 1;
         }*/
        if (xIn - x <= 10 & xDirection == 1 & yDirection == -1) {
            xDirection = -1;
            yDirection = -1;
        }

    }

    public void setXDirection(int xdir) {
        xDirection = xdir;
    }

    public void setYDirection(int ydir) {
        yDirection = ydir;
    }

    public void run() {
        try {
            while (true) {
                if (dir) {
                    move();
                    Thread.sleep(5);
                } else {
                    //x = 0;
                    //y = 0;
                            
                }
                

            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }
}
