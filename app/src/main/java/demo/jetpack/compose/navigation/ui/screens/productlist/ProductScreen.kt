package demo.jetpack.compose.navigation.ui.screens.productlist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import demo.jetpack.compose.domain.model.ImageUrls
import demo.jetpack.compose.domain.model.Product
import demo.jetpack.compose.navigation.ui.components.ProductCard
import demo.jetpack.compose.domain.state.Result
import demo.jetpack.compose.navigation.ui.components.MyAppToolbar


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductScreen(navController: NavHostController, viewModel: ProductListViewModel) {
    val screenState by viewModel.screenState.collectAsState()
    Scaffold(
        topBar = {
            MyAppToolbar(title = "Products",
                navigateUp = {
                    navController.popBackStack()
                })
        }

    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .testTag("AppContent")
                .background(MaterialTheme.colorScheme.background)
        ) {

            when (screenState) {
                is Result.Loading -> {
                    // Show loading indicator
                    Box(modifier = Modifier
                        .fillMaxSize()
                        .testTag("LoadingIndicator"),
                        contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }

                is Result.Success -> {
                    // Show data
                    val products = (screenState as Result.Success).data
                    createProductList(products = products, navController)
                }

                is Result.Error -> {
                    // Show error message
                    Text((screenState as Result.Error).errorMessage)
                }
            }
        }
    }
}


@Composable
fun createProductList(products: List<Product>, navController: NavHostController){
    LazyColumn(modifier = Modifier.testTag("ProductList")) {

        items(products) { product ->
            ProductCard(product = product, navController)
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun createProductListPreview(){
    val navController = rememberNavController()

    Scaffold(
        topBar = {
            MyAppToolbar(title = "Products",
                navigateUp = {
                    navController.popBackStack()
                })
        }

    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            createProductList(generatePreviewProducts(), navController)
        }
    }

}

private fun generatePreviewProducts(): List<Product>{
    val list = mutableListOf<Product>()
    for (i in 1 until 50){
        val indicators = mutableListOf<String>()
        repeat(10){
            indicators.add(java.util.UUID.randomUUID().toString().substring(1,3).uppercase())
        }

        list.add(
            Product(
                name = "Product - $i",
                productCode = "PC-$i",
                discount = 0,//Random(20).toString().toInt(),
                price = 100.0,//Random(200).toString().toDouble(),
                indicators = indicators,
                isSoldOut = i % 3 == 0,
                isRestricted = i % 7 == 0,
                imageUrl = ImageUrls.productImageUrls[i % 10],
                description = "Product Description Lorem ipsum dolor sit amet, consectetur adipiscing elit."

            )
        )
    }
    return list
}