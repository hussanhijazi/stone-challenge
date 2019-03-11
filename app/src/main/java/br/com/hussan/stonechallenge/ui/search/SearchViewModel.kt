package br.com.hussan.stonechallenge.ui.search

import androidx.lifecycle.ViewModel
import br.com.hussan.stonechallenge.usecases.GetCategories
import br.com.hussan.stonechallenge.usecases.GetSearches

class SearchViewModel(
    private val getCategoriesCase: GetCategories,
    private val getSearchesCase: GetSearches
) : ViewModel() {

    fun getCategories() = getCategoriesCase.invoke()
    fun getSearches() = getSearchesCase.invoke()

}
