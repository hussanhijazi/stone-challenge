package br.com.hussan.stonechallenge.data.cache

import br.com.hussan.stonechallenge.domain.Search
import io.reactivex.Completable
import io.reactivex.Flowable

interface SearchCache {
    fun saveSearch(search: Search): Completable
    fun isCached(): Boolean
    fun getSearches(): Flowable<List<Search>>
}
