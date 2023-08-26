/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Main;

/**
 *
 * @author Pavel
 */
public class Short_ID {
    
    public String ID_K(String value){
        int n = 5;
        char[] val = value.toCharArray();
        char[] ID = new char[n];
        for(int i=0;i<n;i++){
            ID[i] = val[i+3];
        }
        return String.valueOf(ID);
    }
    
    public String ID_M(String value){
        int n = 8;
        char[] val = value.toCharArray();
        char[] ID = new char[n];
        for(int i=0;i<n;i++){
            ID[i] = val[i+3];
        }
        return String.valueOf(ID);
    }
    
    public String ID_W(String value){
        int n = 4;
        char[] val = value.toCharArray();
        char[] ID = new char[n];
        for(int i=0;i<n;i++){
            ID[i] = val[i+3];
        }
        return String.valueOf(ID);
    }
}
