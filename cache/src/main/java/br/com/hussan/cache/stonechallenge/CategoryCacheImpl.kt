package br.com.hussan.cache.stonechallenge

import br.com.hussan.cache.stonechallenge.mapper.EntityMapper
import br.com.hussan.cache.stonechallenge.model.CategoryEntity
import br.com.hussan.stonechallenge.data.cache.CategoryCache
import br.com.hussan.stonechallenge.domain.Category
import io.reactivex.Completable

class CategoryCacheImpl(
    private val db: AppDatabase,
    private val mapper: EntityMapper<CategoryEntity, Category>
) :
    CategoryCache {
    override fun saveCategories(categories: List<Category>): Completable {
        return db.categoryDao().insertAll(categories.map {
            mapper.mapToCached(it)
        })
    }

    override fun isCached(): Boolean {
        return db.query("SELECT id from CategoryEntity", null).count > 0
    }

}
