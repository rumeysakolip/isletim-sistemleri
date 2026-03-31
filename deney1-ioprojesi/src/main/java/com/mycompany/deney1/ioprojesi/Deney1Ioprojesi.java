package com.mycompany.deney1.ioprojesi;

import java.io.*;
import java.net.*;
import java.io.File;

public class Deney1Ioprojesi {

    // 1️⃣ Disk bilgisi
    public static void disk() {

        File file = new File("C:\\");   // Windows C diski

        long totalSpace = file.getTotalSpace();
        long usableSpace = file.getUsableSpace();
        long freeSpace = file.getFreeSpace();

        System.out.println("=== Partition Detail ===");

        System.out.println("Toplam Alan: " + totalSpace / 1024 / 1024 / 1024 + " GB");
        System.out.println("Kullanilabilir Alan: " + usableSpace / 1024 / 1024 / 1024 + " GB");
        System.out.println("Bos Alan: " + freeSpace / 1024 / 1024 / 1024 + " GB");
    }


    // 2️⃣ İnternet bağlantı kontrolü
    public static void eris() {

        boolean connectivity;

        try {

            URL url = new URL("https://bm.subu.edu.tr/");
            URLConnection conn = url.openConnection();
            conn.connect();

            connectivity = true;

        } catch (Exception e) {

            connectivity = false;

        }

        if (connectivity == true) {

            System.out.println("Internet baglantisi VAR");

        } else {

            System.out.println("Internet baglantisi YOK");

        }
    }


    // 3️⃣ Çalışan processleri listeleme
    public static void processListe() {

        try {

            String line;

            Process p = Runtime.getRuntime().exec(System.getenv("windir") + "\\system32\\" + "tasklist.exe");

            BufferedReader input =
                    new BufferedReader(new InputStreamReader(p.getInputStream()));

            while ((line = input.readLine()) != null) {

                System.out.println(line);

            }

            input.close();

        } catch (Exception err) {

            err.printStackTrace();

        }

    }


    // Program başlangıç noktası
    public static void main(String[] args) {

        System.out.println("===== DISK BILGISI =====");
        disk();

        System.out.println("\n===== INTERNET KONTROL =====");
        eris();

        System.out.println("\n===== CALISAN PROCESSLER =====");
        processListe();

    }

}