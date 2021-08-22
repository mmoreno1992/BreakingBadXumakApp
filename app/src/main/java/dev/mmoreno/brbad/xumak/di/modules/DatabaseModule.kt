package dev.mmoreno.brbad.xumak.di.modules

import androidx.room.Room
import dev.mmoreno.brbad.xumak.db.BreakingBadDatabase
import dev.mmoreno.brbad.xumak.util.DATABASE_NAME
import org.koin.dsl.module

val databaseModule = module {
  single {
    Room.databaseBuilder(
      get(),
      BreakingBadDatabase::class.java,
      DATABASE_NAME
    ).build()
  }

  single {
    get<BreakingBadDatabase>().breakingBadCharacterDao()
  }
}