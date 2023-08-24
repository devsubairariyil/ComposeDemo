package demo.jetpack.compose.navigation.utils

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onParent
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.performScrollToNode
import demo.jetpack.compose.domain.model.Product
import kotlin.math.roundToInt

fun ComposeContentTestRule.assertProductList(products: List<Product>) {
    products.forEach { product ->
        // Find the card for the current product using its content description
        val cardTag = "product_card_${product.productCode}"
        val cardNode = onNodeWithTag(cardTag)
        cardNode.assertIsDisplayed()

        // Inside the card, assert the presence of various UI elements

        onNodeWithText(product.name).assertIsDisplayed()
        onNodeWithText("Price: $${product.price.roundToInt()}").assertIsDisplayed()
        onNodeWithText("Discount: ${product.discount}%").assertIsDisplayed()

        val indicators = "Indicators_${product.productCode}"
        val scrollableContainer = onNodeWithTag(indicators, useUnmergedTree = true)
        scrollableContainer.assertIsDisplayed()

        product.indicators.forEach { indicator ->
            onNodeWithText(indicator).performScrollTo()
            onNodeWithText(indicator).assertIsDisplayed()
        }

    }
}