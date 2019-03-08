package br.com.hussan.cache.stonechallenge

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.hussan.cache.stonechallenge.dao.CategoryDao
import br.com.hussan.cache.stonechallenge.dao.SearchDao
import br.com.hussan.cache.stonechallenge.model.CategoryEntity
import br.com.hussan.cache.stonechallenge.model.SearchEntity

@Database(entities = [CategoryEntity::class, SearchEntity::class], version = 3)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
    abstract fun searchDao(): SearchDao

}
