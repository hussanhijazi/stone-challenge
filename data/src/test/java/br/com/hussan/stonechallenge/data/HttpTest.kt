package br.com.hussan.stonechallenge.data

import br.com.hussan.stonechallenge.data.cache.SearchCache
import br.com.hussan.stonechallenge.data.datasource.FactRepository
import br.com.hussan.stonechallenge.domain.Fact
import br.com.hussan.stonechallenge.domain.Search
import com.google.gson.Gson
import io.reactivex.Completable
import io.reactivex.observers.TestObserver
import junit.framework.TestCase.assertEquals
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.`when`
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit


@RunWith(JUnit4::class)
class HttpTest {
    //    @get:Rule
//    var instantTaskExecutorRule = InstantTaskExecutorRule()
    lateinit var repository: FactRepository
    lateinit var mockServer: MockWebServer
    lateinit var api: AppApi
    lateinit var cache: SearchCache
    lateinit var gson: Gson


    @Before
    @Throws
    fun setUp() {

        gson = Gson()
        cache = mock()

        mockServer = MockWebServer()
        val baseURL = mockServer.url("/").toString()

//        mockServer.start()

        // Get an okhttp client
        val okHttpClient = OkHttpClient.Builder()
            .build()

        // Get an instance of Retrofit
        val retrofit = Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        // Get an instance of blogService
        api = retrofit.create(AppApi::class.java)

        repository = FactRepository(api, cache = cache)
        // Initialized repository


    }

    @Test
    fun testBlogsReturnsListOfBlogs() {

        val testObserver = TestObserver<List<Fact>>()
        val date: Date = mock()
        val factResponse = FactsResponse(10, listOf(Fact("Chuck Norris")))
        val search = Search("query", date)

        // Mock a response with status 200 and sample JSON output
        val mockResponse = MockResponse()
            .setResponseCode(200)
            .setBody(gson.toJson(factResponse))
        mockServer.enqueue(mockResponse)


        `when`(date.time).thenReturn(30L)
        `when`(cache.saveSearch(search)).thenReturn(Completable.complete())

        repository.getFacts("query").subscribe(testObserver)

        testObserver.awaitTerminalEvent(1, TimeUnit.SECONDS)

        testObserver.assertValue(factResponse.result)
        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)


    }

    @Test
    fun testBlogsReturnsError() {
        val testObserver = TestObserver<List<Fact>>()

        val path = "/jokes/search?query=query"

        // Mock a response with status 200 and sample JSON output
        val mockResponse = MockResponse()
            .setResponseCode(500)

        // Enqueue request
        mockServer.enqueue(mockResponse)

        // Call the API
        repository.getFacts("query").subscribe(testObserver)
        testObserver.awaitTerminalEvent(1, TimeUnit.SECONDS)

        // No values
        testObserver.assertNoValues()
        // One error recorded
        assertEquals(1, testObserver.errorCount())

//        Get the request that was just made
        val request = mockServer.takeRequest()
        // Make sure we made the request to the required path
        assertEquals(path, request.path)

    }

    @After
    @Throws
    fun tearDown() {
        mockServer.shutdown()
    }
}
