package br.com.hussan.cache.stonechallenge.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import br.com.hussan.cache.stonechallenge.model.SearchEntity
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface SearchDao {
    @Insert(onConflict = REPLACE)
    fun insert(search: SearchEntity): Completable

    @Query("SELECT * from search order by searchedAt desc")
    fun loadSearches(): Flowable<List<SearchEntity>>
}
