package com.company;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;

public class Main {

    public static void main(String[] args) throws IOException {
        String str1 = read("C:\\Users\\Admin\\IdeaProjects\\InputAndOutput\\src\\com\\company\\caodao");
        System.out.println(str1 + "\n");

        String str2 = readUTF8("C:\\Users\\Admin\\IdeaProjects\\InputAndOutput\\src\\com\\company\\caodao");
        System.out.println(str2 + "\n");

        String str3 = readByLine("C:\\Users\\Admin\\IdeaProjects\\InputAndOutput\\src\\com\\company\\caodao");
        System.out.println(str3 + "\n");

        String str4 = readUTF8ByLine("C:\\Users\\Admin\\IdeaProjects\\InputAndOutput\\src\\com\\company\\caodao");
        System.out.println(str4 + "\n");

        String str5 = readBuffer("C:\\Users\\Admin\\IdeaProjects\\InputAndOutput\\src\\com\\company\\caodao");
        System.out.println(str5 + "\n");

        byte[] data = readImage("https://i-vnexpress.vnecdn.net/2019/11/29/asnawi-1575004702-1575004720-3749-1575004803.jpg");
        saveFile("D:\\abc.png", data);
//        System.out.println(str6 + "\n");
    }

    public static String read(String filename) throws IOException {

        String str = "";

        InputStream is = new FileInputStream(filename);
        int c;
        while ((c = is.read()) != -1) {
            str += (char) c;
        }

        is.close();
        return str;
    }
    public static String readUTF8(String filename) throws IOException {

        String str = "";

        Reader is = new FileReader(filename);
        int c;
        while ((c = is.read()) != -1) {
            str += (char) c;
        }

        is.close();
        return str;
    }
    public static String readByLine(String filename) throws IOException {

        String str = "";

        InputStream is = new FileInputStream(filename);
        DataInputStream dis = new DataInputStream(is);
        String line;
        while ((line = dis.readLine()) != null) {
            str += line + '\n';
        }
        is.close();

        return str;
    }
    public static String readUTF8ByLine(String filename) throws IOException {

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
    public static String readBuffer(String filename) throws IOException {

        String str = null;
        // 0123456789
        byte[] buffer = new byte[10]; //[0][1][2]

        InputStream is = new FileInputStream(filename);
        BufferedInputStream bis = new BufferedInputStream(is);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        int count;
        while ((count = bis.read(buffer)) != -1) {
            baos.write(buffer, 0, count);
        }
        str = new String(baos.toByteArray());
        baos.close();
        is.close();

        return str;
    }
    public static String readOnlineResource(String strUrl) throws IOException {

        String str = null;

        URL url = new URL(strUrl);
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();

        int responseCode = conn.getResponseCode();
        if (responseCode == HttpsURLConnection.HTTP_OK) {

            InputStream is = conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            byte[] buffer = new byte[100];
            int count;
            while ((count = bis.read(buffer)) != -1) {
                baos.write(buffer, 0, count);
            }
            str = new String(baos.toByteArray());

            is.close();
        }

        return str;
    }

    public static byte[] readImage(String strUrl) throws IOException {

        byte[] str = null;

        URL url = new URL(strUrl);
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();

        int responseCode = conn.getResponseCode();
        if (responseCode == HttpsURLConnection.HTTP_OK) {

            InputStream is = conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            byte[] buffer = new byte[100];
            int count;
            while ((count = bis.read(buffer)) != -1) {
                baos.write(buffer, 0, count);
            }
            str = baos.toByteArray();

            is.close();
        }

        return str;
    }

    public static void saveFile(String filename, byte[] data) throws IOException {
        FileOutputStream fos = new FileOutputStream(filename);
        fos.write(data);
        fos.close();
    }
    
}
