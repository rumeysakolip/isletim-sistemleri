# İşletim Sistemleri

Bu repo, İşletim Sistemleri dersi kapsamında hazırlanmış laboratuvar ve uygulama çalışmalarını içermektedir. Projeler ağırlıklı olarak Java dili ile geliştirilmiş olup process yönetimi, thread kullanımı, senkronizasyon ve temel sistem bilgisi alma gibi konulara odaklanmaktadır.

# İçerik

## lab2Process
Bu klasör, process oluşturma ve dış komut çalıştırma konularına odaklanan bir laboratuvar çalışmasını içerir. Uygulama içinde Windows komutlarının process olarak çalıştırılması, parametre ile yeni process başlatılması ve internet bağlantısı kontrolü gibi örnekler yer alır.

## Lab3
Bu klasörde process yönetimi ile ilgili farklı senaryolar bulunmaktadır. Tek bir process çalıştırma, process bitene kadar bekleme, sıralı process çalıştırma ve paralel process çalıştırma örnekleri bu bölümde toplanmıştır.

## Lab4
Bu klasör thread kavramını farklı yaklaşımlarla ele alan örnekler içerir.

### `src/lab4/Lab4.java`
Thread sınıfını genişletme, `Runnable` kullanımı ve aynı anda sayı/karakter yazdıran thread örneklerini bir arada gösteren ana örnek dosyadır.

### `src/testthread/TestThread.java`
Paralel çalışan thread’ler ile sayı ve karakter yazdırma mantığını gösteren temel bir thread örneğidir.

### `src/threadexample1/ThreadExample1.java`
`Thread` sınıfını genişleterek basit bir iş parçacığı oluşturma örneğidir.

### `src/threadexample2/ThreadExample2.java`
`Runnable` arayüzü ile birden fazla thread oluşturma ve çalıştırma örneğidir.

## Lab5
Bu laboratuvar, thread senkronizasyonu konusuna odaklanır.

### `src/lab5/Lab5.java`
`wait()` ve `notify()` mekanizmalarının nasıl çalıştığını göstermek için hazırlanmıştır. Thread sonucu beklenmeden okunduğunda oluşabilecek sorun ile doğru senkronizasyon kullanımı karşılaştırılır.

## Lab6
Bu laboratuvar çoklu thread kullanarak sayısal işlem yapma örneği sunar.

### `src/lab6/Lab6.java`
Komut satırından girilen sayılar üzerinde ortalama, minimum ve maksimum değerleri ayrı thread’ler ile hesaplar. Böylece aynı veri kümesi üzerinde eş zamanlı işlem mantığı gösterilir.

## deney1-ioprojesi
Bu klasör temel giriş/çıkış ve sistem bilgisi odaklı bir Java uygulamasını içerir.

### `src/main/java/com/mycompany/deney1/ioprojesi/Deney1Ioprojesi.java`
Disk bilgisi görüntüleme, internet bağlantısını kontrol etme ve çalışan process’leri listeleme işlemlerini yapan ana uygulama dosyasıdır.

# Proje Yapısı

Repo içinde farklı proje tipleri bulunmaktadır:

- Bazı klasörlerde `build.xml`, `manifest.mf`, `nbproject/` ve `build/` yapısı yer alır. Bunlar NetBeans/Ant tabanlı proje yapısını gösterir.
- `deney1-ioprojesi` klasöründe `pom.xml` ve `target/` bulunur. Bu yapı Maven tabanlı projeye işaret eder.

# Amaç
Bu repo, İşletim Sistemleri dersinde ele alınan temel kavramların uygulamalı şekilde öğrenilmesi amacıyla hazırlanmıştır. İçerikte özellikle şu başlıklar öne çıkmaktadır:

- Process oluşturma ve yönetimi
- Thread oluşturma yöntemleri
- Thread senkronizasyonu
- Paralel çalışma mantığı
- Sistem bilgisi ve giriş/çıkış işlemleri

# Not
Projelerde kullanılan bazı komutlar Windows ortamına özeldir. Özellikle `cmd`, `tasklist`, `mspaint.exe` ve `notepad.exe` gibi yapılar Linux ortamında doğrudan çalışmayabilir. Bu nedenle bazı örnekler işletim sistemine göre uyarlanmalıdır.
