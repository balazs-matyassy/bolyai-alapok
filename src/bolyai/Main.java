package bolyai;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // Ha bárhol történik hiba, akkor megszakad a try blokk,
        // és a catch blokkban folytatódik a program végrehajtása.
        // 1. - adatok beolvasása
        // 2. - adatok feldolgozása
        // 3. - adatok kiírása
        // Ha bárhol hiba van, akkor a többi lépést NEM kell folytatni.
        try {
            // List<T>: Dinamikusan bővülő tömb.
            // T: típusparaméter (milyen típusú értékeket tárolunk a listában)
            // <>: diamond ("gyémánt") operátor:
            //      new ArrayList<>() típusa a bal oldalon megadott típus lesz,
            //      new ArrayList<Donto>()
            // List<T>: az interfész
            // ArrayList<T>: konkrét implementáció (gyorsan lehet benne az adott helyen megnézni)
            //      olvasásra optimalizálva
            // LinkedList<T>: konkrét implementáció (gyorsan lehet a végére új beszúrni)
            //      írásra optimalizálva
            // Ezen a szinten bármelyik haszálható, nem jelent nagy különbséget.
            // Ha nincs jobb ötletünk, akkor használjuk az ArrayList<T>-t.
            List<Donto> dontok = new ArrayList<>();

            /*
                1. Adatok beolvasása
             */

            // try-with resources
            // Hibától függetlenül lezárjuk a megnyitott fájlt a blokk végén.
            // Nincs catch blokk, emiatt nem kezeli a hibákat,
            // de nem tartjuk feleslegesen nyitva a fájlt.
            try (BufferedReader reader = new BufferedReader(new FileReader("SuperBowl.txt"))) {
                reader.readLine(); // fejléc "eldobása", nem mentjük semmilyen változóba

                String sor; // következő beolvasott sor

                // Ha vége van a fájlnak, akkor a reader.readLine() null-t ad vissza.
                // Összetett kifejezés:
                // 1. line változóban elmenti a beolvasott értéket
                // 2. ellenőrizzük, hogy végére értünk-e a fájlnak
                while ((sor = reader.readLine()) != null) {
                    // sor konvertálása döntővé a segédmetódus segítségével
                    Donto donto = sorbolDonto(sor);
                    // döntő hozzáfűzése a lista végére
                    dontok.add(donto);
                }
            }

            /*
                2. Adatok feldolgozása
             */
            // System.out.print: NEM formázott, NINCS sortörés
            // System.out.println: NEM formázott, VAN sortörés
            // System.out.printf: formázott, NINCS sortörés
            System.out.printf("4. feladat: Döntők száma: %d\n", dontok.size());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
        1. Adatok beolvasása
    */
    private static Donto sorbolDonto(String sor) {
        // A fájl 1 sorának "széttörése" az elválasztó karakter (";") mentén
        // különálló értékekre.
        String[] ertekek = sor.split(";");

        // Új (üres, mivel nincs konstruktor) Donto példány létrehozása.
        Donto donto = new Donto();

        // Döntő értékeinek a beállítása.
        donto.sorszam = ertekek[0];
        donto.datum = ertekek[1];
        donto.gyoztes = ertekek[2];
        donto.eredmeny = ertekek[3];
        donto.vesztes = ertekek[4];
        donto.helyszin = ertekek[5];
        donto.varosAllam = ertekek[6];
        donto.nezoszam = Integer.parseInt(ertekek[7]);

        // Kész döntő visszaadása a hívónak (main metódus)
        return donto;
    }

    private static String dontobolSor(Donto donto) {
        return null;
    }

}
