package br.com.hussan.stonechallenge.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.hussan.stonechallenge.domain.Fact
import br.com.hussan.stonechallenge.usecases.GetFacts
import br.com.hussan.stonechallenge.usecases.SaveCategories

class FactsViewModel(
    private val getFactsCase: GetFacts,
    private val getCategoriesCase: SaveCategories
) : ViewModel() {

    private val results = MutableLiveData<List<Fact>>()

    fun getFacts(query: String) = getFactsCase.invoke(query).doOnNext {
        results.postValue(it)
    }

    fun getCategtories() = getCategoriesCase.invoke()

    fun getResultFacts() = results
}

