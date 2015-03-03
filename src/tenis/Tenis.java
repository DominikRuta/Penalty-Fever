/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tenis;

/**
 *
 * @author ml
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.util.Timer;
import static tenis.Ball.xa;
import static tenis.Ball.ya;
import static tenis.Goalkeeper.WIDTH;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
// Třída Tenis (hrací plocha) dědí vlastnosti třídy JPanel
public class Tenis extends JPanel {
    
    public static int stav = 0;
    public static int lvl = 1;
    public static int balls = 8;
    public static  JLabel score = new JLabel("Počet gólů: "+stav);
    public static  JLabel level = new JLabel("Level: "+lvl);
    public static  JLabel numBalls = new JLabel("Míčů: "+balls);
    public static ArrayList<Power> powerRects = new ArrayList<>();
    public static int powerRectsNum = 5;
    public static boolean pressedSpace = false;
    
    JLabel background=new JLabel(new ImageIcon("16.PNG"));
    
    private void createPowerRects(){
        for (int i = 0; i < powerRectsNum; i++){
          powerRects.add(new Power(this));
          powerRects.get(i).setColor(Color.RED);
        }
    }  
    
    public static int randIntDirection() {
    int min = 0;
    int max = 10;
    // NOTE: Usually this should be a field rather than a method
    // variable so that it is not re-seeded every call.
    Random rand = new Random();

    // nextInt is normally exclusive of the top value,
    // so add 1 to make it inclusive
    int randomNum = rand.nextInt((max - min) + 1) + min;

    return randomNum;
    }


    Ball ball = new Ball(this);
    // Vytvoření objektu rakety (pálky)
    
    Goal goal = new Goal(this);
    
    
    Bar bar = new Bar(this,goal.getLeftSide(),goal.getRightSide());
    
    Goalkeeper goalkeeper = new Goalkeeper(this);
    

    // Konstruktor hrací plochy
    public Tenis() {
 
        createPowerRects();
        // Přidání "posluchače" - naslouchá stisku kláves
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }
            // Ošetření akce uvolnění klávesy
            @Override
            public void keyReleased(KeyEvent e) {
            
            }
            // Ošetření akce stisku klávesy
            @Override
            public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if(!Tenis.pressedSpace){
                if(randIntDirection() == 0)
                    goalkeeper.xa = 0;    
                else if(randIntDirection()%2 == 0)
                    goalkeeper.xa = -goalkeeper.randIntSpeed();
                else if(randIntDirection()%2 != 0)
                    goalkeeper.xa = goalkeeper.randIntSpeed();
            xa = 0;
            ya = -4;
            Tenis.pressedSpace = true;
           }
        }
            }
        });
        setFocusable(true);
        
        
        setFocusable(true);
    }
    
     
   
    
    

    
    private void move() throws InterruptedException {
        ball.move();
        goalkeeper.move();
    }   

    
    @Override
    public void paint(Graphics g) {
 
        super.paint(g);
        
        Graphics2D g2d = (Graphics2D) g;
        
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        
        ball.paint(g2d);
        goal.paint(g2d);
        bar.paint(g2d);
        goalkeeper.paint(g2d);
        score.setBounds(100,300,300,100);
        level.setBounds(180,270,100,100);
        numBalls.setBounds(260,300,300,100);
        background.setBounds(-3,-5,400,400);
        add(score);
        add(level);
        add(numBalls);
        add(background);
        for (Power pow : powerRects){
           pow.paint(g2d);
        }  
    }

 
    public void gameOver() throws InterruptedException {
            if(balls == 0){
                JOptionPane.showMessageDialog(this,"Game Over", "Game Over", JOptionPane.YES_NO_OPTION);
                System.exit(ABORT);
            }    
            else{
                Ball.x = 150;
                Ball.y = 265;
                Ball.ya = 0;
                Ball.xa = 3;
                Thread.sleep(500);
                pressedSpace = false;
                Goalkeeper.x = 195-Goalkeeper.WIDTH/2;
                Goalkeeper.xa = 0;
                balls--;
                Tenis.numBalls.setText("Míčů: "+Tenis.balls);
                if(stav == 5){
                    Tenis.stav = 0;
                    lvl++;
                    Tenis.level.setText("Level: "+Tenis.lvl);
                    balls=8;
                    Tenis.numBalls.setText("Míčů: "+Tenis.balls);
                }
                

        }
    }

    public static void main(String[] args) throws InterruptedException {

        JFrame frame = new JFrame("Penalty Fever");
        
        Tenis plocha = new Tenis();
        frame.add(plocha);
        frame.setSize(400, 400);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
        score.setFont(new Font(score.getFont().getName(),Font.BOLD, 20));
        score.setForeground(Color.BLACK);
        level.setFont(new Font(level.getFont().getName(),Font.BOLD, 20));
        level.setForeground(Color.BLACK);
        numBalls.setFont(new Font(numBalls.getFont().getName(),Font.BOLD, 20));
        numBalls.setForeground(Color.BLACK);
  
        
        
        
        // Opakované vyvolání metody move() a překreslení plochy
        while (true) {
            plocha.move();
            plocha.repaint();
            // Nastavení zpoždění cyklu na 10 ms 
            Thread.sleep(5);
        }
    }
}
