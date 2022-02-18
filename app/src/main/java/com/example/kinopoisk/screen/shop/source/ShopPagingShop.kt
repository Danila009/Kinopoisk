package com.example.kinopoisk.screen.shop.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.kinopoisk.api.model.shop.Shop
import com.example.kinopoisk.api.repository.ApiUserRepository
import java.lang.Exception

class ShopPagingShop(
    private val userRepository: ApiUserRepository,
    private val ratingMax:Float?,
    private val ratingMin:Float?,
    private val search:String?
):PagingSource<Int, Shop>() {
    override fun getRefreshKey(state: PagingState<Int, Shop>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Shop> {
        return try {
            val nextPage = params.key ?: 1
            val shopItem = userRepository.getShop(
                ratingMax = ratingMax,
                ratingMin = ratingMin,
                search = search,
                page = nextPage
            ).body()!!
            LoadResult.Page(
                data = shopItem,
                prevKey = if (nextPage == 1) null else nextPage -1,
                nextKey = nextPage.plus(1)
            )
        }catch (e:Exception){
            LoadResult.Error(e)
        }
    }
}