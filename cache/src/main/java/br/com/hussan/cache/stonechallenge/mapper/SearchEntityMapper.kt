package br.com.hussan.cache.stonechallenge.mapper

import br.com.hussan.cache.stonechallenge.model.SearchEntity
import br.com.hussan.stonechallenge.domain.Search
import java.util.Date

class SearchEntityMapper : EntityMapper<SearchEntity, Search> {
    override fun mapFromCached(type: SearchEntity): Search {
        return Search(type.query)

    }

    override fun mapToCached(type: Search): SearchEntity {
        return SearchEntity(type.query, Date())

    }

}
