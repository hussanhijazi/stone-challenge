package br.com.hussan.cache.stonechallenge

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.hussan.cache.stonechallenge.dao.CategoryDao
import br.com.hussan.cache.stonechallenge.model.CategoryEntity

@Database(entities = [CategoryEntity::class], version = 3)
abstract class AppDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao

}
