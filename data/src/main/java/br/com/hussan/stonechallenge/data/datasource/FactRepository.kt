package br.com.hussan.stonechallenge.data.datasource

import br.com.hussan.stonechallenge.data.AppApi
import br.com.hussan.stonechallenge.domain.Fact
import io.reactivex.Observable

class RepoRepository(private val api: AppApi) : RepoDatasource {

    override fun getRepos(user: String): Observable<List<Fact>> {
        return api.listRepos(user)
    }
}

interface RepoDatasource {
    fun getRepos(user: String): Observable<List<Fact>>
}
