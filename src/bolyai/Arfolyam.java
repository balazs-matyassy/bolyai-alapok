package bolyai;

import java.util.Objects;

public class Arfolyam {
    public String datum;

    public int benzin;

    public int dizel;

    // üzleti értelemben vett egyenlőség (döntés kérdése):
    // - minden mező egyenlő
    // - kulcs egyenlő (frissíteni kell-e egy régi értéket vagy új értéket kell létrehozni)


    // csak dátumra (kulcsra) támaszkodó egyenlőség
    @Override // @Override: szülő (Object) is rendelkezik vele (ebben az esetben minden objektum)
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Arfolyam)) return false;
        Arfolyam arfolyam = (Arfolyam) o;
        return datum.equals(arfolyam.datum);
    }

    // Keresés gyorsítására szolgál, mindig párban kell az equals-al létrehozni.
    // Nem támaszkodhat több mezőre, mint az equals (kevesebbre igen).
    // Kulcs részhalmazára hivatkozhat.
    @Override
    public int hashCode() {
        return Objects.hash(datum);
    }

    // Szöveges reprezentáció.
    // Pl. System.out.println kompatibilis az objektummal.
    @Override
    public String toString() {
        return "Arfolyam{" +
                "datum='" + datum + '\'' +
                ", benzin=" + benzin +
                ", dizel=" + dizel +
                '}';
    }
}
