package br.com.hussan.stonechallenge.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.hussan.stonechallenge.domain.Repo
import br.com.hussan.stonechallenge.mock
import br.com.hussan.stonechallenge.usecases.GetRepos
import io.reactivex.Observable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify


class RepoViewModelTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val getRepos: GetRepos = mock()
    //    private val dataSource: RepoDatasource = mock()
    private lateinit var mViewModel: MainViewModel

    @Before
    fun setUp() {
        mViewModel = MainViewModel(getRepos)
    }

    @Test
    fun `Get User repositories when OK`() {

        val user = "hussanhijazi"

        val repos = listOf(Repo(""))

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
