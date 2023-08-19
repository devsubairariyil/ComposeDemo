package demo.jetpack.compose.domain.usecase

import demo.jetpack.compose.domain.model.Product
import demo.jetpack.compose.domain.state.Result
import demo.jetpack.compose.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class GetProducts @Inject constructor (private val repository: ProductRepository) {
    suspend operator fun invoke(): Flow<Result<List<Product>>> {
        return repository.fetchProducts()
            .map { products ->
                if (products.isEmpty()) {
                    Result.Error("Product List is empty")
                } else {
                    Result.Success(products)
                }
            }
            .onStart { emit(Result.Loading) }
            .catch { emit(Result.Error(it.message ?: "Unknown Error")) }
    }
}