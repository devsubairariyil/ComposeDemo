package demo.jetpack.compose.domain.repository

import demo.jetpack.compose.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    suspend fun fetchProducts(): Flow<List<Product>>
    suspend fun getProductById(id: String): Flow<Product>
}