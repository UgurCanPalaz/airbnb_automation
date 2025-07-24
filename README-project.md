# Sahibinden Yazılım Test Otomasyon Stajyer Proje Ödevi

##  Kapsam ve Beklentiler
Aşağıda verilen senaryo ve teknolojiler doğrultusunda bir test otomasyon projesi geliştirmeniz beklenmektedir.

### Kullanılması Gereken Teknolojiler
- Java
- Selenium WebDriver
- JUnit 5
- Maven veya Gradle

Belirtilen teknolojilerin dışına çıkılmaması rica olunur.

---

##  Test Edilecek Senaryo

Testler, [https://www.airbnb.com](https://www.airbnb.com) adresi üzerinden gerçekleştirilecektir.

### 1. Arama Yapılması
- Airbnb web sitesi ziyaret edilir.
- Paris lokasyonu seçilir.
- Herhangi bir tarih aralığı belirlenir.
- 2 yetişkin, 1 çocuk için arama yapılır.
- Arama sonuç sayfasında, bu filtrelerin URL parametreleri olarak yer aldığı kontrol edilir.

### 2. Filtre Uygulama ve Doğrulama
- Sonuç sayfasındaki Filters alanından:
    - Maksimum fiyat: 20.000 TL
    - Free cancellation (ücretsiz iptal) filtresi seçilir.
- Filtrelerin arama sonuç üzerinde doğru yansıtıldığı doğrulanır.
- Arama sonuçlarından bir ilan seçilerek detay sayfasına gidilir.
- Açılan ilan sayfasında, seçilen filtrelere (fiyat, iptal seçeneği vb.) uygunluk kontrol edilir.

### 3. Ekstra Filtre Senaryoları
- Yukarıdakilere ek olarak 2 farklı filtre senaryosu daha oluşturulmalı ve otomatikleştirilmelidir.
- Filtre seçimleri serbesttir.

---

##  Teslim İçeriği

### 1. Proje Kaynak Kodları
- Maven veya Gradle projesi olarak yapılandırılmış olmalı.

### 2. `README.md` (Bu dosya)
- Projenin nasıl çalıştırılacağını açıklayan dokümantasyon.
- Kullanılan teknolojiler ve test senaryoları hakkında kısa açıklamalar.

### 3. Test Raporu
- Test sonuçlarının sunulduğu bir rapor dosyası veya rapor sayfası.
- Aşağıdaki yöntemlerden biri ile olabilir:
    - Hazır bir raporlama aracı (ör. Allure)
    - Basit, özelleştirilmiş bir HTML raporu

---

## ⭐⭐⭐ Bonus Özellikler

-  Yazılmış tüm senaryoların mobil site üzerinde de koşması
-  Lokalde testlerin paralel (eş zamanlı birden çok browser ın aynı anda açılması) olarak çalışması (Sadece Chrome yeterlidir)
-  Docker ile bir Jenkins kurulumu ve senaryoların Jenkins üzerinde paralel koşması

---

## Teslim Süresi

- Teslim tarihi en geç, 30 Temmuz 2025 Çarşamba - 23.30

---

**Başarılar!** 

