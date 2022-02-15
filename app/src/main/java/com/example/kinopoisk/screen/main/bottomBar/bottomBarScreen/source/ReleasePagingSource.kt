package com.example.kinopoisk.screen.main.bottomBar.bottomBarScreen.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.kinopoisk.api.ApiRepository
import com.example.kinopoisk.api.model.premiere.ReleaseItem
import java.lang.Exception

class ReleasePagingSource(
    private val apiRepository: ApiRepository,
    private val year:Int,
    private val mont:String
):PagingSource<Int, ReleaseItem>() {
    override fun getRefreshKey(state: PagingState<Int, ReleaseItem>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ReleaseItem> {
        return try {
            val nextPage = params.key ?:1
            val release = apiRepository.getRelease(
                year = year,
                month = mont,
                page = nextPage
            ).body()!!.releases
            LoadResult.Page(
                data = release,
                prevKey = if (nextPage == 1) null else nextPage -1,
                nextKey = nextPage.plus(1)
            )
        }catch (e:Exception){
            LoadResult.Error(e)
        }
    }
}