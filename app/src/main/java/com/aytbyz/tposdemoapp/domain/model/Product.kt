package com.aytbyz.tposdemoapp.domain.model

data class Product(
    val id: String,
    val name: String,
    val price: Double,
    val imageUrl: String
)

object ProductUIDummy {
    fun getDummyProducts(): List<Product> {
        return listOf(
            Product(
                "1",
                "Hugin TIGER T300",
                299.90,
                "https://www.hugin.com.tr/Uploads/Images/tigert300.jpg"
            ),
            Product(
                "2",
                "Payfone N750",
                849.99,
                "https://www.hugin.com.tr/Uploads/Images/products/Payfone%20N750/payfone-n750.png"
            ),
            Product("3", "Hugin FP-300", 199.00, "https://hugin.com.tr/uploads/fp300_555320.jpg"),
            Product(
                "4",
                "T300U Unattended POS",
                129.50,
                "https://hugin.com.tr/Uploads/hugin-t300u.png"
            ),
            Product(
                "5",
                "e-Fatura POS N910",
                399.00,
                "https://hugin.com.tr/Uploads/Images/products/n910/n910.png"
            )
        )
    }
}