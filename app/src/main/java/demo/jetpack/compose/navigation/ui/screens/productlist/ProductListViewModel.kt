package demo.jetpack.compose.navigation.ui.screens.productlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import demo.jetpack.compose.domain.model.Product
import demo.jetpack.compose.domain.state.Result
import demo.jetpack.compose.domain.usecase.GetProducts
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(val getProducts: GetProducts): ViewModel() {
    private val _screenState = MutableStateFlow<Result<List<Product>>>(Result.Loading)
    val screenState = _screenState

    init {
        viewModelScope.launch {
            try {
                val productsFlow = getProducts()
                productsFlow.collect { productsScreenState ->
                    _screenState.value = productsScreenState
                }
            } catch (e: Exception) {
                _screenState.value = Result.Error(e.message ?: "Unknown Error")
            }
        }
    }
}