package br.com.hussan.stonechallenge.data.datasource

import br.com.hussan.stonechallenge.data.AppApi
import br.com.hussan.stonechallenge.data.RetryWithDelay
import br.com.hussan.stonechallenge.data.cache.SearchCache
import br.com.hussan.stonechallenge.domain.Fact
import br.com.hussan.stonechallenge.domain.Search
import io.reactivex.Observable
import java.util.*


class FactRepository(private val api: AppApi, private val cache: SearchCache) : FactDatasource {

    override fun getFacts(query: String): Observable<List<Fact>> {
        return api.getFacts(query).map { it.result }
            .retryWhen(RetryWithDelay(listOf(4, 8)))
//            .doOnNext { cache.saveSearch(Search(query, Date())).subscribe() }

            .flatMap {
                Observable.fromCallable {
                    cache.saveSearch(Search(query, Date()))
                    it
                }
            }
//            .flatMapCompletable {
//                cache.saveSearch(Search(query, Date()))
////                    .subscribe()
//            }.toObservable()
    }
}

interface FactDatasource {
    fun getFacts(query: String): Observable<List<Fact>>
}
