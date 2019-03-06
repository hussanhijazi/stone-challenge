package br.com.hussan.stonechallenge.usecases.di

import br.com.hussan.stonechallenge.usecases.GetFacts
import org.koin.dsl.module.module

val useCaseModule = module {
    single {
        GetFacts(get())
    }
}
