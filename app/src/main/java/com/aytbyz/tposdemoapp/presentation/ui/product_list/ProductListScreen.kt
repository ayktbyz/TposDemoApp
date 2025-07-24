package com.aytbyz.tposdemoapp.presentation.ui.product_list

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.aytbyz.tposdemoapp.R
import com.aytbyz.tposdemoapp.domain.model.product.Product
import com.aytbyz.tposdemoapp.presentation.ui.components.bottomsheet.PaymentOptionsBottomSheet
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun ProductListScreen(
    onSelectQr: () -> Unit,
    onSelectNfc: () -> Unit,
    onSelectLoyalty: () -> Unit
) {
    val viewModel: ProductListViewModel = hiltViewModel()

    val productList by viewModel.products.collectAsState()

    var paymentOptionsBottomShowSheet by remember { mutableStateOf(false) }

    if (paymentOptionsBottomShowSheet) {
        PaymentOptionsBottomSheet(
            onSelectQr = { paymentOptionsBottomShowSheet = false },
            onSelectNfc = { paymentOptionsBottomShowSheet = false },
            onSelectLoyalty = { paymentOptionsBottomShowSheet = false },
            onDismissRequest = { paymentOptionsBottomShowSheet = false }
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
                    onBuyClick = { paymentOptionsBottomShowSheet = true })
            }
        }
    }
}

@Composable
fun ProductCard(
    product: Product,
    onBuyClick: () -> Unit
) {
    val shape = RoundedCornerShape(12.dp)
    val buttonShape = RoundedCornerShape(8.dp)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(0.85f)
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(product.imageUrl),
                contentDescription = product.name,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .clip(shape)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = product.name,
                style = MaterialTheme.typography.labelLarge,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(4.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "₺${product.price}",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Black
                )

                Button(
                    onClick = onBuyClick,
                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 2.dp),
                    shape = buttonShape
                ) {
                    Text(
                        text = stringResource(id = R.string.buy_now),
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}