package com.example.kinopoisk.screen.staffInfo

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavController
import com.example.kinopoisk.api.model.staff.StaffInfo
import com.example.kinopoisk.di.DaggerAppComponent
import com.example.core_utils.navigation.staffInfoNavGraph.StaffInfoScreenRoute
import com.example.core_utils.key.StaffInfoScreenKey
import com.example.kinopoisk.ui.theme.primaryBackground
import com.example.kinopoisk.utils.Converters
import com.example.kinopoisk.utils.launchWhenStarted
import kotlinx.coroutines.flow.onEach

@Composable
fun MoreStaffScreen(
    lifecycleScope: LifecycleCoroutineScope,
    navController: NavController,
    staffId:Int,
    filmId:Int
) {
    val context = LocalContext.current
    val staffInfoViewModel = DaggerAppComponent.builder()
        .context(context = context)
        .build()
        .staffInfoViewModel()

    val staff = remember { mutableStateOf(StaffInfo()) }

    staffInfoViewModel.getStaffInfo(staffId)
    staffInfoViewModel.responseStaffInfo.onEach {
        staff.value = it
    }.launchWhenStarted(lifecycleScope)

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = primaryBackground,
                elevation = 8.dp,
                title = {
                    Text(text = staff.value.nameRu.toString())
                }, navigationIcon = {
                    IconButton(onClick = { navController.navigate(StaffInfoScreenRoute.StaffInfo.base(
                        staffId = staffId.toString(),
                        filmId = filmId.toString(),
                        key = StaffInfoScreenKey.FILM.name
                    )) }) {
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
                Text(
                    text = staff.value.toString(),
                    modifier = Modifier.padding(5.dp),
                    color = Color.White
                )
            }
        }
    )
}