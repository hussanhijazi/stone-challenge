package br.com.hussan.stonechallenge.data.dataSource

import br.com.hussan.stonechallenge.data.AppApi
import br.com.hussan.stonechallenge.data.FactsResponse
import br.com.hussan.stonechallenge.data.datasource.FactDatasource
import br.com.hussan.stonechallenge.data.datasource.FactRepository
import br.com.hussan.stonechallenge.domain.Fact
import com.google.gson.Gson
import io.reactivex.observers.TestObserver
import junit.framework.TestCase
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@RunWith(JUnit4::class)
class FactRepositoryTest {

    lateinit var repository: FactDatasource
    lateinit var api: AppApi
    lateinit var mockServer: MockWebServer
    lateinit var gson: Gson


    @Before
    @Throws
    fun setUp() {

        gson = Gson()

        mockServer = MockWebServer()
        val baseURL = mockServer.url("/").toString()

        val okHttpClient = OkHttpClient.Builder()
            .build()

        val retrofit = Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        api = retrofit.create(AppApi::class.java)

        repository = FactRepository(api)

    }

    @Test
    fun `Get facts remote`() {

        val testObserver = TestObserver<List<Fact>>()
        val factResponse = FactsResponse(10, listOf(Fact("Chuck Norris")))
        val query = "query"
        val path = "/jokes/search?query=$query"

        val mockResponse = MockResponse()
            .setResponseCode(200)
            .setBody(gson.toJson(factResponse))
        mockServer.enqueue(mockResponse)

        repository.getFacts(query).subscribe(testObserver)

        testObserver.awaitTerminalEvent(1, TimeUnit.SECONDS)

        testObserver.assertValue(factResponse.result)
        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)

        val request = mockServer.takeRequest()
        TestCase.assertEquals(path, request.path)
    }

}
