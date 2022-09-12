package bolyai;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // try-with-resources (try speciális esete)
        // FileReader csak karakterenként tud olvasni fájlból (nem felhasználóbarát)
        // becsomagoljuk egy BufferedReaderbe, az képes pl. soronként olvasni (de sok egyéb plusz funkciót is nyújt)
        // a BufferedReader "összegyűjti" a karaktereket, és soronként adja vissza
        try (BufferedReader reader = new BufferedReader(new FileReader("uzemanyag.txt"))) {
            // 1. fájl beolvasása
            // 2. adatok feldolgozása
            // 3. fájl írása
            // ha bármelyik lépésnél kivétel történik, akkor a többi nem fut le
            // (a catch blokkban folytatódik a végrehajtás).

            // dinamikus adatszerkezet (mint a tömb, de átméretezkető)
            // List<T> interfész: 2 implementáció van (ArrayList<T>, LinkedList<T>)
            // felcserélhetőek, de bizonyos műveletek az egyiknél gyorsabban,
            // más műveletek pedig a másiknál (ArrayList<T> egy jó alapértelmezett választás)
            // <> diamond operátor: ArrayList átveszi a List típusát
            List<Arfolyam> arfolyamok = new ArrayList<>();

            // 2. feladat
            String line;

            while ((line = reader.readLine()) != null) {
                String[] cellak = line.split(";");

                Arfolyam arfolyam = new Arfolyam();
                arfolyam.datum = cellak[0];
                arfolyam.benzin = Integer.parseInt(cellak[1]);
                arfolyam.dizel = Integer.parseInt(cellak[2]);

                arfolyamok.add(arfolyam);
            }

            // 3. feladat
            System.out.printf("3. feladat: Változások száma: %d\n", arfolyamok.size());

            // 4. feladat (minimum keresés)
            // lista első eleme is jó lenne +végtelen helyett
            int minKulonbseg = Integer.MAX_VALUE;

            // for-each
            for (Arfolyam arfolyam : arfolyamok) {
                int kulonbseg = Math.abs(arfolyam.benzin - arfolyam.dizel);

                if (kulonbseg < minKulonbseg) {
                    minKulonbseg = kulonbseg;
                }
            }

            System.out.printf("4. feladat: A legkisebb különbség: %d\n", minKulonbseg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
