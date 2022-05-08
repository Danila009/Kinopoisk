package com.example.core_network_domain.useCase.rickAndMorty

import com.example.core_network_domain.model.rickAndMorty.Character
import com.example.core_network_domain.repository.RickAndMortyRepository
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(
    private val rickAndMortyRepository: RickAndMortyRepository
) {
    suspend operator fun invoke(
        search: String,
        status: String,
        species: String,
        type: String,
        gender: String,
        page: Int
    ):Character{
        return rickAndMortyRepository.getCharacters(
            search, status, species, type, gender, page
        )
    }
}