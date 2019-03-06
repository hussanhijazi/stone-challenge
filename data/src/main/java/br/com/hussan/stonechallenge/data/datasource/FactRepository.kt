package br.com.hussan.stonechallenge.data.datasource

import br.com.hussan.stonechallenge.data.AppApi
import br.com.hussan.stonechallenge.domain.Fact
import io.reactivex.Observable

class FactRepository(private val api: AppApi) : FactDatasource {

    override fun getFacts(query: String): Observable<List<Fact>> {
        return api.getFacts(query)
    }
}

interface FactDatasource {
    fun getFacts(user: String): Observable<List<Fact>>
}
