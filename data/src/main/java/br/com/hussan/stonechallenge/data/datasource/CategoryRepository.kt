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
    companion object {
        const val FIRST_CALL = 4
        const val SECOND_CALL = 8
    }

    override fun saveCategories(): Completable {
        return if (!cache.isCached()) {
            api.getCategories()
                .retryWhen(RetryWithDelay(listOf(FIRST_CALL, SECOND_CALL)))
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
