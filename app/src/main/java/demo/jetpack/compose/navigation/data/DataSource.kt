package demo.jetpack.compose.navigation.data

object DataSource {
    val productList = generateProducts()

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

    fun getProductById(id: String): Product {
        return productList.first {
            id == it.productCode
        }
    }
}