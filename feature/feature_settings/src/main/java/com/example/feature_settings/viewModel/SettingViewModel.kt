package com.example.feature_settings.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core_database_domain.useCase.user.SaveStatusRegistrationUseCase
import com.example.core_network_domain.useCase.history.DeleteHistoryMovieUseCase
import com.example.core_network_domain.useCase.history.DeleteHistorySearchUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class SettingViewModel @Inject constructor(
    private val deleteHistoryMovieUseCase: DeleteHistoryMovieUseCase,
    private val deleteHistorySearchUseCase: DeleteHistorySearchUseCase,
    private val saveStatusRegistrationUseCase: SaveStatusRegistrationUseCase
):ViewModel() {

    fun deleteHistoryMovie() = viewModelScope.launch {
        deleteHistoryMovieUseCase.invoke()
    }

    fun deleteHistorySearch() = viewModelScope.launch {
        deleteHistorySearchUseCase.invoke()
    }

    fun saveStatusRegistration(userRegistration:Boolean) = viewModelScope.launch {
        saveStatusRegistrationUseCase.invoke(
            userRegistration = userRegistration
        )
    }
}