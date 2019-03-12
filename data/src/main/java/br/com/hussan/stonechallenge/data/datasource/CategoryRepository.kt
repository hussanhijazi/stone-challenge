package br.com.hussan.stonechallenge.data.datasource

import br.com.hussan.stonechallenge.data.AppApi
import br.com.hussan.stonechallenge.data.RetryWithDelay
import br.com.hussan.stonechallenge.data.cache.CategoryCache
import br.com.hussan.stonechallenge.domain.Category
import io.reactivex.Completable
import io.reactivex.Flowable

class CategoryRepository(
    private val api: AppApi,
    private val cache: CategoryCache
) : CategoryDatasource {

    override fun saveCategories(): Completable {
        return if (!cache.isCached()) {
            api.getCategories()
                .retryWhen(RetryWithDelay(listOf(4, 8)))
                .flatMapCompletable {
                    cache.saveCategories(it.mapIndexed { id, value ->
                        Category(id + 1, value)
                    })
                }
        } else Completable.complete()
    }

    override fun getCategories(): Flowable<List<Category>> = cache.getCategories()

}

interface CategoryDatasource {
    fun saveCategories(): Completable
    fun getCategories(): Flowable<List<Category>>
}
