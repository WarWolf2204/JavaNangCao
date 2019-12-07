package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        delFileLikeContent("D:\\Java-nang-cao","test.txt");
    }
    public static ArrayList<String> listPaths = new ArrayList<>();

    public static List<File> listText = new ArrayList<>();

    public static void listFiles(String folderPath) throws IOException {
        File folder = new File(folderPath);
        File[] files = folder.listFiles();

        if (files.length == 0) {
            listPaths.add(folder.getCanonicalPath());
        }
        for (File f : files) {
            if (f.isFile()) {
                listPaths.add(f.getName());
                listText.add(f);
            } else {
                listFiles(f.getPath());
            }
        }
    }
    public static String GetContentFile(String filename) throws IOException {

        String str = "";

        Reader reader = new FileReader(filename);
        BufferedReader br = new BufferedReader(reader);
        String line;
        while ((line = br.readLine()) != null) {
            str += line + '\n';
        }
        reader.close();

        return str;
    }
    public static boolean sametext(String file1, String file2) throws IOException{
        boolean isEqual = true;
        String txtF1 = GetContentFile(file1);
        String txtF2 = GetContentFile(file2);

        if(txtF1.equals(txtF2)){
            isEqual = true;
        }
        else isEqual = false;
        return isEqual;
    }
    public static void delFileLikeContent(String folderPath, String fileName) throws IOException {
        listFiles(folderPath);
        String vlContent = "";
        String vlPath = folderPath+ "\\" +fileName;
        System.out.println(vlPath);

        for(int i = 1; i < listText.size(); i++){
            vlContent = listText.get(i).getName();
            if(vlContent.equals(fileName) || sametext(vlPath, listText.get(i).toString())){
                System.out.println("I Have Deleted: " + listText.get(i));
                listText.get(i).delete(); //is testing
            }
        }
        System.out.println("\n\n");
    }

}
