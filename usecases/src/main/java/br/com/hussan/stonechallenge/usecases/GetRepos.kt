package br.com.hussan.stonechallenge.usecases

import br.com.hussan.stonechallenge.data.datasource.RepoDatasource
import br.com.hussan.stonechallenge.domain.Repo
import io.reactivex.Observable

class GetRepos(private val dataSource: RepoDatasource) {
    operator fun invoke(user: String): Observable<List<Repo>> {
        return dataSource.getRepos(user)
    }
}

