package demo.jetpack.compose.navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import dagger.hilt.android.AndroidEntryPoint
import demo.jetpack.compose.navigation.model.AppMenu
import demo.jetpack.compose.navigation.model.DataSource
import demo.jetpack.compose.navigation.ui.components.App
import demo.jetpack.compose.navigation.ui.theme.DemoAppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            DemoAppTheme {
                App(menuList = generateMenu())
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