package br.com.hussan.stonechallenge.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.hussan.stonechallenge.domain.Fact
import br.com.hussan.stonechallenge.mock
import br.com.hussan.stonechallenge.usecases.GetFacts
import br.com.hussan.stonechallenge.usecases.SaveCategories
import io.reactivex.Observable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify


class FactsViewModelTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val getFactsCase: GetFacts = mock()
    private val saveCategoryCase: SaveCategories = mock()
    private lateinit var mViewModel: FactsViewModel

    @Before
    fun setUp() {
        mViewModel = FactsViewModel(getFactsCase, saveCategoryCase)
    }

    @Test
    fun `Get User repositories when OK`() {

        val query = "car"

        val repos = listOf(Fact(""))

        `when`(getFactsCase.invoke(query)).thenReturn(Observable.fromArray(repos))

        mViewModel.getFacts(query)
            .test()
            .assertValue(repos)
            .assertComplete()

        verify(getFactsCase).invoke(query)

    }

    @Test
    fun `User repositories when error`() {

        val exception = Exception()
        val query = "car"

        `when`(getFactsCase.invoke(query)).thenReturn(Observable.error(exception))

        mViewModel.getFacts(query)
            .test()
            .assertError(exception)

    }
}
