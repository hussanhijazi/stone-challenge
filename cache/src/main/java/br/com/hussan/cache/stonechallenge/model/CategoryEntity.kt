package br.com.hussan.cache.stonechallenge.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CategoryEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") var name: String?
)
