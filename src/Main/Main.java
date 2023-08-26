/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Main;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author user
 */

public class Main {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, SQLException {
        // TODO code application logic here
        /*
        int n=4;
        ArrayList<Person> pp = new ArrayList<Person>();
        for(int i=0;i<n;i++){
            Person p = new Person();
            p.Add_Klient(13243,"Paweł", "Nowak", "Mickiewicza", "543", "34-344", "Kraków");
            p.wypocz.data_wypocz.Add_Date(2, 3, 2014, 12, 14);
            p.wypocz.data_zwrotu.Add_Date(4, 5, 2014, 12, 00);
            p.wypocz.Set_ID_K();
            pp.add(p);
        }
    	
        for(int i=0;i<n;i++){
            //System.out.println(pp.get(i).Klient_r());
        }
        
        Mysql sql = new Mysql();
        sql.Klient();
        System.out.println(" ");
        sql.Magazyn();
        */
        
        Panel js = new Panel();
        js.main(args);
        
        
    	
        //Plik j = new Plik();
        //j.main(args);
        
        
        //dateeeeeeeeeeees data = new dateeeeeeeeeeees();
        
        
        //int a;
        //a = System.in.read();
       
    	//System.out.println(p.wypocz.data_wypocz.Date_r());
        //System.out.println(p.wypocz.ID_K());
    	
    }
    
    
}
