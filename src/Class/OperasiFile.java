/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

import javax.swing.*;
import java.io.*;
import java.util.*;

public class OperasiFile {

    private String[] doc;
    private Dokumen[] dokumens;
    private Dokumen[][] kat_dokumens;

    public OperasiFile() {
        doc = null;
    }

    /**
     * Program ini akan membaca dari sekumpulan file dan hasilnya disimpan dalam
     * struktur data dok , yaitu array of String. Masing-masing slot
     * merepresentasikan isi dokumen ke-i
     *
     * @param f, File f
     */
    
    public void BacaFilesKeBuf(File[] f) {
        doc = new String[f.length];
        for (int i = 0; i < f.length; i++) {
            doc[i] = BacaFileText(f[i]);
        }
    }

    /**
     * Program ini akan membaca dari sekumpulan file dan hasilnya disimpan dalam
     * struktur data dokumen bertipe Dokume(Katagori, Dok). Masing-masing slot
     * merepresentasikan isi dokumen ke-i
     *
     * @param f, File f
     */
    public void BacaFilesKeBufDoks(File[] f) {
        dokumens = new Dokumen[f.length];
        for (int i = 0; i < f.length; i++) {
            dokumens[i] = BacaFileTextDok(i, f[i]);
        }
    }

    /**
     * Program ini akan membaca dari sekumpulan file dan hasilnya disimpan dalam
     * struktur data dokumen bertipe Dokume(Katagori, Dok). Masing-masing slot
     * merepresentasikan isi dokumen ke-i
     *
     * @param f, File f
     */
    public void BacaFilesKeBufDoks(String namaKatagori, File[] f) {
        dokumens = new Dokumen[f.length];
        for (int i = 0; i < f.length; i++) {
            dokumens[i] = BacaFileTextDok(i, f[i]);
        }
    }

    /**
     * Program ini akan membaca dari sekumpulan file dan hasilnya disimpan dalam
     * struktur data dokumen bertipe Dokume(Katagori, Dok). Masing-masing slot
     * merepresentasikan isi dokumen ke-i
     *
     * @param f, File f
     */
    public void BacaFilesKeBufDoks(int idKat, String namaKatagori, File[] f) {

        for (int i = 0; i < f.length; i++) {
            kat_dokumens[idKat][i] = BacaFileTextDok(i, f[i]);
        }
    }

    /**
     * Program ini akan membaca dari sekumpulan file dari suatu direktori induk
     * dan direktori anak-anaknya dan hasilnya disimpan dalam struktur data
     * dokumen bertipe Dokume(Katagori, Dok). Masing-masing slot
     * merepresentasikan isi dokumen ke-i
     *
     * @param f, File f (direktori)
     */
    public void BacaFilesKeBufDoks(File f) {
        File files[];

        if (f.isDirectory()) {
            files = f.listFiles(); //files adalah kumpulan direktori
            int jumlahKatagori = files.length;
            kat_dokumens = new Dokumen[files.length][];
            for (int i = 0; i < files.length; i++) {
                String namaKatagori = files[i].getName();
                BacaFilesKeBufDoks(i, namaKatagori, files);
            }
        }
    }

    public Vector<Dokumen> BacaFilesKeBufDokVektor(String namaFile) {
        Vector<Dokumen> dok = new Vector<Dokumen>();
        File f = new File(namaFile);
        int nomer = 0;
        if (f.isDirectory()) {
            File[] files1, files2;
            files1 = f.listFiles();
            for (int i = 0; i < files1.length; i++) {
                files2 = files1[i].listFiles();
                String namaKatagori = files1[i].getName();
                for (int j = 0; j < files2.length; j++) {
                    dok.add(BacaFileTextDok(namaKatagori, nomer, files2[j]));
                    nomer++;
                }
            }
        }
        return dok;
    }

    /**
     * program ini akan menghasilkan array of string, dimana masing-masing slot
     * merepresentasikan isi suatu dokumen ke - i
     *
     * @return Array of String
     */
    public String[] getDocs() {
        return doc;
    }

    /**
     * program ini akan mengembalikan dokumen-dokumen yang dibaca dari
     * sekumpulan file
     *
     * @return
     */
    public Dokumen[] getDokumens() {
        return dokumens;
    }

    public Dokumen[][] getKatDokumen() {
        return this.kat_dokumens;
    }

    /**
     * program ini akan membaca data dari file f kemudian dikembalikan dalam
     * bentuk string
     *
     * @param f bertipe File
     * @return Isi File text
     */
    public String BacaFileText(File f) {
        String out = "";
        
        try {
            FileReader fr = new FileReader(f);
            BufferedReader bf = new BufferedReader(fr);
            String line = bf.readLine();
            while (line != null) {
                out += line + "\n";
                line = bf.readLine();
            }
            bf.close();
        } catch (IOException e) {
        }
        return out;
    }

    /**
     * Baca isi dari semua file dan hasilnya disimpan pada array of string
     *
     * @param f
     * @return array of string
     */
    public String[] BacaSemuaFile(File f[]) {
        // membaca semua file hasilnya dari masing-masing file sebagai array of string
        String hasil[] = new String[f.length];
        for (int i = 0; i < f.length; i++) {
            hasil[i] = BacaFileText(f[i]);
        }
        return hasil;
    }

    /**
     * Membaca data dari file teks simpan dalam struktur data dokumen yang
     * bertipe Dokumen, struktur data Dokumen terdiri dari elemen katagori dan
     * dok
     *
     * @param f : File
     * @return Dokumen
     */
    public Dokumen BacaFileTextDok(int i, File f) {
        Dokumen dokumen = null;
        String jdl = "";
        String dok = "";
        String katagori = f.getName();
        try {
            FileReader fr = new FileReader(f);
            BufferedReader bf = new BufferedReader(fr);
            katagori = f.getParent();
            jdl = bf.readLine();
            String line = bf.readLine();
            while (line != null) {
                dok += line + "\n";
                line = bf.readLine();
            }
            bf.close();
        } catch (IOException e) {
        }
        //dokumen = new Dokumen(i, jdl, dok, katagori);
        dokumen = new Dokumen(i, jdl, dok);
        return dokumen;
    }

    /**
     * Membaca data dari file teks simpan dalam struktur data dokumen yang
     * bertipe Dokumen, struktur data Dokumen terdiri dari elemen katagori dan
     * dok
     *
     * @param f : File
     * @return Dokumen
     */
    public Dokumen BacaFileTextDok(String namaKatagori, int i, File f) {
        Dokumen dokumen = null;
        String jdl = "";
        String dok = "";
        String katagori = namaKatagori;
        try {
            FileReader fr = new FileReader(f);
            BufferedReader bf = new BufferedReader(fr);
            katagori = f.getParent();
            jdl = bf.readLine();
            String line = bf.readLine();
            while (line != null) {
                dok += line + "\n";
                line = bf.readLine();
            }
            bf.close();
        } catch (IOException e) {
        }
        //dokumen = new Dokumen(i, jdl, dok, katagori);
        dokumen = new Dokumen(i, dok, katagori);
        return dokumen;
    }

    /**
     * Membaca dokumen dari file-file hasilnya simpan dalam struktur data array
     * of Dokumen
     *
     * @param f : File
     * @return Array of Dokumen
     */
    public Dokumen[] BacaSemuaFileDok(File f[]) {
        Dokumen[] hasil = new Dokumen[f.length];
        for (int i = 0; i < f.length; i++) {
            hasil[i] = BacaFileTextDok(i, f[i]);
        }
        return hasil;
    }

    /**
     * Baca dari File dan hasilnya lansung ditampilkan di JTextArea
     *
     * @param jt : JTextArea
     * @param f : File
     */
    public void BacaDariFile(JTextArea jt, File f) {
        try {
            FileReader fr = new FileReader(f);
            BufferedReader in = new BufferedReader(fr);
            String st = in.readLine();
            while (st != null) {
                jt.append(st + "\n");
                st = in.readLine();
            }
            in.close();
        } catch (IOException e) {
        }
    }

    /**
     * Tulis ke File isi dari JTextArea
     *
     * @param jt : JTextArea
     * @param f : File
     */
    public void TulisKeFile(JTextArea jt, File f) {
        try {
            FileWriter fw = new FileWriter(f);
            fw.write(jt.getText());
            fw.close();
        } catch (IOException e) {

        }
    }
}
