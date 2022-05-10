package com.example.core_network_domain.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.core_network_domain.model.movie.ImageMovieItem
import com.example.core_network_domain.useCase.movie.GetImageUseCase
import java.lang.Exception

class ImageMoviePagingSource(
    private val getImageMovieUseCase: GetImageUseCase,
    private val id:Int,
    private val type:String,
):PagingSource<Int, ImageMovieItem>() {
    override fun getRefreshKey(state: PagingState<Int, ImageMovieItem>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ImageMovieItem> {
        return try {
            val nextPage = params.key ?:1
            val image = getImageMovieUseCase.invoke(
                id = id,
                type = type,
                page = nextPage
            ).items
            LoadResult.Page(
                data = image,
                prevKey = if (nextPage == 1) null else nextPage -1,
                nextKey = nextPage.plus(1)
            )
        }catch (e:Exception){
            LoadResult.Error(e)
        }
    }
}