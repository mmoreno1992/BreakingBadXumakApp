package dev.mmoreno.brbad.xumak.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dev.mmoreno.brbad.xumak.db.converters.BooleanToStringConverter
import dev.mmoreno.brbad.xumak.db.daos.BreakingBadCharacterDao
import dev.mmoreno.brbad.xumak.db.entities.BreakingBadCharacterEntity

@Database(entities = [BreakingBadCharacterEntity::class], version = 1, exportSchema = false)
@TypeConverters(BooleanToStringConverter::class)
abstract class BreakingBadDatabase : RoomDatabase() {
  abstract fun breakingBadCharacterDao(): BreakingBadCharacterDao
}