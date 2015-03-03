/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tenis;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author Dominik
 */
public class Bar {
    private static final int y = 0;
    static final int WIDTH = 5;
    private static final int HEIGHT = 27;
    // x - umístění rakety v ose x
    int xL;
    int xP;
    private Tenis plocha;

    
    public Bar(Tenis plocha,int a, int b) {
        this.plocha = plocha;
        xL = a;
        xP = b;
        int sirka = WIDTH;
    }
    
     public void paint(Graphics2D g) {
        g.setColor(Color.white);
        g.fillRect(xL, y, WIDTH, HEIGHT);
        g.fillRect(xP, y, WIDTH, HEIGHT);
        
     }
     
    
    public Rectangle getBoundsL() {
        return new Rectangle(xL, y, WIDTH, HEIGHT);
    }
    
    public Rectangle getBoundsP() {
        return new Rectangle(xP, y, WIDTH, HEIGHT);
    }


    
    public int getTopY() {
        return -y;
    }
    
    public int getRightSideBarL(){
        return xL+WIDTH;
    }
    
    public int getLeftSideBarP(){
        return xP;
    }
    
    
    
}
