package br.com.hussan.stonechallenge.data.di

import br.com.hussan.stonechallenge.data.datasource.RepoDatasource
import br.com.hussan.stonechallenge.data.datasource.RepoRepository
import org.koin.dsl.module.module

val dataModule = module {
    // single instance of HelloRepository
    single<RepoDatasource> { RepoRepository(get()) }
}
