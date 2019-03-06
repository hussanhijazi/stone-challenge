package br.com.hussan.stonechallenge.ui.main

import androidx.lifecycle.ViewModel
import br.com.hussan.stonechallenge.usecases.GetRepos

class MainViewModel(private val getReposCase: GetRepos) : ViewModel() {

    fun getRepos(user: String) = getReposCase.invoke(user)

}

