package dev.mmoreno.brbad.xumak.networking

import dev.mmoreno.brbad.xumak.networking.model.BreakingBadCharacterResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

/**
 * Interface mapping breaking bad api endpoints
 */
interface BreakingBadApi {

  @GET("characters")
  @Headers("Accept: application/json")
  suspend fun getCharacters(
    @Query("offset") offset: Int,
    @Query("limit") limit: Int
  ): List<BreakingBadCharacterResponse>

}