package demo.jetpack.compose.navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import dagger.hilt.android.AndroidEntryPoint
import demo.jetpack.compose.navigation.model.AppMenu
import demo.jetpack.compose.navigation.ui.components.App
import demo.jetpack.compose.navigation.ui.theme.DemoAppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            DemoAppTheme {
                App(
                    menuList = listOf(
                        AppMenu(
                            "Product List",
                            1
                        )
                    )
                )
            }
            //ProductScreen(products = DataSource.productList)
            //ProductDetailsScreen(productId = "PC-12")
            }
        }
    }

