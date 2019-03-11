package br.com.hussan.stonechallenge.data

import io.reactivex.Observable
import io.reactivex.functions.Function
import retrofit2.HttpException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeUnit

class RetryWithDelay(private val maxRetries: List<Int>/*, private val retryDelayMillis: Int*/) :
    Function<Observable<out Throwable>, Observable<*>> {
    private var retryCount: Int = -1


    override fun apply(attempts: Observable<out Throwable>): Observable<*> {
        return attempts
            .flatMap {
                when (it) {
                    is SocketException,
                    is SocketTimeoutException,
                    is UnknownHostException -> {
                        retry(it)
                    }
                    is HttpException -> {
                        if (it.code() in 500..598)
                            retry(it)
                        else Observable.error(it)
                    }
                    else -> Observable.error(it)
                }
            }
    }

    private fun retry(throwable: Throwable): Observable<Long>? {
        return if (++retryCount < maxRetries.size) {
            Observable.timer(
                maxRetries[retryCount].toLong(),
                TimeUnit.SECONDS
            )
        } else Observable.error(throwable)
    }
}
