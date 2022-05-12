package com.example.feature_update_user_password.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core_network_domain.useCase.user.PatchUpdatePasswordUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class UpdateUserPasswordViewModel @Inject constructor(
    private val patchUpdatePasswordUseCase: PatchUpdatePasswordUseCase
):ViewModel() {

    private val _responseMessageUpdatePassword = MutableStateFlow("")
    val responseMessageUpdatePassword = _responseMessageUpdatePassword.asStateFlow()

    fun patchUpdatePassword(email:String, password:String) = viewModelScope.launch {
        val response = patchUpdatePasswordUseCase.invoke(email, password)
        _responseMessageUpdatePassword.value = response
    }
}