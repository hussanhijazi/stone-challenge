package br.com.hussan.cache.stonechallenge.mapper

import br.com.hussan.cache.stonechallenge.model.FactEntity
import br.com.hussan.stonechallenge.domain.Fact

class FactEntityMapper : EntityMapper<FactEntity, Fact> {
    override fun mapFromCached(type: FactEntity): Fact {
        return Fact(type.id, type.value, query = type.query)

    }

    override fun mapToCached(type: Fact): FactEntity {
        return FactEntity(type.id, type.value, type.query)

    }

}
