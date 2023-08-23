package demo.jetpack.compose.domain.model

data class Product(
    val name: String,
    val productCode: String,
    val indicators: List<String>,
    val price: Double,
    val discount: Int,
    val imageUrl:String = "",
    val isSoldOut: Boolean = false,
    val isRestricted: Boolean = false,
    val description: String = "",

) {
    companion object {
        fun default(): Product {
            return Product(
                name = "Sample Product",
                productCode = "Code",
                indicators = listOf("A", "B"),
                price = 100.0,
                discount = 25,
                imageUrl = "",
                isSoldOut = true,
                isRestricted = true,
                description = ""
                )
        }
    }
}

object ImageUrls {
    val productImageUrls = arrayOf(
        "https://picsum.photos/id/1018/400/300",
        "https://picsum.photos/id/1021/400/300",
        "https://picsum.photos/id/1024/400/300",
        "https://picsum.photos/id/1027/400/300",
        "https://picsum.photos/id/1030/400/300",
        "https://picsum.photos/id/1033/400/300",
        "https://picsum.photos/id/1036/400/300",
        "https://picsum.photos/id/1039/400/300",
        "https://picsum.photos/id/1042/400/300",
        "https://picsum.photos/id/1045/400/300"
    )

}
