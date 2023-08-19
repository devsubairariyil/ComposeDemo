package demo.jetpack.compose.navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import demo.jetpack.compose.navigation.data.AppMenu
import demo.jetpack.compose.navigation.data.DataSource
import demo.jetpack.compose.navigation.ui.components.App
import demo.jetpack.compose.navigation.ui.theme.DemoAppTheme

class MainActivity : ComponentActivity() {
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            DemoAppTheme {
                App(menuList = generateMenu(), products = DataSource.productList)
            }
        }
    }

    private fun generateMenu(): List<AppMenu> {
        val list = mutableListOf<AppMenu>()
        list.add(AppMenu("Product Screen") {

        })
        return list
    }
}