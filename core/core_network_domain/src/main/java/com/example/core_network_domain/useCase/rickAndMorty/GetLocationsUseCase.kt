package com.example.core_network_domain.useCase.rickAndMorty

import com.example.core_network_domain.model.rickAndMorty.Location
import com.example.core_network_domain.repository.RickAndMortyRepository
import javax.inject.Inject

class GetLocationsUseCase @Inject constructor(
    private val rickAndMortyRepository: RickAndMortyRepository
) {
    suspend operator fun invoke(
        name:String,
        dimension:String,
        page:Int,
    ):Location = rickAndMortyRepository.getLocations(
        name, dimension, page
    )
}