package demo.jetpack.compose.navigation.ui.screens.details

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import demo.jetpack.compose.domain.usecase.GetProductById
import demo.jetpack.compose.domain.usecase.GetProducts
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(getProductById: GetProductById): ViewModel() {
    
}