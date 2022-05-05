package com.example.feature_authorization.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.core_database_domain.useCase.user.SavaUserRoleUseCase
import com.example.core_database_domain.useCase.user.SaveStatusRegistrationUseCase
import com.example.core_database_domain.useCase.user.SaveTokenUseCase
import com.example.core_network_domain.model.user.Authorization
import com.example.core_network_domain.useCase.user.AuthorizationUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class AuthorizationViewModel @Inject constructor(
    private val authorizationUseCase: AuthorizationUseCase,
    private val saveTokenUseCase: SaveTokenUseCase,
    private val savaUserRoleUseCase: SavaUserRoleUseCase,
    private val saveStatusRegistrationUseCase: SaveStatusRegistrationUseCase
):ViewModel() {

    private val _responseAuthorizationError = MutableStateFlow("")
    val responseAuthorizationError = _responseAuthorizationError.asStateFlow()

    fun authorization(
        authorization:Authorization,
        navController: NavController
    ){
        authorizationUseCase.invoke(authorization).onEach {
            if (it.message == null){
                it.data?.let { response ->
                    saveTokenUseCase.invoke(response.access_token)
                    saveStatusRegistrationUseCase(userRegistration = true)
                    savaUserRoleUseCase(userRole = response.role)
                    navController.navigate("MAIN_ROUTE")
                }
            }else{
                _responseAuthorizationError.value = if (it.message!! == "HTTP 400 ") "" +
                        "Пользователя с таким email и паролем не существует"
                else it.message!!
            }
        }.launchIn(viewModelScope)
    }
}