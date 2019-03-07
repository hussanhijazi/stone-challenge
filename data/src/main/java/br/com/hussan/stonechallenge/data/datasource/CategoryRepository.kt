package br.com.hussan.stonechallenge.data.datasource

import br.com.hussan.stonechallenge.data.AppApi
import io.reactivex.Observable

class CategoryRepository(private val api: AppApi) : CategoryDatasource {

    override fun getCategories(): Observable<List<String>> {
        return api.getCategories()
    }
}

interface CategoryDatasource {
    fun getCategories(): Observable<List<String>>
}
