package demo.jetpack.compose.navigation.ui.screens.details

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import demo.jetpack.compose.navigation.R
import demo.jetpack.compose.navigation.model.DataSource
import demo.jetpack.compose.navigation.ui.components.MyAppToolbar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailsScreen(productId: String, navController: NavController) {
    val product = DataSource.getProductById(productId) // Replace with your data retrieval logic
    var isFabVisible by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            MyAppToolbar(
                title = product.name,
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
    )
}


