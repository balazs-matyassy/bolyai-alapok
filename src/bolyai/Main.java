package bolyai;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Main {

    // PIPELINE (csővezeték)
    // 1. adatok beolvasása -> 2. adatok feldolgozása -> 3. adatok kiírása
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
            // Ebben az esetben olvasásra nyitunk meg egy fájlt.
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
            // (4. feladat)
            System.out.printf("4. feladat: Döntők száma: %d\n", dontok.size());

            // (5. feladat)
            int ossz_kulonbseg = 0;

            // for-each (összegzés)
            for (Donto donto : dontok) {
                // adott meccshez tartozó különbség kiszámítása
                String[] eredmenyek = donto.eredmeny.split("-");
                int gyoztesEredmeny = Integer.parseInt(eredmenyek[0]);
                int vesztesEredmeny = Integer.parseInt(eredmenyek[1]);
                // Math.abs nem feltétlenül szükséges, mivel gyoztesEredmeny >= vesztesEredmeny
                int kulonbseg = Math.abs(gyoztesEredmeny - vesztesEredmeny);

                ossz_kulonbseg += kulonbseg;
            }

            // double: tört szám
            // ossz_kulonbseg / dontok.size(): egész számok osztása (elveszik a tört)
            // double tort = 5 / 3; // ? -> tort = 1; (tört elveszik, mivel 2 egészet osztunk)
            // (double): kasztolás (törtté alakítás) -> NEM jelentkezik a probléma
            double atlag_kulonbseg = (double) ossz_kulonbseg / dontok.size();
            System.out.printf("5. feladat: Átlagos pontkülönbség: %.1f\n", atlag_kulonbseg);

            // 6. feladat (maximum keresés - OOP verzió)
            // nem számot, hanem teljes sort / rekordot / objektumot... keresünk
            Donto maxNezoszam = null; // null: érvénytelen érték (nincs adat)

            for (Donto donto : dontok) {
                // első iterációnál mindenképpen felülírásra kerül (null miatt)
                // || VAGY (short circuit / rövidzáras kiértékelés)
                // HA döntő == null, akkor a  donto.nezoszam > maxNezoszam.nezoszam összehasonlítás
                // NEM kerül kiértékelésre.
                // donto.nezoszam >= null.nezoszam -> NullPointerException kivétel keletkezik.
                if (maxNezoszam == null || donto.nezoszam > maxNezoszam.nezoszam) {
                    maxNezoszam = donto;
                }
            }

            RomaiSorszam romaiSorszam = new RomaiSorszam(maxNezoszam.sorszam);
            String arabSorszam = romaiSorszam.getArabSsz();

            // redundáns, korábban már elvégeztük
            String[] eredmenyek = maxNezoszam.eredmeny.split("-");
            int gyoztesEredmeny = Integer.parseInt(eredmenyek[0]);
            int vesztesEredmeny = Integer.parseInt(eredmenyek[1]);

            System.out.println("6. feladat: Legmagasabb nézőszám a döntők során:");
            System.out.printf("\tSorszám (dátum): %s (%s)\n", arabSorszam, maxNezoszam.datum);
            System.out.printf(
                    "\tGyőztes csapat: %s, szerzett pontok: %d\n",
                    maxNezoszam.gyoztes,
                    gyoztesEredmeny
            );
            System.out.printf(
                    "\tVesztes csapat: %s, szerzett pontok: %d\n",
                    maxNezoszam.vesztes,
                    vesztesEredmeny
            );
            System.out.printf("\tHelyszín: %s\n", maxNezoszam.helyszin);
            System.out.printf("\tVáros, állam: %s\n", maxNezoszam.varosAllam);
            System.out.printf("\tNézőszám: %d\n", maxNezoszam.nezoszam);


            /*
                3. Adatok kiírása
             */
            // Írásra nyitunk meg egy fájlt.
            // Itt sincs hibakezelés (ebben az esetben a külső catch kezeli az esetleges hibákat).
            // 7. feladat
            try (PrintWriter writer = new PrintWriter("SuperBowlNew.txt")) {
                // Létezik HasMap<T> is. Kicsit gyorsabb, de nem rendezett.
                Map<String, Integer> reszvetelekSzama = new TreeMap<>();

                // Ha szeretnénk fejlécet:
                writer.println("Ssz;Dátum;Győztes;Eredmény;Vesztes;Nézőszám");

                // for-each szerkezet
                // Egy gyűjtemény (List<T>, Set<T> vagy T[] (tömb)) minden elemét értékenként bejárunk.
                // A feldolgozáshoz nincs szükség az indexre.
                for (Donto donto : dontok) {
                    romaiSorszam = new RomaiSorszam(maxNezoszam.sorszam);
                    arabSorszam = romaiSorszam.getArabSsz();
                    // régi érték + 1 vagy 1 (ha még nem volt régi érték)
                    int gyoztesSzereplesek = reszvetelekSzama.getOrDefault(donto.gyoztes, 0) + 1;
                    reszvetelekSzama.put(donto.gyoztes, gyoztesSzereplesek); // számláló frissítése
                    int vesztesSzereplesek = reszvetelekSzama.getOrDefault(donto.vesztes, 0) + 1;
                    reszvetelekSzama.put(donto.vesztes, vesztesSzereplesek);

                    // Döntő visszaalakítása sorrá a segédmetódus segítségével.
                    // Ebben az esetben változatlan formában írjuk ki az értékeket,
                    // de amúgy lehtőség lenne a dontobolSor metódusban egyéb formátumra is konvertálni.
                    String sor = dontobolSor(donto, arabSorszam, gyoztesSzereplesek, vesztesSzereplesek);
                    // Konvertált sor kiírása a megnyitott fájlba.
                    // lsd. System.out.println: NINCS formázás, VAN sortörés.
                    writer.println(sor);
                }

                // Alternatív megoldás:
                /* for (int i = 0; i < dontok.size(); i++) {
                    Donto donto = dontok.get(i);
                    String sor = dontobolSor(donto);
                    writer.println(sor);
                } */
            }
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

    /*
        3. Adatok kiírása
    */
    private static String dontobolSor(
            Donto donto, String arabSorszam,
            int gyoztesSzereplesek, int vesztesSzereplesek
    ) {
        return arabSorszam
                + ";" + donto.datum
                + ";" + donto.gyoztes + " (" + gyoztesSzereplesek + ")"
                + ";" + donto.eredmeny
                + ";" + donto.vesztes + " (" + vesztesSzereplesek + ")"
                + ";" + donto.nezoszam; // számból automatikus a szöveggé konvertálás
    }

}
