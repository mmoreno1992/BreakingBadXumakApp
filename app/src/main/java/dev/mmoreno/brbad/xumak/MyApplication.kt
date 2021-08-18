package dev.mmoreno.brbad.xumak

import android.app.Application
import dev.mmoreno.brbad.xumak.di.modules.databaseModule
import dev.mmoreno.brbad.xumak.di.modules.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class MyApplication : Application() {
  override fun onCreate() {
    super.onCreate()
    // Start Koin
    startKoin {
      androidLogger()
      androidContext(this@MyApplication)
      modules(networkModule, databaseModule)
    }
  }
}