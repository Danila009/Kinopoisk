package com.example.feature_home.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.core_network_domain.model.movie.premiere.ReleaseItem
import com.example.core_network_domain.useCase.movie.GetReleaseUseCase
import java.lang.Exception

class ReleasePagingSource (
    private val getReleaseUseCase: GetReleaseUseCase,
    private val year:Int,
    private val mont:String
):PagingSource<Int, ReleaseItem>() {
    override fun getRefreshKey(state: PagingState<Int, ReleaseItem>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ReleaseItem> {
        return try {
            val nextPage = params.key ?:1
            val release = getReleaseUseCase.invoke(
                year = year,
                month = mont,
                page = nextPage
            ).releases
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