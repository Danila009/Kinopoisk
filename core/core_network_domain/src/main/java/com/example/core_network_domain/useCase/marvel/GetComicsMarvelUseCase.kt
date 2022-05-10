package com.example.core_network_domain.useCase.marvel

import com.example.core_network_domain.model.marvel.comics.ComicsMarvel
import com.example.core_network_domain.repository.MarvelRepository
import javax.inject.Inject

class GetComicsMarvelUseCase @Inject constructor(
    private val marvelRepository: MarvelRepository
) {
    suspend operator fun invoke(search:String?, offset:Int = 1):ComicsMarvel?{
        return marvelRepository.getComics(search, offset)
    }
}