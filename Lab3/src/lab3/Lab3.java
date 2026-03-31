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
            // paintBekle();

            // 3. Paint bittikten sonra Notepad aç
            //siraliProcessler();

            // 4. Paint ve Notepad aynı anda açılsın,
            // önce Paint sonra Notepad kapansın diye bekle
            paralelProcessler();

        } catch (Exception e) {
            System.out.println("Bir hata oluştu: " + e.getMessage());
        }
    }

    // 1. cmd /C dir çıktısını ekrana yazdırır
    public static void processCiktisiniGoster() throws IOException {
        System.out.println("1) Process çıktısı görüntüleniyor...\n");

        Process dirProc = Runtime.getRuntime().exec("cmd /C dir");

        InputStream in = dirProc.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }

        br.close();

        System.out.println("\n-----------------------------------\n");
    }

    // 2. Paint açılır ve kapanana kadar beklenir
    public static void paintBekle() {
        try {
            System.out.println("2) Paint açılıyor. Kapatana kadar program bekleyecek...");

            Process p = Runtime.getRuntime().exec("mspaint.exe");
            p.waitFor();

            System.out.println("Paint kapatıldı.\n");
            System.out.println("-----------------------------------\n");

        } catch (Exception e) {
            System.out.println("Paint örneğinde hata: " + e);
        }
    }

    // 3. Önce Paint, sonra Notepad çalışır
    public static void siraliProcessler() {
        try {
            System.out.println("3) Önce Paint, sonra Notepad çalışacak.");

            Process paintProcess = new ProcessBuilder("mspaint.exe").start();
            paintProcess.waitFor();

            System.out.println("Paint kapatıldı.");
            System.out.println("Notepad açılıyor...");

            new ProcessBuilder("notepad.exe").start();

            notepadKapananaKadarBekle();

            System.out.println("Notepad gerçekten kapatıldı.");
            System.out.println("-----------------------------------");

        } catch (Exception e) {
            System.out.println("Sıralı process örneğinde hata: " + e);
        }
    }

    // 4. Paint ve Notepad aynı anda çalışır
    public static void paralelProcessler() {
        try {
            System.out.println("4) Paint ve Notepad aynı anda açılıyor.");

            Process paintProcess = new ProcessBuilder("mspaint.exe").start();
            new ProcessBuilder("notepad.exe").start();

            boolean paintKapandi = false;
            boolean notepadKapandi = false;

            while (!(paintKapandi && notepadKapandi)) {

                // Paint kapanmış mı kontrol et
                if (!paintKapandi) {
                    try {
                        paintProcess.exitValue(); // kapanmışsa exception atmaz
                        paintKapandi = true;
                        System.out.println("Paint kapatıldı.");
                    } catch (IllegalThreadStateException e) {
                        // Paint hâlâ açık
                    }
                }

                // Notepad kapanmış mı kontrol et
                if (!notepadKapandi && !notepadAcikMi()) {
                    notepadKapandi = true;
                    System.out.println("Notepad kapatıldı.");
                }

                Thread.sleep(500);
            }

            System.out.println("-----------------------------------");

        } catch (Exception e) {
            System.out.println("Paralel process örneğinde hata: " + e);
        }
    }

// Windows 11 Notepad hâlâ açık mı kontrol eder
public static boolean notepadAcikMi() throws IOException {

    Process kontrol = new ProcessBuilder(
            "cmd",
            "/c",
            "tasklist /FI \"IMAGENAME eq Notepad.exe\""
    ).start();

    BufferedReader br = new BufferedReader(
            new InputStreamReader(kontrol.getInputStream())
    );

    String satir;

    while ((satir = br.readLine()) != null) {
        if (satir.contains("Notepad.exe")) {
            br.close();
            return true;
        }
    }

    br.close();
    return false;
}

    // Windows 11 Notepad kapanana kadar gerçek process takibi yapar
    public static void notepadKapananaKadarBekle() throws IOException, InterruptedException {

        while (true) {

            Process kontrol = new ProcessBuilder(
                    "cmd",
                    "/c",
                    "tasklist /FI \"IMAGENAME eq Notepad.exe\""
            ).start();

            BufferedReader br = new BufferedReader(
                    new InputStreamReader(kontrol.getInputStream())
            );

            String satir;
            boolean notepadAcik = false;

            while ((satir = br.readLine()) != null) {
                if (satir.contains("Notepad.exe")) {
                    notepadAcik = true;
                    break;
                }
            }

            br.close();

            if (!notepadAcik) {
                break;
            }

            Thread.sleep(1000);
        }
    }
}