package dev.mmoreno.brbad.xumak.paging

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import dev.mmoreno.brbad.xumak.db.BreakingBadDatabase
import dev.mmoreno.brbad.xumak.db.entities.BreakingBadCharacterEntity
import dev.mmoreno.brbad.xumak.networking.BreakingBadApi
import dev.mmoreno.brbad.xumak.util.toBreakingBadCharacterEntities
import org.koin.core.component.KoinComponent
import retrofit2.HttpException
import java.io.IOException

@ExperimentalPagingApi
class BreakingBadRemoteMediator(
  private val database: BreakingBadDatabase,
  private val service: BreakingBadApi
) : RemoteMediator<Int, BreakingBadCharacterEntity>(), KoinComponent {

  private val dao = database.breakingBadCharacterDao()

  override suspend fun load(
    loadType: LoadType,
    state: PagingState<Int, BreakingBadCharacterEntity>
  ): MediatorResult {
    return try {
      var endOfPagination = false
      Log.i("BBAD", "CUANTAS PAGINAS VIENEN YA: " + state.pages.size)
      Log.i("BBAD", "load: $loadType")
      val loadKey = when (loadType) {
        LoadType.REFRESH -> null
        // In this example, we never need to prepend
        // Immediately return, reporting end of pagination.
        LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
        LoadType.APPEND -> {
          val lastItem = state.lastItemOrNull()
          if (lastItem == null)
            0
          else
            getMaxOffset() + 1
        }
      }

      if (loadType == LoadType.APPEND) {
        Log.i("BBAD", "load: Quiere agregar con key $loadKey")
        val response =
          service.getCharacters(offset = loadKey ?: 0, limit = state.config.pageSize)
        database.withTransaction {
          dao.insertAsList(response.toBreakingBadCharacterEntities(loadKey ?: 0))
        }
        endOfPagination = response.isEmpty()
      }
      MediatorResult.Success(endOfPaginationReached = endOfPagination)
    } catch (e: IOException) {
      MediatorResult.Error(e)
    } catch (e: HttpException) {
      MediatorResult.Error(e)
    }
  }

  /**
   * It gets the max offset for avoiding duplicated elements
   * because the order changes when you mark a character as
   * favorite.
   */
  private suspend fun getMaxOffset(): Int {
    return dao.getMaxOffset()
  }

}