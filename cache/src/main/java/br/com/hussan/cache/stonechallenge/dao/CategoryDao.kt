package br.com.hussan.cache.stonechallenge.dao

import androidx.room.Dao
import androidx.room.Insert
import br.com.hussan.cache.stonechallenge.model.CategoryEntity
import io.reactivex.Completable

@Dao
interface CategoryDao {
    @Insert
    fun insertAll(categories: List<CategoryEntity>): Completable

}
