package br.com.hussan.stonechallenge.data.di

import br.com.hussan.stonechallenge.data.datasource.CategoryDatasource
import br.com.hussan.stonechallenge.data.datasource.CategoryRepository
import br.com.hussan.stonechallenge.data.datasource.FactDatasource
import br.com.hussan.stonechallenge.data.datasource.FactRepository
import org.koin.dsl.module.module

val dataModule = module {
    single<FactDatasource> { FactRepository(get()) }
    single<CategoryDatasource> { CategoryRepository(get(), get()) }
}
