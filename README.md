📁 Proje Yapısı Açıklaması

📦 data/
Veri kaynaklarının yer aldığı katmandır. Bu katman Room DAO arayüzlerini, entity-domain dönüşüm işlemlerini ve repository implementasyonlarını içerir.
	•	local/dao/: Room veri tabanı erişim katmanı (DAO arayüzleri).
	•	mapper/: Entity (veritabanı modeli) ile domain modelleri arasında dönüşüm yapan yardımcı sınıflar.
	•	repository/: Repository arayüzlerinin veri kaynağına özel implementasyonları (örneğin ProductRepositoryImpl, SaleRepositoryImpl).

📦 domain/
Uygulamanın iş kurallarını barındıran katmandır. Veri kaynaklarından bağımsızdır.
	•	model/: Uygulamanın merkezinde yer alan domain modelleri (Sale, Product, PaymentType gibi).
	•	repository/: İş kurallarında kullanılacak repository arayüzleri.
	•	usecase/: Her bir işlev için tek sorumluluğa sahip Use Case sınıfları (örneğin CreateSaleWithRandomProductUseCase, DeleteSaleUseCase).

📦 presentation/
Kullanıcı arayüzünü ve kullanıcıyla etkileşimi yöneten katmandır. Jetpack Compose ve ViewModel ile yazılmıştır.
	•	ui/components/: Uygulama içinde yeniden kullanılabilir UI bileşenleri (SaleCard, PaymentMethodRow vb.).
	•	ui/nfc_payment/: NFC ile ödeme ekranı ve bu ekranın ViewModel’i.
	•	ui/qr_payment/: QR ile ödeme ekranı ve ilgili ViewModel.
	•	ui/sales/: Satış listesini gösteren ekran ve ViewModel.
	•	ui/settings/: Ayarlar ekranı.
	•	util/: Yardımcı sınıflar (NfcManager, tarih/saat dönüştürücü gibi).
