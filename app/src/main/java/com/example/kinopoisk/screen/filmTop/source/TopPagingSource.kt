package com.example.kinopoisk.screen.filmTop.source

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.kinopoisk.api.ApiRepository
import com.example.kinopoisk.api.model.topFilm.TopItem
import java.lang.Exception

class TopPagingSource(
    private val apiRepository: ApiRepository,
    private val type:String
):PagingSource<Int, TopItem>() {
    override fun getRefreshKey(state: PagingState<Int, TopItem>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TopItem> {
        return try {
            val nextPage = params.key ?:1
            val top = apiRepository.getTop(
                type = type,
                page = nextPage
            ).body()!!.films
            LoadResult.Page(
                data = top,
                prevKey = if (nextPage == 1) null else nextPage -1,
                nextKey = nextPage.plus(1)
            )
        }catch (e:Exception){
            Log.d("Retrofit:",e.message.toString())
            LoadResult.Error(e)
        }
    }
}