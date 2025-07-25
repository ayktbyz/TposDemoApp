tposdemoapp/
│
├── data/                         # Veri kaynakları (Room, Mapper, Repository Implementation)
│   ├── local/
│   │   └── dao/                  # Room DAO arayüzleri
│   ├── mapper/                   # Entity <-> Domain dönüştürücüleri
│   └── repository/               # Repository implementasyonları (ProductRepositoryImpl, SaleRepositoryImpl)
│
├── domain/                      # Uygulamanın iş kurallarını içeren katman
│   ├── model/                   # Domain modelleri (Sale, Product, PaymentType, vb.)
│   ├── repository/              # Repository arayüzleri
│   └── usecase/                 # UseCase’ler (örneğin: CreateSaleWithRandomProductUseCase, DeleteSaleUseCase)
│
├── presentation/                # UI katmanı (Jetpack Compose + ViewModel)
│   ├── ui/
│   │   ├── components/          # Yeniden kullanılabilir UI bileşenleri (örneğin: SaleCard, PaymentMethodRow)
│   │   ├── nfc_payment/         # NFC ile ödeme ekranı ve ViewModel’i
│   │   ├── qr_payment/          # QR ile ödeme ekranı ve ilgili ViewModel
│   │   ├── sales/               # Satış listesi ekranı ve ViewModel’i
│   │   └── settings/            # Ayarlar ekranı
│   └── util/                    # Yardımcı sınıflar (örneğin: NfcManager, zaman formatlayıcı)
│
├── res/
│   ├── values/
│   │   ├── strings.xml          # Çok dilli destekli metinler (örneğin: nfc_payment_prompt, sale_deleted_message)
│   │   └── themes.xml           # Tema tanımları
│   └── drawable/                # Statik ikonlar (örneğin: creditcard.png, qrcode.png)
│
├── MainActivity.kt              # Uygulamanın giriş noktası
└── TPosDemoApplication.kt       # Hilt uygulama başlatıcısı
