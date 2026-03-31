package lab6;

public class Lab6 {

    public static void main(String[] args) {

//        // Eger komut satirindan deger girmezsen test amacli bu dizi kullanilir
//        if (args.length == 0) {
//            args = new String[]{"12", "5", "8", "23", "3", "17"};
//        }

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

    // =====================================================
    // Ortalama hesaplayan thread
    // =====================================================
    static class OrtalamaThread extends Thread {

        private final String[] sayilar;
        private double ortalama;
        private double toplam;

        public OrtalamaThread(String[] args) {
            this.sayilar = args;
        }

        @Override
        public void run() {

            for (String sayi : sayilar) {
                toplam += Double.parseDouble(sayi);
            }

            ortalama = toplam / sayilar.length;

            System.out.println("Ortalama = " + ortalama);
        }
    }

    // =====================================================
    // Minimum degeri bulan thread
    // =====================================================
    static class MinThread extends Thread {

        private final String[] sayilar;
        private double minimum;

        public MinThread(String[] args) {
            this.sayilar = args;
        }

        @Override
        public void run() {

            minimum = Double.parseDouble(sayilar[0]);

            for (int i = 1; i < sayilar.length; i++) {

                double deger = Double.parseDouble(sayilar[i]);

                if (deger < minimum) {
                    minimum = deger;
                }
            }

            System.out.println("Minimum = " + minimum);
        }
    }

    // =====================================================
    // Maksimum degeri bulan thread
    // =====================================================
    static class MaxThread extends Thread {

        private final String[] sayilar;
        private double maximum;

        public MaxThread(String[] args) {
            this.sayilar = args;
        }

        @Override
        public void run() {

            maximum = Double.parseDouble(sayilar[0]);

            for (int i = 1; i < sayilar.length; i++) {

                double deger = Double.parseDouble(sayilar[i]);

                if (deger > maximum) {
                    maximum = deger;
                }
            }

            System.out.println("Maximum = " + maximum);
        }
    }
}