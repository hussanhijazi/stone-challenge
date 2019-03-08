package br.com.hussan.cache.stonechallenge

import br.com.hussan.cache.stonechallenge.mapper.SearchEntityMapper
import br.com.hussan.stonechallenge.data.cache.SearchCache
import br.com.hussan.stonechallenge.domain.Search
import io.reactivex.Flowable

class SearchCacheImpl(
    private val db: AppDatabase,
    private val mapper: SearchEntityMapper
) : SearchCache {
    override fun saveSearch(search: Search) = db.searchDao().insert(mapper.mapToCached(search))

    override fun isCached(): Boolean {
        return db.query("SELECT id from search", null).count > 0
    }

    override fun getSearches(): Flowable<List<Search>> {
        return db.searchDao().loadSearches().map { it.map { mapper.mapFromCached(it) } }
    }
}
