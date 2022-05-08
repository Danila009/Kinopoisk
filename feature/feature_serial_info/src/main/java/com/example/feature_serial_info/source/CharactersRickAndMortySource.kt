package com.example.feature_serial_info.source

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.core_network_domain.model.rickAndMorty.CharacterItem
import com.example.core_network_domain.useCase.rickAndMorty.GetCharactersUseCase

class CharactersRickAndMortySource(
    private val search: String,
    private val status: String,
    private val species: String,
    private val type: String,
    private val gender: String,
    private val getCharactersUseCase: GetCharactersUseCase
):PagingSource<Int, CharacterItem>() {
    override fun getRefreshKey(state: PagingState<Int, CharacterItem>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterItem> {
        return try {
            val nextPage = params.key ?: 1

            val characterItems = getCharactersUseCase.invoke(
                search, status, species, type, gender, nextPage
            ).results

            LoadResult.Page(
                data = characterItems,
                prevKey = if (nextPage == 1) null else nextPage -1,
                nextKey = nextPage.plus(1)
            )
        }catch (e:Exception){
            LoadResult.Error(e)
        }
    }
}