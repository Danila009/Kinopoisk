package com.example.kinopoisk.navigation.navGraph.staffInfoNavGraph

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.example.core_utils.navigation.staffInfoNavGraph.StaffInfoConstants.Argument.STAFF_ID_ARGUMENT
import com.example.core_utils.navigation.staffInfoNavGraph.StaffInfoConstants.Route.STAFF_INFO_ROUTE
import com.example.core_utils.navigation.staffInfoNavGraph.StaffInfoScreenRoute
import com.example.feature_staff_info.screen.StaffInfoScreen
import com.example.feature_staff_info_more.screen.StaffInfoMoreScreen
import com.example.kinopoisk.di.AppComponent
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalPagerApi
fun NavGraphBuilder.staffInfoNavGraph(
    navController: NavController,
    appComponent: AppComponent
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
                            type = NavType.IntType
                        }
                    )
                )
            ){
                StaffInfoScreen(
                    navController = navController,
                    staffInfoViewModel = appComponent.staffInfoViewModel(),
                    staffId = it.arguments?.getInt(STAFF_ID_ARGUMENT) ?: 0
                )
            }
            composable(
                StaffInfoScreenRoute.MoreStaff.route,
                arguments = listOf(
                    navArgument(STAFF_ID_ARGUMENT){
                        type = NavType.IntType
                    }
                )
            ) {
                StaffInfoMoreScreen(
                    navController = navController,
                    staffInfoViewModel = appComponent.staffInfoMoreViewModel(),
                    staffId = it.arguments?.getInt(STAFF_ID_ARGUMENT)!!
                )
            }
        }
    )
}