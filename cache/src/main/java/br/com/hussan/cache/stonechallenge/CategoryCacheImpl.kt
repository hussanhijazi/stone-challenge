package br.com.hussan.cache.stonechallenge

import br.com.hussan.cache.stonechallenge.mapper.CategoryEntityMapper
import br.com.hussan.stonechallenge.data.cache.CategoryCache
import br.com.hussan.stonechallenge.domain.Category
import io.reactivex.Completable
import io.reactivex.Flowable

class CategoryCacheImpl(
    private val db: AppDatabase,
    private val mapper: CategoryEntityMapper
) :
    CategoryCache {
    override fun getCategories(): Flowable<List<Category>> {
        return db.categoryDao().loadCategories().map { it.map { mapper.mapFromCached(it) } }
    }

    override fun saveCategories(categories: List<Category>): Completable {
        return db.categoryDao().insertAll(categories.map {
            mapper.mapToCached(it)
        })
    }

    override fun isCached(): Boolean {
        return db.query("SELECT id from category", null).count > 0
    }

}
