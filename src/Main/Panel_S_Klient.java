/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Main;

import java.awt.CardLayout;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Pavel
 */
public class Panel_S_Klient extends javax.swing.JPanel {
    String Hidden_ID;
    /**
     * Creates new form Panel_E_Klients
     */

    public Panel ParentFrame;
          
    public void Clear(){
        LL_ID.setText("");
        LL_imie.setText("");
        LL_nazwisko.setText("");
        LL_ulica.setText("");
        LL_nr.setText("");
        LL_kod.setText("");
        LL_miasto.setText("");
    }
    public Panel_S_Klient() {
        initComponents();
        Baza();
    }
    
    public static void Load(){
        //Baza();
        //Clear();
        System.out.println("Loader_Klient");
    }
    
    public void ReLoad()
    {
        Baza();
        Clear();
    }
    
    public void Delete(String ID){
        try {
            Mysql sql = new Mysql();
            int spr = sql.Client_Spr_Del(ID);
            if(spr == 0){
                sql.Client_Delete(ID);
            }else{
                System.out.println("nie można usunac, bo istnieje w wypoczalnia");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Panel_S_Klient.class.getName()).log(Level.SEVERE, null, ex);
            Plik p = new Plik();
            try {
                p.zapisPliku(ex.toString());
            } catch (IOException ex1) {
                Logger.getLogger(Panel_E_Klient.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }
    
    public void Baza() {
         try{
            Mysql sql = new Mysql();
            int n = sql.Tabel_row("klient");
            int c = sql.Tabel_col("klient");
            String[][] baza = new String[n][c];
            baza = sql.Client_Stan();
            String[] klient = new String[n];
            for(int i=0;i<n;i++){
                klient[i] = "ID:"+baza[i][0]+" "+baza[i][2]+" "+baza[i][1];
            }
            L_Klient.setListData(klient);                
        } catch (SQLException ex) {
            Logger.getLogger(Panel_S_Mag.class.getName()).log(Level.SEVERE, null, ex);
            Plik p = new Plik();
            try {
                p.zapisPliku(ex.toString());
            } catch (IOException ex1) {
                Logger.getLogger(Panel_E_Klient.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        B_edit = new javax.swing.JButton();
        B_del = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        L_Klient = new javax.swing.JList();
        L_ID = new javax.swing.JLabel();
        L_imie = new javax.swing.JLabel();
        L_nazwisko = new javax.swing.JLabel();
        L_ulica = new javax.swing.JLabel();
        L_nr = new javax.swing.JLabel();
        L_kod = new javax.swing.JLabel();
        L_miasto = new javax.swing.JLabel();
        LL_ID = new javax.swing.JLabel();
        LL_imie = new javax.swing.JLabel();
        LL_nazwisko = new javax.swing.JLabel();
        LL_ulica = new javax.swing.JLabel();
        LL_nr = new javax.swing.JLabel();
        LL_kod = new javax.swing.JLabel();
        LL_miasto = new javax.swing.JLabel();

        setName("Panel"); // NOI18N

        B_edit.setText("Edytuj");
        B_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_editActionPerformed(evt);
            }
        });

        B_del.setText("Usuń");
        B_del.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_delActionPerformed(evt);
            }
        });

        L_Klient.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        L_Klient.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                L_KlientMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(L_Klient);

        L_ID.setText("ID:");

        L_imie.setText("Imię:");

        L_nazwisko.setText("Nazwisko:");

        L_ulica.setText("Ulica:");

        L_nr.setText("Nr:");

        L_kod.setText("Kod:");

        L_miasto.setText("Miasto:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(B_del)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(B_edit))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(L_ID)
                                    .addComponent(L_imie)
                                    .addComponent(L_nazwisko))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(17, 17, 17)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(LL_ID)
                                            .addComponent(LL_imie)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(LL_nazwisko)
                                            .addComponent(LL_ulica)
                                            .addComponent(LL_nr)
                                            .addComponent(LL_kod)
                                            .addComponent(LL_miasto)))))
                            .addComponent(L_miasto)
                            .addComponent(L_kod)
                            .addComponent(L_nr)
                            .addComponent(L_ulica))))
                .addContainerGap(242, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(B_edit)
                    .addComponent(B_del))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(L_ID)
                            .addComponent(LL_ID))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(L_imie)
                            .addComponent(LL_imie))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(L_nazwisko)
                            .addComponent(LL_nazwisko))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(L_ulica, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LL_ulica))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(L_nr)
                            .addComponent(LL_nr))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(L_kod)
                            .addComponent(LL_kod))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(L_miasto)
                            .addComponent(LL_miasto)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(133, Short.MAX_VALUE))
        );

        getAccessibleContext().setAccessibleName("Panel_SS");
    }// </editor-fold>//GEN-END:initComponents

    private void B_delActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_delActionPerformed
        int index = L_Klient.getSelectedIndex();
        if(index != -1){
            String value = (String) L_Klient.getSelectedValue();
            Short_ID SID = new Short_ID();
            Delete(SID.ID_K(value));
        }
    }//GEN-LAST:event_B_delActionPerformed

    private void B_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_editActionPerformed
        if(L_Klient.getSelectedIndex()!=-1){
            String name = (String)L_Klient.getSelectedValue();
            Short_ID SID = new Short_ID();
            ParentFrame.Opened_E_Klient(SID.ID_K(name));
        }
        
       
    }//GEN-LAST:event_B_editActionPerformed
   
    private void L_KlientMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_L_KlientMouseClicked
        int index = L_Klient.getSelectedIndex();
        String name = (String)L_Klient.getSelectedValue();
        if(index != -1){
            Short_ID SID = new Short_ID();
            Search(SID.ID_K(name));
        }
    }//GEN-LAST:event_L_KlientMouseClicked

    public void Search(String ID){
        try {
             Mysql sql = new Mysql();
            String name[] = new String[7];
            name = sql.Client_Search(ID);
            LL_ID.setText(name[0]);
            LL_imie.setText(name[1]);
            LL_nazwisko.setText(name[2]);
            LL_ulica.setText(name[3]);
            LL_nr.setText(name[4]);
            LL_kod.setText(name[5]);
            LL_miasto.setText(name[6]);
        } catch (SQLException ex) {
            Logger.getLogger(Panel_S_Mag.class.getName()).log(Level.SEVERE, null, ex);
            Plik p = new Plik();
            try {
                p.zapisPliku(ex.toString());
            } catch (IOException ex1) {
                Logger.getLogger(Panel_E_Klient.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton B_del;
    private javax.swing.JButton B_edit;
    private javax.swing.JLabel LL_ID;
    private javax.swing.JLabel LL_imie;
    private javax.swing.JLabel LL_kod;
    private javax.swing.JLabel LL_miasto;
    private javax.swing.JLabel LL_nazwisko;
    private javax.swing.JLabel LL_nr;
    private javax.swing.JLabel LL_ulica;
    private javax.swing.JLabel L_ID;
    private javax.swing.JList L_Klient;
    private javax.swing.JLabel L_imie;
    private javax.swing.JLabel L_kod;
    private javax.swing.JLabel L_miasto;
    private javax.swing.JLabel L_nazwisko;
    private javax.swing.JLabel L_nr;
    private javax.swing.JLabel L_ulica;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
