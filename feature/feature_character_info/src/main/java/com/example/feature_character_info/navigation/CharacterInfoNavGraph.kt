package com.example.feature_character_info.navigation

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.example.core_utils.navigation.characterNavGraph.CHARACTER_ID_ARGUMENT
import com.example.core_utils.navigation.characterNavGraph.CHARACTER_INFO_ROUTE
import com.example.core_utils.navigation.characterNavGraph.CHARACTER_STATE_ARGUMENT
import com.example.core_utils.navigation.characterNavGraph.CharacterScreenRoute
import com.example.feature_character_info.screen.CharacterInfoScreen
import com.example.feature_character_info.viewModel.CharacterInfoViewModel

fun NavGraphBuilder.characterInfoNavGraph(
    navController: NavController,
    characterInfoViewModel: CharacterInfoViewModel
) {
    navigation(
        startDestination = CharacterScreenRoute.CharacterInfoScreen.route,
        route = CHARACTER_INFO_ROUTE,
        builder = {
            composable(
                route = CharacterScreenRoute.CharacterInfoScreen.route,
                arguments = listOf(
                    navArgument(
                        name = CHARACTER_ID_ARGUMENT,
                        builder = {
                            type = NavType.IntType
                        }
                    ),
                    navArgument(
                        name = CHARACTER_STATE_ARGUMENT,
                        builder = {
                            type = NavType.StringType
                        }
                    ),
                ),
                content = {
                    CharacterInfoScreen(
                        navController = navController,
                        characterInfoViewModel = characterInfoViewModel,
                        characterInfoState = enumValueOf(it.arguments?.getString(CHARACTER_STATE_ARGUMENT)!!),
                        id = it.arguments!!.getInt(CHARACTER_ID_ARGUMENT)
                    )
                }
            )
        }
    )
}