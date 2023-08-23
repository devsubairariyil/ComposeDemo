package demo.jetpack.compose.navigation

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import demo.jetpack.compose.navigation.model.AppMenu
import demo.jetpack.compose.navigation.ui.screens.menu.MenuScreen
import org.junit.Rule
import org.junit.Test


class MenuScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testEmptyMenuList() {
        composeTestRule.setContent {
            MenuScreen(menuList = emptyList()){}
        }

        // Assert that no text is displayed since the list is empty
        composeTestRule.onNodeWithText("").assertDoesNotExist()
    }

    @Test
    fun testNonEmptyMenuList() {
        val menuList = listOf(
            AppMenu("Item 1", 1),
            AppMenu("Item 2", 2)
        )

        composeTestRule.setContent {
            MenuScreen(menuList = menuList){}
        }

        // Assert that each item in the list is displayed
        menuList.forEach { item ->
            composeTestRule.onNodeWithText(item.title).assertIsDisplayed()
        }
    }
}