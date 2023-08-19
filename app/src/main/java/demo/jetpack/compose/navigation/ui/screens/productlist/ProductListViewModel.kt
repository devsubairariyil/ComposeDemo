package demo.jetpack.compose.navigation.ui.screens.productlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import demo.jetpack.compose.domain.model.Product
import demo.jetpack.compose.domain.state.ProductScreenState
import demo.jetpack.compose.domain.usecase.GetProducts
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(val getProducts: GetProducts): ViewModel() {
    private val _screenState = MutableStateFlow<ProductScreenState>(ProductScreenState.Loading)
    val screenState: StateFlow<ProductScreenState> = _screenState

    init {
        viewModelScope.launch {
            try {
                val productsFlow = getProducts()
                productsFlow.collect { productsScreenState ->
                    _screenState.value = productsScreenState
                }
            } catch (e: Exception) {
                _screenState.value = ProductScreenState.Error
            }
        }
    }
}