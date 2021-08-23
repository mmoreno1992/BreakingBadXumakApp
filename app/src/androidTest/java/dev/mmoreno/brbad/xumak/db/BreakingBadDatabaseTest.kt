package dev.mmoreno.brbad.xumak.db

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import dev.mmoreno.brbad.xumak.db.daos.BreakingBadCharacterDao
import dev.mmoreno.brbad.xumak.db.entities.BreakingBadCharacterEntity
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class BreakingBadDatabaseTest {
  private lateinit var db: BreakingBadDatabase
  private lateinit var dao: BreakingBadCharacterDao

  @Before
  fun setUp() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    db = Room.inMemoryDatabaseBuilder(context, BreakingBadDatabase::class.java).build()
    dao = db.breakingBadCharacterDao()
  }

  @After
  fun tearDown() {
    db.close()
  }

  @Test
  fun breakingBadCharacterDao() = runBlocking {
    val language = BreakingBadCharacterEntity(
      id = 1, name = "Walter W.",
      nickname = "Heisenberg", "www.example.com", isFavorite = false,
      customOffset = 0
    )
    dao.insert(language)
    val languages = dao.getAllCharacters()
    assertTrue(languages.contains(language))
  }
}