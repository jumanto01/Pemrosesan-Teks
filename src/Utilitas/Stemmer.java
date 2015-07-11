/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilitas;

import java.util.ArrayList;
import snowballstemmer.EnglishStemmer;

/**
 *
 * @author RO'I FAHREZA
 */
public class Stemmer {

    public static void main(String[] args) {
       Stemmer kk= new Stemmer();
       String m=kk.setKalimat("playing saying ");
        System.out.println(m);
    }

    public static ArrayList<String> wordsList = new ArrayList<String>();

    public String setKalimat(String s) {
        EnglishStemmer stemmer = new EnglishStemmer();

        String k = " ";
        wordsList.clear();
        s = s.trim().replaceAll("\\s+", " ");
        String[] words = s.split(" ");


//remove stop words here from the temp list
        for (int i = 0; i < words.length; i++) {
            stemmer.setCurrent(words[i]);
            if (stemmer.stem()) {
                wordsList.add(stemmer.getCurrent());

            }
            else   {
                System.out.println("gak maksid");
            }
        }

        for (String str : wordsList) {
            k = k + str + " ";
        }

        return k;
    }

}
