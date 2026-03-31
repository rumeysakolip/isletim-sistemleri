package lab2process;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Lab2Process {

    public static void main(String[] args) {

        try {

            System.out.println("===== GOREV 1: Windows Komutlarini Calistirma =====");

            String[] commands = {
                "cmd", "/c",
                "dir",
                "&&",
                "ping", "google.com",
                "&&",
                "tasklist"
            };

            ProcessBuilder builder = new ProcessBuilder(commands);

            builder.directory(new File("C:\\"));

            Process process = builder.start();

            System.out.println("Olusturulan Process ID: " + process.pid());

            BufferedReader reader =
                new BufferedReader(
                    new InputStreamReader(process.getInputStream())
                );

            String line;

            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            reader.close();

            System.out.println("\n===== GOREV 2: Parametre ile Process Calistirma =====");

            if (args.length != 3) {
                System.out.println("Kullanim:");
                System.out.println("java OSProcess cmd.exe /C ping");
                return;
            }

            ProcessBuilder pb =
                new ProcessBuilder(args[0], args[1], args[2], "google.com");

            Process process2 = pb.start();

            System.out.println("Parametre ile olusturulan Process ID: " + process2.pid());

            BufferedReader reader2 =
                new BufferedReader(
                    new InputStreamReader(process2.getInputStream())
                );

            while ((line = reader2.readLine()) != null) {
                System.out.println(line);
            }

            reader2.close();

            // internet bağlantı kontrolü
            internetKontrol();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void internetKontrol() {

        boolean baglanti;

        try {

            URL url = new URL("https://www.google.com");
            URLConnection conn = url.openConnection();
            conn.connect();

            baglanti = true;

        } catch (Exception e) {

            baglanti = false;
        }

        if (baglanti) {
            System.out.println("Baglanti basarili");
        } else {
            System.out.println("Baglanti yok");
        }
    }
}