/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tenis;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

/**
 *
 * @author Dominik
 */
public class Goal {
    private static final int Y = 0;
    private static final int WIDTH = 250;
    private static final int HEIGHT = 5;
    // x - umístění rakety v ose x
    private static final int x = 65;
    private Tenis plocha;

    
    public Goal(Tenis plocha) {
        this.plocha = plocha;
    }
    
     public void paint(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.fillRect(x, Y, WIDTH, HEIGHT);
        
    }
     
    
    public Rectangle getBounds() {
        return new Rectangle(x, Y, WIDTH, HEIGHT);
    }

    public int getTopY() {
        return -Y;
    }
    
    public int getLeftSide(){
        return x;
    }
    
    public int getRightSide(){
        return x+WIDTH;
    }
    
}
