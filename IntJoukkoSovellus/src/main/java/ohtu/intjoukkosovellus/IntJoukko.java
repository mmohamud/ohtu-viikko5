package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int KAPASITEETTI = 5, // aloitustalukon koko
            OLETUSKASVATUS = 5;  // luotava uusi taulukko on 
    // näin paljon isompi kuin vanha
    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] ljono;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public void taulukonAlustus(int kapasiteetti) {
        ljono = new int[kapasiteetti];
        for (int i = 0; i < ljono.length; i++) {
            ljono[i] = 0;
        }
        alkioidenLkm = 0;
    }

    public IntJoukko() {
        ljono = new int[KAPASITEETTI];
        for (int i = 0; i < ljono.length; i++) {
            ljono[i] = 0;
        }
        alkioidenLkm = 0;
        this.kasvatuskoko = OLETUSKASVATUS;
    }

    public IntJoukko(int kapasiteetti) {
        if (kapasiteetti < 0) {
            return;
        }
        taulukonAlustus(kapasiteetti);
        this.kasvatuskoko = OLETUSKASVATUS;

    }

    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0) {
            throw new IndexOutOfBoundsException("Kapasitteetti väärin");//heitin vaan jotain :D
        }
        if (kasvatuskoko < 0) {
            throw new IndexOutOfBoundsException("kapasiteetti2");//heitin vaan jotain :D
        }
        taulukonAlustus(kapasiteetti);
        this.kasvatuskoko = kasvatuskoko;
    }

    public void lisaa(int luku) {
        if (!kuuluu(luku)) {
            if (ljono.length < alkioidenLkm) {
                ljono[alkioidenLkm] = luku;
                alkioidenLkm++;
            } else {
                ljono = kopioiTaulukko(ljono);
                ljono[alkioidenLkm] = luku;
                alkioidenLkm++;
            }
        }
    }

    public boolean kuuluu(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == ljono[i]) {
                return true;
            }
        }
        return false;
    }

    public void poista(int luku) {
        int kohta = missaKohtaa(luku);
        ljono[kohta] = ljono[alkioidenLkm - 1];
        alkioidenLkm--;
    }

    public int missaKohtaa(int luku) {
        int kohta = -1;
        for (int i = 0; i < alkioidenLkm; i++) {
            if (ljono[i] == luku) {
                kohta = i;
            }
        }
        return kohta;
    }

    private int[] kopioiTaulukko(int[] vanha) {
        int[] uusiTaulukko = new int[vanha.length + OLETUSKASVATUS];
        for (int i = 0; i < vanha.length; i++) {
            uusiTaulukko[i] = vanha[i];
        }
        return uusiTaulukko;
    }

    public int mahtavuus() {
        return alkioidenLkm;
    }

    public String toStringTyhjaJoukko() {
        return "{}";
    }

    public String toStringJoukkoJossaYksiLuku() {
        return "{" + ljono[0] + "}";
    }

    public String toStringJoukkoJossaUseitaLukuja() {
        String tuotos = "{";
        for (int i = 0; i < alkioidenLkm - 1; i++) {
            tuotos += ljono[i] + ", ";
        }
        tuotos += ljono[alkioidenLkm - 1] + "}";
        return tuotos;
    }

    @Override
    public String toString() {
        if (alkioidenLkm == 0) {
            return toStringTyhjaJoukko();
        } else if (alkioidenLkm == 1) {
            return toStringJoukkoJossaYksiLuku();
        } else {
            return toStringJoukkoJossaUseitaLukuja();
        }
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = ljono[i];
        }
        return taulu;
    }
    

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko x = a;
        for (int i : b.toIntArray()) {
            x.lisaa(i);
        }
        return x;
    }


    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko y = a;
        for (int i : a.toIntArray()) {
            for (int j : b.toIntArray()) {
                if(i == j) {
                    y.lisaa(j);
                }
            }
        }
        return y;
    }

    public static IntJoukko erotus(IntJoukko a, IntJoukko b) {
        IntJoukko x = a;
        for (int i : b.toIntArray()) {
            x.poista(i);
        }
        return x;
    }
}
