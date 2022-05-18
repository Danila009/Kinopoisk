package com.example.feature_cinema_map.screen

import android.Manifest
import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.example.core_network_domain.common.Response
import com.example.core_network_domain.model.route.Route
import com.example.core_network_domain.model.cinema.Cinema
import com.example.core_ui.animation.BaseRawListShimmer
import com.example.core_ui.animation.ImageShimmer
import com.example.core_ui.view.BaseBackdropScaffold
import com.example.core_utils.common.Tag.RETROFIT_TAG
import com.example.core_utils.common.getGPSUser
import com.example.core_utils.common.launchWhenStarted
import com.example.core_utils.navigation.cinemaNavGraph.CinemaScreenRoute
import com.example.feature_cinema_map.viewModel.CinemaMapViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@ExperimentalPermissionsApi
@ExperimentalMaterialApi
@SuppressLint("FlowOperatorInvokedInComposition")
@Composable
fun CinemaMapScreen(
    navController: NavController,
    cinemaViewModel:CinemaMapViewModel
) {
    val context = LocalContext.current
    val lifecycleScope = LocalLifecycleOwner.current.lifecycleScope
    val lifecycle = LocalLifecycleOwner.current.lifecycle

    val checkNavMap = remember { mutableStateOf(false) }
    var cinema:Response<List<Cinema>> by remember { mutableStateOf(Response.Loading()) }
    var route:Response<Route>? by remember { mutableStateOf(null) }
    var endRoutePosition:LatLng? by remember { mutableStateOf(null) }

    val scope = rememberCoroutineScope()

    val backdropState = rememberBackdropScaffoldState(initialValue = BackdropValue.Concealed)
    val offset by backdropState.offset
    val halfHeightDp = LocalConfiguration.current.screenHeightDp / 2.5
    val halfHeightPx = with(LocalDensity.current) { halfHeightDp.dp.toPx() }

    val permission = rememberPermissionState(permission = Manifest.permission.ACCESS_FINE_LOCATION)

    val getGPSUser = getGPSUser(
        permission = permission.hasPermission,
        context = context
    )

    val cameraPosition = rememberCameraPositionState{
        position = CameraPosition.fromLatLngZoom(getGPSUser, 17f)
    }

    cinemaViewModel.getCinema()
    cinemaViewModel.responseCinema.onEach {
        cinema = it
    }.launchWhenStarted(lifecycleScope, lifecycle)

    cinemaViewModel.responseRoute.onEach {
        route = it
    }.launchWhenStarted(lifecycleScope, lifecycle)

    LaunchedEffect(key1 = Unit, block = {
        permission.launchPermissionRequest()
    })

    LaunchedEffect(backdropState) {
        backdropState.reveal()
    }

    LaunchedEffect(key1 = endRoutePosition, block = {
        endRoutePosition?.let {
            cinemaViewModel.getRoute(
                start = "${getGPSUser.longitude},${getGPSUser.latitude}",
                end = "${it.longitude},${getGPSUser.latitude}"
            )
        }
    })

    Column {
        BaseBackdropScaffold(
            headerHeight = halfHeightDp.dp,
            peekHeight = 0.dp,
            scaffoldState = backdropState,
            gesturesEnabled = true,
            backLayerContent = {
                GoogleMap(
                    modifier = Modifier
                        .fillMaxSize(),
                    properties = MapProperties(
                        isMyLocationEnabled = permission.hasPermission
                    ),
                    cameraPositionState = cameraPosition,
                    content = {
                        if (cinema is Response.Success){
                            repeat(cinema.data!!.size){
                                if (checkNavMap.value){
                                    LaunchedEffect(key1 = Unit, block ={
                                        navController.navigate(
                                            CinemaScreenRoute.CinemaInfo.base(
                                                cinemaId = cinema.data!![it].id
                                            )
                                        )
                                    })
                                }

                                Marker(
                                    position = LatLng(
                                        cinema.data!![it].latitude,
                                        cinema.data!![it].longitude
                                    ), title = "${cinema.data!![it].title} ${cinema.data!![it].adress}",
                                    onInfoWindowClick = {
                                        checkNavMap.value = true
                                    }
                                )
                            }
                        }

                        route?.let {
                            when(route){
                                is Response.Error -> {
                                    Log.e(RETROFIT_TAG, route!!.message.toString())
                                    Toast.makeText(context, "Error: ${route!!.message}", Toast.LENGTH_SHORT).show()
                                }
                                is Response.Loading -> {
                                    Toast.makeText(context, "Loading...", Toast.LENGTH_SHORT).show()
                                }
                                is Response.Success -> {
                                    route!!.data?.let {
                                        it.features.forEach { feature ->
                                            val coordinates = ArrayList<LatLng>()
                                            feature.geometry.coordinates.forEach {  coordinate ->
                                                coordinates.add(
                                                    LatLng(coordinate[1], coordinate[0])
                                                )
                                            }
                                            Polyline(
                                                points = coordinates,
                                                color = Color.Red
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                )
            },
            frontLayerContent = {
                val verticalListAlpha = ((halfHeightPx - offset) / halfHeightPx).coerceIn(0f..1f)
                val horizontalAlpha = (offset / halfHeightPx).coerceIn(0f..1f)

                LazyColumn(
                    modifier = Modifier.alpha(verticalListAlpha),
                    content = {
                        when(cinema){
                            is Response.Error -> {
                                item {
                                    Text(text = cinema.message.toString())
                                }
                            }
                            is Response.Loading -> {
                                items(10) {
                                    BaseRawListShimmer()
                                }
                            }
                            is Response.Success -> {
                                items(cinema.data!!){ item ->
                                    Column(
                                        modifier = Modifier.clickable {
                                            if (permission.hasPermission){
                                                endRoutePosition = LatLng(item.latitude, item.longitude)
                                                cameraPosition.position = CameraPosition.fromLatLngZoom(
                                                    LatLng(item.latitude, item.longitude), 17f
                                                )
                                            } else {
                                                permission.launchPermissionRequest()
                                            }
                                            scope.launch {
                                                backdropState.reveal()
                                            }
                                        }
                                    ) {
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            SubcomposeAsyncImage(
                                                model = item.icon,
                                                contentDescription = null,
                                                modifier = Modifier
                                                    .width(200.dp)
                                                    .height(200.dp)
                                                    .padding(5.dp)
                                            ) {
                                                val state = painter.state
                                                if (
                                                    state is AsyncImagePainter.State.Loading ||
                                                    state is AsyncImagePainter.State.Error
                                                ) {
                                                    ImageShimmer(
                                                        modifier = Modifier
                                                            .width(200.dp)
                                                            .height(200.dp)
                                                            .padding(5.dp)
                                                    )
                                                } else {
                                                    SubcomposeAsyncImageContent()
                                                }
                                            }

                                            Column {
                                                Text(
                                                    text = item.title,
                                                    modifier = Modifier.padding(5.dp)
                                                )

                                                Text(
                                                    text = item.adress,
                                                    modifier = Modifier.padding(5.dp)
                                                )
                                            }
                                        }

                                        Divider()
                                    }
                                }
                            }
                        }
                    }
                )

                LazyRow(
                    modifier = Modifier.alpha(horizontalAlpha),
                    content = {
                        when(cinema){
                            is Response.Error -> {
                                item {
                                    Text(text = cinema.message.toString())
                                }
                            }
                            is Response.Loading -> {
                                items(10) {
                                    BaseRawListShimmer()
                                }
                            }
                            is Response.Success -> {
                                items(cinema.data!!){ item ->
                                    Column {
                                        SubcomposeAsyncImage(
                                            model = item.icon,
                                            contentDescription = null,
                                            modifier = Modifier
                                                .width(200.dp)
                                                .height(200.dp)
                                                .padding(5.dp)
                                        ) {
                                            val state = painter.state
                                            if (
                                                state is AsyncImagePainter.State.Loading ||
                                                state is AsyncImagePainter.State.Error
                                            ) {
                                                ImageShimmer(
                                                    modifier = Modifier
                                                        .width(200.dp)
                                                        .height(200.dp)
                                                        .padding(5.dp)
                                                )
                                            } else {
                                                SubcomposeAsyncImageContent()
                                            }
                                        }

                                        Text(
                                            text = item.title,
                                            modifier = Modifier.padding(5.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }
                )
            }
        )
        
        Spacer(modifier = Modifier.height(60.dp))
    }
}