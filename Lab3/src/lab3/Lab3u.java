package lab3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Lab3 {

    public static void main(String[] args) {
        try {
            System.out.println("=== LAB 3 - Process Ornekleri ===\n");

            // 1. Process çıktısını görüntüleme
            // processCiktisiniGoster();

            // 2. Tek process çalıştır ve bitmesini bekle
            // hesapMakinesiBekle();

            // 3. Önce hesap makinesi, sonra gedit çalışsın
            // siraliProcessler();

            // 4. Hesap makinesi ve gedit aynı anda açılsın
            paralelProcessler();

        } catch (Exception e) {
            System.out.println("Bir hata oluştu: " + e.getMessage());
        }
    }

    // 1. ls çıktısını ekrana yazdırır
    public static void processCiktisiniGoster() throws IOException {
        System.out.println("1) Process çıktısı görüntüleniyor...\n");

        Process dirProc = Runtime.getRuntime().exec("ls");

        InputStream in = dirProc.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }

        br.close();

        System.out.println("\n-----------------------------------\n");
    }

    // 2. Hesap makinesi açılır ve kapanana kadar beklenir
    public static void hesapMakinesiBekle() {
        try {
            System.out.println("2) Hesap makinesi açılıyor. Kapatana kadar program bekleyecek...");

            Process p = Runtime.getRuntime().exec("gnome-calculator");
            p.waitFor();

            System.out.println("Hesap makinesi kapatıldı.\n");
            System.out.println("-----------------------------------\n");

        } catch (Exception e) {
            System.out.println("Hesap makinesi örneğinde hata: " + e);
        }
    }

    // 3. Önce hesap makinesi, sonra gedit çalışır
    public static void siraliProcessler() {
        try {
            System.out.println("3) Önce hesap makinesi, sonra gedit çalışacak.");

            Process hesapProcess = new ProcessBuilder("gnome-calculator").start();
            hesapProcess.waitFor();

            System.out.println("Hesap makinesi kapatıldı.");
            System.out.println("Gedit açılıyor...");

            new ProcessBuilder("gedit").start();

            geditKapananaKadarBekle();

            System.out.println("Gedit gerçekten kapatıldı.");
            System.out.println("-----------------------------------");

        } catch (Exception e) {
            System.out.println("Sıralı process örneğinde hata: " + e);
        }
    }

    // 4. Hesap makinesi ve gedit aynı anda çalışır
    public static void paralelProcessler() {
        try {
            System.out.println("4) Hesap makinesi ve gedit aynı anda açılıyor.");

            Process hesapProcess = new ProcessBuilder("gnome-calculator").start();
            new ProcessBuilder("gedit").start();

            boolean hesapKapandi = false;
            boolean geditKapandi = false;

            while (!(hesapKapandi && geditKapandi)) {

                // Hesap makinesi kapanmış mı kontrol et
                if (!hesapKapandi) {
                    try {
                        hesapProcess.exitValue();
                        hesapKapandi = true;
                        System.out.println("Hesap makinesi kapatıldı.");
                    } catch (IllegalThreadStateException e) {
                        // Hâlâ açık
                    }
                }

                // Gedit kapanmış mı kontrol et
                if (!geditKapandi && !geditAcikMi()) {
                    geditKapandi = true;
                    System.out.println("Gedit kapatıldı.");
                }

                Thread.sleep(500);
            }

            System.out.println("-----------------------------------");

        } catch (Exception e) {
            System.out.println("Paralel process örneğinde hata: " + e);
        }
    }

    // Gedit hâlâ açık mı kontrol eder
    public static boolean geditAcikMi() throws IOException {

        Process kontrol = new ProcessBuilder(
                "pgrep",
                "-x",
                "gedit"
        ).start();

        BufferedReader br = new BufferedReader(
                new InputStreamReader(kontrol.getInputStream())
        );

        boolean geditAcik = (br.readLine() != null);

        br.close();
        return geditAcik;
    }

    // Gedit kapanana kadar bekler
    public static void geditKapananaKadarBekle() throws IOException, InterruptedException {

        while (true) {

            if (!geditAcikMi()) {
                break;
            }

            Thread.sleep(1000);
        }
    }
}
