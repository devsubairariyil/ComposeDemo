package demo.jetpack.compose.navigation.model

data class AppMenu(
    val title: String,
    val action: () -> Unit
)
