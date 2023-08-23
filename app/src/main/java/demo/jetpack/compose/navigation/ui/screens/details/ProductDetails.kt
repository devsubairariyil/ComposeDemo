package demo.jetpack.compose.navigation.ui.screens.details

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import demo.jetpack.compose.domain.state.Result
import demo.jetpack.compose.navigation.R
import demo.jetpack.compose.navigation.ui.components.MyAppToolbar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailsScreen(productId: String, navController: NavController) {
    val viewModel: ProductDetailsViewModel = hiltViewModel()
    val screenState by viewModel.screenState.collectAsState()
    var isFabVisible by remember { mutableStateOf(false) }

    viewModel.getProductById(productId)
    Scaffold(
        topBar = {
            MyAppToolbar(
                title = "Product Details",
                navigateUp =  {
                    navController.popBackStack()
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* Handle FAB click if needed */ },
                content = { Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = null) }
            )
        },
        content = {
            when (screenState) {
                is Result.Loading -> {
                    // Show loading indicator
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                        CircularProgressIndicator()
                    }
                }
                is Result.Success -> {
                    // Show data
                    val product = (screenState as Result.Success).data
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(it),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        state = rememberLazyListState()

                    ) {
                        item {
                            AsyncImage(
                                model = product.imageUrl,
                                placeholder = painterResource(id = R.drawable.thumbnail),
                                error = painterResource(id = R.drawable.thumbnail),
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp)
                                    .padding(16.dp),
                                contentScale = ContentScale.Fit
                            )
                        }
                        item{
                            Text(
                                modifier = Modifier.padding(start = 16.dp, top = 4.dp),
                                text = product.name,
                                style = MaterialTheme.typography.titleLarge
                            )
                        }
                        item {
                            Text(modifier = Modifier.padding(start = 16.dp, top = 4.dp),
                                text = product.description,
                                style = MaterialTheme.typography.bodyMedium.copy(Color.Black)
                            )
                        }
                    }
                }
                is Result.Error -> {
                    // Show error message
                    Text((screenState as Result.Error).errorMessage)
                }
            }

        }
    )
}


