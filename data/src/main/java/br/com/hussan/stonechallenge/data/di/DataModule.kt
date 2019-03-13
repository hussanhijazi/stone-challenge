package br.com.hussan.stonechallenge.data.di

//import br.com.hussan.stonechallenge.data.datasource.
import br.com.hussan.stonechallenge.data.datasource.*
import org.koin.dsl.module.module

val dataModule = module {
    single<FactDatasource> { FactRepository(get(), get()) }
    single<CategoryDatasource> { CategoryRepository(get(), get()) }
    single<SearchDatasource> { SearchRepository(get()) }
}
