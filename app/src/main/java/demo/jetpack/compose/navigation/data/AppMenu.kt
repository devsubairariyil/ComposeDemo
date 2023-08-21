package demo.jetpack.compose.navigation.data

data class AppMenu(
    val title: String,
    val action: () -> Unit
)