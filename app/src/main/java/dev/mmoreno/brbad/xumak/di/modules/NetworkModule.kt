package dev.mmoreno.brbad.xumak.di.modules

import dev.mmoreno.brbad.xumak.networking.BreakingBadApi
import dev.mmoreno.brbad.xumak.util.READ_TIME_OUT
import dev.mmoreno.brbad.xumak.util.URL_BREAKING_BAD
import dev.mmoreno.brbad.xumak.util.URL_SERVICE
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {

  single {
    Retrofit.Builder()
      .baseUrl(get<String>(qualifier = named(URL_BREAKING_BAD)))
      .client(get())
      //.addConverterFactory(MoshiConverterFactory.create())
      .addConverterFactory(GsonConverterFactory.create())
      .build()
  }

  single {
    OkHttpClient.Builder()
      .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
      .addInterceptor(HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
      })
      .build()
  }

  single {
    get<Retrofit>().create(BreakingBadApi::class.java)
  }

  single(named(URL_BREAKING_BAD)) { URL_SERVICE }

}