/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tenis;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

/**
 *
 * @author ml
 */
// Třída pro vytvoření objektu rakety
public class Racquet {
    // Konstanty - umístění rakety na ploše (Y), šířka a výška
    private static final int Y = 330;
    private static final int WIDTH = 60;
    private static final int HEIGHT = 10;
    // x - umístění rakety v ose x
    int x = 140;
    // xa - relativní posun rakety v ose x
    int xa = 0;
    // deklarace proměnné pro hrací plochu
    private Tenis plocha;

    // konstruktor, který vytváří objekt rakety na hrací ploše
    public Racquet(Tenis plocha) {
        this.plocha = plocha;
    }

    // Metoda pro pohyb rakety v ose x
    public void move() {
        // podmínka omezuje pohyb rakety v hrací ploše
        if (x + xa > 0 && x + xa < plocha.getWidth() - WIDTH) {
            // posun umístění rakety v ose x o relativní hodnotu xa
            x = x + xa;
        }
    }

    // Metoda pro vykreslení rakety v podobě obdélníku
    public void paint(Graphics2D g) {
        g.fillRect(x, Y, WIDTH, HEIGHT);
    }

    // Metoda ošetřující událost uvolnění libovolné klávesy
    public void keyReleased(KeyEvent e) {
        // relativní posun se vynuluje
        xa = 0;
    }

    // Metoda ošetřuje událost stisknuté klávesy
    public void keyPressed(KeyEvent e) {
        // Z události e je metodou getKeyCode() zjištěn kód klávesy
        // Po porovnání s klávesovými konstantami (KeyEvent.VK_LEFT)
        // dojde ke změně relativního posunu xa v kladném, nebo záporném směru
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            xa = -1;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            xa = 1;
        }
    }

    // Metoda vrací hranici rakety
    public Rectangle getBounds() {
        return new Rectangle(x, Y, WIDTH, HEIGHT);
    }

    // Metoda vrací hodnotu horní hrany objektu rakety
    public int getTopY() {
        return Y;
    }
}
