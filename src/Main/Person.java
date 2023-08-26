package Main;
public class Person{
    static int id_klient;
    private String imie;
    private String nazwisko;
    private String ulica;
    private String nr_dom;
    private String kod_poczt;
    private String miasto;
    String mail;
    String login;
    String pass; 
    Wypozyczalnia wypocz;
    
    public Person(){
        wypocz = new Wypozyczalnia();
    }
    
    public void Add_Klient(int id, String i, String n, String u, String nr, String kod, String m){
    	id_klient = id;
        imie = i;
    	nazwisko = n;
        ulica = u;
        nr_dom = nr;
        kod_poczt = kod;
        miasto = m;
    }
    
    public String Klient_r(){
        return "Nr:"+id_klient+" Klient: "+imie+" "+nazwisko+" Adres: "+ulica+" "+nr_dom+", "+kod_poczt+" "+miasto;
    }
    public int ID_K(){return id_klient;}
    public String Imie(){return imie;}
    public String Nazwisko(){return nazwisko;}
    public String Ulica(){return ulica;}
    public String Nr_dom(){return nr_dom;}
    public String Kod(){return kod_poczt;}
    public String Miasto(){return miasto;}
}