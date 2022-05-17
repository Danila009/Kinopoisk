package com.example.feature_genre.screen

import androidx.compose.ui.test.assertContentDescriptionContains
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.feature_genre.screen.common.TestTag.MAIN_TOP_APP_BAR
import com.example.feature_genre.viewModel.GenreViewModel
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito

@RunWith(AndroidJUnit4::class)
class GenreScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun mainTopAppBar(){
        composeTestRule.setContent { GenreScreen(
            genreViewModel = Mockito.mock(GenreViewModel::class.java),
            navController = rememberNavController()
        )}
        val topAppBar = composeTestRule.onNode(hasTestTag(MAIN_TOP_APP_BAR), useUnmergedTree = true)
        topAppBar.assertIsDisplayed()
        topAppBar.assertContentDescriptionContains("navigationIcon")
    }
}