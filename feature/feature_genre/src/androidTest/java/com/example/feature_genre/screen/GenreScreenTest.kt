package com.example.feature_genre.screen

import androidx.compose.ui.test.assertContentDescriptionContains
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createComposeRule
import com.example.feature_genre.screen.common.TestTag.MAIN_TOP_APP_BAR
import org.junit.Rule
import org.junit.Test

class GenreScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun mainTopAppBar(){
        val topAppBar = composeTestRule.onNode(hasTestTag(MAIN_TOP_APP_BAR))
        topAppBar.assertIsDisplayed()
        topAppBar.assertContentDescriptionContains("navigationIcon")
    }
}