/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tenis;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.Random;
import static tenis.Ball.xa;
import static tenis.Ball.ya;

/**
 *
 * @author Dominik
 */
public class Goalkeeper {
    int pom_2 = 1;


    private static final int Y = 24;
    public static int WIDTH = 50;
    private static final int HEIGHT = 7;
    public static int x = 195-WIDTH/2;
    public static int xa = 0;
    private Tenis plocha;
    

    public Goalkeeper(Tenis plocha) {
        this.plocha = plocha;      
    }
    
    public int setWidth(){
        
        int pom = Tenis.lvl;
        if(pom_2 < pom){
            WIDTH+= 5;
            pom_2 = pom;
        }
          return WIDTH;  
    }
    
    
    
    public void paint(Graphics2D g) {
        g.setColor(Color.black);
        g.fillRect(x, Y, setWidth(), HEIGHT);
    }
   
    public Rectangle getBounds() {
        return new Rectangle(x, Y, setWidth(), HEIGHT);
    }
    
  
    
    void move() {
        if (x + xa < plocha.bar.getRightSideBarL()+1) {
            xa = 0;
        } 
        if (x + xa + setWidth() > plocha.bar.getLeftSideBarP()-1) {
            xa = 0;
        }

        x = x + xa;
    }

    public static int randIntSpeed() {

    // NOTE: Usually this should be a field rather than a method
    // variable so that it is not re-seeded every call.
    Random rand = new Random();
    int min = 1;
    int max = 4;
    // nextInt is normally exclusive of the top value,
    // so add 1 to make it inclusive
    int randomNum = rand.nextInt((max - min) + 1) + min;

    return randomNum;
}
    
   public int getWidth(){
        return setWidth();
   }
}
