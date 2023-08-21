package demo.jetpack.compose.navigation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import demo.jetpack.compose.navigation.data.Product
import demo.jetpack.compose.navigation.ui.components.MyAppToolbar
import demo.jetpack.compose.navigation.ui.components.ProductCard
import demo.jetpack.compose.navigation.ui.theme.AquaBlue


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductScreen(products: List<Product>) {
    Scaffold(
        topBar = {
            MyAppToolbar(title = "Products")
        }

    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .background(AquaBlue)
        ) {

            LazyColumn {
                items(products) { product ->
                    ProductCard(product = product)
                }
            }
        }
    }
}