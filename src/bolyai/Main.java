package bolyai;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // try-with-resources (try speciális esete)
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
