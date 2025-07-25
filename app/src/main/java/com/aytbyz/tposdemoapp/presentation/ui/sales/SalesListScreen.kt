package com.aytbyz.tposdemoapp.presentation.ui.sales

import android.widget.Toast
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
import com.aytbyz.tposdemoapp.domain.model.sale.Sale
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.aytbyz.tposdemoapp.R
import com.aytbyz.tposdemoapp.presentation.ui.components.card.SaleCard
import com.aytbyz.tposdemoapp.presentation.util.toReadableTime
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SalesListScreen(
    viewModel: SalesListViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val sales by viewModel.sales.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.events.collectLatest { event ->
            when (event) {
                is SalesListViewModel.SalesListEvent.ShowSaleDeletedToast -> {
                    Toast.makeText(
                        context,
                        context.getString(R.string.sale_deleted_message),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.sales_title),
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        if (sales.isEmpty()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 48.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = R.string.no_sales_message),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 6.dp, horizontal = 6.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(sales) { sale ->
                    SaleCard(
                        paymentMethod = sale.paymentMethod,
                        time = sale.timestamp.toReadableTime(),
                        productName = sale.productName,
                        productId = stringResource(id = R.string.nfc_product_id, sale.productId),
                        price = "₺${sale.price}",
                        cardUid = sale.cardUid,
                        onDeleteClick = {
                            viewModel.deleteSale(sale.id)
                        }
                    )
                }
            }
        }
    }
}