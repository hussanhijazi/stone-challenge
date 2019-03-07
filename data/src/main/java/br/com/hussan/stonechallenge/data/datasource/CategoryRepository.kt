package br.com.hussan.stonechallenge.data.datasource

import br.com.hussan.stonechallenge.data.AppApi
import br.com.hussan.stonechallenge.data.cache.CategoryCache
import br.com.hussan.stonechallenge.domain.Category
import io.reactivex.Completable

class CategoryRepository(private val api: AppApi, private val cache: CategoryCache) : CategoryDatasource {
    override fun saveCategories(): Completable {
        return if (!cache.isCached()) {
            api.getCategories().flatMapCompletable {
                cache.saveCategories(it.mapIndexed { id, value ->
                    Category(id + 1, value)
                })
            }
        } else Completable.complete()
    }
}

interface CategoryDatasource {
    fun saveCategories(): Completable
}
