package demo.jetpack.compose.navigation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import demo.jetpack.compose.navigation.R
import demo.jetpack.compose.navigation.data.AppMenu
import demo.jetpack.compose.navigation.ui.theme.ButtonBlue
import demo.jetpack.compose.navigation.ui.theme.DeepBlue
import demo.jetpack.compose.navigation.ui.theme.TextWhite

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuScreen(menuList: List<AppMenu>, menuItemClicked: (menuId: Int) -> Unit) {
    var favoriteState by remember { mutableStateOf(false) }
    var settingsState by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = DeepBlue),
                title = {
                    Text(
                        text = "Compose Demo",
                        color = Color.White
                    )
                },
                actions = {
                    IconButton(onClick = { favoriteState = !favoriteState }) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_favourite),
                            contentDescription = "Favorite",
                            tint = if (favoriteState) Color.Red else Color.Gray
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    IconToggleButton(
                        checked = settingsState,
                        onCheckedChange = { settingsState = it }
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_settings),
                            contentDescription = "Settings"
                        )
                    }
                }

            )
        }
    ) {
        Column(
            Modifier
                .padding(it)
                .verticalScroll(rememberScrollState())
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            for (menu in menuList) {
                Box(

                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally)
                        .padding(16.dp)
                        .background(ButtonBlue)
                        .padding(16.dp)
                        .clickable {
                            menuItemClicked(menu.menuId)

                        }
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        text = menu.title,
                        style = MaterialTheme.typography.titleMedium.copy(color = TextWhite)
                    )
                }
            }
        }
    }
}