package com.example.core_network_domain.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.core_network_domain.model.marvel.comics.Result
import com.example.core_network_domain.useCase.marvel.GetComicsMarvelUseCase

class ComicsMarvelSource(
    private val search:String,
    private val getComicsMarvelUseCase: GetComicsMarvelUseCase
):PagingSource<Int, Result>(){
    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        return try {

            val nextPage = params.key ?:1

            val data = getComicsMarvelUseCase.invoke(
                search = search.ifEmpty { null },
                offset = nextPage
            )!!.data.results

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