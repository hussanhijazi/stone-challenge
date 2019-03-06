package br.com.hussan.stonechallenge.usecases

import br.com.hussan.stonechallenge.data.datasource.RepoDatasource
import br.com.hussan.stonechallenge.domain.Fact
import io.reactivex.Observable

class GetRepos(private val dataSource: RepoDatasource) {
    operator fun invoke(user: String): Observable<List<Fact>> {
        return dataSource.getRepos(user)
    }
}

