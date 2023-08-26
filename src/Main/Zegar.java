/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Main;

import java.awt.*;
import java.text.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author Pavel
 */
class Zegar extends JLabel implements Runnable {
    private Thread watek;
    
    private int pauza = 1000;
    
    public Zegar(){
        setFont(new Font("Consolas", Font.BOLD, 28));
        setForeground(Color.BLUE);
        setOpaque(true);
    }
    
    public void start(){
        if(watek == null){
            watek = new Thread(this);
            watek.start();
        }
    }
    
    public void run(){
        while(watek == Thread.currentThread()){
            Date time = new Date();
            DateFormat df = DateFormat.getTimeInstance(DateFormat.MEDIUM);
            setText(df.format(time));
            try {
                watek.sleep(pauza);
            } catch (InterruptedException ex) {
                Logger.getLogger(Zegar.class.getName()).log(Level.SEVERE, null, ex);
            }
       }
    }
    
    public void stop(){
        watek = null;
    }
    
}
