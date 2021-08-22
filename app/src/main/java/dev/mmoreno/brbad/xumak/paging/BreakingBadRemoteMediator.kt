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
import org.koin.core.component.inject
import retrofit2.HttpException
import java.io.IOException

@ExperimentalPagingApi
class BreakingBadRemoteMediator(
) : RemoteMediator<Int, BreakingBadCharacterEntity>(), KoinComponent {
  val database: BreakingBadDatabase by inject()
  val service: BreakingBadApi by inject()
  val userDao = database.breakingBadCharacterDao()

  override suspend fun load(
    loadType: LoadType,
    state: PagingState<Int, BreakingBadCharacterEntity>
  ): MediatorResult {
    return try {
      // The network load method takes an optional `after=<user.id>` parameter. For every
      // page after the first, we pass the last user ID to let it continue from where it
      // left off. For REFRESH, pass `null` to load the first page.
      Log.i("BBAD", "CUANTAS PAGINAS VIENEN YA: " + state.pages.size)
      Log.i("BBAD", "load: $loadType")
      val loadKey = when (loadType) {
        LoadType.REFRESH -> null
        // In this example, we never need to prepend, since REFRESH will always load the
        // first page in the list. Immediately return, reporting end of pagination.
        LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
        LoadType.APPEND -> {
          val lastItem = state.lastItemOrNull()

          // We must explicitly check if the last item is `null` when appending,
          // since passing `null` to networkService is only valid for initial load.
          // If lastItem is `null` it means no items were loaded after the initial
          // REFRESH and there are no more items to load.
          Log.i("BBAD", "load: Offset ${lastItem?.offset}")

          lastItem?.offset?.plus(1) ?: 0
        }
      }

      if (loadType == LoadType.APPEND) {
        Log.i("BBAD", "load: Quiere agregar con key $loadKey")

        // Suspending network load via Retrofit. This doesn't need to be wrapped in a
        // withContext(Dispatcher.IO) { ... } block since Retrofit's Coroutine CallAdapter
        // dispatches on a worker thread.
        val response =
          service.getCharacters(offset = loadKey ?: 0, limit = state.config.pageSize)
        // val response = networkService.searchUsers(query = query, after = loadKey)

        database.withTransaction {

          // Insert new users into database, which invalidates the current
          // PagingData, allowing Paging to present the updates in the DB.
          userDao.insertAsList(response.toBreakingBadCharacterEntities(loadKey ?: 0))
        }
      }


      MediatorResult.Success(endOfPaginationReached = false)
    } catch (e: IOException) {
      MediatorResult.Error(e)
    } catch (e: HttpException) {
      MediatorResult.Error(e)
    }
  }

  /* private suspend fun loadKeysForLastPlayer(
     state: PagingState<Int, BreakingBadCharacterEntity>
   ) = state.pages.lastOrNull { it.data.isNotEmpty() }
     ?.data?.lastOrNull()?.let { player ->
       baseballDatabase.playerKeysDao().getPlayerKeysByPlayerId(player.playerId)
     }*/

  /*private suspend fun loadKeysForClosestPlayer(
    state: PagingState<Int, PlayerListItem>
  ) = state.anchorPosition?.let { position ->
    state.closestItemToPosition(position)?.playerId?.let { playerId ->
      baseballDatabase.playerKeysDao().getPlayerKeysByPlayerId(playerId)
    }
  }*/
}