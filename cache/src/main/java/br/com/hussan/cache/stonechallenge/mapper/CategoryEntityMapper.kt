package br.com.hussan.cache.stonechallenge.mapper

import br.com.hussan.cache.stonechallenge.model.CategoryEntity
import br.com.hussan.stonechallenge.data.model.Category

class CategoryEntityMapper : EntityMapper<CategoryEntity, Category> {
    override fun mapFromCached(type: CategoryEntity): Category {
        return Category(type.id, type.name)

    }

    override fun mapToCached(type: Category): CategoryEntity {
        return CategoryEntity(type.id, name = type.name)

    }

}
