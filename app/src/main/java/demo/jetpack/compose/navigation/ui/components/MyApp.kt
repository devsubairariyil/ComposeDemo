package demo.jetpack.compose.navigation.ui.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import demo.jetpack.compose.navigation.ui.screens.MenuScreen
import com.plcoding.meditationuiyoutube.ui.Screens.ProductDetailsScreen
import com.plcoding.meditationuiyoutube.ui.Screens.ProductScreen
import demo.jetpack.compose.navigation.data.AppMenu
import demo.jetpack.compose.navigation.data.Product

@Composable
fun App(menuList: List<AppMenu>, products: List<Product>) {

    val navController = rememberNavController()


    NavHost(navController = navController, startDestination = "menu") {
        composable("menu") { MenuScreen(navController, menuList) }
        composable("product_list") {
            ProductScreen(
                navController = navController,
                products = products
            )
        }
        composable(
            "productDetails/{productId}",
            arguments = listOf(navArgument("productId") { type = NavType.StringType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId")
            productId?.let { ProductDetailsScreen(it, navController) }
        }
    }
}