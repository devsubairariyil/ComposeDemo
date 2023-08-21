package demo.jetpack.compose.navigation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import demo.jetpack.compose.navigation.R
import demo.jetpack.compose.navigation.data.Product
import kotlin.math.roundToInt

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ProductCard(navController: NavController,
                product: Product,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, start = 16.dp, end = 16.dp)
            .clickable { navController.navigate("productDetails/${product.productCode}") },
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
        ) {
            // Thumbnail
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(10.dp))
            ) {
                AsyncImage(
                    model = product.imageUrl,
                    contentDescription = "Thumbnail",
                    contentScale = ContentScale.FillBounds,
                    placeholder = painterResource(id = R.drawable.thumbnail),
                    error = painterResource(id = R.drawable.thumbnail),
                )
                if (product.isSoldOut) {
                    Text(
                        text = "Sold Out",
                        color = Color.White,
                        fontSize = 14.sp,
                        modifier = Modifier
                            .background(Color.Red)
                            .padding(4.dp)
                            .align(Alignment.TopEnd)
                    )
                }
                if (product.isRestricted) {
                    Box(
                        modifier = Modifier
                            .wrapContentWidth()
                            .rotate(-45f) // Rotate the banner text diagonally
                            .align(Alignment.Center)
                    ) {
                        Text(
                            text = "Restricted",
                            color = Color.White,
                            fontSize = 10.sp,
                            modifier = Modifier
                                .background(Color.Red)
                                .padding(4.dp)
                        )
                    }
                }
            }


            Spacer(modifier = Modifier.width(16.dp))

            // Product details
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = product.name,
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Product Code: ${product.productCode}",
                    style = MaterialTheme.typography.bodyMedium.copy(Color.Black)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Price: $${product.price.roundToInt()}",
                        style = MaterialTheme.typography.bodyMedium.copy(Color.Black)
                    )
                    Text(
                        text = "-${product.discount}%",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Red,
                        fontSize = 16.sp
                    )
                }

                // Indicators
                Row(
                    modifier = Modifier
                        .padding(top = 4.dp)
                        .horizontalScroll(rememberScrollState())
                ) {
                    product.indicators.forEach { indicator ->
                        Text(
                            text = indicator,
                            modifier = Modifier
                                .padding(end = 4.dp)
                                .clip(CircleShape)
                                .background(Color.Blue)
                                .padding(horizontal = 8.dp, vertical = 4.dp),
                            color = Color.White,
                            fontSize = 12.sp
                        )
                    }
                }


            }
        }
    }
}
