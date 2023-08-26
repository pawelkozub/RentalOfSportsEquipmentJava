package Main;

public class Magazyn{
    static int id_magazyn;
    static int id_kategorie;
    String nazwa;
    String model;
    String rozmiar;
    double cena;
    int sztuk_baza;
    int sztuk_obecny;
    
    public Magazyn(){
        
    }
    
    public int ID_M(){return id_magazyn;}
    public int ID_K(){return id_kategorie;}
}