package demo.jetpack.compose.navigation

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.NavHostController
import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.hilt.android.testing.HiltAndroidTest
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

        // Set the mock ViewModel's screenState to Loading
        every { mockViewModel.screenState } returns MutableStateFlow(Result.Success(listOf(Product.default())))

        // Launch the ProductScreen Composable with the mock ViewModel
        composeTestRule.setContent {
            ProductScreen(navController, mockViewModel)
        }

        composeTestRule.onNodeWithTag("LoadingIndicator").assertDoesNotExist()
        composeTestRule.assertProductList(listOf(Product.default()))

    }
}
