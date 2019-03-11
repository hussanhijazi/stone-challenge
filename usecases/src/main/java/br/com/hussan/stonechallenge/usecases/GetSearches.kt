package br.com.hussan.stonechallenge.usecases

import br.com.hussan.stonechallenge.data.datasource.SearchDatasource
import br.com.hussan.stonechallenge.domain.Search
import io.reactivex.Flowable

class GetSearches(private val dataSource: SearchDatasource) {
    operator fun invoke(): Flowable<List<Search>> {
        return dataSource.getSearches()
    }
}
