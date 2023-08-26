package demo.jetpack.compose.navigation

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.printToLog
import androidx.navigation.NavHostController
import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.hilt.android.testing.HiltAndroidTest
import demo.jetpack.compose.domain.model.ImageUrls
import demo.jetpack.compose.domain.model.Product
import demo.jetpack.compose.domain.state.Result
import demo.jetpack.compose.navigation.ui.screens.productlist.ProductListViewModel
import demo.jetpack.compose.navigation.ui.screens.productlist.ProductScreen
import demo.jetpack.compose.navigation.utils.assertProductList
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class ProductScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testLoadingState() {
        // Create a mock ViewModel instance
        val mockViewModel: ProductListViewModel = mockk()
        val navController: NavHostController = mockk()

        // Set the mock ViewModel's screenState to Loading
        every { mockViewModel.screenState } returns MutableStateFlow(Result.Loading)

        // Launch the ProductScreen Composable with the mock ViewModel
        composeTestRule.setContent {
            ProductScreen(navController, mockViewModel)
        }

        // Use composeTestRule to find UI elements and assert their properties
        // For example, assert that a CircularProgressIndicator is displayed
        composeTestRule.onNodeWithTag("LoadingIndicator").assertExists()
    }
    @Test
    fun testProductListState() {
        // Create a mock ViewModel instance
        val mockViewModel: ProductListViewModel = mockk()
        val navController: NavHostController = mockk()

        val list = generateProducts()
        // Set the mock ViewModel's screenState to Loading
        every { mockViewModel.screenState } returns MutableStateFlow(Result.Success(list))

        // Launch the ProductScreen Composable with the mock ViewModel
        composeTestRule.setContent {
            ProductScreen(navController, mockViewModel)
        }

        composeTestRule.onNodeWithTag("LoadingIndicator").assertDoesNotExist()
        composeTestRule.assertProductList(list)

    }
    @Test
    fun testRestrictedProduct() {
        // Create a mock ViewModel instance
        val mockViewModel: ProductListViewModel = mockk()
        val navController: NavHostController = mockk()

        val list = listOf(
            Product(
                name = "Sample Product",
                productCode = "Code",
                indicators = emptyList(),
                price = 100.0,
                discount = 25,
                imageUrl = "",
                isSoldOut = false,
                isRestricted = true,
                description = ""
            )

        )
        // Set the mock ViewModel's screenState to Loading
        every { mockViewModel.screenState } returns MutableStateFlow(Result.Success(list))

        // Launch the ProductScreen Composable with the mock ViewModel
        composeTestRule.setContent {
            ProductScreen(navController, mockViewModel)
        }

        composeTestRule.onNodeWithTag("LoadingIndicator").assertDoesNotExist()
        composeTestRule.onNodeWithText("Restricted").assertIsDisplayed()
        composeTestRule.onNodeWithText("Sold Out").assertDoesNotExist()
        composeTestRule.assertProductList(list)

    } @Test
    fun testSoldOutProduct() {
        // Create a mock ViewModel instance
        val mockViewModel: ProductListViewModel = mockk()
        val navController: NavHostController = mockk()

        val list = listOf(
            Product(
                name = "Sample Product",
                productCode = "Code",
                indicators = emptyList(),
                price = 100.0,
                discount = 25,
                imageUrl = "",
                isSoldOut = true,
                isRestricted = false,
                description = ""
            )

        )
        // Set the mock ViewModel's screenState to Loading
        every { mockViewModel.screenState } returns MutableStateFlow(Result.Success(list))

        // Launch the ProductScreen Composable with the mock ViewModel
        composeTestRule.setContent {
            ProductScreen(navController, mockViewModel)
        }

        composeTestRule.onNodeWithTag("LoadingIndicator").assertDoesNotExist()
        composeTestRule.onNodeWithText("Restricted").assertDoesNotExist()
        composeTestRule.onNodeWithText("Sold Out").assertIsDisplayed()
        composeTestRule.assertProductList(list)

    }
    private fun generateProducts(): List<Product>{
        val list = mutableListOf<Product>()
        for (i in 1 until 50){
            val indicators = mutableListOf<String>()
            repeat(10){
                indicators.add(java.util.UUID.randomUUID().toString().substring(1,3).uppercase())
            }

            list.add(
                Product(
                    name = "Product - $i",
                    productCode = "PC-$i",
                    discount = 0,//Random(20).toString().toInt(),
                    price = 100.0,//Random(200).toString().toDouble(),
                    indicators = indicators.distinct(),
                    isSoldOut = i % 3 == 0,
                    isRestricted = i % 7 == 0,
                    imageUrl = ImageUrls.productImageUrls[i % 10],
                    description = "Product Description Lorem ipsum dolor sit amet, consectetur adipiscing elit."

                )
            )
        }
        return list
    }
    @Test
    fun testErrorState() {
        // Create a mock ViewModel instance
        val mockViewModel: ProductListViewModel = mockk()
        val navController: NavHostController = mockk()

        // Set the mock ViewModel's screenState to Loading
        every { mockViewModel.screenState } returns MutableStateFlow(Result.Error("Test Error"))

        // Launch the ProductScreen Composable with the mock ViewModel
        composeTestRule.setContent {
            ProductScreen(navController, mockViewModel)
        }

        // Use composeTestRule to find UI elements and assert their properties
        // For example, assert that a CircularProgressIndicator is displayed
        composeTestRule.onNodeWithText("Test Error").assertExists()
    }
}
