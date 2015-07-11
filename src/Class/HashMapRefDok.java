package Class;

import Utilitas.*;
import java.io.File;
import java.text.DecimalFormat;
import java.util.*;

public final class HashMapRefDok {

    private HashMap<String, InfoListRefDok> hListInfoRefDok;
    private Matrik mtf, mwi; // mwi(i) = mtf(i)*log(D/df(i);
    private Matrik mtfUji, mwiUji;
    //...
    private Dokumen[] dokLatih, dokUji;
    private Vector<Dokumen> dLatih, dUji;
    private HashMap<Double, String> judulHasil;

    // constructor
    public HashMapRefDok() {
        hListInfoRefDok = new HashMap<String, InfoListRefDok>();
    }

    public HashMapRefDok(Dokumen[] dLatih, Dokumen[] dUji) {
        this.dokLatih = dLatih;
        this.dokUji = dUji;
        hListInfoRefDok = new HashMap<String, InfoListRefDok>();
        for (int i = 0; i < dokLatih.length; i++) {
            EntriData(dokLatih[i], 0);
        }
        for (int i = 0; i < dokUji.length; i++) {
            EntriData(dokUji[i], 1);
        }
        buatMatrik();
        // .....
    }

    public HashMapRefDok(Vector<Dokumen> dLatih, Vector<Dokumen> dUji) {
        this.dLatih = dLatih;
        this.dUji = dUji;
        hListInfoRefDok = new HashMap<String, InfoListRefDok>();
        for (int i = 0; i < dLatih.size(); i++) {
            EntriData(dLatih.elementAt(i), 0);
        }
        for (int i = 0; i < dUji.size(); i++) {
            EntriData(dUji.elementAt(i), 1);
        }
        buatMatrik1();
    }

    public void hitungCw_dan_SigmaTf() {
        judulHasil = new HashMap<Double, String>();
        int i = 0;

        for (Map.Entry<String, InfoListRefDok> term : hListInfoRefDok.entrySet()) {
            InfoListRefDok ilo = term.getValue();
            ArrayList<RefDok> lo = ilo.getListRefDok();

            int k = 0;
            double cw = 0.0;
            double sigma_doc_tf = 0.0;
            double pw = 0.0;
            double doc_tfw = 0.0;
            double gw = 0.0;
            while (k < lo.size()) {
                RefDok rfdok = lo.get(k++);
                if (rfdok.getIdDok() != 1) { // diasumsikan dok1 dijadikan dok uji
                    cw += (double) rfdok.getFText();
                    sigma_doc_tf += (double) rfdok.getFText();
                } else {
                    doc_tfw = (double) rfdok.getFText();
                }
            }
            pw = cw / sigma_doc_tf;
            gw = doc_tfw * pw;

            System.out.println(term.getKey());
            System.out.println("cw = " + cw
                    + "\nsigma_doc_tf = " + sigma_doc_tf
                    + "\npw = " + pw
                    + "\ndoc_tfw = " + doc_tfw
                    + "\ngw = " + gw);
            judulHasil.put(gw, term.getKey());
        }
    }

    private void buatMatrik() {
        int baris = hListInfoRefDok.size(); // jumlah term unik        
        int kolom1 = this.dokLatih.length; // jumlah dokumen uji
        int kolom2 = this.dokUji.length; // jumlah dokumen latih       
        mtf = new Matrik(baris, kolom1);
        mwi = new Matrik(baris, kolom1);
        mtfUji = new Matrik(baris, kolom2);
        mwiUji = new Matrik(baris, kolom2);
        double IDf;
        int i = 0;
        for (Map.Entry<String, InfoListRefDok> term : hListInfoRefDok.entrySet()) {
            String kunci = term.getKey();
            InfoListRefDok ilo = term.getValue();
            int df = ilo.getInfoJumlahRefDok();// jumlah dokumen uji
            // IDf hanya dihitung berdasarkan data latih
           
            IDf = Math.log10((double) kolom1 / (double) df);
          //   if (IDf >=0.0){System.out.println(" makanan");}
             
      if(Double.isInfinite(IDf)){ IDf=0.0;}
            
            ArrayList<RefDok> loDtUji = ilo.getListRefDokUji();
            int l = 0;
            while (l < loDtUji.size()) {
                RefDok rfdok = loDtUji.get(l++);
                int j = rfdok.getIdDok();
                double ftext = (double) rfdok.getFText();
                double tfi = ftext;
                double wi = tfi * IDf;
                mtfUji.setItem(i, j, tfi);
                mwiUji.setItem(i, j, wi);
            }
            ArrayList<RefDok> lo = ilo.getListRefDok();
            int k = 0;
            while (k < lo.size()) {
                RefDok rfdok = lo.get(k++);
                int j = rfdok.getIdDok();
                double ftext = (double) rfdok.getFText();
                double tfi = ftext;
                double wi = tfi * IDf;
                mtf.setItem(i, j, tfi);
                mwi.setItem(i, j, wi);
            }
            i++;
        }
    }

    private void buatMatrik1() {
        int baris = hListInfoRefDok.size(); // jumlah term unik
        int kolom1 = this.dLatih.size(); // jumlah dokumen uji
        int kolom2 = this.dUji.size(); // jumlah dokumen latih

        mtf = new Matrik(baris, kolom1);
        mwi = new Matrik(baris, kolom1);
        mtfUji = new Matrik(baris, kolom2);
        mwiUji = new Matrik(baris, kolom2);

        double IDf;
        int i = 0;
        for (Map.Entry<String, InfoListRefDok> term : hListInfoRefDok.entrySet()) {
            String kunci = term.getKey();
            InfoListRefDok ilo = term.getValue();
            int df = ilo.getInfoJumlahRefDok();// jumlah dokumen uji
            // IDf hanya dihitung berdasarkan data latih
           
            IDf = Math.log10((double) kolom1 / (double) df);
            System.out.println("zzzzzzzzzz"+IDf);
            ArrayList<RefDok> loDtUji = ilo.getListRefDokUji();
            int l = 0;
            while (l < loDtUji.size()) {
                RefDok rfdok = loDtUji.get(l++);
                int j = rfdok.getIdDok();
                double ftext = (double) rfdok.getFText();
                double tfi = ftext;
                double wi = tfi * IDf;
                mtfUji.setItem(i, j, tfi);
                mwiUji.setItem(i, j, wi);
            }

            ArrayList<RefDok> lo = ilo.getListRefDok();
            int k = 0;
            while (k < lo.size()) {
                RefDok rfdok = lo.get(k++);
                int j = rfdok.getIdDok();
                double ftext = (double) rfdok.getFText();
                double tfi = ftext;
                double wi = tfi * IDf;
                mtf.setItem(i, j, tfi);
                mwi.setItem(i, j, wi);
                //System.out.println(wi);
            }
            i++;
        }
    }

    public Matrik getMTF() {
        return this.mtf;
    }

    public Matrik getMWi() {
        return this.mwi;
    }

    public Matrik getMtfUji() {
        return this.mtfUji;
    }

    public Matrik getMWiUji() {
        return this.mwiUji;
    }

    /**
     * Masukkan Dokumen ke dalam struktur data dalam HashMap Langkah-langkahnya
     * : 1. untuk masing-masing term dalam Dokumen evaluasi 2. Jika term
     * tersebut telah ada dalam hashMap sisimpan dokumen ke dalam info dokumen
     * 3. Jika term tersebut belum ada dalam hashMap buat termInfoBaru
     */
    public void EntriData(Dokumen o, int status) {
        Vector vterm = o.getAllTerm();
        HashMap<String, Bobot> htext = o.getInfoText();
        for (int i = 0; i < vterm.size(); i++) {

            int fText = 0;
            String kunci = (String) vterm.get(i);

            if (htext.containsKey(kunci)) {
                fText = (htext.get(kunci)).getFrek();
            }

            RefDok rdok = new RefDok(o, fText);
            if (!hListInfoRefDok.containsKey(kunci)) {
                InfoListRefDok infoRefDok = new InfoListRefDok();
                if (status == 0) {
                    infoRefDok.SisipKeListRefDok(rdok);
                } else {
                    infoRefDok.SisipKeListRefDokUji(rdok);
                }
                hListInfoRefDok.put(kunci, infoRefDok);
            } else {
                InfoListRefDok infoRefDok = hListInfoRefDok.get(kunci);
                if (status == 0) {
                    infoRefDok.SisipKeListRefDok(rdok);
                } else {
                    infoRefDok.SisipKeListRefDokUji(rdok);
                }
            }
        }
    }

    public String[] getTermUnik() {
        int n = hListInfoRefDok.size();
        String[] hasil = new String[n];
        int i = 0;
        for (Map.Entry<String, InfoListRefDok> entry : hListInfoRefDok.entrySet()) {
            String kunci = entry.getKey();
            hasil[i++] = kunci;
        }
        return hasil;
    }

    public Matrik HitungKesamaanDokumen() {
        Matrik mKali = mwiUji.tranpos().kali(mwi);
        Vektor DnormLatih = mwi.HitungNormKolom();
        Vektor DnormUji = mwiUji.HitungNormKolom();
        int nBaris, nKolom;
        nBaris = mKali.getnBaris();
        nKolom = mKali.getnKolom();

        Matrik hasil = new Matrik(nBaris, nKolom);
        for (int i = 0; i < nBaris; i++) {
            for (int j = 0; j < nKolom; j++) {
                hasil.setItem(i, j, mKali.getDt(i, j) / (DnormLatih.getItem(j) * DnormUji.getItem(i)));
            }
        }
        return hasil;
    }

    public void cetakIsiHashMap() {

        for (Map.Entry<String, InfoListRefDok> entry : hListInfoRefDok.entrySet()) {
            System.out.println("------------------------------------------------------------");
            String kunci = entry.getKey();
            InfoListRefDok ilo = entry.getValue();
            System.out.println("Dok Latih");
            System.out.print("|" + kunci + "|");
            System.out.println(ilo.getInfoJumlahRefDok() + "");
            //ArrayList <RefDok> lo = ilo.getListRefDok();
            ArrayList<RefDok> lDLatih = ilo.getListRefDok();
            int j = 0;
            while (j < lDLatih.size()) {
                (lDLatih.get(j)).cetakRefDok();
                System.out.println(" ");
                j++;
            }
            System.out.println("");
            System.out.println("Dokument Uji");
            ArrayList<RefDok> lDUji = ilo.getListRefDokUji();
            int k = 0;
            while (k < lDUji.size()) {
                (lDUji.get(k)).cetakRefDok();
                System.out.println(" ");
                k++;
            }
        }
    }

    public HashMap<String, InfoListRefDok> gethListInfoRefDok() {
        return this.hListInfoRefDok;
    }
    public double []urutan(double sort[]){
        System.out.println("");
        System.out.println("Cosine similariti : Decending");
         double temp;
        for(int i=0; i < sort.length-1; i++){
 
            for(int j=1; j < sort.length-i; j++){
                if(sort[j-1] < sort[j]){
                    temp=sort[j-1];
                    sort[j-1] = sort[j];
                    sort[j] = temp;
                }
            }
           // System.out.println((i+1)+"th iteration result: "+Arrays.toString(sort));
        }
        return sort;
    }

    public static void main(String s[]) {
        read dir = new read();
        OperasiFile files = new OperasiFile();
        String kategori = "DOK";
        dir.listFiles(kategori);
        String nama[] = dir.namaFile();
        File f[] = new File[dir.jumFile()];
        for (int i = 0; i < dir.jumFile(); i++) {
            f[i] = new File(kategori+ nama[i]);
        }
        String tampung[] = files.BacaSemuaFile(f);
        System.out.println();
        Dokumen[] dLat = new Dokumen[tampung.length]; // udah
        //---------------
        //-----Stop Word
        StopWord st = new StopWord();
        //----------------
        for (int i = 0; i < tampung.length; i++) {

            dLat[i] = new Dokumen(i, tampung[i], kategori);
            System.out.println(tampung[i]);
            System.out.println(st.setKalimat(tampung[i]));
        }

        
        Dokumen[] dU = new Dokumen[1];
        Scanner input = new Scanner(System.in);
        dU[0] = new Dokumen(0, input.nextLine()); // sudah

//        String[] teks = {
//            "",
//            "",
//            "",
//            "",
//            ""};

        HashMapRefDok hMapRefDok = new HashMapRefDok(dLat, dU);
//        hMapRefDok.cetakIsiHashMap();

        String[] terms = hMapRefDok.getTermUnik();
        for (int i = 0; i < terms.length; i++) {
            System.out.printf("%s\n", terms[i]); 
        }
        
        System.out.println();
        Matrik mdf, mwi, mtfLatih, mtfUji, mwiUji, sim;
        mdf = hMapRefDok.getMTF();
        mwi = hMapRefDok.getMWi();
        mtfUji = hMapRefDok.getMtfUji();
        mtfLatih = hMapRefDok.getMTF();
        mwiUji = hMapRefDok.getMWiUji();
        System.out.println(mtfLatih.cetak());
        System.out.println("batas");
        System.out.println(mtfLatih.cetak());
//        System.out.println(mtfUji.tampil());

//        System.out.println(mwi.tampil());
//        System.out.println(mwiUji.goldtampil()); // blm

        double akar = 0;

        //------------------------- BOBOT Latih ------------------
        int o = mwi.getnBaris();
        int p = mwi.getnKolom();

        double beratLatih[][] = new double[o][p];
        for (int i = 0; i < o; i++) {
            for (int j = 0; j < p; j++) {

                beratLatih[i][j] = mwi.getDt(i, j);
                akar += beratLatih[i][j];
            }
        }
        //------------------------------------------------------
        //------------- CETAK M-LATIH-------------------------
//        for (int i = 0; i < o; i++) {
//            for (int j = 0; j < p; j++) {
//                System.out.print("    " + beratLatih[i][j]);
//            }
//            System.out.println("");
//        }

        //----------------------------------------------------------
        //--------------------Matrik bobot Uji ---------
        //------------------------- BOBOT Latih ------------------
        int baris = mwiUji.getnBaris();
        int kolom = mwiUji.getnKolom();

        double beratUji[][] = new double[baris][kolom];

        //System.out.println(baris + "  " + kolom);
        for (int i = 0; i < baris; i++) {
            for (int j = 0; j < kolom; j++) {

                beratUji[i][j] = mwiUji.getDt(i, j);
            }
        }
        //----------------CETAK M_UJI--------------------------
//        System.out.println("M -UJI");
//        for (int i = 0; i <baris; i++) {
//            for (int j = 0; j < kolom; j++) {
//                System.out.print("    " + beratUji[i][j]);
//            }
//            System.out.println("");
//        }
//        

        //--------------------------------------------------
        //------------KUADRAT LATIH-------------------------------
        double kuadrat[][] = new double[o][p];

        for (int i = 0; i < o; i++) {
            for (int j = 0; j < p; j++) {
                kuadrat[i][j] = beratLatih[i][j] * beratLatih[i][j];

            }
        }
//        System.out.println("         KUADRAT LATIH");
//        for (int i = 0; i < o; i++) {
//            for (int j = 0; j < p; j++) {
//                System.out.print("    "+kuadrat[i][j]);
//            }
//            System.out.println("");
//        }

        //-------------KUADRAT UJI
        //--------------------------------------------------
        //------------KUADRAT UJI-------------------------------
        double kuadratUJI[][] = new double[baris][kolom];
        for (int i = 0; i < baris; i++) {
            for (int j = 0; j < kolom; j++) {
                kuadratUJI[i][j] = beratUji[i][j] * beratUji[i][j];

            }
        }
//        System.out.println("                                                             KUADRAT uji");
//        for (int i = 0; i < baris; i++) {
//            for (int j = 0; j < kolom; j++) {
//                System.out.print("      "+kuadratUJI[i][j]);
//            }
//            System.out.println("");
//        }
        //---------------------------------------------------
        //jumlah akar uadrat DOKUMENT LATIH---------------
        //System.out.println("JUMAH AKA KUADRAT DOKUMENT");
        double jum[] = new double[p];
        double akarDokLAt[] = new double[p];
        for (int i = 0; i < p; i++) {
            for (int j = 0; j < o; j++) {
                jum[i] = jum[i] + kuadrat[j][i];
                akarDokLAt[i] = jum[i];
            }
            akarDokLAt[i] = Math.sqrt(akarDokLAt[i]);
        }

        //-----
        System.out.println("JUMLAH AKAR DOK LAT KUADRAT");
        for (int i = 0; i < akarDokLAt.length; i++) {
            System.out.println(akarDokLAt[i]);
        }
        double jumDokUJi[] = new double[kolom];
        double akarDokUji[] = new double[kolom];
        for (int i = 0; i < kolom; i++) {
            for (int j = 0; j < baris; j++) {
                jumDokUJi[i] = jumDokUJi[i] + kuadratUJI[j][i];
                akarDokUji[i] = jumDokUJi[i];
            }
            akarDokUji[i] = Math.sqrt(akarDokUji[i]);
        }

        //-----
        System.out.println("");
        System.out.println("JUMLAH AKAR DOK UJI KUADRAT");
        for (int i = 0; i < akarDokUji.length; i++) {
            System.out.println(akarDokUji[i]);
        }

        //  PROSES Q.D
        //  
        //
        double tampQ_D[] = new double[3];
        for (int i = 0; i < p; i++) {
            for (int j = 0; j < o; j++) {
                tampQ_D[i] += beratUji[j][0] * beratLatih[j][i];
            }
        }
        System.out.println("");
        System.out.println(" PROSES  Q.D");
        for (int i = 0; i < tampQ_D.length; i++) {
            System.out.println("Q.D[" + i + "]  " + tampQ_D[i]);
        }

        System.out.println("");
        System.out.println("COSINE tetha 0");
        double cosTeta[] = new double[3];
        for (int i = 0; i < p; i++) {
            cosTeta[i] = tampQ_D[i] / (akarDokUji[0] * akarDokLAt[i]);
            System.out.println("COS tetha D" + i + " :" + cosTeta[i]);
        }
    }
}
