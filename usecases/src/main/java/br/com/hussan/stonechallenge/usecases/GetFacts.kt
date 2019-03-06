package br.com.hussan.stonechallenge.usecases

import br.com.hussan.stonechallenge.data.datasource.FactDatasource
import br.com.hussan.stonechallenge.domain.Fact
import io.reactivex.Observable

class GetFacts(private val dataSource: FactDatasource) {
    operator fun invoke(user: String): Observable<List<Fact>> {
        return dataSource.getRepos(user)
    }
}

