package br.com.hussan.stonechallenge.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.hussan.stonechallenge.domain.Fact
import br.com.hussan.stonechallenge.domain.Search
import br.com.hussan.stonechallenge.usecases.GetFacts
import br.com.hussan.stonechallenge.usecases.SaveCategories
import br.com.hussan.stonechallenge.usecases.SaveSearch
import io.reactivex.Observable

class FactsViewModel(
    private val getFactsCase: GetFacts,
    private val saveCategoriesCase: SaveCategories,
    private val saveSearchCase: SaveSearch
) : ViewModel() {

    private val results = MutableLiveData<List<Fact>>()

    fun getFacts(query: String) = getFactsCase.invoke(query)
        .doOnNext {
            results.postValue(it)
        }
        .flatMap { data ->
            saveSearchCase(Search(query)).andThen(Observable.just(data))
        }

    fun getCategories() = saveCategoriesCase.invoke()

    fun getResultFacts() = results
}

