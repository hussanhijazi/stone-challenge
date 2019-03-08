package br.com.hussan.stonechallenge.ui.search

import androidx.lifecycle.ViewModel
import br.com.hussan.stonechallenge.usecases.GetCategories

class SearchViewModel(
    private val getCategoriesCase: GetCategories
) : ViewModel() {

    fun getCategories() = getCategoriesCase.invoke()

}
