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
import javax.swing.JLabel;
import static tenis.Tenis.randIntDirection;
import static tenis.Tenis.stav;

/**
 *
 * @author ml
 */
// Třída pro vytvoření objektu míčku
public class Ball {
    // Konstanta - průměr míčku
    private static final int DIAMETER = 20;
    // Proměnné (atributy) určující počáteční souřadnice míčku
    public static int x = 150;
    public static int y = 265;
    // Proměnné (atributy) pro relativní posun míčku v ose x a y
    public static int xa = 2;
    public static int ya = 0;
    // Deklarace proměnné (atributu) pro hrací plochu (třída Tenis)
    private Tenis plocha;
    // Kostruktor pro vytvoření objektu míčku
    public Ball(Tenis plocha) {
        this.plocha = plocha;
    }
    
    // Metoda zajišťující pohyb míčku
    void move() throws InterruptedException {
        // Podmínka zabrání pohybu míčku mimo levý okraj hrací plochy 
        if (x + xa < plocha.goal.getLeftSide()) {
            xa = 2;
        }
        // Podmínka zabrání pohybu míčku mimo pravý okraj hrací plochy 
        if (x + xa > plocha.goal.getRightSide()) {
            xa = -2;
        }
        if (hitBarL()) {
            ya = 1;
        }
        if (hitBarP()) {
            ya =1;
             
        }
        // V případě, že míček dosáhne dolního okraje hrací plochy, hra bude ukončena 
        if (y + ya > plocha.getHeight() - 70  - DIAMETER) {
             plocha.gameOver();
        }
        
        if (save()) {
            ya = 1;
            
        }
        
        if (goal()) {
            if(Tenis.stav < 5){
            Tenis.stav++;
            Tenis.score.setText("Počet gólů: "+Tenis.stav);
            }
            else if(Tenis.stav == 5){
               Tenis.stav = 0;
               Tenis.score.setText("Počet gólů: "+Tenis.stav);
            }
            plocha.gameOver();
            
        }
        // V ostatních případech dochází ke změně souřadnic objektu míčku
        // podle relativních posunů v ose x a y
        x = x + xa;
        y = y + ya;
    }

    // Metoda detekující kolize pálky a míčku
    
    private boolean goal() {
        // zjistí průnik objektu rakety s objektem míčku
        return plocha.goal.getBounds().intersects(getBounds());
    }
    
    private boolean save() {
        // zjistí průnik objektu rakety s objektem míčku
        return plocha.goalkeeper.getBounds().intersects(getBounds());
    }
    
    private boolean hitBarL() {
        // zjistí průnik objektu rakety s objektem míčku
        return plocha.bar.getBoundsL().intersects(getBounds());
    }
    
    private boolean hitBarP() {
       
        return plocha.bar.getBoundsP().intersects(getBounds());
    }

    // Metoda vykreslující míček (jako vyplněný kruh)
    public void paint(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.fillOval(x, y, DIAMETER, DIAMETER);
    }

    // Metoda vrací hranici míčku
    public Rectangle getBounds() {
        return new Rectangle(x, y, DIAMETER, DIAMETER);
    }
    
}