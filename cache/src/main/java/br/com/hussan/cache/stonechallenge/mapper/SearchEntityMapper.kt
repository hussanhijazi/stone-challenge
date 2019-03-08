package br.com.hussan.cache.stonechallenge.mapper

import br.com.hussan.cache.stonechallenge.model.SearchEntity
import br.com.hussan.stonechallenge.domain.Search

class SearchEntityMapper : EntityMapper<SearchEntity, Search> {
    override fun mapFromCached(type: SearchEntity): Search {
        return Search(type.query, type.searchedAt)

    }

    override fun mapToCached(type: Search): SearchEntity {
        return SearchEntity(type.query, type.searchedAt)

    }

}
