package Main;

public class Wypozyczalnia{
    static int id_wypocz;
    static int id_klient;
    static int id_magazyn;
    Dates data_wypocz;
    Dates data_zwrotu;
    
    public Wypozyczalnia()
    {
    	data_wypocz = new Dates();
    	data_zwrotu = new Dates();
    }
    
    public void Set_ID_K(){id_klient = Person.id_klient;}
    public int ID_W(){return id_wypocz;}
    public int ID_K(){return id_klient;}
    public int ID_M(){return id_magazyn;}
}