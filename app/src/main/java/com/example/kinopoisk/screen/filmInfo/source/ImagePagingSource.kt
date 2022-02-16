package com.example.kinopoisk.screen.filmInfo.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.kinopoisk.api.repository.ApiRepository
import com.example.kinopoisk.api.model.filmInfo.ImageItem
import java.lang.Exception

class ImagePagingSource(
    private val apiRepository: ApiRepository,
    private val id:Int,
    private val type:String,
):PagingSource<Int, ImageItem>() {
    override fun getRefreshKey(state: PagingState<Int, ImageItem>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ImageItem> {
        return try {
            val nextPage = params.key ?:1
            val image = apiRepository.getImage(
                id = id,
                type = type,
                page = nextPage
            ).body()!!.items
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