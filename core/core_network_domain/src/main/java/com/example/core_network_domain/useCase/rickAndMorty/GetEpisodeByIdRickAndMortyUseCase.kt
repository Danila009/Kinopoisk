package com.example.core_network_domain.useCase.rickAndMorty

import com.example.core_network_domain.apiResponse.BaseApiResponse
import com.example.core_network_domain.common.Response
import com.example.core_network_domain.model.rickAndMorty.EpisodeItem
import com.example.core_network_domain.repository.RickAndMortyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetEpisodeByIdRickAndMortyUseCase @Inject constructor(
    private val rickAndMortyRepository: RickAndMortyRepository
):BaseApiResponse() {

    operator fun invoke(id:Int):Flow<Response<EpisodeItem>> = flow {
        emit( safeApiCall { rickAndMortyRepository.getEpisodeById(id) } )
    }
}