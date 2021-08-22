package dev.mmoreno.brbad.xumak

import android.app.Application
import androidx.paging.ExperimentalPagingApi
import dev.mmoreno.brbad.xumak.di.modules.databaseModule
import dev.mmoreno.brbad.xumak.di.modules.networkModule
import dev.mmoreno.brbad.xumak.di.modules.repositoryModule
import dev.mmoreno.brbad.xumak.di.modules.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class MyApplication : Application() {
  @ExperimentalPagingApi
  override fun onCreate() {
    super.onCreate()

    // Starting Koin, register all the modules defines
    // in the package di.modules
    startKoin {
      androidLogger()
      androidContext(this@MyApplication)
      modules(
        networkModule,
        databaseModule,
        repositoryModule,
        viewModelModule
      )
    }
  }
}