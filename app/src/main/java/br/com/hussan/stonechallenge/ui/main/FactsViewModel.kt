package br.com.hussan.stonechallenge.ui.main

import androidx.lifecycle.ViewModel
import br.com.hussan.stonechallenge.usecases.GetFacts
import br.com.hussan.stonechallenge.usecases.SaveCategories

class FactsViewModel(
    private val getFactsCase: GetFacts,
    private val getCategoriesCase: SaveCategories
) : ViewModel() {

    fun getFacts(query: String) = getFactsCase.invoke(query)

    fun getCategtories() = getCategoriesCase.invoke()

}

