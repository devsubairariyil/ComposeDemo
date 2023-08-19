package demo.jetpack.compose.navigation.ui.screens.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import demo.jetpack.compose.domain.model.Product
import demo.jetpack.compose.domain.state.Result
import demo.jetpack.compose.domain.state.Result.Loading
import demo.jetpack.compose.domain.usecase.GetProductById
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(val fetchProductById: GetProductById): ViewModel() {
    private val _screenState = MutableStateFlow<Result<Product>>(Result.Loading)
    val screenState = _screenState

    fun getProductById(id: String) {
        viewModelScope.launch {
            try {
                _screenState.value = Loading
                val productsFlow = fetchProductById(id)
                productsFlow.collect { product ->
                    _screenState.value = product
                }
            } catch (e: Exception) {
                _screenState.value = Result.Error(e.message ?: "Unknown Error")
            }
        }
    }
}