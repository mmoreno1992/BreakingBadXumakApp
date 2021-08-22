package dev.mmoreno.brbad.xumak.db.daos

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.mmoreno.brbad.xumak.db.entities.BreakingBadCharacterEntity

@Dao
abstract class BreakingBadCharacterDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  abstract suspend fun insert(vararg character: BreakingBadCharacterEntity)

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  abstract suspend fun insertAsList(characters: List<BreakingBadCharacterEntity>)

  @Query("SELECT * FROM Breaking_Bad_Character_Table Order By id")
  abstract fun pagingSource(): PagingSource<Int, BreakingBadCharacterEntity>

  @Query("Update Breaking_Bad_Character_Table Set is_favorite = :isFavorite Where id = :characterId")
  abstract suspend fun setBreakingBadCharacterAsFavorite(characterId: Int, isFavorite: Boolean)

  @Query("Select * From Breaking_Bad_Character_Table Order by id desc")
  abstract fun getCharacters(): List<BreakingBadCharacterEntity>

}