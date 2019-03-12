package br.com.hussan.stonechallenge

import io.reactivex.observers.DisposableObserver

abstract class CallbackWrapper<T>(private val errorCallback: (error: Throwable) -> Unit) : DisposableObserver<T>() {
    protected abstract fun onSuccess(t: T)

    override fun onNext(t: T) {
        onSuccess(t)
    }

    override fun onError(e: Throwable) {
//        if (e is HttpException) {
//            val responseBody = e.response().errorBody()
//        } else if (e is SocketTimeoutException) {
//        } else if (e is IOException) {
//        } else {
//        }
        errorCallback.invoke(e)
    }

    override fun onComplete() {
    }
}
