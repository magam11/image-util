package main;

import java.io.*;

public class IODemo {
//    String fileSeparator = System.getProperty("file.separator");

    public static void main(String[] args) throws IOException {
        File file = new File("C:\\Users\\muraz\\Desktop\\myfile1.txt");
        file.createNewFile();
        InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file));
        BufferedReader br = new BufferedReader(inputStreamReader);
        String thisLine ="";
        while ((thisLine = br.readLine()) != null) {
            System.out.println(thisLine);
        }


    }

}
