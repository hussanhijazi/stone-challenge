package br.com.hussan.stonechallenge.ui.main

import androidx.lifecycle.ViewModel
import br.com.hussan.stonechallenge.usecases.GetFacts

class FactsViewModel(private val getFactsCase: GetFacts) : ViewModel() {

    fun getFacts(query: String) = getFactsCase.invoke(query)

}

