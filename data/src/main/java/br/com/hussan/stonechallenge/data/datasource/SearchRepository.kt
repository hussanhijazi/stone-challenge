package br.com.hussan.stonechallenge.data.datasource

import br.com.hussan.stonechallenge.data.cache.SearchCache
import br.com.hussan.stonechallenge.domain.Search
import io.reactivex.Completable
import io.reactivex.Flowable

class SearchRepository(private val cache: SearchCache) : SearchDatasource {
    override fun getSearches(): Flowable<List<Search>> = cache.getSearches()
    override fun saveSearch(search: Search) = cache.saveSearch(search)

}

interface SearchDatasource {
    fun getSearches(): Flowable<List<Search>>
    fun saveSearch(search: Search): Completable
}
