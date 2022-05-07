package com.example.kinopoisk.screen.main.bottomBar.bottomBarScreen.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.core_network_domain.model.person.PersonItem
import com.example.kinopoisk.api.repository.ApiRepository
import java.lang.Exception

class PersonPagingSource(
    private val apiRepository: ApiRepository,
    private val name:String
):PagingSource<Int, PersonItem>() {
    override fun getRefreshKey(state: PagingState<Int, PersonItem>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PersonItem> {
        return try {
            val nextPage = params.key ?: 1
            val personItem = apiRepository.getSearchPerson(
                name = name,
                page = nextPage
            ).body()!!.items
            LoadResult.Page(
                data = personItem,
                prevKey = if (nextPage == 1) null else nextPage -1,
                nextKey = nextPage.plus(1)
            )
        }catch (e:Exception){
            LoadResult.Error(e)
        }
    }
}