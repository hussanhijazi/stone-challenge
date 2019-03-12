package br.com.hussan.stonechallenge.data.dataSource

import br.com.hussan.stonechallenge.data.cache.SearchCache
import br.com.hussan.stonechallenge.data.datasource.SearchDatasource
import br.com.hussan.stonechallenge.data.datasource.SearchRepository
import br.com.hussan.stonechallenge.data.mock
import br.com.hussan.stonechallenge.domain.Search
import io.reactivex.Completable
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import java.util.Date

@RunWith(JUnit4::class)
class SearchRepositoryTest {

    lateinit var repository: SearchDatasource
    lateinit var cache: SearchCache


    @Before
    @Throws
    fun setUp() {

        cache = mock()
        repository = SearchRepository(cache)

    }

    @Test
    fun `Get searches locally`() {

        val searches = listOf(Search("search", Date()))

        `when`(cache.getSearches()).thenReturn(Flowable.just(searches))

        val testObserver = repository.getSearches().test()

        testObserver.assertValue(searches)

        verify(cache).getSearches()
    }

    @Test
    fun `Get searches locally with error`() {

        val exception = Exception()

        `when`(cache.getSearches()).thenReturn(Flowable.error(exception))

        val testObserver = repository.getSearches().test()

        testObserver.assertError(exception)

        verify(cache).getSearches()
    }

    @Test
    fun `Save search locally`() {
        val search = Search("search", Date())

        `when`(cache.saveSearch(search)).thenReturn(Completable.complete())

        val testObserver = repository.saveSearch(search).test()

        testObserver.assertNoErrors()
        testObserver.assertComplete()
        verify(cache).saveSearch(search)
    }

    @Test
    fun `Save search locally with error`() {

        val search = Search("search", Date())
        val exception = Exception()

        `when`(cache.saveSearch(search)).thenReturn(Completable.error(exception))

        val testObserver = repository.saveSearch(search).test()

        testObserver.assertError(exception)
        verify(cache).saveSearch(search)
    }
}
