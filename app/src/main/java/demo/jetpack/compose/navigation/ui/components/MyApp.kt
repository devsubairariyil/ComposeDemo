package demo.jetpack.compose.navigation.ui.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import demo.jetpack.compose.navigation.ui.screens.productlist.ProductScreen
import demo.jetpack.compose.navigation.model.AppMenu
import demo.jetpack.compose.navigation.ui.screens.details.ProductDetailsScreen
import demo.jetpack.compose.navigation.ui.screens.menu.MenuScreen

@Composable
fun App(menuList: List<AppMenu>) {

    val navController = rememberNavController()


    NavHost(navController = navController, startDestination = "menu") {
        composable("menu") {
            MenuScreen(menuList){
                if(it == 1){
                    navController.navigate("product_list")
                }
            }
        }
        composable("product_list") {
            ProductScreen(
                navController = navController
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