package br.com.hussan.stonechallenge.usecases.di

import br.com.hussan.stonechallenge.usecases.GetCategories
import br.com.hussan.stonechallenge.usecases.GetFacts
import br.com.hussan.stonechallenge.usecases.SaveCategories
import org.koin.dsl.module.module

val useCaseModule = module {
    single { GetFacts(get()) }
    single { SaveCategories(get()) }
    single { GetCategories(get()) }
}
