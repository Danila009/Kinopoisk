package com.example.feature_character_info.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core_network_domain.common.Response
import com.example.core_network_domain.model.rickAndMorty.CharacterItem
import com.example.core_network_domain.model.rickAndMorty.EpisodeItem
import com.example.core_network_domain.useCase.rickAndMorty.GetCharacterRickAndMortyByIdUseCase
import com.example.core_network_domain.useCase.rickAndMorty.GetEpisodeByIdRickAndMortyUseCase
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class CharacterInfoViewModel @Inject constructor(
    private val getCharacterRickAndMortyByIdUseCase: GetCharacterRickAndMortyByIdUseCase,
    private val getEpisodeByIdRickAndMortyUseCase: GetEpisodeByIdRickAndMortyUseCase
):ViewModel() {

    private val _responseCharacterRickAndMorty:MutableStateFlow<Response<CharacterItem>> =
        MutableStateFlow(Response.Loading())
    val responseCharacterRickAndMorty = _responseCharacterRickAndMorty.asStateFlow()


    fun getCharacterRickAndMortyById(id:Int){
        getCharacterRickAndMortyByIdUseCase.invoke(id).onEach {
            _responseCharacterRickAndMorty.value = it
        }.launchIn(viewModelScope)
    }

    fun getEpisodeByIdRickAndMorty(id: Int): Flow<Response<EpisodeItem>> {
        return getEpisodeByIdRickAndMortyUseCase.invoke(id)
    }
}