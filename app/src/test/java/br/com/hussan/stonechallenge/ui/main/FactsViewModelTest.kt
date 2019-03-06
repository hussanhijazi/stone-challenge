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
    //    private val dataSource: FactDatasource = mock()
    private lateinit var mViewModel: FactsViewModel

    @Before
    fun setUp() {
        mViewModel = FactsViewModel(getRepos)
    }

    @Test
    fun `Get User repositories when OK`() {

        val user = "hussanhijazi"

        val repos = listOf(Fact(""))

        `when`(getRepos.invoke(user)).thenReturn(Observable.fromArray(repos))

        mViewModel.getRepos(user)
            .test()
            .assertValue(repos)
            .assertComplete()

        verify(getRepos).invoke(user)

    }

    @Test
    fun `User repositories when error`() {

        val exception = Exception()
        val user = "hussanhijazi"

        `when`(getRepos.invoke(user)).thenReturn(Observable.error(exception))

        mViewModel.getRepos(user)
            .test()
            .assertError(exception)

    }
}
