package com.aytbyz.tposdemoapp.presentation.ui.product_list

import androidx.compose.foundation.Image
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aytbyz.tposdemoapp.R
import androidx.hilt.navigation.compose.hiltViewModel
import com.aytbyz.tposdemoapp.domain.model.product.Product
import com.aytbyz.tposdemoapp.presentation.ui.components.card.ProductCard
import com.aytbyz.tposdemoapp.presentation.util.QRUtils.generateQrBitmap

@Composable
fun ProductListScreen() {
    val viewModel: ProductListViewModel = hiltViewModel()
    val productList by viewModel.products.collectAsState()

    var showQrDialog by remember { mutableStateOf(false) }
    var selectedProduct by remember { mutableStateOf<Product?>(null) }

    if (showQrDialog && selectedProduct != null) {
        val qrText =
            "${selectedProduct!!.id}:${selectedProduct!!.name}:${selectedProduct!!.price}:${selectedProduct!!.imageUrl}"
        val bitmap = remember(qrText) { generateQrBitmap(qrText) }

        AlertDialog(
            onDismissRequest = { showQrDialog = false },
            confirmButton = {
                Button(onClick = { showQrDialog = false }) {
                    Text(text = stringResource(id = R.string.ok))
                }
            },
            title = { Text(stringResource(id = R.string.qr_code_title)) },
            text = {
                Column {
                    Text(stringResource(id = R.string.qr_code_description))
                    Spacer(modifier = Modifier.height(16.dp))
                    Image(
                        bitmap = bitmap.asImageBitmap(),
                        contentDescription = stringResource(id = R.string.qr_code_content_description),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                    )
                }
            }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.products_title),
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(productList) { product ->
                ProductCard(
                    product = product,
                    onBuyClick = {
                        selectedProduct = product
                        showQrDialog = true
                    }
                )
            }
        }
    }
}