package bolyai;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Application {

    public static void main(String[] args) {


        try {
            /*
                1. adatok beolvasása
             */
            List<Telek> telkek = new ArrayList<>();
            int adosavA, adosavB, adosavC;

            // BufferedReader reader = new BufferedReader(new FileReader("utca.txt"));
            // reader.close();
            // Try-with resources automatikusan meghívja a close-t a blokk végén (hiba esetén is meghívja)
            try (BufferedReader reader = new BufferedReader(new FileReader("utca.txt"))) {
                String fejlec = reader.readLine();
                String[] ertekek = fejlec.split(" ");
                adosavA = Integer.parseInt(ertekek[0]);
                adosavB = Integer.parseInt(ertekek[1]);
                adosavC = Integer.parseInt(ertekek[2]);

                String sor;

                while ((sor = reader.readLine()) != null) {
                    Telek telek = sorbolTelek(sor);
                    telkek.add(telek);
                }
            }

            /*
                2. adatok feldolgozása
             */
            System.out.printf("Telkek száma: %d\n", telkek.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
        1. adatok beolvasása
     */
    private static Telek sorbolTelek(String sor) {
        String[] ertekek = sor.split(" ");

        Telek telek = new Telek();
        telek.adoszam = ertekek[0];
        telek.utca = ertekek[1];
        telek.hazszam = ertekek[2];
        telek.adosav = ertekek[3];
        telek.alapterulet = Integer.parseInt(ertekek[4]);

        return telek;
    }

}
