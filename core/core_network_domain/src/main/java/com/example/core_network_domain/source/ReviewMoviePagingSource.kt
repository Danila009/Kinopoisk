package com.example.core_network_domain.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.core_network_domain.model.movie.review.ReviewItem
import com.example.core_network_domain.useCase.movie.GetReviewMovieUseCase
import java.lang.Exception

class ReviewMoviePagingSource(
    private val getReviewMovieUseCase: GetReviewMovieUseCase,
    private val id:Int
):PagingSource<Int, ReviewItem>() {
    override fun getRefreshKey(state: PagingState<Int, ReviewItem>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ReviewItem> {
        return try {
         val nextPage = params.key ?:1
         val reviewItem = getReviewMovieUseCase.invoke(
             id = id,
             page = nextPage
         ).reviews
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