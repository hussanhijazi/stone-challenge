package br.com.hussan.cache.stonechallenge.di

import androidx.room.Room.databaseBuilder
import br.com.hussan.cache.stonechallenge.AppDatabase
import br.com.hussan.cache.stonechallenge.CategoryCacheImpl
import br.com.hussan.cache.stonechallenge.mapper.CategoryEntityMapper
import br.com.hussan.cache.stonechallenge.mapper.EntityMapper
import br.com.hussan.cache.stonechallenge.model.CategoryEntity
import br.com.hussan.stonechallenge.data.cache.CategoryCache
import br.com.hussan.stonechallenge.domain.Category
import org.koin.dsl.module.module

val cacheModule = module {
    single {
        databaseBuilder(
            get(),
            AppDatabase::class.java, "stone-challenge"
        ).build()
    }
    single<EntityMapper<CategoryEntity, Category>> { CategoryEntityMapper() }

    single<CategoryCache> {
        CategoryCacheImpl(get(), get())
    }
}
