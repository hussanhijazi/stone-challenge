package br.com.hussan.stonechallenge.ui.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.hussan.stonechallenge.domain.Category
import br.com.hussan.stonechallenge.domain.Search
import br.com.hussan.stonechallenge.usecases.GetCategories
import br.com.hussan.stonechallenge.usecases.GetSearches
import io.reactivex.Flowable
import mock
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify


class SearchViewModelTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val getSearchCase: GetSearches = mock()
    private var getCategoryCase: GetCategories = mock()
    private lateinit var mViewModel: SearchViewModel

    @Before
    fun setUp() {
        mViewModel = SearchViewModel(getCategoryCase, getSearchCase)
    }

    @Test
    fun `Get categories locally`() {

        val categories = listOf(Category(1, "Dev"))
        `when`(getCategoryCase.invoke()).thenReturn(Flowable.fromArray(categories))

        mViewModel.getCategories()
            .test()
            .assertValue(categories)
            .assertComplete()

        verify(getCategoryCase).invoke()

    }

    @Test
    fun `Get categories locally with error`() {

        val exception = Exception()
        `when`(getCategoryCase.invoke()).thenReturn(Flowable.error(exception))

        mViewModel.getCategories()
            .test()
            .assertError(exception)

        verify(getCategoryCase).invoke()

    }

    @Test
    fun `Get searches locally`() {

        val searches = listOf(Search("Dev"))
        `when`(getSearchCase.invoke()).thenReturn(Flowable.fromArray(searches))

        mViewModel.getSearches()
            .test()
            .assertValue(searches)
            .assertComplete()

        verify(getSearchCase).invoke()

    }

    @Test
    fun `Get searches locally with error`() {

        val exception = Exception()
        `when`(getSearchCase.invoke()).thenReturn(Flowable.error(exception))

        mViewModel.getSearches()
            .test()
            .assertError(exception)

        verify(getSearchCase).invoke()

    }
}
