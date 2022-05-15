package com.example.core_network_domain.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.core_network_domain.model.rickAndMorty.EpisodeItem
import com.example.core_network_domain.useCase.rickAndMorty.GetEpisodesUseCase

class EpisodesRickAndMortySource(
    private val getEpisodesUseCase: GetEpisodesUseCase,
    private val search:String,
    private val episode:Int?,
):PagingSource<Int, EpisodeItem>() {
    override fun getRefreshKey(state: PagingState<Int, EpisodeItem>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, EpisodeItem> {
        return try {

            val nextPage = params.key ?: 0

            val data = getEpisodesUseCase.invoke(
                name = search,
                episode = episode,
                page = nextPage
            ).results

            LoadResult.Page(
                data = data,
                prevKey = if (nextPage == 1) null else nextPage -1,
                nextKey = nextPage.plus(1)
            )
        }catch (e:Exception){
            LoadResult.Error(e)
        }
    }
}