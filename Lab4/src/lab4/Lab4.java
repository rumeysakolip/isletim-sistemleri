package lab4;

public class Lab4 {

    public static void main(String[] args) {

        System.out.println("=== LAB 4 - Thread Ornekleri ===\n");

        /*
         * Calistirmak istedigin ornegin basindaki // isaretini kaldir.
         */

        // threadSinifiOrnegi();
        // runnableOrnegi();
        // testThreadOrnegi();
    }

    // =====================================================
    // 1) Thread sinifini extend ederek thread olusturma
    // =====================================================
    public static void threadSinifiOrnegi() {

        System.out.println("1) Thread sinifini extend ederek thread olusturma\n");

        ToplamaThread t1 = new ToplamaThread();
        t1.start();

        try {
            t1.join();
        } catch (InterruptedException e) {
            System.out.println("Thread beklenirken hata olustu.");
            Thread.currentThread().interrupt();
        }

        System.out.println("\n-----------------------------------\n");
    }

    static class ToplamaThread extends Thread {

        @Override
        public void run() {
            int a = 10;
            int b = 12;
            int result = a + b;

            System.out.println("Thread calismaya basladi...");
            System.out.println("Iki sayinin toplami: " + result);
        }
    }

    // =====================================================
    // 2) Runnable interface kullanarak thread olusturma
    // =====================================================
    public static void runnableOrnegi() {

        System.out.println("2) Runnable interface ile thread olusturma\n");

        YeniThread t1 = new YeniThread("Thread 1");
        YeniThread t2 = new YeniThread("Thread 2");
        YeniThread t3 = new YeniThread("Thread 3");

        try {
            t1.thread.join();
            t2.thread.join();
            t3.thread.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread kesintiye ugradi.");
            Thread.currentThread().interrupt();
        }

        System.out.println("Main Thread'den cikiliyor.");
        System.out.println("\n-----------------------------------\n");
    }

    static class YeniThread implements Runnable {

        String name;
        Thread thread;

        public YeniThread(String name) {
            this.name = name;
            this.thread = new Thread(this, name);

            System.out.println("Yeni thread olusturuldu: " + thread);
            thread.start();
        }

        @Override
        public void run() {

            try {
                for (int j = 10; j > 0; j--) {
                    System.out.println(name + " : " + j);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                System.out.println(name + " thread kesintiye ugradi.");
                Thread.currentThread().interrupt();
            }

            System.out.println(name + " thread cikiliyor.");
        }
    }

    // =====================================================
    // 3) Ayni anda sayi ve karakter yazdiran threadler
    // =====================================================
    public static void testThreadOrnegi() {

        System.out.println("3) Sayi ve karakter yazdiran paralel thread ornegi\n");

        PrintChar printA = new PrintChar('a', 100);
        PrintChar printB = new PrintChar('b', 100);
        PrintNum print100 = new PrintNum(100);

        print100.start();
        printA.start();
        printB.start();

        try {
            print100.join();
            printA.join();
            printB.join();
        } catch (InterruptedException e) {
            System.out.println("Thread beklenirken hata olustu.");
            Thread.currentThread().interrupt();
        }

        System.out.println("\n\nTum threadler tamamlandi.");
        System.out.println("-----------------------------------");
    }

    static class PrintChar extends Thread {

        private final char charToPrint;
        private final int times;

        public PrintChar(char c, int t) {
            charToPrint = c;
            times = t;
        }

        @Override
        public void run() {
            for (int i = 0; i < times; i++) {
                System.out.print(charToPrint);

                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    static class PrintNum extends Thread {

        private final int lastNum;

        public PrintNum(int n) {
            lastNum = n;
        }

        @Override
        public void run() {
            for (int i = 1; i <= lastNum; i++) {
                System.out.print(" " + i);

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}