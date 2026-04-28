package lab8;

public class Lab8 {

    // 1. Paylaşılan veriyi ve senkronizasyonu bir sınıf içine hapsediyoruz (Encapsulation)
    static class OrtakKasa {
        private int toplamPara = 0;

        // 'synchronized' kelimesi bu metoda aynı anda sadece 1 thread'in girmesini garanti eder.
        // Bu metodun içi bizim "Kritik Bölgemizdir" (Critical Section).
        public synchronized void islemYap(String calisanAdi, int eklenecekMiktar) {
            System.out.println(calisanAdi + " ==> Kasaya ulasti. (Kritik bolgeye girildi)");
            
            int oncekiPara = toplamPara;
            // Paylaşılan veriyi güncelliyoruz
            toplamPara += eklenecekMiktar; 
            
            System.out.println(calisanAdi + " ==> Islem yapildi. (Eski Bakiye: " + oncekiPara + " | Yeni Bakiye: " + toplamPara + ")");
            System.out.println(calisanAdi + " ==> Kasadan ayriliyor. (Kritik bolgeden cikildi)\n");
        }
    }

    // 2. Thread sınıfını miras almak yerine, Runnable arayüzünü uyguluyoruz
    static class Calisan implements Runnable {
        private OrtakKasa kasa; // Tüm çalışanların erişeceği ortak nesne
        
        public Calisan(OrtakKasa kasa) {
            this.kasa = kasa;
        }

        @Override
        public void run() {
            String ad = Thread.currentThread().getName();
            System.out.println(ad + " calismaya basladi ve kasaya dogru gidiyor.");
            
            // Her çalışan kasaya 100 birim para eklesin
            kasa.islemYap(ad, 100);
        }
    }

    /**
     * Main Metodu
     */
    public static void main(String[] args) throws InterruptedException {
        int threadSayisi = 3;
        
        // Bellekte tek bir kasa nesnesi oluşturuyoruz. Bütün threadler bu nesneyi paylaşacak.
        OrtakKasa anaKasa = new OrtakKasa(); 
        Thread[] calisanlar = new Thread[threadSayisi];

        System.out.println("--- Test Islemi Basliyor ---\n");

        // Thread'leri (Çalışanları) oluşturup başlatıyoruz
        for (int i = 0; i < threadSayisi; i++) {
            calisanlar[i] = new Thread(new Calisan(anaKasa), "Calisan-" + (i + 1));
            calisanlar[i].start();
        }

        // Main thread'in diğer tüm thread'lerin işini bitirmesini beklemesi (join işlemi)
        System.out.println("Main: Calisanlarin isini bitirmesi bekleniyor...\n");
        for (int i = 0; i < threadSayisi; i++) {
            calisanlar[i].join(); 
        }

        System.out.println("--- Tum calisanlar isini bitirdi. Program sonlaniyor. ---");
    }
}