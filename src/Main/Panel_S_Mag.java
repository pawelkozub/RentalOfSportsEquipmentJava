/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Main;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pavel
 */
public class Panel_S_Mag extends javax.swing.JPanel {
    String loader[] = new String[]{"Loading..."};
    String empty[] = new String[]{"Pusty..."};
    /**
     * Creates new form Panel_E_Mag
     */
    
    public Panel ParentFrame;
    
    public Panel_S_Mag(){
        initComponents();
        Baza();
    }
    
    public void Delete(String ID){
        try {
            Mysql sql = new Mysql();
            int spr = sql.Mag_Spr_Del(ID);
            if(spr == 0){
                sql.Mag_Delete(ID);
            }
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
        
    public static void Load(){
        //Baza();
        //Clear();
        System.out.println("Load Magazyn");
    }
    
    public void Button_true(){
        B_del.setVisible(true);
        B_edit.setVisible(true);
    }
    
    public void Button_false(){
        B_del.setVisible(false);
        B_edit.setVisible(false);
    }
    
    public void Clear(){
        LL_ID.setText("");
        LL_producent.setText("");
        LL_nazwa.setText("");
        LL_model.setText("");
        LL_cena.setText("");
        LL_opis.setText("");
        
    }
    
    public void Baza(){
        Clear();
        try {
            L_Mag.clearSelection();
            L_Mag.setListData(loader);
            Mysql sql = new Mysql();
            int n = sql.Tabel_row("magazyn");
            int c = sql.Tabel_col("magazyn");
            int kk = sql.Tabel_row("kategorie");
            String kat[][] = new String[kk][2];
            kat = sql.Kat_Stan();
            String katt[] = new String[kk+1];
            katt[0] = "ALL";
            for(int i=0;i<kk;i++){
                katt[i+1] = kat[i][1];
            }
            L_Kat.setListData(katt);
            String baza[][] = new String[n][c];
            baza = sql.Mag_Stan();
            String mag[] = new String[n];
           
            for(int i =0;i<n;i++){
                for(int a=0;a<c-2;a++){
                    if(a==1){
                        mag[i]+=kat[Integer.parseInt(baza[i][1])-1][1]+" ";
                      }else if(a==0){
                        mag[i]="ID:"+baza[i][a]+" ";
                    }else{
                        mag[i]+=baza[i][a]+" ";
                    }
                }
            }
            L_Mag.setListData(mag);
            
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
    public void Group(int kat){
       try {
            Mysql sql = new Mysql();
            int r = sql.Tabel_row("magazyn");
            int c = sql.Tabel_col("magazyn");
            String baza[][] = new String[r][c];
            baza = sql.Mag_Stan();
            int ii=0;
            for(int i=0;i<r;i++){
                int spr_kat = Integer.parseInt(baza[i][1]);
                if(spr_kat == kat){
                    ii++; 
                }
            }
            String baza_L[] = new String[ii];
            ii=0;
            for(int i=0;i<r;i++){
                int spr_kat = Integer.parseInt(baza[i][1]);
                if(spr_kat == kat){
                    baza_L[ii] = "ID:"+baza[i][0]+" "+baza[i][3]+" "+baza[i][2]+" "+baza[i][4];
                    ii++;
                } 
                
            }
            if(ii==0){
                L_Mag.clearSelection();
                L_Mag.setListData(empty);
            }else{
                L_Mag.clearSelection();
                L_Mag.setListData(baza_L);
            }
            
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
    
    public static String convertToMultiline(String orig)
    {
        String tmp = orig.replaceAll("-", "<br>- ");
        tmp = tmp.substring(4, tmp.length());
        
        return "<html>" +tmp + "</html>";
    }
    
    public void Search(String ID){
        try {
            if(ID=="empty"){
                LL_ID.setText("");
                LL_producent.setText("");
                LL_nazwa.setText("");
                LL_model.setText("");
                LL_cena.setText("");
                LL_opis.setText("");
            }else{
                Mysql sql = new Mysql();
                String name[] = new String[7];
                name = sql.Mag_Search(ID);
                
                LL_ID.setText(name[0]);
                LL_producent.setText(name[2]);
                LL_nazwa.setText(name[3]);
                LL_model.setText(name[4]);
                LL_cena.setText(name[6]);
                LL_opis.setText(convertToMultiline(name[5]));  
                
                if(sql.Wyp_Is_Mag(ID)==0){
                    Button_true();
                }else{
                    Button_false();
                }
            }
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
        L_Kat = new javax.swing.JList();
        jScrollPane3 = new javax.swing.JScrollPane();
        L_Mag = new javax.swing.JList();
        L_ID = new javax.swing.JLabel();
        L_producent = new javax.swing.JLabel();
        L_nazwa = new javax.swing.JLabel();
        L_model = new javax.swing.JLabel();
        L_cena = new javax.swing.JLabel();
        L_opis = new javax.swing.JLabel();
        LL_ID = new javax.swing.JLabel();
        LL_producent = new javax.swing.JLabel();
        LL_nazwa = new javax.swing.JLabel();
        LL_model = new javax.swing.JLabel();
        LL_cena = new javax.swing.JLabel();
        LL_opis = new javax.swing.JLabel();

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

        L_Kat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                L_KatMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(L_Kat);

        L_Mag.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                L_MagMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(L_Mag);

        L_ID.setText("ID:");

        L_producent.setText("Producent:");

        L_nazwa.setText("Nazwa:");

        L_model.setText("Model:");

        L_cena.setText("Cena:");

        L_opis.setText("Opis:");

        LL_opis.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LL_opis.setToolTipText("");
        LL_opis.setAutoscrolls(true);
        LL_opis.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        LL_opis.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(L_ID)
                                    .addComponent(L_producent)
                                    .addComponent(L_nazwa)
                                    .addComponent(L_model)
                                    .addComponent(L_cena)
                                    .addComponent(L_opis))
                                .addGap(37, 37, 37)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(LL_cena, javax.swing.GroupLayout.DEFAULT_SIZE, 1, Short.MAX_VALUE)
                                    .addComponent(LL_model, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(LL_nazwa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(LL_producent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(LL_ID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(41, 41, 41)
                                .addComponent(B_edit)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(B_del)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 163, Short.MAX_VALUE)
                        .addComponent(LL_opis)
                        .addGap(0, 171, Short.MAX_VALUE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(B_edit)
                    .addComponent(B_del))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(L_ID)
                            .addComponent(LL_ID))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(L_producent)
                            .addComponent(LL_producent))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(L_nazwa)
                            .addComponent(LL_nazwa))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(L_model)
                            .addComponent(LL_model))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(L_cena)
                            .addComponent(LL_cena))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(L_opis)
                            .addComponent(LL_opis))))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void L_KatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_L_KatMouseClicked
        int kat = L_Kat.getSelectedIndex();
        //System.out.println(kat);
        L_Mag.clearSelection();
        L_Mag.setListData(loader);
        Clear();
        if(kat == 0){
            Baza();
        }else{
            Group(kat);
        }     
    }//GEN-LAST:event_L_KatMouseClicked

    private void L_MagMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_L_MagMouseClicked
        int index = L_Mag.getSelectedIndex();
        String name = (String) L_Mag.getSelectedValue();
        if(name == "Pusty..."){
            Search("empty");
        }else{
            Short_ID SID = new Short_ID();
            Search(SID.ID_M(name));
        }
    }//GEN-LAST:event_L_MagMouseClicked

   
        
    private void B_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_editActionPerformed
        int index = L_Mag.getSelectedIndex();
        if(index != -1){
            String name = (String) L_Mag.getSelectedValue();
            Short_ID SID = new Short_ID();
            ParentFrame.Opened_E_Mag(SID.ID_M(name));
        }
       
    }//GEN-LAST:event_B_editActionPerformed

    private void B_delActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_delActionPerformed
        int index = L_Mag.getSelectedIndex();
        if(index != -1){
            String value = (String) L_Mag.getSelectedValue();
            Short_ID SID = new Short_ID();
            Delete(SID.ID_M(value));
        }
    }//GEN-LAST:event_B_delActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton B_del;
    private javax.swing.JButton B_edit;
    private javax.swing.JLabel LL_ID;
    private javax.swing.JLabel LL_cena;
    private javax.swing.JLabel LL_model;
    private javax.swing.JLabel LL_nazwa;
    private javax.swing.JLabel LL_opis;
    private javax.swing.JLabel LL_producent;
    private javax.swing.JLabel L_ID;
    private javax.swing.JList L_Kat;
    private javax.swing.JList L_Mag;
    private javax.swing.JLabel L_cena;
    private javax.swing.JLabel L_model;
    private javax.swing.JLabel L_nazwa;
    private javax.swing.JLabel L_opis;
    private javax.swing.JLabel L_producent;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    // End of variables declaration//GEN-END:variables
}
