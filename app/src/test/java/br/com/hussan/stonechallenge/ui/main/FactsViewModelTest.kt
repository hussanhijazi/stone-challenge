package br.com.hussan.stonechallenge.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.hussan.stonechallenge.domain.Fact
import br.com.hussan.stonechallenge.mock
import br.com.hussan.stonechallenge.usecases.GetFacts
import io.reactivex.Observable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify


class FactsViewModelTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val getRepos: GetFacts = mock()
    private lateinit var mViewModel: FactsViewModel

    @Before
    fun setUp() {
        mViewModel = FactsViewModel(getRepos)
    }

    @Test
    fun `Get User repositories when OK`() {

        val query = "car"

        val repos = listOf(Fact(""))

        `when`(getRepos.invoke(query)).thenReturn(Observable.fromArray(repos))

        mViewModel.getFacts(query)
            .test()
            .assertValue(repos)
            .assertComplete()

        verify(getRepos).invoke(query)

    }

    @Test
    fun `User repositories when error`() {

        val exception = Exception()
        val query = "car"

        `when`(getRepos.invoke(query)).thenReturn(Observable.error(exception))

        mViewModel.getFacts(query)
            .test()
            .assertError(exception)

    }
}
