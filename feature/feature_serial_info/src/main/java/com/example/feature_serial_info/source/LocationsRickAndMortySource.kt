package com.example.feature_serial_info.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.core_network_domain.model.rickAndMorty.LocationItem
import com.example.core_network_domain.useCase.rickAndMorty.GetLocationsUseCase

class LocationsRickAndMortySource(
    private val name:String,
    private val dimension:String,
    private val getLocationsUseCase: GetLocationsUseCase
):PagingSource<Int, LocationItem>() {
    override fun getRefreshKey(state: PagingState<Int, LocationItem>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, LocationItem> {
        return try {
            val nextPage = params.key ?: 1
            val locations = getLocationsUseCase.invoke(
                name, dimension, nextPage
            ).results
            LoadResult.Page(
                data = locations,
                prevKey = if (nextPage == 1) null else nextPage -1,
                nextKey = nextPage.plus(1)
            )
        }catch (e:Exception){
            LoadResult.Error(e)
        }
    }
}