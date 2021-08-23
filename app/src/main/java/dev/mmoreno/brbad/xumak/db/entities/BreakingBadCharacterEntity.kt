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
  @ColumnInfo(name = "custom_offset")
  val customOffset: Int
){
  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false

    other as BreakingBadCharacterEntity

    if (id != other.id) return false

    return true
  }

  override fun hashCode(): Int {
    return id
  }
}