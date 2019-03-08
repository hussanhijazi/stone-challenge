package br.com.hussan.stonechallenge.di

import br.com.hussan.stonechallenge.AppNavigator
import br.com.hussan.stonechallenge.ui.main.FactsActivity
import br.com.hussan.stonechallenge.ui.main.FactsViewModel
import br.com.hussan.stonechallenge.ui.search.SearchViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val appModule = module {
    viewModel { FactsViewModel(get(), get()) }
    viewModel { SearchViewModel(get()) }
    single { (activity: FactsActivity) ->
        AppNavigator(activity = activity)
    }
}
