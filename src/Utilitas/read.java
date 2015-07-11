/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilitas;

import java.io.File;

public class read {

    int s;
    String[] nama;

    /**
     * List all the files and folders from a directory
     *
     * @param directoryName to be listed
     */
    public void listFilesAndFolders(String directoryName) {

        File directory = new File(directoryName);

        //get all the files from a directory
        File[] fList = directory.listFiles();

        for (File file : fList) {
            System.out.println(file.getName());
        }
    }

    /**
     * List all the files under a directory
     *
     * @param directoryName to be listed
     */
    public void listFiles(String directoryName) {
        int x = 0;
        File directory = new File(directoryName);

        //get all the files from a directory
        File[] fList = directory.listFiles();
        s = fList.length;
        nama = new String[s];
        System.out.println("jum : " + s);
        for (File file : fList) {
            if (file.isFile()) {
                System.out.println(file.getName());
                nama[x] = (String) file.getName();
                x++;
            }
        }

    }

    public int jumFile() {
        return s;
    }

    public String[] namaFile() {
        return nama;
    }

    /**
     * List all the folder under a directory
     *
     * @param directoryName to be listed
     */
    public void listFolders(String directoryName) {

        File directory = new File(directoryName);

        //get all the files from a directory
        File[] fList = directory.listFiles();

        for (File file : fList) {
            if (file.isDirectory()) {
                System.out.println(file.getName());
            }
        }
    }

    /**
     * List all files from a directory and its subdirectories
     *
     * @param directoryName to be listed
     */
    public void listFilesAndFilesSubDirectories(String directoryName) {

        File directory = new File(directoryName);

        //get all the files from a directory
        File[] fList = directory.listFiles();

        for (File file : fList) {
            if (file.isFile()) {
                System.out.println(file.getAbsolutePath());
            } else if (file.isDirectory()) {
                listFilesAndFilesSubDirectories(file.getAbsolutePath());
            }
        }
    }

    public static void main(String[] args) {

        read listFilesUtil = new read();

        final String directoryLinuxMac = "/Users/loiane/test";

        //Windows directory example
        final String directoryWindows = "F://DOK";

        listFilesUtil.listFiles(directoryWindows);
    }
}
