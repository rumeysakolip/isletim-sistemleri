import java.util.Arrays;

public class Lab6 {

    public static void main(String[] args) {
        // Eğer dışarıdan sayı girilmezse kullanıcıyı uyar ve programı sonlandır
        if (args.length == 0) {
            System.out.println("Hata: Lutfen sayilari parametre olarak giriniz.");
            System.out.println("Ornek kullanim: java Lab6 10 20 30");
            return;
        }

        System.out.println("=== LAB 6 - Ortalama / Min / Max Thread Ornegi ===\n");

        OrtalamaThread ortalamaThread = new OrtalamaThread(args);
        MinThread minThread = new MinThread(args);
        MaxThread maxThread = new MaxThread(args);

        ortalamaThread.start();
        minThread.start();
        maxThread.start();

        try {
            ortalamaThread.join();
            minThread.join();
            maxThread.join();
        } catch (InterruptedException e) {
            System.out.println("Thread beklenirken hata olustu.");
            Thread.currentThread().interrupt();
        }

        System.out.println("\nTum threadler tamamlandi.");
    }

    static class OrtalamaThread extends Thread {
        private final String[] sayilar;
        public OrtalamaThread(String[] args) { this.sayilar = args; }

        @Override
        public void run() {
            double toplam = 0;
            for (String sayi : sayilar) {
                toplam += Double.parseDouble(sayi);
            }
            System.out.println("Ortalama = " + (toplam / sayilar.length));
        }
    }

    static class MinThread extends Thread {
        private final String[] sayilar;
        public MinThread(String[] args) { this.sayilar = args; }

        @Override
        public void run() {
            double minimum = Double.parseDouble(sayilar[0]);
            for (int i = 1; i < sayilar.length; i++) {
                double deger = Double.parseDouble(sayilar[i]);
                if (deger < minimum) minimum = deger;
            }
            System.out.println("Minimum = " + minimum);
        }
    }

    static class MaxThread extends Thread {
        private final String[] sayilar;
        public MaxThread(String[] args) { this.sayilar = args; }

        @Override
        public void run() {
            double maximum = Double.parseDouble(sayilar[0]);
            for (int i = 1; i < sayilar.length; i++) {
                double deger = Double.parseDouble(sayilar[i]);
                if (deger > maximum) maximum = deger;
            }
            System.out.println("Maximum = " + maximum);
        }
    }
}
