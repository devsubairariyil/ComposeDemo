package demo.jetpack.compose.domain.state

import demo.jetpack.compose.domain.model.Product

sealed class ProductScreenState {
    object Loading : ProductScreenState()
    data class Success(val products: List<Product>) : ProductScreenState()
    object Error : ProductScreenState()
}