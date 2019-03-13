package br.com.hussan.stonechallenge.usecases

import br.com.hussan.stonechallenge.data.datasource.SearchDatasource
import br.com.hussan.stonechallenge.domain.Search
import io.reactivex.Completable

class SaveSearch(private val dataSource: SearchDatasource) {
    operator fun invoke(search: Search): Completable {
        print("usecase invoce")
        return dataSource.saveSearch(search)
    }
}

