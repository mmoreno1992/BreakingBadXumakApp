package dev.mmoreno.brbad.xumak.paging

import androidx.lifecycle.LiveData
import androidx.paging.*
import dev.mmoreno.brbad.xumak.db.BreakingBadDatabase
import dev.mmoreno.brbad.xumak.db.entities.BreakingBadCharacterEntity

class BreakingBadRepository @ExperimentalPagingApi constructor(
  database: BreakingBadDatabase,
  private val breakingBadRemoteMediator: BreakingBadRemoteMediator
) {

  private val dao = database.breakingBadCharacterDao()

  @ExperimentalPagingApi
  fun getBreakingBadCharactersList(): LiveData<PagingData<BreakingBadCharacterEntity>> {
    return Pager(
      config = PagingConfig(
        pageSize = 10,
        prefetchDistance = 10
      ),
      pagingSourceFactory = {
        dao.pagingSource()
      },
      remoteMediator = breakingBadRemoteMediator
    ).liveData
  }

  suspend fun setBreakingBadCharacterAsFavorite(characterId: Int, isFavorite: Boolean) {
    dao.setBreakingBadCharacterAsFavorite(characterId, isFavorite)
  }
}