package br.com.hussan.stonechallenge.data

import br.com.hussan.stonechallenge.data.cache.SearchCache
import br.com.hussan.stonechallenge.data.datasource.FactRepository
import br.com.hussan.stonechallenge.domain.Fact
import com.google.gson.Gson
import io.reactivex.observers.TestObserver
import io.reactivex.schedulers.TestScheduler
import junit.framework.TestCase.assertEquals
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
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
    fun `Test search fact term`() {

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
        assertEquals(path, request.path)
    }

    @Test
    fun `Test search fact term with error`() {
        val query = "query"
        val path = "/jokes/search?query=$query"
        val testScheduler = TestScheduler()
        val mockResponse = MockResponse()
            .setResponseCode(500)

        mockServer.enqueue(mockResponse)

        val testObserver = repository.getFacts(query).test().assertEmpty()

        testScheduler.advanceTimeBy(4, TimeUnit.SECONDS)
        testObserver.assertNotTerminated()
        testObserver.assertEmpty()

        testScheduler.advanceTimeBy(8, TimeUnit.SECONDS)
        testObserver.assertNotTerminated()


//        testScheduler.advanceTimeBy(15, TimeUnit.SECONDS)
//        testObserver.assertNotTerminated()

//        testObserver.assertError(Exception())

//        testObserver.awaitTerminalEvent(2, TimeUnit.SECONDS)
//
//        testObserver.assertNoValues()
//        testObserver.assertNotComplete()


//        assertEquals(1, testObserver.errorCount())
        val request = mockServer.takeRequest()
        assertEquals(path, request.path)

    }

    @After
    @Throws
    fun tearDown() {
        mockServer.shutdown()
    }
}
