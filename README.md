📁 Proje Yapısı Açıklaması

# 💳 TPOS Demo App

**TPOS Demo App**, NFC ve QR kod ile ödeme işlemlerinin simüle edildiği, satışların kaydedildiği ve listelendiği bir Android uygulamasıdır. Uygulama Jetpack Compose ve Clean Architecture prensipleriyle geliştirilmiştir.

---

## 🏛 Mimari Yapı

Proje, Clean Architecture ilkelerine uygun olarak 3 ana katmanda organize edilmiştir:

### 📦 `data/` – Veri Katmanı
- **`local/dao/`** – Room DAO arayüzleri
- **`mapper/`** – Entity <-> Domain dönüşümleri
- **`repository/`** – Repository implementasyonları (`ProductRepositoryImpl`, `SaleRepositoryImpl`)

### 💼 `domain/` – İş Kuralları Katmanı
- **`model/`** – Domain modelleri (`Sale`, `Product`, `PaymentType`)
- **`repository/`** – Repository arayüzleri
- **`usecase/`** – UseCase sınıfları (`CreateSaleWithRandomProductUseCase`, `DeleteSaleUseCase`)

### 🎨 `presentation/` – Arayüz Katmanı
- **`components/`** – Reusable UI bileşenleri (`SaleCard`, `PaymentMethodRow`)
- **`nfc_payment/`** – NFC ile ödeme ekranı
- **`qr_payment/`** – QR ile ödeme ekranı
- **`sales/`** – Satış listesi ekranı
- **`settings/`** – Ayarlar ekranı
- **`util/`** – Yardımcı sınıflar (`NfcManager`, zaman dönüştürücü vs.)

### 📁 Diğer Klasörler ve Dosyalar
- **`res/`** – Kaynak dosyaları (`strings.xml`, `themes.xml`, ikonlar)
- **`MainActivity.kt`** – Uygulamanın giriş noktası
- **`TPosDemoApplication.kt`** – Hilt uygulama başlatıcısı

---

## ⚙️ Kurulum

1. **Projeyi klonla:**
   ```bash
   git clone https://github.com/ayktbyz/tposdemoapp.git


# 💳 Detaylı Özet

📦 DATA

Veri kaynaklarının yer aldığı katmandır. Bu katman Room DAO arayüzlerini, entity-domain dönüşüm işlemlerini ve repository implementasyonlarını içerir.

	🧩 local/dao/: Room veri tabanı erişim katmanı (DAO arayüzleri).
 
	🧩 mapper/: Entity (veritabanı modeli) ile domain modelleri arasında dönüşüm yapan yardımcı sınıflar.
 
	🧩 repository/: Repository arayüzlerinin veri kaynağına özel implementasyonları (örneğin ProductRepositoryImpl, SaleRepositoryImpl).

📦 DOMAIN

Uygulamanın iş kurallarını barındıran katmandır. Veri kaynaklarından bağımsızdır.

	🧩 model/: Uygulamanın merkezinde yer alan domain modelleri (Sale, Product, PaymentType gibi).
 
	🧩 repository/: İş kurallarında kullanılacak repository arayüzleri.
 
	🧩 usecase/: Her bir işlev için tek sorumluluğa sahip Use Case sınıfları (örneğin CreateSaleWithRandomProductUseCase, DeleteSaleUseCase).

📦 PRESENTATION

Kullanıcı arayüzünü ve kullanıcıyla etkileşimi yöneten katmandır. Jetpack Compose ve ViewModel ile yazılmıştır.

	🧩 ui/components/: Uygulama içinde yeniden kullanılabilir UI bileşenleri (SaleCard, PaymentMethodRow vb.).
 
	🧩 ui/nfc_payment/: NFC ile ödeme ekranı ve bu ekranın ViewModel’i.
 
	🧩 ui/qr_payment/: QR ile ödeme ekranı ve ilgili ViewModel.
 
	🧩 ui/sales/: Satış listesini gösteren ekran ve ViewModel.
 
	🧩 ui/settings/: Ayarlar ekranı.
 
	🧩 util/: Yardımcı sınıflar (NfcManager, tarih/saat dönüştürücü gibi).
