package demo.jetpack.compose.data.repo

import demo.jetpack.compose.domain.model.ImageUrls
import demo.jetpack.compose.domain.model.Product
import demo.jetpack.compose.domain.repository.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class ProductRepositoryImpl : ProductRepository {

    override suspend fun fetchProducts(): Flow<List<Product>> {
        return flow {
            delay(3000)
            emit(generateProducts())
        }.flowOn(Dispatchers.IO)

    }

    override suspend fun getProductById(id: String): Flow<Product> {
        return flow {
            delay(3000)
            emit(generateProducts().first { it.productCode == id })
        }.flowOn(Dispatchers.IO)
    }

    private fun generateProducts(): List<Product>{
        val list = mutableListOf<Product>()
        for (i in 1 until 500){
            val indicators = mutableListOf<String>()
            repeat(10){
                indicators.add(java.util.UUID.randomUUID().toString().substring(1,3).uppercase())
            }

            list.add(
                Product(
                    name = "Product - $i",
                    productCode = "PC-$i${i+1}",
                    discount = 0,//Random(20).toString().toInt(),
                    price = 100.0,//Random(200).toString().toDouble(),
                    indicators = indicators,
                    isSoldOut = i % 3 == 0,
                    isRestricted = i % 7 == 0,
                    imageUrl = ImageUrls.productImageUrls[i % 10],
                    description = "Product Description Lorem ipsum dolor sit amet, consectetur adipiscing elit."

                )
            )
        }
        return list
    }
}