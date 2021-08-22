package dev.mmoreno.brbad.xumak.di.modules

import androidx.paging.ExperimentalPagingApi
import dev.mmoreno.brbad.xumak.paging.BreakingBadRemoteMediator
import dev.mmoreno.brbad.xumak.repositories.BreakingBadRepository
import org.koin.dsl.module

@ExperimentalPagingApi
val repositoryModule = module {

  single {
    BreakingBadRepository(get(), get())
  }

  factory {
    BreakingBadRemoteMediator(get(), get())
  }
}