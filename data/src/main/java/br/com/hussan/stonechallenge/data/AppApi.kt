package br.com.hussan.stonechallenge.data

import br.com.hussan.stonechallenge.domain.Fact
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path


interface AppApi {
    @GET("users/{user}/repos")
    fun listRepos(@Path("user") user: String): Observable<List<Fact>>
}
