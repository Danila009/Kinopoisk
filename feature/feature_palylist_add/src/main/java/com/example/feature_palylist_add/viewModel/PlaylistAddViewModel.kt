package com.example.feature_palylist_add.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core_network_domain.common.Response
import com.example.core_network_domain.model.playlist.PlaylistAdd
import com.example.core_network_domain.useCase.playlist.PostPlaylistUseCase
import com.example.core_utils.common.Tag.RETROFIT_TAG
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.ExperimentalSerializationApi
import javax.inject.Inject

@ExperimentalSerializationApi
class PlaylistAddViewModel @Inject constructor(
    private val postPlaylistUseCase: PostPlaylistUseCase
):ViewModel() {

    private val _responsePlaylistAddResult:MutableStateFlow<Response<Void?>?> = MutableStateFlow(null)
    val responsePlaylistAddResult = _responsePlaylistAddResult.asStateFlow()

    fun postPlaylist(playlistAdd: PlaylistAdd) = viewModelScope.launch {
        try {
            _responsePlaylistAddResult.value = Response.Loading()
            postPlaylistUseCase.invoke(playlistAdd)
            _responsePlaylistAddResult.value = Response.Success(data = null)
        }catch (e:Exception){
            Log.e(RETROFIT_TAG, e.message.toString())
            _responsePlaylistAddResult.value = Response.Error(message = e.message.toString())
        }
    }
}