package Utilitas;

import java.text.DecimalFormat;
import java.util.*;

public class Matrik {

    private int nBaris, nKolom;
    private double[][] data;

    public Matrik(int m, int n) {
        this.nBaris = m;
        this.nKolom = n;
        data = new double[nBaris][nKolom];
    }

    public Matrik(double[][] A) {
        this.nBaris = A.length;
        this.nKolom = A[0].length;
        data = new double[nBaris][nKolom];
        for (int i = 0; i < nBaris; i++) {
            for (int j = 0; j < nKolom; j++) {
                data[i][j] = A[i][j];
            }
        }
    }

    public double[] getBaris(int id) {
        double[] hasil = new double[nKolom];
        for (int i = 0; i < nKolom; i++) {
            hasil[i] = data[id][i];
        }
        return hasil;
    }

    public double[] getKolom(int id) {
        double[] hasil = new double[nBaris];
        for (int i = 0; i < nBaris; i++) {
            hasil[i] = data[i][id];
        }
        return hasil;
    }

    public void setBaris(int idBaris, double[] dt) {
        for (int j = 0; j < dt.length; j++) {
            data[idBaris][j] = dt[j];
        }
    }

    public void setKolom(int idKolom, double[] dt) {
        for (int i = 0; i < dt.length; i++) {
            data[i][idKolom] = dt[i];
        }
    }

    public void setItem(int i, int j, double dt) {
        data[i][j] = dt;
    }

    public double getDt(int i, int j) {
        return data[i][j];
    }

    public double akarJumKuadrat(int id) {
        double hasil = 0;
        double tot = 0;
        double[] v = this.getKolom(id);

        for (int i = 0; i < v.length; i++) {
            tot += Math.pow(v[i], 2);
        }

        hasil = Math.sqrt(tot);
        return hasil;
    }

    public double VekDotVektor(double[] dt, int idV) {
        double hasil = 0;
        double[] Di = this.getKolom(idV);
        for (int i = 0; i < this.nBaris; i++) {
            hasil += dt[i] * Di[i];
        }

        return hasil;
    }

    public Vektor CosineTheta() {
        double[] hasil = null;
        int jDok = nKolom - 1; // Jumlah dokumen
        double[] q = this.getKolom(jDok); // Vektor query
        double Nq = this.HitNorm(new Vektor(q)); // Norm Vektor Query
        double[] Q_dot_D = new double[jDok]; // Q dot Di
        double[] NDi = new double[jDok]; // Norm Masing-masing Dok i
        double[] Cosine_theta = new double[jDok];
        for (int i = 0; i < jDok; i++) {
            Q_dot_D[i] = VekDotVektor(q, i);
            NDi[i] = HitNorm(new Vektor(getKolom(i)));
            Cosine_theta[i] = Q_dot_D[i] / (Nq * NDi[i]);
        }
        Vektor Vhasil = new Vektor(Cosine_theta);
        return Vhasil;
    }

    public Matrik tranpos() {
        Matrik hasil = new Matrik(nKolom, nBaris);
        for (int i = 0; i < nKolom; i++) {
            hasil.setBaris(i, this.getKolom(i));
        }
        return hasil;

    }

    public static Matrik tranpos(Matrik a) {
        Matrik hasil = new Matrik(a.nKolom, a.nBaris);
        for (int i = 0; i < a.nKolom; i++) {
            hasil.setBaris(i, a.getKolom(i));
        }
        return hasil;
    }

    public double HitungNorm(double[] dt) {
        double hasil = 0;
        for (int i = 0; i < dt.length; i++) {
            hasil += Math.pow(dt[i], 2);
        }
        hasil = Math.sqrt(hasil);
        return hasil;
    }

    public double HitNorm(Vektor v) {
        double hasil = 0;
        for (int i = 0; i < v.getSize(); i++) {
            hasil += Math.pow(v.getItem(i), 2);
        }
        hasil = Math.sqrt(hasil);
        return hasil;
    }

    public Matrik kali(Matrik A) {
        if (this.nKolom == A.getnBaris()) {
            double[][] c = new double[this.nBaris][A.getnKolom()];
            for (int i = 0; i < nBaris; i++) {
                for (int j = 0; j < A.getnKolom(); j++) {
                    double hasil = 0;
                    for (int k = 0; k < this.nKolom; k++) {
                        hasil += this.getDt(i, k) * A.getDt(k, j);
                    }
                    c[i][j] = hasil;
                }
            }

            Matrik hasil = new Matrik(c);
            return hasil;
        }
        return null;
    }

    public static double[][] cosSim(Matrik A, Matrik B) {
        double[][] hasil = new double[B.nKolom][A.nKolom];
        Matrik mC = Matrik.kali(Matrik.tranpos(B), A);
        for (int h = 0; h < B.nKolom; h++) {// jumlah data latih
            for (int i = 0; i < A.nKolom; i++) { // jumlah data uji
                hasil[h][i] = mC.getDt(h, i) / (A.akarJumKuadrat(i) * B.akarJumKuadrat(h));
            }
        }
        return hasil;
    }

    public static Matrik kali(Matrik a, Matrik b) {
        Matrik c = null;
        if (a.nKolom == b.nBaris) {
            c = new Matrik(a.nBaris, b.nKolom);
            for (int i = 0; i < a.nBaris; i++) {
                for (int j = 0; j < b.nKolom; j++) {
                    double hasil = 0;
                    for (int k = 0; k < a.nKolom; k++) {
                        hasil += a.getDt(i, k) * b.getDt(k, j);
                    }
                    c.setItem(i, j, hasil);
                }
            }
            return c;
        }
        return null;
    }

    public Vektor HitungNormKolom() {
        Vektor vNorm = new Vektor(nKolom);
        for (int i = 0; i < nKolom; i++) {
            vNorm.setItem(i, HitNorm(new Vektor(this.getKolom(i))));
        }
        return vNorm;
    }

    public String cetakToString1(String komentar, Vector kUAll) {
        String ot = "";
        ot += komentar + "\n";
        DecimalFormat df = new DecimalFormat("0.0000");
        for (int i = 0; i < this.nBaris; i++) {
            ot += kUAll.elementAt(i) + "\t";
            for (int j = 0; j < this.nKolom; j++) {
                ot += df.format(this.data[i][j]) + "\t";
            }
            ot += "\n";
        }
        ot += "\n";
        return ot;
    }

    public String cetak() {
        String out = "";
        DecimalFormat df = new DecimalFormat("0.0000");
        
        
        
        for (int i = 0; i < nBaris; i++) {
            for (int j = 0; j < nKolom; j++) {
                out += df.format(data[i][j]) + "\t";
            }
            out += "\n";
        }
        return out;
    }

    public String[] tampil() {
        String out[] = new String[nBaris];
        DecimalFormat df = new DecimalFormat("0.0");
//        for (int a = 0; a < nBaris; a++) {
        for (int i = 0; i < nBaris; i++) {
            for (int j = 0; j < nKolom; j++) {
                out[i] = df.format(data[i][j]);
                System.out.println(out[i]);
            }

        }
        return out;
    }

    public String[] tampil2() {
        String out[] = new String[nBaris];
        String tampung = "";
        DecimalFormat df = new DecimalFormat("0.0000");
//        for (int a = 0; a < nBaris; a++) {
        for (int i = 0; i < nBaris; i++) {
            for (int j = 0; j < nKolom; j++) {
                tampung += df.format(data[i][j]) + "\t";
            }
            out[i] = tampung;
            tampung += "\n";
            System.out.print(out[nBaris-1]);
        }

        return out;
    }

    public int getnBaris() {
        return nBaris;
    }

    public int getnKolom() {
        return nKolom;
    }

    public static void main(String s[]) {
        double[][] a = {
            {0.4771, 0.0000, 0.0000},
            {0.0000, 0.0000, 0.0000},
            {0.0000, 0.4771, 0.0000},
            {0.0000, 0.1761, 0.1761},
            {0.0000, 0.9542, 0.0000},
            {0.0000, 0.0000, 0.0000},
            {0.0000, 0.1761, 0.1761},
            {0.1761, 0.0000, 0.1761},
            {0.0000, 0.0000, 0.0000},
            {0.4771, 0.0000, 0.0000},
            {0.1761, 0.0000, 0.1761}};
        double[][] b = {
            {0.0000, 0.23},
            {0.0000, 0.1},
            {0.0000, 0},
            {0.1761, 0},
            {0.4771, 0.01},
            {0.0000, 0.12},
            {0.0000, 0.21},
            {0.1761, 0},
            {0.0000, 0},
            {0.0000, 0.21},
            {0.0000, 0.13}};
        Matrik mA, mB, mT, mC;
        mA = new Matrik(a);
        mB = new Matrik(b);
        mT = Matrik.tranpos(mB);
        String stmA, stmB, stmT, stmC;
//        stmA = mA.cetak("Matrik A");
//        stmB = mB.cetak("Matrik B");
//        stmT = mT.cetak("Matrik transpos dari B");
//        mC = Matrik.kali(mT, mA);
//        stmC = mC.cetak("Matrik C");
//        System.out.println(stmA + "\n" + stmB + "\n" + stmT + "\n" + stmC);
        double[][] cos = Matrik.cosSim(mA, mB);
        for (int i = 0; i < cos.length; i++) {
            for (int j = 0; j < cos[1].length; j++) {
                System.out.println("Cos (D" + i + ",D" + j + ") : " + cos[i][j]);
            }
            System.out.println();
        }
    }
}
