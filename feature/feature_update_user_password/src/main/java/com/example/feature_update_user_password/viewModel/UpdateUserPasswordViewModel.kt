package com.example.feature_update_user_password.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core_network_domain.useCase.user.PutUpdatePasswordUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class UpdateUserPasswordViewModel @Inject constructor(
    private val putUpdatePasswordUseCase: PutUpdatePasswordUseCase
):ViewModel() {

    private val _responseMessageUpdatePassword = MutableStateFlow("")
    val responseMessageUpdatePassword = _responseMessageUpdatePassword.asStateFlow()

    fun putUpdatePassword(email:String, password:String) = viewModelScope.launch {
        val response = putUpdatePasswordUseCase.invoke(email, password)
        _responseMessageUpdatePassword.value = response
    }
}