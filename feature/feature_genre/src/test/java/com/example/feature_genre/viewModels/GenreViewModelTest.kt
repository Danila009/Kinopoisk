package com.example.feature_genre.viewModels

import com.example.core_network_domain.useCase.movie.GetFilterUseCase
import com.example.feature_genre.viewModel.GenreViewModel
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock

class GenreViewModelTest {

    private val getFilterUseCase = mock(GetFilterUseCase::class.java)

    @After
    fun after(){
        //Clear
        Mockito.reset(getFilterUseCase)
    }

    @Before
    fun before(){

    }

    @Test
    fun `should load model filter` (): Unit = runBlocking {
        val viewModel = GenreViewModel(getFilterUseCase = getFilterUseCase)

        viewModel.getFilter()

        val firstFilterItem = viewModel.responseFilter.first()

        assertThat(firstFilterItem).isNotNull()
    }
}