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
import java.awt.event.MouseEvent;
import static tenis.Ball.xa;
import static tenis.Ball.ya;
import static tenis.Bar.WIDTH;
import static tenis.Tenis.powerRects;

/**
 *
 * @author Dominik
 */
public class Power {

    private Tenis plocha;
    private static final int x = 10;
    private static final int y = 360;
    private static final int WIDTH = 50;
    private static final int HEIGHT = 5;
    private Color color = Color.BLACK;
    
    
    public Power(Tenis plocha) {
        this.plocha = plocha;
    }
    
    public void paint(Graphics2D g) {
        if(Tenis.stav < 5){
            if(Tenis.stav == 0){
                for(int i = 0;Tenis.powerRectsNum > i;i++){
                    g.setColor(color);
                    g.fillRect(x,y-10*i,WIDTH,HEIGHT);
                }
            }
            else if(Tenis.stav > 0 && Tenis.stav < 5)
                for(int i = 0;Tenis.powerRectsNum > i;i++){
                    for(int a = 0;Tenis.stav > a;a++){
                        g.setColor(Color.GREEN);
                        g.fillRect(x,y-10*a,WIDTH,HEIGHT);
                    }
                g.setColor(Color.RED);
                g.fillRect(x,y-10*i,WIDTH,HEIGHT);
                }
        }
    

            
    }
    public Rectangle getBounds() {
        return new Rectangle(x, y, WIDTH, HEIGHT);
    }

    public Color getColor(){
        return this.color;
    }
    
    void setColor(Color c) {
        this.color = c;    
    }
  
   

}

