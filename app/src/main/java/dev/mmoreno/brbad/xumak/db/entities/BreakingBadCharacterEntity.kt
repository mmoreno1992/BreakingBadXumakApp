package dev.mmoreno.brbad.xumak.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Breaking_Bad_Character_Table")
class BreakingBadCharacterEntity(
  @PrimaryKey(autoGenerate = false)
  val id: Int,
  val name: String,
  val nickname: String,
  val image: String,
  @ColumnInfo(name = "is_favorite")
  val isFavorite: Boolean = false,
  val offset: Int
)