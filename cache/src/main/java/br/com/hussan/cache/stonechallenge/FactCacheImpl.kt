package br.com.hussan.cache.stonechallenge

import br.com.hussan.cache.stonechallenge.mapper.FactEntityMapper
import br.com.hussan.stonechallenge.data.cache.FactCache
import br.com.hussan.stonechallenge.domain.Fact
import io.reactivex.Completable
import io.reactivex.Flowable

class FactCacheImpl(
    private val db: AppDatabase,
    private val mapper: FactEntityMapper
) :
    FactCache {
    override fun getRandomFacts(): Flowable<List<Fact>> {
        return db.factDao().loadRandomFacts().map { it.map { mapper.mapFromCached(it) } }
    }

    override fun getFacts(query: String): Flowable<List<Fact>> {
        return db.factDao().loadFacts(query).map { it.map { mapper.mapFromCached(it) } }
    }

    override fun saveFacts(categories: List<Fact>): Completable {
        return db.factDao().insertAll(categories.map {
            mapper.mapToCached(it)
        })
    }

}
