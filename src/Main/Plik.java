/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pavel
 */
public class Plik {
   public static void odczytPlikuTekstowego(String nazwa) throws IOException {
        // klasa FileReader służy do odczytu plików tekstowych
        // następuje automatyczna konwersja odczytanych bajtów na znaki
        // unicode (16 bitowe)
        FileReader plikWe = null;
        try {
            plikWe = new FileReader(nazwa);
            System.out.println("Odczyt znak po znaku:\n");
            int c;
            // odczyt pliku znak po znaku i wyświetlenie na ekranie monitora
            while ((c = plikWe.read()) != -1) { // jeżeli c = -1 to koniec pliku
                System.out.print((char)c);
            }
        } finally { // klauzula finally służy do wykonania instrukcji
                    // niezależnie od tego kiedy i w jaki sposób (normalnie lub
                    // przez wyjątek) zostało zakończone wykonywanie bloku try
            if (plikWe != null) {
                plikWe.close(); // zamknięcie pliku
            }
        }
        
        // odczyt wiersz po wierszu
        BufferedReader plik2 = null;
        try {            
            plik2 = new BufferedReader(new FileReader(nazwa));
            System.out.println("\n\nOdczyt buforowany:\n");
            String l = plik2.readLine();
            while (l != null) {
                System.out.println(l);
                l = plik2.readLine();
            }
        } finally {
            if (plik2 != null) {
                plik2.close();
            }
        }
    }
 
    public static void zapisPliku(String tekst) throws IOException {
        FileWriter plikWy = null;
        Date d = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd_hh:mm:ss");
        String day = df.format(d);
        day += ".txt";
        try {
            // tworzy nowy plik jeżeli nie istnieje, w przeciwnym przypadku
            // usuwa zawartość pliku i nadpisuje od początku
            plikWy = new FileWriter(day);
            // zapis łańcucha
            //String tekst = "Dziś jest piękny dzień\nna egzamin z programowania.\n";
            plikWy.write(tekst);
            // zapis po znaku
 
        } finally {
            if (plikWy != null) {
                plikWy.close();
            }
        }
    }
 
    public static void odczytFormatowany(String plik) throws IOException {
        Scanner plikWe = null;
        try {
            // "cebulkowy" sposób tworzenia obiektu klasy Scanner
            // BufferedReader zapewnia efektywny odczyt pliku dzięki
            // odczytowi blokowemu a nie znak po znaku
            plikWe = new Scanner(new BufferedReader(new FileReader(plik)));
            // sposób mniej efektywny też działa
            // plikWe = new Scanner(new FileReader(nazwa));
 
            // wczytaj kolejno wszystkie wyrazy (tokeny) z pliku, zsumuj te które
            // są liczbami całkowitymi
            int suma = 0;
            while (plikWe.hasNext()) { // czy jest coś do odczytu?
                if (plikWe.hasNextInt()) {
                    int l = plikWe.nextInt();
                    suma += l;
                } else {
                    plikWe.next(); // wczytaj kolejny "wyraz", ale nie rób z nim nic
                }
            }
            System.out.format("Suma wczytanych liczb wynosi: %d\n", suma);
        } finally {
            if (plikWe != null) {
                plikWe.close();
            }
        }
    }
 
    public static boolean czyPlikIstnieje(String plik) {
        // Klasa File w Javie służy do reprezentacji i zarządzania ścieżkami do
        // plików i folderów, można jej użyć np. do sprawdzenia, czy dany plik
        // istnieje, jak pokazano poniżej.
        File f = new File(plik);
        return f.exists() && f.isFile();
    }
 
    public static void main(String[] args) throws IOException {
        String nazwaPliku = "klient.txt";
 
        if (czyPlikIstnieje(nazwaPliku)) {
            System.out.println("Plik " + nazwaPliku + " istnieje");
        } else {
            System.out.println("Nie ma pliku o nazwie " + nazwaPliku);
        }
 
        //zapisPliku(nazwaPliku);
        odczytPlikuTekstowego(nazwaPliku);
        //odczytFormatowany(nazwaPliku);
    }
}
