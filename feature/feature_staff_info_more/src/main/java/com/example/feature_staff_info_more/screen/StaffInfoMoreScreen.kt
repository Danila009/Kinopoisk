package com.example.feature_staff_info_more.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import com.example.core_network_domain.common.Response
import com.example.core_network_domain.model.movie.staff.StaffInfo
import com.example.core_ui.animation.TextShimmer
import com.example.core_ui.ui.theme.primaryBackground
import com.example.core_ui.ui.theme.secondaryBackground
import com.example.core_utils.common.launchWhenStarted
import com.example.feature_staff_info_more.viewModel.StaffInfoMoreViewModel
import kotlinx.coroutines.flow.onEach

@SuppressLint("FlowOperatorInvokedInComposition")
@Composable
fun StaffInfoMoreScreen(
    staffInfoViewModel:StaffInfoMoreViewModel,
    navController: NavController,
    staffId:Int
) {
    val lifecycleScope = LocalLifecycleOwner.current.lifecycleScope
    val lifecycle = LocalLifecycleOwner.current.lifecycle

    var staff:Response<StaffInfo> by remember { mutableStateOf(Response.Loading()) }

    staffInfoViewModel.getStaffInfo(staffId)
    staffInfoViewModel.responseStaffInfo.onEach {
        staff = it
    }.launchWhenStarted(lifecycleScope, lifecycle)

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = primaryBackground,
                elevation = 8.dp,
                title = {
                        when(staff) {
                            is Response.Error -> {
                                Text(text = "Error")
                            }
                            is Response.Loading -> {
                                TextShimmer(modifier = Modifier.fillMaxWidth(0.3f))
                            }
                            is Response.Success -> {
                                Text(text = staff.data?.nameRu ?: "")
                            }
                        }
                }, navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowLeft,
                            contentDescription = null
                        )
                    }
                }
            )
        }, content = {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = primaryBackground
            ) {
                when(staff){
                    is Response.Error -> {
                        Text(
                            text = staff.message ?: "",
                            modifier = Modifier.padding(5.dp),
                            color = Color.Red
                        )
                    }
                    is Response.Loading -> {
                        CircularProgressIndicator(
                            color = secondaryBackground
                        )
                    }
                    is Response.Success -> {
                        Row {
                            Column {
                                Text(
                                    text = "Карьера",
                                    modifier = Modifier.padding(5.dp),
                                    fontWeight = FontWeight.W100
                                )
                            }

                            Column {
                                Text(
                                    text = staff.data?.profession ?: "",
                                    modifier = Modifier.padding(5.dp),
                                    fontWeight = FontWeight.W900
                                )
                            }
                        }
                    }
                }
            }
        }
    )
}