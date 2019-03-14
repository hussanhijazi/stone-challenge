package br.com.hussan.stonechallenge.data.datasource

import br.com.hussan.stonechallenge.data.AppApi
import br.com.hussan.stonechallenge.data.RetryWithDelay
import br.com.hussan.stonechallenge.data.cache.SearchCache
import br.com.hussan.stonechallenge.domain.Fact
import io.reactivex.Observable


class FactRepository(private val api: AppApi, private val cache: SearchCache) : FactDatasource {

    companion object {
        const val FIRST_CALL = 4
        const val SECOND_CALL = 8
    }

    override fun getFacts(query: String): Observable<List<Fact>> {
        return api.getFacts(query).map { it.result }
            .retryWhen(RetryWithDelay(listOf(FIRST_CALL, SECOND_CALL)))
    }
}

interface FactDatasource {
    fun getFacts(query: String): Observable<List<Fact>>
}
