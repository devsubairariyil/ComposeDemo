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
                .background(MaterialTheme.colorScheme.background)
        ) {

            when (screenState) {
                is Result.Loading -> {
                    // Show loading indicator
                    Box(modifier = Modifier.fillMaxSize().testTag("LoadingIndicator"),
                        contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }

                is Result.Success -> {
                    // Show data
                    val products = (screenState as Result.Success).data
                    LazyColumn {

                        items(products) { product ->
                            ProductCard(product = product, navController)
                        }
                    }
                }

                is Result.Error -> {
                    // Show error message
                    Text((screenState as Result.Error).errorMessage)
                }
            }
        }
    }
}
