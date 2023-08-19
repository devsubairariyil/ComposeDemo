package demo.jetpack.compose.domain.usecase

import demo.jetpack.compose.domain.model.Product
import demo.jetpack.compose.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductById @Inject constructor (private val repository: ProductRepository) {
    suspend operator fun invoke(id: String): Flow<Product> {
        return repository.getProductById(id)
    }
}