package demo.jetpack.compose.navigation.utils

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasAnyChild
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performScrollToNode
import androidx.compose.ui.test.printToLog
import demo.jetpack.compose.domain.model.Product
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.math.roundToInt

fun ComposeContentTestRule.assertProductList(products: List<Product>) = runBlocking {

    onNodeWithTag("ProductList", useUnmergedTree = true).printToLog("UI-Structure")
    products.forEach { product ->
        println("Compose-Test Verifying - ${product.productCode}")
        // Find the card for the current product using its content description
        val cardTag = "product_card_${product.productCode}"
        val cardNode = onNodeWithTag(cardTag)
        onNodeWithTag("ProductList", useUnmergedTree = true)
            .performScrollToNode(hasTestTag(cardTag))
        delay(300L)
        cardNode.assertIsDisplayed()
        onNode(
            hasAnyChild(hasText(product.name)) and
                    hasAnyChild(hasText("Price: $${product.price.roundToInt()}")) and
                    hasAnyChild(hasText("Product Code: ${product.productCode}"))
        ).assertIsDisplayed()
        val indicators = "Indicators_${product.productCode}"
        product.indicators.forEach { indicator ->
            onNodeWithTag(indicators, useUnmergedTree = true)
                .performScrollToNode(hasText(indicator))
            onNodeWithTag(
                "${product.productCode}.$indicator",
                useUnmergedTree = true
            ).assertIsDisplayed()
        }
        println("Compose-Test  Completed - ${product.productCode}")

    }
}