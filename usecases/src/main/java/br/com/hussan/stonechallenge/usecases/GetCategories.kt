package br.com.hussan.stonechallenge.usecases

import br.com.hussan.stonechallenge.data.datasource.CategoryDatasource
import io.reactivex.Completable

class GetCategories(private val dataSource: CategoryDatasource) {
    operator fun invoke(): Completable {
        return dataSource.saveCategories()
    }
}

