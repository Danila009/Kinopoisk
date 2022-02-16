package com.example.kinopoisk.screen.filmInfo.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.kinopoisk.api.repository.ApiRepository
import com.example.kinopoisk.api.model.review.ReviewItem
import java.lang.Exception

class ReviewPagingSource(
    private val apiRepository: ApiRepository,
    private val id:Int
):PagingSource<Int, ReviewItem>() {
    override fun getRefreshKey(state: PagingState<Int, ReviewItem>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ReviewItem> {
        return try {
         val nextPage = params.key ?:1
         val reviewItem = apiRepository.getReview(
             id = id,
             page = nextPage
         ).body()!!.reviews
            LoadResult.Page(
                data = reviewItem,
                prevKey = if (nextPage == 1) null else nextPage -1,
                nextKey = nextPage.plus(1)
            )
        }catch (e:Exception){
            LoadResult.Error(e)
        }
    }
}