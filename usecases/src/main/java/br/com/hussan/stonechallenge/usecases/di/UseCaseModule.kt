package br.com.hussan.stonechallenge.usecases.di

import br.com.hussan.stonechallenge.usecases.*
import org.koin.dsl.module.module

val useCaseModule = module {
    single { GetFacts(get()) }
    single { SaveCategories(get()) }
    single { GetCategories(get()) }
    single { GetSearches(get()) }
    single { SaveSearch(get()) }
}
