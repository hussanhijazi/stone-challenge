package br.com.hussan.stonechallenge.data.cache

import br.com.hussan.stonechallenge.domain.Fact
import io.reactivex.Completable
import io.reactivex.Flowable

interface FactCache {
    fun saveFacts(facts: List<Fact>): Completable
    fun getFacts(query: String): Flowable<List<Fact>>
    fun getRandomFacts(): Flowable<List<Fact>>
}
