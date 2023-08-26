package Main;

import java.io.IOException;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Mysql {
    private String url ="jdbc:mysql://localhost/";
    private String login = "root";
    private String pass = "";
    private String db = "projekt_java?useUnicode=true&characterEncoding=utf8";
    
    //private String SET1 = "SET NAMES 'utf8'";
    //private String SET2 = "SET CHARACTER SET utf8";
    //private String SET3 = "SET collation_connection = utf8_polish_ci";
    
    public void Client() throws SQLException{
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url+db,login,pass);
            Statement st = conn.createStatement();
            ResultSet res = st.executeQuery("select * from klient");
            ResultSetMetaData metadata = res.getMetaData();
            int column = metadata.getColumnCount();
            //String StringColumn = metadata.getColumnName(7); --> nazwa kolumnę; 
            //System.out.println("Kolumna zawiera: "+column+" "+rows);
            while(res.next()){
                
                int id = res.getInt(1);
                String imie = res.getNString(2);
                String nazwisko = res.getNString(3);
                String ulica = res.getNString(4);
                String nr = res.getNString(5);
                String kod = res.getNString(6);
                String miasto = res.getNString(7);
                System.out.println("ID:"+id+" Klient: "+imie+" "+nazwisko+" Adres: "+ulica+" "+nr+", "+kod+" "+miasto);
            }
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
            Plik p = new Plik();
            try {
                p.zapisPliku(e.toString());
            } catch (IOException ex) {
                Logger.getLogger(Mysql.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void Mag() throws SQLException{
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url+db,login,pass);
            Statement st = conn.createStatement();
            ResultSet res = st.executeQuery("select * from Magazyn");
            ResultSetMetaData metadata = res.getMetaData();
            int column = metadata.getColumnCount();
            while(res.next()){ 
                System.out.println(res.getInt(1)+" "+res.getInt(2)+" "+res.getNString(3)+" "
                        +res.getNString(4)+" "+res.getNString(5)+" "+res.getNString(6)+" "
                        +res.getNString(7)+" "+res.getInt(8)+" "+res.getInt(9));
                //for(int i=1;i<=column;i++){
                    //System.out.print(res.getNString(i)+" ");
                //}
                //System.out.println(" ");
            }
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
            Plik p = new Plik();
            try {
                p.zapisPliku(e.toString());
            } catch (IOException ex) {
                Logger.getLogger(Mysql.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public String[] Mag_Search(String ID) throws SQLException{
        try{
            int col = Tabel_col("magazyn");
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url+db,login,pass);
            Statement st = conn.createStatement();
            ResultSet res = st.executeQuery("select * from Magazyn where ID_Magazyn='"+ID+"'");
            ResultSetMetaData metadata = res.getMetaData();
            int column = metadata.getColumnCount();
            String temp[] = new String[col];
            while(res.next()){
                for(int i=0;i<col;i++){
                    temp[i] = res.getString(i+1);
                }
            }
            return temp;
        }catch(Exception e){
            e.printStackTrace();
            Plik p = new Plik();
            try {
                p.zapisPliku(e.toString());
            } catch (IOException ex) {
                Logger.getLogger(Mysql.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    
    public String Mag_Search_Name(String ID) throws SQLException{
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url+db,login,pass);
            Statement st = conn.createStatement();
            ResultSet res = st.executeQuery("select * from Magazyn where ID_Magazyn='"+ID+"'");
            ResultSetMetaData metadata = res.getMetaData();
            String value = new String();
            while(res.next()){
                value = res.getString(4)+" "+res.getString(3)+" "+res.getString(5);
            }
            return value;
        }catch(Exception e){
            e.printStackTrace();
            Plik p = new Plik();
            try {
                p.zapisPliku(e.toString());
            } catch (IOException ex) {
                Logger.getLogger(Mysql.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    
    public int Mag_Spr_Del(String ID) throws SQLException{
        try{
            int is = 0;
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url+db,login,pass);
            Statement st = conn.createStatement();
            ResultSet res = st.executeQuery("select * from wypoczalnia where ID_Magazyn='"+ID+"'");
            ResultSetMetaData metadata = res.getMetaData();
            while(res.next()){
                is++;
            }
            return is;
        }catch(Exception e){
            e.printStackTrace();
            Plik p = new Plik();
            try {
                p.zapisPliku(e.toString());
            } catch (IOException ex) {
                Logger.getLogger(Mysql.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return 0;
    }
    
    public void Mag_Delete(String ID) throws SQLException{
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url+db,login,pass);
            Statement st = conn.createStatement();
            st.executeUpdate("delete from magazyn where ID_Magazyn='"+ID+"'");
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
            Plik p = new Plik();
            try {
                p.zapisPliku(e.toString());
            } catch (IOException ex) {
                Logger.getLogger(Mysql.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public String[] Client_Search(String ID) throws SQLException{
        try{
            int col= Tabel_col("klient");
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url+db,login,pass);
            Statement st = conn.createStatement();
            ResultSet res = st.executeQuery("select * from klient where ID_klient='"+ID+"'");
            ResultSetMetaData metadata = res.getMetaData();
            int column = metadata.getColumnCount();
            String temp[] = new String[col];
            while(res.next()){
                for(int i=0;i<col;i++){
                    temp[i] = res.getString(i+1);
                }
            }
            return temp;
        }catch(Exception e){
            e.printStackTrace();
            Plik p = new Plik();
            try {
                p.zapisPliku(e.toString());
            } catch (IOException ex) {
                Logger.getLogger(Mysql.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    
    public int Client_Spr_Del(String ID) throws SQLException{
        try{
            int col= Tabel_col("klient");
            int is = 0;
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url+db,login,pass);
            Statement st = conn.createStatement();
            ResultSet res = st.executeQuery("select * from wypoczalnia where ID_klient='"+ID+"'");
            ResultSetMetaData metadata = res.getMetaData();
            int column = metadata.getColumnCount();
            String temp[] = new String[col];
            while(res.next()){
                is++;
            }
            return is;
        }catch(Exception e){
            e.printStackTrace();
            Plik p = new Plik();
            try {
                p.zapisPliku(e.toString());
            } catch (IOException ex) {
                Logger.getLogger(Mysql.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return 0;
    }
    
    public void Client_Delete(String ID) throws SQLException{
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url+db,login,pass);
            Statement st = conn.createStatement();
            st.executeUpdate("delete from klient where ID_klient='"+ID+"'");
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
            Plik p = new Plik();
            try {
                p.zapisPliku(e.toString());
            } catch (IOException ex) {
                Logger.getLogger(Mysql.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public String Client_Search_Name(String ID) throws SQLException{
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url+db,login,pass);
            Statement st = conn.createStatement();
            ResultSet res = st.executeQuery("select * from klient where ID_klient='"+ID+"'");
            ResultSetMetaData metadata = res.getMetaData();
            String temp = new String();
            while(res.next()){
                temp = res.getString(3) + " " + res.getString(2);
            }
            return temp;
        }catch(Exception e){
            e.printStackTrace();
            Plik p = new Plik();
            try {
                p.zapisPliku(e.toString());
            } catch (IOException ex) {
                Logger.getLogger(Mysql.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }    
    
    public String[] Wyp_Search(String ID) throws SQLException{
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url+db,login,pass);
            Statement st = conn.createStatement();
            ResultSet res = st.executeQuery("select * from wypoczalnia where ID_wypocz='"+ID+"'");
            ResultSetMetaData metadata = res.getMetaData();
            int column = metadata.getColumnCount();
            String temp[] = new String[Tabel_col("wypoczalnia")];
            while(res.next()){
                for(int i=0;i<Tabel_col("wypoczalnia");i++){
                    temp[i] = res.getString(i+1);
                }
            }
            return temp;
        }catch(Exception e){
            e.printStackTrace();
            Plik p = new Plik();
            try {
                p.zapisPliku(e.toString());
            } catch (IOException ex) {
                Logger.getLogger(Mysql.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    
    public void Mag_Add(int kat, String prod, String naz, String model, String opis, String cena) throws SQLException{
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url+db,login,pass);
            Statement st = conn.createStatement();
            String query = "INSERT INTO magazyn VALUES (NULL,'"+kat+"','"+prod+"','"+naz+"','"+model+"','"+opis+"','"+cena+"')";
            //st.executeUpdate(SET1);
            //st.executeUpdate(SET2);
            //st.executeUpdate(SET3);
            st.executeUpdate(query);
            conn.close();  
        }catch(Exception e){
            e.printStackTrace(); 
            Plik p = new Plik();
            try {
                p.zapisPliku(e.toString());
            } catch (IOException ex) {
                Logger.getLogger(Mysql.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void Client_Add(String imie, String nazw, String ul, String nrr, String kod, String miasto) throws SQLException{
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url+db,login,pass);
            Statement st = conn.createStatement();
            //String query = "insert into klient values (NULL,'"+imie+"','"+nazw+"','"+ul+"','"+nrr+"','"+kod+"','"+miasto+"')";
            String query = "insert into klient values (NULL,'"+imie+"','"+nazw+"','"+ul+"','"+nrr+"','"+kod+"','"+miasto+"')";
            //st.execute(SET1);
            //st.execute(SET2);
            //st.execute(SET3);
            st.executeUpdate(query);
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
            Plik p = new Plik();
            try {
                p.zapisPliku(e.toString());
            } catch (IOException ex) {
                Logger.getLogger(Mysql.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void Client_Edit(String imie, String nazw, String ul, String nrr, String kod, String miasto, String ID) throws SQLException{
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url+db,login,pass);
            Statement st = conn.createStatement();
            String query = "update klient set imie='"+imie+"',nazwisko='"+nazw+"',ulica='"+ul+"',nr='"+nrr+"',kod='"+kod+"',miasto='"+miasto+"' where ID_klient="+ID;
            //System.out.println(query);
            //st.executeUpdate(SET1);
            //st.executeUpdate(SET2);
            //st.executeUpdate(SET3);
            st.executeUpdate(query);
            //System.out.println(query);
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
            Plik p = new Plik();
            try {
                p.zapisPliku(e.toString());
            } catch (IOException ex) {
                Logger.getLogger(Mysql.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void Mag_Edit(String prod, String nazw, String model, String opis, String cena, String ID) throws SQLException{
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url+db,login,pass);
            Statement st = conn.createStatement();
            String query = "update magazyn set Producent='"+prod+"',Nazwa='"+nazw+"',Model='"+model+"',Opis='"+opis+"',Cena='"+cena+"' where ID_Magazyn="+ID;
            //st.executeUpdate(SET1);
            //st.executeUpdate(SET2);
            //st.executeUpdate(SET3);
            st.executeUpdate(query);
            //System.out.println(query);
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
            Plik p = new Plik();
            try {
                p.zapisPliku(e.toString());
            } catch (IOException ex) {
                Logger.getLogger(Mysql.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void Kat_Add(String kat) throws SQLException{
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url+db,login,pass);
            Statement st = conn.createStatement();
            //st.executeUpdate(SET1);
            //st.executeUpdate(SET2);
            //st.executeUpdate(SET3);
            st.executeUpdate("insert into kategorie values ('NULL','"+kat+"')");
            conn.close();
            
        }catch(Exception e){
            e.printStackTrace();
            Plik p = new Plik();
            try {
                p.zapisPliku(e.toString());
            } catch (IOException ex) {
                Logger.getLogger(Mysql.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public String[][] Client_Stan() throws SQLException{
       try{
            int ile = 0;
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url+db,login,pass);
            Statement st = conn.createStatement();
            ResultSet res = st.executeQuery("select * from klient order by nazwisko asc");
            //ResultSetMetaData metadata = res.getMetaData();
            //int column = metadata.getColumnCount();
            String tab[][] = new String[Tabel_row("klient")][Tabel_col("klient")];
            
            while(res.next()){ 
                for(int i=0;i<Tabel_col("klient");i++){
                    tab[ile][i] = res.getString(i+1);
                }
                ile++;
            }
            conn.close();
            return tab;
        }catch(Exception e){
            e.printStackTrace();
            Plik p = new Plik();
            try {
                p.zapisPliku(e.toString());
            } catch (IOException ex) {
                Logger.getLogger(Mysql.class.getName()).log(Level.SEVERE, null, ex);
            }   
        } 
        return null;
    }
    
    public String[][] Mag_Stan() throws SQLException{
       try{
            int ile = 0;
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url+db,login,pass);
            Statement st = conn.createStatement();
            ResultSet res = st.executeQuery("select * from magazyn order by ID_kategorie asc");
            //ResultSetMetaData metadata = res.getMetaData();
            //int column = metadata.getColumnCount();
            String tab[][] = new String[Tabel_row("magazyn")][Tabel_col("magazyn")];
            
            while(res.next()){ 
                for(int i=0;i<Tabel_col("magazyn");i++){
                    tab[ile][i] = res.getString(i+1);
                }
                ile++;
            }
            conn.close();
            return tab;
        }catch(Exception e){
            e.printStackTrace();
            Plik p = new Plik();
            try {
                p.zapisPliku(e.toString());
            } catch (IOException ex) {
                Logger.getLogger(Mysql.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        return null;
    }
    
    public String Mag_Cena(String ID) throws SQLException{
       try{
            String value = null;
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url+db,login,pass);
            Statement st = conn.createStatement();
            ResultSet res = st.executeQuery("select * from magazyn where ID_Magazyn='"+ID+"'");
            while(res.next()){
               value = res.getString(7);
            }
            conn.close();
            return value;
        }catch(Exception e){
            e.printStackTrace();
            Plik p = new Plik();
            try {
                p.zapisPliku(e.toString());
            } catch (IOException ex) {
                Logger.getLogger(Mysql.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        return null;
    }
    
    public String[][] Kat_Stan() throws SQLException{
       try{
            int ile = 0;
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url+db,login,pass);
            Statement st = conn.createStatement();
            ResultSet res = st.executeQuery("select * from kategorie");
            //ResultSetMetaData metadata = res.getMetaData();
            //int column = metadata.getColumnCount();
            String tab[][] = new String[Tabel_row("kategorie")][Tabel_col("kategorie")];
            while(res.next()){
                tab[ile][0] = res.getString(1);
                tab[ile][1] = res.getString(2);
                ile++;
            }
            conn.close();
            return tab;
        }catch(Exception e){
            e.printStackTrace();
            Plik p = new Plik();
            try {
                p.zapisPliku(e.toString());
            } catch (IOException ex) {
                Logger.getLogger(Mysql.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        return null;
    }
    public String[][] Wyp_Stan() throws SQLException{
       try{
            int ile = 0;
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url+db,login,pass);
            Statement st = conn.createStatement();
            ResultSet res = st.executeQuery("select * from wypoczalnia");
            String tab[][] = new String[Tabel_row("wypoczalnia")][Tabel_col("wypoczalnia")];
            while(res.next()){ 
                for(int i=0;i<Tabel_col("wypoczalnia");i++){
                    tab[ile][i] = res.getString(i+1);
                }
                ile++;
            }
            conn.close();
            return tab;
        }catch(Exception e){
            e.printStackTrace();
            Plik p = new Plik();
            try {
                p.zapisPliku(e.toString());
            } catch (IOException ex) {
                Logger.getLogger(Mysql.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        return null;
    }
    
     public String[] Wyp_Search_Klient(String ID) throws SQLException{
       try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url+db,login,pass);
            Statement st = conn.createStatement();
            ResultSet res = st.executeQuery("select * from wypoczalnia where ID_Klient='"+ID+"'");
            String tab[] = new String[Tabel_col("wypoczalnia")];
            while(res.next()){ 
                for(int i=0;i<Tabel_col("wypoczalnia");i++){
                    tab[i] = res.getString(i+1);
                }
            }
            conn.close();
            return tab;
        }catch(Exception e){
            e.printStackTrace();
            Plik p = new Plik();
            try {
                p.zapisPliku(e.toString());
            } catch (IOException ex) {
                Logger.getLogger(Mysql.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        return null;
    }
     
     public String[][] Wyp_Search_Klient_All(String ID) throws SQLException{
       try{
            int ile = 0;
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url+db,login,pass);
            Statement st = conn.createStatement();
            ResultSet res = st.executeQuery("select * from wypoczalnia where ID_Klient='"+ID+"'");
            String tab[][] = new String[Tabel_row_Wyp(ID)][Tabel_col("wypoczalnia")];
            while(res.next()){ 
                for(int i=0;i<Tabel_col("wypoczalnia");i++){
                    tab[ile][i] = res.getString(i+1);
                }
                ile++;
            }
            conn.close();
            return tab;
        }catch(Exception e){
            e.printStackTrace();
            Plik p = new Plik();
            try {
                p.zapisPliku(e.toString());
            } catch (IOException ex) {
                Logger.getLogger(Mysql.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        return null;
    }
    
     public String[][] Wyp_Stan_ID() throws SQLException{
       try{
            int ile = 0;
            int n = Tabel_row("wypoczalnia");
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url+db,login,pass);
            Statement st = conn.createStatement();
            ResultSet res = st.executeQuery("select * from wypoczalnia");
            String tab[][] = new String[n][4];
            while(res.next()){
                tab[ile][0] = res.getString(1);
                tab[ile][1] = res.getString(2);
                tab[ile][2] = res.getString(3);
                tab[ile][3] = res.getString(5);
                ile++;
            }
            conn.close();
            return tab;
        }catch(Exception e){
            e.printStackTrace();
            Plik p = new Plik();
            try {
                p.zapisPliku(e.toString());
            } catch (IOException ex) {
                Logger.getLogger(Mysql.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        return null;
    }
    
    public void Wyp_Delete(String ID) throws SQLException{
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url+db,login,pass);
            Statement st = conn.createStatement();
            st.executeUpdate("delete from wypoczalnia where ID_wypocz='"+ID+"'");
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
            Plik p = new Plik();
            try {
                p.zapisPliku(e.toString());
            } catch (IOException ex) {
                Logger.getLogger(Mysql.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    } 
     
    public int Tabel_row(String tab) throws SQLException{
       try{
            int ile = 0;
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url+db,login,pass);
            Statement st = conn.createStatement();
            ResultSet res = st.executeQuery("select * from "+tab);
            while(res.next()){
                ile++;
            }
            conn.close();
            return ile;
        }catch(Exception e){
            e.printStackTrace();
            Plik p = new Plik();
            try {
                p.zapisPliku(e.toString());
            } catch (IOException ex) {
                Logger.getLogger(Mysql.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
       return 0;
    }
    
    public int Tabel_row_Wyp(String ID) throws SQLException{
       try{
            int ile = 0;
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url+db,login,pass);
            Statement st = conn.createStatement();
            ResultSet res = st.executeQuery("select * from wypoczalnia where ID_Klient='"+ID+"'");
            while(res.next()){
                ile++;
            }
            conn.close();
            return ile;
        }catch(Exception e){
            e.printStackTrace();
            Plik p = new Plik();
            try {
                p.zapisPliku(e.toString());
            } catch (IOException ex) {
                Logger.getLogger(Mysql.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
       return 0;
    }
    
    public int Tabel_col(String tab) throws SQLException{
       try{
            int ile = 0;
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url+db,login,pass);
            Statement st = conn.createStatement();
            ResultSet res = st.executeQuery("select * from "+tab);
            ResultSetMetaData metadata = res.getMetaData();
            int column = metadata.getColumnCount();
            conn.close();
            return column;
        }catch(Exception e){
            e.printStackTrace();
            Plik p = new Plik();
            try {
                p.zapisPliku(e.toString());
            } catch (IOException ex) {
                Logger.getLogger(Mysql.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        return 0;
    }
    
    public String[] Wyp_Search_Mag(String ID) throws SQLException{
       try{
            int col = Tabel_col("wypoczalnia");
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url+db,login,pass);
            Statement st = conn.createStatement();
            ResultSet res = st.executeQuery("select * from wypoczalnia where ID_magazyn='"+ID+"'");
            String tab[] = new String[col];
            while(res.next()){
               for(int i=0;i<col;i++){
                   tab[i] = res.getString(i+1);
               }
            }
            conn.close();
            return tab;
        }catch(Exception e){
            e.printStackTrace();
            Plik p = new Plik();
            try {
                p.zapisPliku(e.toString());
            } catch (IOException ex) {
                Logger.getLogger(Mysql.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        return null;
    }
    
    public int Wyp_Is_Mag(String ID) throws SQLException{
       try{
            int cout = 0;
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url+db,login,pass);
            Statement st = conn.createStatement();
            ResultSet res = st.executeQuery("select * from wypoczalnia where ID_magazyn='"+ID+"'");
            while(res.next()){
               cout++;
            }
            conn.close();
            return cout;
        }catch(Exception e){
            e.printStackTrace();
            Plik p = new Plik();
            try {
                p.zapisPliku(e.toString());
            } catch (IOException ex) {
                Logger.getLogger(Mysql.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        return 0;
    }
    
    public int Wyp_Is_Klient(String ID) throws SQLException{
       try{
            int cout = 0;
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url+db,login,pass);
            Statement st = conn.createStatement();
            ResultSet res = st.executeQuery("select * from wypoczalnia where ID_klient='"+ID+"'");
            while(res.next()){
               cout++;
            }
            conn.close();
            return cout;
        }catch(Exception e){
            e.printStackTrace();
            Plik p = new Plik();
            try {
                p.zapisPliku(e.toString());
            } catch (IOException ex) {
                Logger.getLogger(Mysql.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        return 0;
    }
    
     public void Wyp_Add(String ID_K, String ID_M, String D_W, String D_Z) throws SQLException{
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url+db,login,pass);
            Statement st = conn.createStatement();
            String query = "insert into wypoczalnia(ID_klient,ID_Magazyn,Data_wypocz,Data_zwrot) values ('"+ID_K+"','"+ID_M+"','"+D_W+"','"+D_Z+"')";
            //System.out.println(query);
            st.executeUpdate(query);
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
            Plik p = new Plik();
            try {
                p.zapisPliku(e.toString());
            } catch (IOException ex) {
                Logger.getLogger(Mysql.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    
}
