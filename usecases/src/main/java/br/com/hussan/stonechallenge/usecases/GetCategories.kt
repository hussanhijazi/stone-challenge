package br.com.hussan.stonechallenge.usecases

import br.com.hussan.stonechallenge.data.datasource.CategoryDatasource
import br.com.hussan.stonechallenge.domain.Category
import io.reactivex.Flowable

class GetCategories(private val dataSource: CategoryDatasource) {
    operator fun invoke(): Flowable<List<Category>> {
        return dataSource.getCategories()
    }
}
