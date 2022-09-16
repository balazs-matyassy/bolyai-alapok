package bolyai;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // try-with-resources (try speciális esete)
        // FileReader csak karakterenként tud olvasni fájlból (nem felhasználóbarát)
        // becsomagoljuk egy BufferedReaderbe, az képes pl. soronként olvasni (de sok egyéb plusz funkciót is nyújt)
        // a BufferedReader "összegyűjti" a karaktereket, és soronként adja vissza
        try {
            // 8. feladat
            BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

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

            // ha a try blokkob belül keletkezik egy hiba,
            // akkor is lezárásra kerülnek az erőforrások
            try (BufferedReader reader = new BufferedReader(new FileReader("uzemanyag.txt"))) {
                while ((line = reader.readLine()) != null) {
                    String[] cellak = line.split(";");

                    Arfolyam arfolyam = new Arfolyam();
                    arfolyam.datum = cellak[0];
                    arfolyam.benzin = Integer.parseInt(cellak[1]);
                    arfolyam.dizel = Integer.parseInt(cellak[2]);

                    arfolyamok.add(arfolyam);
                }
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

            // 5. feladat (számlálás)
            int szamlalo = 0;

            for (Arfolyam arfolyam : arfolyamok) {
                int kulonbseg = Math.abs(arfolyam.benzin - arfolyam.dizel);

                if (kulonbseg == minKulonbseg) {
                    szamlalo++; // szamlalo += 1;
                }
            }

            System.out.printf("5. feladat: A legkisebb különbség előfordulása: %d\n", szamlalo);

            // 6. feladat (keresés)
            boolean voltESzokonap = false;

            for (Arfolyam arfolyam : arfolyamok) {
                if (szokoNapE(arfolyam.datum)) {
                    voltESzokonap = true;
                    break; // befejezi a ciklust
                }
            }

            if (voltESzokonap) {
                System.out.println("6. feladat: Volt változás szökőnapon!");
            } else {
                System.out.println("6. feladat: Nem volt változás szökőnapon!");
            }

            // 7. feladat
            try (PrintWriter writer = new PrintWriter("euro.txt")) {
                for (Arfolyam arfolyam : arfolyamok) {
                    writer.println(arfolyambolSor(arfolyam));
                }
            }

            // 8. feladat
            int evszam;

            do {
                // NEM println, a felhasználó írja az entert
                System.out.print("8. feladat: Kérem adja meg az évszámot [2011..2016]: ");
                line = console.readLine();
                evszam = Integer.parseInt(line);
            } while (evszam < 2011 || evszam > 2016);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean szokoNapE(String datum) {
        String[] reszek = datum.split("\\.");

        int ev = Integer.parseInt(reszek[0]);
        int honap = Integer.parseInt(reszek[1]);
        int nap = Integer.parseInt(reszek[2]);

        return ev % 4 == 0
                && honap == 2
                && nap == 24;
    }

    // 7. feladat
    private static String arfolyambolSor(Arfolyam arfolyam) {
        double benzinEur = arfolyam.benzin / 307.7;
        double dizelEur = arfolyam.dizel / 307.7;

        return String.format("%s;%.2f;%.2f", arfolyam.datum, benzinEur, dizelEur);
    }

}
