/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Main;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pavel
 */
public class Panel_N_Wyp extends javax.swing.JPanel {

    /**
     * Creates new form Panel_U_Wyp
     */
    public Panel_N_Wyp() {
        initComponents();
        Baza();
        Item();
        L_View_Klient.setVisible(false);
        L_View_Mag.setVisible(false);
        error_date.setVisible(false);
    }
    
    public static int calculateDifference(Date a, Date b){
        int tempDifference = 0;
        int difference = 0;
        Calendar earlier = Calendar.getInstance();
        Calendar later = Calendar.getInstance();
        
        if (a.compareTo(b) < 0){
            earlier.setTime(a);
            later.setTime(b);
        }else{
            earlier.setTime(b);
            later.setTime(a);
        }

        while (earlier.get(Calendar.YEAR) != later.get(Calendar.YEAR)){
            tempDifference = 365 * (later.get(Calendar.YEAR) - earlier.get(Calendar.YEAR));
            difference += tempDifference;

            earlier.add(Calendar.DAY_OF_YEAR, tempDifference);
        }

        if (earlier.get(Calendar.DAY_OF_YEAR) != later.get(Calendar.DAY_OF_YEAR)){
            tempDifference = later.get(Calendar.DAY_OF_YEAR) - earlier.get(Calendar.DAY_OF_YEAR);
            difference += tempDifference;

            earlier.add(Calendar.DAY_OF_YEAR, tempDifference);
        }

        return difference;
    }
   
    public void Item(){
         JCombo_Mag.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent ie) {
                if(ie.getStateChange() == ItemEvent.SELECTED){
                    String value = (String) JCombo_Mag.getSelectedItem();
                    if(JCombo_Mag.getSelectedIndex() != 0){
                        Short_ID SID = new Short_ID();
                        value = SID.ID_M(value);
                        //System.out.println(value);
                    }
                }
            }
        });
        
        
        JCombo_Klient.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent ie) {
                if(ie.getStateChange() == ItemEvent.SELECTED){
                    String value = (String) JCombo_Klient.getSelectedItem();
                    if(JCombo_Klient.getSelectedIndex() != 0){
                        Short_ID SID = new Short_ID();
                        value = SID.ID_K(value);
                        //System.out.println(value);
                    }
                }  
            }
        });
        
        
         JDate_wypoz.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {
            //@Override
            public void propertyChange(PropertyChangeEvent e) {
                if ("date".equals(e.getPropertyName())) {
                    //System.out.println((Date) e.getNewValue());
                    if(JDate_zwrot.getDate() != null && JDate_wypoz.getDate()!= null){
                        if(JDate_wypoz.getDate().compareTo(JDate_zwrot.getDate())<0){
                           if(JCombo_Mag.getSelectedIndex() != 0){
                                int dni = calculateDifference(JDate_wypoz.getDate(),JDate_zwrot.getDate());
                                Search_ID_cen(dni, JCombo_Mag.getSelectedItem().toString());
                            }
                            error_date.setVisible(false);
                        }else{
                            error_date.setVisible(true);
                        }                        
                    }
                }
            }
        });
        
        JDate_zwrot.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {
            //@Override
            public void propertyChange(PropertyChangeEvent e) {
                if ("date".equals(e.getPropertyName())) {
                    if(JDate_zwrot.getDate() != null && JDate_wypoz.getDate()!= null){
                       if(JDate_wypoz.getDate().compareTo(JDate_zwrot.getDate())<0){
                            if(JCombo_Mag.getSelectedIndex() != 0){
                                int dni = calculateDifference(JDate_wypoz.getDate(),JDate_zwrot.getDate());
                                Search_ID_cen(dni, JCombo_Mag.getSelectedItem().toString());
                            }
                            error_date.setVisible(false);
                        }else{
                            error_date.setVisible(true);
                        }
                    }
                }
            }
        });
        
    }
    
    public void Search_ID_cen(int day, String item){
        Short_ID SID = new Short_ID();
        String v = SID.ID_M(item);
        
        try {
            Mysql sql = new Mysql();
            String cena = sql.Mag_Cena(v);
            int cen = Integer.parseInt(cena);
            cen *= day;
            L_cen.setText("Koszt wypozyczalnia: "+cen+" złotych");
        } catch (SQLException ex) {
            Logger.getLogger(Panel_N_Wyp.class.getName()).log(Level.SEVERE, null, ex);
            Plik p = new Plik();
            try {
                p.zapisPliku(ex.toString());
            } catch (IOException ex1) {
                Logger.getLogger(Panel_E_Klient.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }
    
    public void Save(){
        int tab[] = new int[4];
        String K_value = (String) JCombo_Klient.getSelectedItem();
        String M_value = (String) JCombo_Mag.getSelectedItem();
        
        char nazwa_K[] = K_value.toCharArray();
        char nazwa_M[] = M_value.toCharArray();
        char ID_K[] = new char[5];
        char ID_M[] = new char[8];
        if(K_value == "[wybierz}"){
            tab[0] = 0;
        }else{
            tab[0] = 1;
            for(int i=0;i<5;i++){
                ID_K[i] = nazwa_K[i+3];
            }
        }
        
        if(M_value == "[wybierz]"){
            tab[1] = 0;
        }else{
            tab[1] = 1;
            for(int i=0;i<8;i++){
                ID_M[i] = nazwa_M[i+3];
            }
        }
        
        SimpleDateFormat ft = new SimpleDateFormat("YYYY-MM-dd");
        String dw = ft.format(JDate_wypoz.getDate());
        String dz = ft.format(JDate_zwrot.getDate());
        
        if(JDate_wypoz.getDate()==null){
            tab[2] = 0;
        }else{
            tab[2] = 1;
        }
        
        if(JDate_zwrot.getDate()==null){
            tab[3] = 0;
        }else{
            tab[3] = 1;
        }
        int sum = 0;
        for(int i=0;i<4;i++){
            sum+=tab[i];
        }
        if(sum==4){
            Save_Baza(String.valueOf(ID_K),String.valueOf(ID_M),dw,dz);
            //this.setVisible(false);
        }
        
    }
    
    public void Save_Baza(String ID_K, String ID_M, String D_W, String D_Z){
        try {
            Mysql sql = new Mysql();
            sql.Wyp_Add(ID_K, ID_M, D_W, D_Z);
        } catch (SQLException ex) {
            Logger.getLogger(Panel_N_Wyp.class.getName()).log(Level.SEVERE, null, ex);
            Plik p = new Plik();
            try {
                p.zapisPliku(ex.toString());
            } catch (IOException ex1) {
                Logger.getLogger(Panel_E_Klient.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }
    
    public void Baza(){
        try {
            JCombo_Klient.addItem("[wybierz]");
            JCombo_Mag.addItem("[wybierz]");
            
            Mysql sql = new Mysql();
            String Baza_k[][] = new String[sql.Tabel_row("klient")][sql.Tabel_col("klient")];
            Baza_k = sql.Client_Stan();
            String baza_k[] = new String[sql.Tabel_row("klient")];
            for(int i=0;i<sql.Tabel_row("klient");i++){
                baza_k[i] ="ID:"+Baza_k[i][0]+" "+Baza_k[i][2]+" "+Baza_k[i][1]+" - "+Baza_k[i][6];
                JCombo_Klient.addItem(baza_k[i]);
            }
            
            String Baza_m[][] = new String[sql.Tabel_row("magazyn")][sql.Tabel_col("magazyn")];
            Baza_m = sql.Mag_Stan();
            String baza_m[] = new String[sql.Tabel_row("magazyn")];
            for(int i=0;i<sql.Tabel_row("magazyn");i++){
                int is = sql.Wyp_Is_Mag(Baza_m[i][0]);
                if(is == 0){
                    baza_m[i] ="ID:"+Baza_m[i][0]+" "+Baza_m[i][3]+" "+Baza_m[i][2]+" "+Baza_m[i][4]+" Cena: "+Baza_m[i][6];
                    JCombo_Mag.addItem(baza_m[i]);
                }
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Panel_N_Wyp.class.getName()).log(Level.SEVERE, null, ex);
            Plik p = new Plik();
            try {
                p.zapisPliku(ex.toString());
            } catch (IOException ex1) {
                Logger.getLogger(Panel_E_Klient.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        
    }
    
    public void Clear(){
        JCombo_Klient.setSelectedIndex(0);
        JCombo_Mag.setSelectedIndex(0);
        JDate_wypoz.setCalendar(null);
        JDate_zwrot.setCalendar(null);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        L_klient = new javax.swing.JLabel();
        L_mag = new javax.swing.JLabel();
        L_D_wyp = new javax.swing.JLabel();
        L_D_zw = new javax.swing.JLabel();
        JDate_zwrot = new com.toedter.calendar.JDateChooser();
        JDate_wypoz = new com.toedter.calendar.JDateChooser();
        JCombo_Klient = new javax.swing.JComboBox();
        JCombo_Mag = new javax.swing.JComboBox();
        B_Save = new javax.swing.JButton();
        B_Clear = new javax.swing.JButton();
        L_View_Klient = new javax.swing.JLabel();
        L_View_Mag = new javax.swing.JLabel();
        error_date = new javax.swing.JLabel();
        L_cen = new javax.swing.JLabel();

        L_klient.setText("Klient:");

        L_mag.setText("Magazyn:");

        L_D_wyp.setText("Data wypożu:");

        L_D_zw.setText("Data zwrotu:");

        B_Save.setText("Zapisz");
        B_Save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_SaveActionPerformed(evt);
            }
        });

        B_Clear.setText("Wyczyść");
        B_Clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_ClearActionPerformed(evt);
            }
        });

        L_View_Klient.setText("jLabel1");

        L_View_Mag.setText("jLabel2");

        error_date.setForeground(new java.awt.Color(255, 0, 0));
        error_date.setText("Nie prawdłowe datę");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(L_D_zw)
                    .addComponent(L_D_wyp)
                    .addComponent(L_klient)
                    .addComponent(L_mag))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(JDate_wypoz, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(error_date))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(B_Save)
                                    .addComponent(JDate_zwrot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(B_Clear))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(28, 28, 28)
                                        .addComponent(L_cen)))))
                        .addContainerGap())
                    .addComponent(JCombo_Klient, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JCombo_Mag, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(layout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addComponent(L_View_Klient)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 222, Short.MAX_VALUE)
                .addComponent(L_View_Mag)
                .addGap(149, 149, 149))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(L_klient)
                    .addComponent(JCombo_Klient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(L_mag)
                    .addComponent(JCombo_Mag, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(L_D_wyp)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(L_D_zw)
                                    .addComponent(JDate_zwrot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(JDate_wypoz, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(error_date)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(L_cen)))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(B_Save)
                    .addComponent(B_Clear))
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(L_View_Klient)
                    .addComponent(L_View_Mag))
                .addContainerGap(216, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void B_SaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_SaveActionPerformed
       Save();
    }//GEN-LAST:event_B_SaveActionPerformed

    private void B_ClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_ClearActionPerformed
        Clear();
    }//GEN-LAST:event_B_ClearActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton B_Clear;
    private javax.swing.JButton B_Save;
    private javax.swing.JComboBox JCombo_Klient;
    private javax.swing.JComboBox JCombo_Mag;
    private com.toedter.calendar.JDateChooser JDate_wypoz;
    private com.toedter.calendar.JDateChooser JDate_zwrot;
    private javax.swing.JLabel L_D_wyp;
    private javax.swing.JLabel L_D_zw;
    private javax.swing.JLabel L_View_Klient;
    private javax.swing.JLabel L_View_Mag;
    private javax.swing.JLabel L_cen;
    private javax.swing.JLabel L_klient;
    private javax.swing.JLabel L_mag;
    private javax.swing.JLabel error_date;
    // End of variables declaration//GEN-END:variables
}
