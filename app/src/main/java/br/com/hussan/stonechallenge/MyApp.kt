package br.com.hussan.stonechallenge

import android.app.Application
import br.com.hussan.cache.stonechallenge.di.cacheModule
import br.com.hussan.stonechallenge.data.di.apiModule
import br.com.hussan.stonechallenge.data.di.dataModule
import br.com.hussan.stonechallenge.di.appModule
import br.com.hussan.stonechallenge.usecases.di.useCaseModule
import org.koin.android.ext.android.startKoin

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(appModule, useCaseModule, apiModule, dataModule, cacheModule))

    }
}
