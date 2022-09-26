package bolyai;

public class Main {

    public static void main(String[] args) {
        // memória (RAM) 2 részre van bontva Java programoknál
        // - STACK: itt csak "kicsi" értékek kerülhetnek tárolásra
        //      - primitívek (int, boolean, double, float, long, char...)
        //      - referenciák (minden más): hivatkozás a HEAP-re
        // - HEAP: itt kerülnek a "nagy" objektumok tárolásra
        Arfolyam arfolyam1 = new Arfolyam();
        arfolyam1.datum = "2020.01.01.";
        arfolyam1.benzin = 390;
        arfolyam1.dizel = 370;

        Arfolyam arfolyam2 = new Arfolyam();
        arfolyam2.datum = "2020.01.01.";
        arfolyam2.benzin = 430;
        arfolyam2.dizel = 400;

        Arfolyam maxBenzin = arfolyam2;

        arfolyam1.benzin += 10;
        arfolyam2.benzin += 10;

        System.out.println(arfolyam1.benzin);
        System.out.println(arfolyam2.benzin);
        System.out.println(maxBenzin.benzin);

        // MINDIG TRUE: == az azt vizsgálja, hogy AZONOS objektumról van-e szó
        // @704 == @704
        System.out.println(arfolyam2 == maxBenzin);

        if (arfolyam1.equals(arfolyam2)) {
            // Üzleti döntés: Csak a kulcs szerepel az equals-ban.
            System.out.println("A két bejegyzés azonos napra vonatkozik, frissíteni kell az értékeket.");
        }

        // Miért nem szabad szövegeket == -val összehasonlítani.
        // Válasz: Stringek is objektumok.
        String uzenet1 = "Hello World!";
        String uzenet2 = "Hello ";
        uzenet2 += "World!";

        System.out.println(uzenet1 == uzenet2); // FALSE: NEM azonos referencia
        System.out.println(uzenet1.equals(uzenet2)); // TRUE: Üzletileg egyenlő értékek

        System.out.println(uzenet1); // toString() meghívásra kerül
    }

}
