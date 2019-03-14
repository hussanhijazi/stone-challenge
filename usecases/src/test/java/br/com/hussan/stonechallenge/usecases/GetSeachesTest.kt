package br.com.hussan.stonechallenge.usecases

import br.com.hussan.stonechallenge.data.datasource.SearchDatasource
import br.com.hussan.stonechallenge.domain.Search
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.*

@RunWith(JUnit4::class)
class GetSeachesTest {

    private lateinit var getSearches: GetSearches
    private lateinit var repository: SearchDatasource

    @Before
    fun setUp() {
        repository = mock()
        getSearches = GetSearches(repository)
    }

    @Test
    fun `Get search call locally`() {
        val searches = listOf(Search("car"))

        `when`(repository.getSearches()).thenReturn(Flowable.just(searches))

        getSearches.invoke().test()
            .assertValue(searches)
            .assertNoErrors()
            .assertComplete()


        verify(repository).getSearches()
        verifyNoMoreInteractions(repository)
    }

    @Test
    fun `Get search call locally with error`() {
        val exception = Exception()
        `when`(repository.getSearches()).thenReturn(Flowable.error(exception))

        getSearches.invoke().test().assertError(exception)

        verify(repository).getSearches()
        verifyNoMoreInteractions(repository)
    }
}
