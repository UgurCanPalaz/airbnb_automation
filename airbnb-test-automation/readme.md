# Airbnb Test Otomasyonu

## ⚠️ Önemli Notlar
- Selector seçimi isim bazlı olabildiğinden site Türkçe olarak açılmalı.


Bu proje, Selenium WebDriver ve JUnit kullanılarak Airbnb web sitesinde otomatik arama ve filtre testleri yapmayı amaçlamaktadır.

---

## Test Senaryosu Özeti

### 1. Arama Yapılması

- Airbnb ana sayfası açılır.
- Lokasyon olarak **Paris** seçilir.
- Rastgele bir tarih aralığı belirlenir (güncel tarihten itibaren 7-14 gün sonrası arasında).
- 2 yetişkin ve 1 çocuk olarak kişi sayısı ayarlanır.
- Arama yapılır.
- Arama sonuç sayfasında filtrelerin URL parametreleri olarak doğru şekilde yer aldığı doğrulanır.

### 2. Filtre Uygulama ve Doğrulama

- Sonuç sayfasındaki **Filtreler** alanı açılır.
- Maksimum fiyat **20.000 TL** olarak ayarlanır.
- Minimum fiyat **1.000 TL** olarak ayarlanır.
- **Free cancellation (Ücretsiz iptal)** filtresi seçilir.
- En az 1 yatak odası seçilir.
- Filtreler uygulanır ve arama sonuçlarının URL'de doğru şekilde yansıdığı doğrulanır.
- Arama sonuçlarından rastgele bir ilan seçilir ve detay sayfasına gidilir.
- Detay sayfasında seçilen filtrelere uygunluk (fiyat aralığı ve ücretsiz iptal) kontrol edilir.

### 3. Pop-up Kapatma

- Eğer açılan detay sayfasında bir pop-up (örneğin, "Kapat" butonu olan) varsa, JavaScript ile tıklanarak kapatılır.

---

## Gereksinimler

- Java 15 veya üzeri (text blocks için)
- Maven (build ve test için)
- Selenium WebDriver
- ChromeDriver (uyumlu versiyon)
- İnternet bağlantısı

---

## Çalıştırma

1. Projeyi klonlayın veya indirin.
2. Maven ile bağımlılıkları indirin ve derleyin:
   ```bash
   mvn clean install
3. mvn test
