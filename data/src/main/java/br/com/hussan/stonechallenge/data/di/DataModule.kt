package br.com.hussan.stonechallenge.data.di

import br.com.hussan.stonechallenge.data.datasource.FactDatasource
import br.com.hussan.stonechallenge.data.datasource.FactRepository
import org.koin.dsl.module.module

val dataModule = module {
    // single instance of HelloRepository
    single<FactDatasource> { FactRepository(get()) }
}
