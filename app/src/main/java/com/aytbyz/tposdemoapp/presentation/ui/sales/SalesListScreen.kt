package com.aytbyz.tposdemoapp.presentation.ui.sales

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.aytbyz.tposdemoapp.domain.model.Sale
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Alignment

@Composable
fun SalesListScreen(
    sales: List<Sale>,
    onDelete: (Sale) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(sales) { sale ->
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Ürün ID: ${sale.productId}")
                    Text("Fiyat: ₺${sale.price}")
                    Text("Yöntem: ${sale.paymentType}")
                    Text("Zaman: ${sale.timestamp}")
                    Button(
                        onClick = { onDelete(sale) },
                        modifier = Modifier.align(Alignment.End)
                    ) {
                        Text("Sil")
                    }
                }
            }
        }
    }
}