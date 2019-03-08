package br.com.hussan.cache.stonechallenge.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.hussan.cache.stonechallenge.model.CategoryEntity
import br.com.hussan.stonechallenge.domain.Category
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface CategoryDao {
    @Insert
    fun insertAll(categories: List<CategoryEntity>): Completable

    @Query("SELECT * from category LIMIT 8")
    fun loadCategories(): Flowable<List<Category>>

}
