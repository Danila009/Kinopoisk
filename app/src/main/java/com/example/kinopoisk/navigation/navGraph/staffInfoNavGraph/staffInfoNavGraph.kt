package com.example.kinopoisk.navigation.navGraph.staffInfoNavGraph

import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.*
import androidx.navigation.compose.composable
import com.example.core_utils.navigation.staffInfoNavGraph.StaffInfoConstants.Argument.FILM_ID_ARGUMENT
import com.example.core_utils.navigation.staffInfoNavGraph.StaffInfoConstants.Argument.KEY_STAFF_SCREEN_ARGUMENT
import com.example.core_utils.navigation.staffInfoNavGraph.StaffInfoConstants.Argument.STAFF_ID_ARGUMENT
import com.example.core_utils.navigation.staffInfoNavGraph.StaffInfoConstants.Route.STAFF_INFO_ROUTE
import com.example.core_utils.navigation.staffInfoNavGraph.StaffInfoScreenRoute
import com.example.kinopoisk.screen.staffInfo.MoreStaffScreen
import com.example.kinopoisk.screen.staffInfo.StaffInfoScreen
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalPagerApi
fun NavGraphBuilder.staffInfoNavGraph(
    navController: NavController,
    lifecycleScope: LifecycleCoroutineScope
) {
    navigation(
        startDestination = StaffInfoScreenRoute.StaffInfo.route,
        route = STAFF_INFO_ROUTE,
        builder = {
            composable(
                StaffInfoScreenRoute.StaffInfo.route,
                arguments = listOf(
                    navArgument(
                        name = STAFF_ID_ARGUMENT,
                        builder = {
                            type = NavType.StringType
                        }
                    ), navArgument(
                        name = FILM_ID_ARGUMENT,
                        builder = {
                            nullable = true
                            type = NavType.StringType
                        }
                    ), navArgument(KEY_STAFF_SCREEN_ARGUMENT){
                        type = NavType.StringType
                        nullable = true
                    }
                )
            ){
                StaffInfoScreen(
                    navController = navController,
                    lifecycleScope = lifecycleScope,
                    staffId = it.arguments?.getString(STAFF_ID_ARGUMENT)!!.toInt(),
                    filmId = it.arguments?.getString(FILM_ID_ARGUMENT)!!.toString(),
                    keyStaffInfoScreenString = it.arguments?.getString(KEY_STAFF_SCREEN_ARGUMENT).toString()
                )
            }
            composable(
                StaffInfoScreenRoute.MoreStaff.route,
                arguments = listOf(
                    navArgument(STAFF_ID_ARGUMENT){
                        type = NavType.StringType
                    }
                )
            ) {
                MoreStaffScreen(
                    navController = navController,
                    lifecycleScope = lifecycleScope,
                    staffId = it.arguments?.getString(STAFF_ID_ARGUMENT)!!.toInt()
                )
            }
        }
    )
}