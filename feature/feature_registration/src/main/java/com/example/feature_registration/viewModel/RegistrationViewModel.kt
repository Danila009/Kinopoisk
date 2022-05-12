package com.example.feature_registration.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.core_database_domain.useCase.user.SavaUserRoleUseCase
import com.example.core_database_domain.useCase.user.SaveStatusRegistrationUseCase
import com.example.core_database_domain.useCase.user.SaveTokenUseCase
import com.example.core_network_domain.common.Response
import com.example.core_network_domain.model.user.Authorization
import com.example.core_network_domain.model.user.Registration
import com.example.core_network_domain.useCase.user.AuthorizationUseCase
import com.example.core_network_domain.useCase.user.RegistrationUseCase
import com.example.core_utils.navigation.mainNavGraph.MainScreenRoute
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class RegistrationViewModel @Inject constructor(
    private val authorizationUseCase: AuthorizationUseCase,
    private val saveTokenUseCase: SaveTokenUseCase,
    private val savaUserRoleUseCase: SavaUserRoleUseCase,
    private val saveStatusRegistrationUseCase: SaveStatusRegistrationUseCase,
    private val registrationUseCase: RegistrationUseCase,
    private val firebaseAuth:FirebaseAuth
):ViewModel() {

    private val _responseRegistrationError = MutableStateFlow("")
    val responseRegistrationError = _responseRegistrationError.asStateFlow()

    fun registration(
        registration: Registration,
        navController: NavController
    ){
        registrationUseCase.invoke(registration).onEach {
            Log.e("GoogleSingIn:", registration.toString())
            if (it is Response.Error){
                firebaseAuth.signOut()
                _responseRegistrationError.value = it.message.toString()
            } else if (it is Response.Success){
                authorization(
                    authorization = Authorization(
                        email = registration.email,
                        password = registration.password
                    ),
                    navController = navController
                )
            }
        }.launchIn(viewModelScope)
    }

    private fun authorization(authorization: Authorization, navController: NavController){
        authorizationUseCase.invoke(authorization).onEach {
            if (it.message == null){
                it.data?.let { response ->
                    saveTokenUseCase.invoke(response.access_token)
                    saveStatusRegistrationUseCase(userRegistration = true)
                    savaUserRoleUseCase(userRole = response.role)
                    navController.navigate(MainScreenRoute.MainRoute.Profile.route)
                }
            }else{
                _responseRegistrationError.value = it.message.toString()
            }
        }.launchIn(viewModelScope)
    }
}