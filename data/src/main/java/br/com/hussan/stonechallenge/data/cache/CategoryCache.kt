package br.com.hussan.stonechallenge.data.cache

import br.com.hussan.stonechallenge.domain.Category
import io.reactivex.Completable

interface CategoryCache {
    fun saveCategories(categories: List<Category>): Completable
    fun isCached(): Boolean
}
