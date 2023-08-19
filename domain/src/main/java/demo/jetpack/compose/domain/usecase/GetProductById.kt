package demo.jetpack.compose.domain.usecase

import demo.jetpack.compose.domain.model.Product
import demo.jetpack.compose.domain.repository.ProductRepository
import demo.jetpack.compose.domain.state.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class GetProductById @Inject constructor (private val repository: ProductRepository) {
    suspend operator fun invoke(id: String): Flow<Result<Product>> {
        return repository.getProductById(id)
            .map { product ->
                if (product == null) {
                    Result.Error("Product Not Found")
                } else {
                    Result.Success(product)
                }
            }
            .onStart { emit(Result.Loading) }
            .catch { emit(Result.Error(it.message ?: "Unknown Error")) }
    }
}

