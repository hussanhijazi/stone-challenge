package br.com.hussan.stonechallenge.usecases.di

import br.com.hussan.stonechallenge.usecases.GetRepos
import org.koin.dsl.module.module

val useCaseModule = module {
    single {
        GetRepos(get())
    }
}
