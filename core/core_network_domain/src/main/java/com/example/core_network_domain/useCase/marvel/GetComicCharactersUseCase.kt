package com.example.core_network_domain.useCase.marvel

import com.example.core_network_domain.model.marvel.character.MarvelCharacter
import com.example.core_network_domain.repository.MarvelRepository
import javax.inject.Inject

class GetComicCharactersUseCase @Inject constructor(
    private val marvelRepository: MarvelRepository
) {
    suspend operator fun invoke(comicId:Int, offset:Int):MarvelCharacter{
        return marvelRepository.getComicCharacters(comicId, offset)
    }
}