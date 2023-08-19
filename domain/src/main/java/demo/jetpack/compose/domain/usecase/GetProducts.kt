package demo.jetpack.compose.domain.usecase

import demo.jetpack.compose.domain.model.Product
import demo.jetpack.compose.domain.repository.ProductRepository
import demo.jetpack.compose.domain.state.ProductScreenState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class GetProducts @Inject constructor (private val repository: ProductRepository) {
    suspend operator fun invoke(): Flow<ProductScreenState> {
        return repository.fetchProducts()
            .map { products ->
                if (products.isEmpty()) {
                    ProductScreenState.Error
                } else {
                    ProductScreenState.Success(products)
                }
            }
            .onStart { emit(ProductScreenState.Loading) }
            .catch { emit(ProductScreenState.Error) }
    }
}