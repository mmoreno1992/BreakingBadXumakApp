package dev.mmoreno.brbad.xumak.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import dev.mmoreno.brbad.xumak.db.BreakingBadDatabase
import dev.mmoreno.brbad.xumak.networking.BreakingBadApi
import dev.mmoreno.brbad.xumak.networking.model.BreakingBadCharacterResponse
import dev.mmoreno.brbad.xumak.util.INITIAL_LOAD_SIZE
import dev.mmoreno.brbad.xumak.util.NETWORK_PAGE_SIZE
import dev.mmoreno.brbad.xumak.util.toBreakingBadCharacterEntities
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class BreakingBadCharacterPagingSource : PagingSource<Int, BreakingBadCharacterResponse>(),
  KoinComponent {

  private val api: BreakingBadApi by inject()
  private val database: BreakingBadDatabase by inject()

  override fun getRefreshKey(state: PagingState<Int, BreakingBadCharacterResponse>): Int? {
    return null
  }

  /**
   * @params: contains information about the load
   * @return: LoadResult with data or exception
   */
  override suspend fun load(params: LoadParams<Int>): LoadResult<Int, BreakingBadCharacterResponse> {
    // params.key is going to be null the first call (load)
    val position = params.key ?: INITIAL_LOAD_SIZE
    val offset =
      if (params.key != null) ((position - 1) * NETWORK_PAGE_SIZE) + 0 else 0
    return try {
      val characters: List<BreakingBadCharacterResponse> =
        api.getCharacters(offset, params.loadSize)

      val nextKey = if (characters.isEmpty()) {
        null
      } else {
        //by default the first called to this function loads (PAGE_SIZE * 3)
        // To make sure we're not requesting duplicating items after the first request
        database.breakingBadCharacterDao()
          .insertAsList(characters.toBreakingBadCharacterEntities(offset))

        position + (params.loadSize / NETWORK_PAGE_SIZE)

      }
      LoadResult.Page(
        data = characters,
        //Only forward paging is needed
        prevKey = null,
        //Next Key is calculated (if nextKey is null there is no more data to load)
        nextKey = nextKey
      )
    } catch (e: Exception) {
      LoadResult.Error(e)
    }
  }
}