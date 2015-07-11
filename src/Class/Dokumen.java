/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

import java.util.*;
import Utilitas.*;

public class Dokumen {

    private int DokKe;
    private String judul;
    private String teks;
    private String katagori;
    public HashMap<String, Bobot> infoText;
    private Vector allTerm;

    public Dokumen(int idDok, String text, String kat) {
        //  System.out.println(text);
        this.DokKe = idDok;

        this.teks = text;
        this.katagori = kat;
        this.allTerm = new Vector();
        infoText = HitungInfo(this.teks);

    }

    public Dokumen(int idDok, String text) {
        this.DokKe = idDok;

        this.teks = text;
        this.allTerm = new Vector();
        infoText = HitungInfo(this.teks);

    }

    public Dokumen() {
    }

    // Struktur data untuk menyinpan term dan frekuensinya
    private HashMap<String, Bobot> HitungInfo(String text) {
        StringTokenizer tjl = new StringTokenizer(text);
        int n = tjl.countTokens();

        HashMap<String, Bobot> h = new HashMap<String, Bobot>();
        for (int i = 0; i < n; i++) {
            String kunci = tjl.nextToken();
            if (!this.allTerm.contains(kunci)) {
                this.allTerm.addElement(kunci);
            }

            if (!h.containsKey(kunci)) {
                h.put(kunci, new Bobot());
            } else {
                Bobot b = h.get(kunci);
                b.Naikkan();
                h.put(kunci, b);
            }
        }
        return h;
    }

    // untuk mendapatkan semua term unik dari judul dan teks
    public Vector getAllTerm() {
        return this.allTerm;
    }

    public HashMap<String, Bobot> getInfoText() {
        return this.infoText;
    }

    public int getIdDok() {
        return this.DokKe;
    }

    public String getKat() {
        return this.katagori;
    }

    public void cetak() {

        System.out.println("\ntext ");
        for (Map.Entry<String, Bobot> entri : infoText.entrySet()) {
            System.out.print(entri.getKey() + " : ");
            System.out.print(entri.getValue().getFrek());
        }

        /**
         * System.out.println("\nAllTerm"); for (int i=0; i<allTerm.size();
         * i++){ System.out.println((String)allTerm.elementAt(i)); }
         */
    }
}
