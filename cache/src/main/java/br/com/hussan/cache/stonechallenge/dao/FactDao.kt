package br.com.hussan.cache.stonechallenge.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.hussan.cache.stonechallenge.model.FactEntity
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface FactDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(facts: List<FactEntity>): Completable

    @Query("SELECT * from fact WHERE term = :term")
    fun loadFacts(term: String): Flowable<List<FactEntity>>

}
