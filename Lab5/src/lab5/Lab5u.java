package lab5;

public class Lab5 {

    public static void main(String[] args) {

        System.out.println("=== LAB 5 - Thread Yonetimi Ornekleri ===\n");

        /*
         * Calistirmak istedigin ornegin basindaki // isaretini kaldir.
         */

        // 1. wait() kullanmadan sonucu alma
        // waitsizOrnek();

        // 2. wait() ve notify() kullanarak thread bekleme
        waitNotifyOrnegi();
    }

    // =====================================================
    // 1) wait() kullanmadan thread sonucu alma
    // =====================================================
    public static void waitsizOrnek() {

        System.out.println("1) wait() kullanilmadan thread sonucu okunuyor\n");

        ToplamThread b = new ToplamThread();
        b.start();

        // Thread henuz bitmeden sonuc okunabilecegi icin
        // bazen 0 veya eksik bir deger gelebilir.
        System.out.println("Toplam: " + b.total);

        System.out.println("\n-----------------------------------\n");
    }

    // =====================================================
    // 2) wait() ve notify() kullanarak dogru sonucu alma
    // =====================================================
    public static void waitNotifyOrnegi() {

        System.out.println("2) wait() ve notify() kullanilarak thread bekleniyor\n");

        ToplamThreadNotify b = new ToplamThreadNotify();
        b.start();

        synchronized (b) {
            try {
                System.out.println("b threadinin islemini bitirmesi bekleniyor...");
                b.wait();

            } catch (InterruptedException e) {
                System.out.println("Bekleme sirasinda hata olustu.");
                Thread.currentThread().interrupt();
            }

            System.out.println("Toplam: " + b.total);
        }

        System.out.println("\n-----------------------------------\n");
    }

    // =====================================================
    // waitsizOrnek() icin kullanilan thread sinifi
    // =====================================================
    static class ToplamThread extends Thread {

        int total = 0;

        @Override
        public void run() {

            for (int i = 0; i < 100; i++) {
                total += i;
            }

            System.out.println("Thread hesaplamayi tamamladi.");
        }
    }

    // =====================================================
    // waitNotifyOrnegi() icin kullanilan thread sinifi
    // =====================================================
    static class ToplamThreadNotify extends Thread {

        int total = 0;

        @Override
        public void run() {

            synchronized (this) {

                for (int i = 0; i < 100; i++) {
                    total += i;
                }

                System.out.println("Thread hesaplamayi tamamladi.");

                // Bekleyen main thread'i uyandir
                notify();
            }
        }
    }
}
