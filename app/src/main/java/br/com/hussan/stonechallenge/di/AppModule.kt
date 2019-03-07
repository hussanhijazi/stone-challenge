package br.com.hussan.stonechallenge.di

import br.com.hussan.stonechallenge.ui.main.FactsViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val appModule = module {
    viewModel { FactsViewModel(get(), get()) }
}
