/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;

/**
 *
 * @author Pavel
 */
public class Panel_Plik extends javax.swing.JPanel {

    /**
     * Creates new form Panel_Plik
     */
    public Panel_Plik() {
        initComponents();
        L_open.setVisible(false);
    }
    
   
    
    public void Save_Klient(){
        String tekst = null;
        try {
            Mysql sql = new Mysql();
            int r = sql.Tabel_row("klient");
            int c = sql.Tabel_col("klient");
            String baza[][] = new String[r][c];
            baza = sql.Client_Stan();
            for(int i=0;i<r;i++){
                for(int j=0;j<c;j++){
                    tekst += baza[i][j]+" ";
                }
                tekst+="\n";
            }            
        } catch (SQLException ex) {
            Logger.getLogger(Panel_Plik.class.getName()).log(Level.SEVERE, null, ex);
            Plik p = new Plik();
            try {
                p.zapisPliku(ex.toString());
            } catch (IOException ex1) {
                Logger.getLogger(Panel_E_Klient.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        
        try {
            Save_plik(tekst, "klient.txt");
        }catch (IOException ex) {
            Logger.getLogger(Panel_Plik.class.getName()).log(Level.SEVERE, null, ex);
            Plik p = new Plik();
            try {
                p.zapisPliku(ex.toString());
            } catch (IOException ex1) {
                Logger.getLogger(Panel_E_Klient.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        
    }
    
    public void Save_Magazyn(){
        String tekst = "";
        try {
            Mysql sql = new Mysql();
            int r = sql.Tabel_row("magazyn");
            int c = sql.Tabel_col("magazyn");
            String baza[][] = new String[r][c];
            baza = sql.Mag_Stan();
            for(int i=0;i<r;i++){
                for(int j=0;j<c;j++){
                    tekst += baza[i][j]+" ";
                    if(j==5 || j==4){
                        tekst+="\n";
                    }
                }
                tekst+="\n \n";
            }            
        } catch (SQLException ex) {
            Logger.getLogger(Panel_Plik.class.getName()).log(Level.SEVERE, null, ex);
            Plik p = new Plik();
            try {
                p.zapisPliku(ex.toString());
            } catch (IOException ex1) {
                Logger.getLogger(Panel_E_Klient.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        
        try {
            Save_plik(tekst, "magazyn.txt");
        }catch (IOException ex) {
            Logger.getLogger(Panel_Plik.class.getName()).log(Level.SEVERE, null, ex);
            Plik p = new Plik();
            try {
                p.zapisPliku(ex.toString());
            } catch (IOException ex1) {
                Logger.getLogger(Panel_E_Klient.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }
    
    public void Save_Wypozyczalnia(){
        String tekst = "";
        try {
            Mysql sql = new Mysql();
            int r = sql.Tabel_row("wypoczalnia");
            int c = sql.Tabel_col("wypoczalnia");
            String baza[][] = new String[r][c];
            baza = sql.Wyp_Stan();
            for(int i=0;i<r;i++){
                for(int j=0;j<c;j++){
                    tekst += baza[i][j]+" ";
                }
                tekst+="\n";
            }            
        } catch (SQLException ex) {
            Logger.getLogger(Panel_Plik.class.getName()).log(Level.SEVERE, null, ex);
            Plik p = new Plik();
            try {
                p.zapisPliku(ex.toString());
            } catch (IOException ex1) {
                Logger.getLogger(Panel_E_Klient.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        
        try {
            Save_plik(tekst, "wypozyczalnia.txt");
        }catch (IOException ex) {
            Logger.getLogger(Panel_Plik.class.getName()).log(Level.SEVERE, null, ex);
            Plik p = new Plik();
            try {
                p.zapisPliku(ex.toString());
            } catch (IOException ex1) {
                Logger.getLogger(Panel_E_Klient.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }
    
    public void Save_plik(String tekst, String plik) throws IOException{
        FileWriter plikWy = null;
        try {
            // tworzy nowy plik jeżeli nie istnieje, w przeciwnym przypadku
            // usuwa zawartość pliku i nadpisuje od początku
            plikWy = new FileWriter(plik);
            // zapis łańcucha
            //tekst = "Dziś jest piękny dzień\nna egzamin z programowania.\n";
            plikWy.write(tekst);
 
        } finally {
            if (plikWy != null) {
                plikWy.close();
            }
        }
    }
    
    public void Open_Klient(){
        String plik = "klient.txt";
        String baza = "klient";
        
        try {
            Open_plik(plik,baza);
        } catch (IOException ex) {
            Logger.getLogger(Panel_Plik.class.getName()).log(Level.SEVERE, null, ex);
            Plik p = new Plik();
            try {
                p.zapisPliku(ex.toString());
            } catch (IOException ex1) {
                Logger.getLogger(Panel_E_Klient.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }
    
    public void Open_Magazyn(){
        String plik = "magazyn.txt";
        String baza = "magazyn";
        
        try {
            Open_plik(plik,baza);
        } catch (IOException ex) {
            Logger.getLogger(Panel_Plik.class.getName()).log(Level.SEVERE, null, ex);
            Plik p = new Plik();
            try {
                p.zapisPliku(ex.toString());
            } catch (IOException ex1) {
                Logger.getLogger(Panel_E_Klient.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }
    
    public void Open_Wypozyczalnia(){
        String plik = "wypozyczalnia.txt";
        String baza = "wypoczalnia";
        
        try {
            Open_plik(plik, baza);
        } catch (IOException ex) {
            Logger.getLogger(Panel_Plik.class.getName()).log(Level.SEVERE, null, ex);
            Plik p = new Plik();
            try {
                p.zapisPliku(ex.toString());
            } catch (IOException ex1) {
                Logger.getLogger(Panel_E_Klient.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }
    
    public void Open_plik(String plik, String baza) throws FileNotFoundException, IOException{
        FileReader plikWe = null;
        BufferedReader plik2 = null;
        try {            
            plik2 = new BufferedReader(new FileReader(plik));
            String l = plik2.readLine();
            Mysql sql = new Mysql();
            int row = sql.Tabel_row(baza);
            String tekst[] = new String[row+1];
            int i=0;
            while (l != null) {
                System.out.println(l);
                tekst[i] = l;
                i++;
                l = plik2.readLine();
            }
            //System.out.println("i: "+i+" row: "+row);
            L_open.setText(tekst.toString());
            L_open.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(Panel_Plik.class.getName()).log(Level.SEVERE, null, ex);
            Plik p = new Plik();
            try {
                p.zapisPliku(ex.toString());
            } catch (IOException ex1) {
                Logger.getLogger(Panel_E_Klient.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            if (plik2 != null) {
                plik2.close();
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        L_open = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();

        jButton1.setText("Zapis Klient w txt");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Zapis Magazyn w txt");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Odczyt Klient z txt");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Odczyt Magazyn z txt");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        L_open.setText("jLabel1");

        jButton5.setText("Zapis Wypozyczalnia w txt");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Odczyt Wypozyczalnia z txt");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton4))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(L_open)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                        .addComponent(jButton6)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton4))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(jButton6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(L_open)
                .addContainerGap(180, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Save_Klient();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Save_Magazyn();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
       Open_Klient();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        Open_Magazyn();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        Save_Wypozyczalnia();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        Open_Wypozyczalnia();
    }//GEN-LAST:event_jButton6ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel L_open;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    // End of variables declaration//GEN-END:variables
}
