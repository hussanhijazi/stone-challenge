package br.com.hussan.stonechallenge.usecases

import br.com.hussan.stonechallenge.data.datasource.CategoryDatasource
import io.reactivex.Observable

class GetCategories(private val dataSource: CategoryDatasource) {
    operator fun invoke(): Observable<List<String>> {
        return dataSource.getCategories()
    }
}

