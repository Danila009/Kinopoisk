package com.example.feature_profile.screen

import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import com.example.core_utils.common.launchWhenStarted
import com.example.feature_profile.viewModel.ProfileViewModel
import com.example.feature_profile.view.StatusRegistrationTrueView
import com.example.feature_profile.view.StatusRegistrationFalseView
import kotlinx.coroutines.flow.onEach

@Composable
fun ProfileScreen(
    navController: NavController,
    profileViewModel: ProfileViewModel
) {
    val lifecycleScope = LocalLifecycleOwner.current.lifecycleScope
    val lifecycle = LocalLifecycleOwner.current.lifecycle

    var statusRegistration by remember { mutableStateOf(false) }

    profileViewModel.responseStatusRegistration.onEach {
        statusRegistration = it
    }.launchWhenStarted(lifecycleScope, lifecycle)

    if (statusRegistration){
        StatusRegistrationTrueView(
            navController = navController,
            profileViewModel = profileViewModel
        )
    }else{
        StatusRegistrationFalseView(
            navController = navController
        )
    }
}